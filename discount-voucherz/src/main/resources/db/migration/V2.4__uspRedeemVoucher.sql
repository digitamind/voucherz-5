IF OBJECT_ID('dbo.uspRedeemVoucher') IS NULL
EXEC('CREATE PROCEDURE dbo.uspRedeemVoucher AS SET NOCOUNT ON;')
GO
ALTER PROCEDURE [dbo].[uspRedeemVoucher]
    @code [nvarchar](16),
	  @merchantId [nvarchar](62)
AS
BEGIN
  BEGIN TRY
    DECLARE @voucherId bigint =NULL;
    DECLARE @redemptionId bigint = NULL;
			SELECT *
			FROM [dbo].[DiscountVoucher]
			WHERE [code] = @code
			AND   [isActive] = 1
			AND   [merchantId] = @merchantId

      IF (ROWCOUNT_BIG() = 0)
				RAISERROR ('DiscountVoucher Not Found', 16, 1);
  END TRY
	BEGIN CATCH
    EXECUTE [dbo].[uspLogError];
	END CATCH
END;