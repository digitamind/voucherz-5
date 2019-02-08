IF OBJECT_ID('dbo.uspInsertRedemptionLog') IS NULL
EXEC('CREATE PROCEDURE dbo.uspInsertRedemptionLog AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspInsertRedemptionLog
    @giftVoucherId bigint,
    @redeemAmount bigint,
    @merchantStoreId nvarchar(62),
    @redemptionDate datetime,
    @redemptionLogId bigint OUTPUT
AS
   BEGIN
      BEGIN TRY
        INSERT [dbo].[RedemptionLog]
				(giftVoucherId, amountRedeemed, merchantStoreId, redemptionDate)
			  VALUES
				(@giftVoucherId, @redeemAmount, @merchantStoreId, @redemptionDate);

        SELECT @redemptionLogId = SCOPE_IDENTITY()

      END TRY
      BEGIN CATCH
          EXECUTE [dbo].[uspLogError];
      END CATCH
    END
GO