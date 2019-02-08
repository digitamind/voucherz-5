IF OBJECT_ID('dbo.uspRedeemVoucher') IS NULL
EXEC('CREATE PROCEDURE dbo.uspRedeemVoucher AS SET NOCOUNT ON;')
GO
ALTER PROCEDURE [dbo].[uspRedeemVoucher]
    @code [nvarchar](16),
	  @amount [bigint] = 0,
    @billerGuid nvarchar(62)
AS
BEGIN
  SET NOCOUNT ON;
	DECLARE @billerId [bigint] = NULL;
	DECLARE @voucherId bigint =NULL;
	DECLARE @redemptionId bigint = NULL;
	DECLARE @paymentId bigint;

  BEGIN TRY
	  BEGIN TRANSACTION
			SELECT
			   @voucherId = [id]
			FROM [dbo].[Voucher]
			WHERE [code] = @code
			AND   [isActive] = 1
			AND   [amount] = @amount

      IF (@voucherId IS NULL)
				RAISERROR ('Operation Failed', 16, 1);

      SELECT @billerId= id FROM [dbo].[Biller]
      WHERE rowguid = @billerGuid

      IF (@billerId IS NULL)
				RAISERROR ('Biller Not Found', 16, 1);

      SELECT @paymentId = id FROM [dbo].[Payment]
      WHERE voucherId = @voucherId
      AND status = 1
      AND billerId = @billerId

      IF (@paymentId IS NULL)
				RAISERROR ('No Payment Found', 16, 1);

      EXECUTE dbo.uspInsertRedemptionLog
          @voucherId, @paymentId, @redemptionId OUTPUT

      IF (@redemptionId IS NULL)
				RAISERROR ('Redemption Failed', 16, 1);
		  SELECT * FROM [dbo].[RedemptionLog]
		  WHERE id = @redemptionId;
		COMMIT
	END TRY
	BEGIN CATCH
	  ROLLBACK
    EXECUTE [dbo].[uspLogError];
	END CATCH
END;