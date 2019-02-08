IF OBJECT_ID('dbo.uspInsertRedemptionLog') IS NULL
EXEC('CREATE PROCEDURE dbo.uspInsertRedemptionLog AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspInsertRedemptionLog
    @voucherId bigint,
    @paymentId bigint,
    @redemptionId bigint = NULL OUTPUT
AS
BEGIN
      BEGIN TRY
        INSERT [dbo].[RedemptionLog]
				(voucherId, paymentId)
			  VALUES
				(@voucherId, @paymentId);

        SELECT @redemptionId = SCOPE_IDENTITY()
      END TRY
      BEGIN CATCH
          EXECUTE [dbo].[uspLogError];
      END CATCH
    END
GO