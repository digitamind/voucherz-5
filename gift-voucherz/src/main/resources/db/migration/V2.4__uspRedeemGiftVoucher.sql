IF OBJECT_ID('dbo.uspRedeemGiftVoucher') IS NULL
EXEC('CREATE PROCEDURE dbo.uspRedeemGiftVoucher AS SET NOCOUNT ON;')
GO
ALTER PROCEDURE [dbo].[uspRedeemGiftVoucher]
    @giftVoucherId INT OUTPUT,
    @code [nvarchar](16),
	  @balance [bigint] = 0 OUTPUT,
    @amount [bigint],
    @campaignId [nvarchar] (62) = 0,
    @customerId [nvarchar] (62) = 0,
    @merchantId [nvarchar] (62),
    @productId [nvarchar] (62) = 0,
    @userId [nvarchar] (62),
    @isActive [bit] = 0 OUTPUT,
    @expiryDate [datetime] = NULL OUTPUT,
    @isCorporate [bit] = 1 OUTPUT,
    @redemptionLogId bigint OUTPUT
AS
BEGIN
  SET NOCOUNT ON;
	DECLARE @redemptionDate [datetime], @merchantStoreId [nvarchar] (62);
	SET @redemptionDate = getdate();
	SET @merchantStoreId = @merchantId;

  BEGIN TRY
	  BEGIN TRANSACTION
			SELECT
			   @giftVoucherId = [id]
			  ,@balance = [balance]
			  ,@expiryDate = [expiryDate]
			  ,@isActive = [isActive]
			  ,@isCorporate = [isCorporate]
			FROM [GiftVoucherz].[dbo].[GiftVoucher]
			WHERE [code] = @code
			AND   ([campaignId] = 0 OR [campaignId] = @campaignId)
			AND   ([productId] = 0 OR [productId] = @productId)
			AND   ([expiryDate] IS NULL OR [expiryDate] >= @redemptionDate)
			AND   [isActive] = 1

      IF (ROWCOUNT_BIG() = 0)
				RAISERROR ('No Record Found ', 16, 1);

			IF @isCorporate = 0
			  BEGIN
          SELECT @merchantStoreId = [merchantStoreId]
          FROM Store
          WHERE [giftVoucherId] = @giftVoucherId
			  END

			EXECUTE dbo.uspInsertRedemptionLog
			    @giftVoucherId, @amount, @merchantStoreId, @redemptionDate, @redemptionLogId OUTPUT

		COMMIT
	END TRY
	BEGIN CATCH
	  ROLLBACK
    EXECUTE [dbo].[uspLogError];
	END CATCH
END;