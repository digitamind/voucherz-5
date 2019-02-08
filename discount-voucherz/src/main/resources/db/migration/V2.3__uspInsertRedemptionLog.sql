IF OBJECT_ID('dbo.uspInsertRedemptionLog') IS NULL
EXEC('CREATE PROCEDURE dbo.uspInsertRedemptionLog AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspInsertRedemptionLog
    @voucherId bigint,
    @redemptionId bigint = NULL OUTPUT
AS
BEGIN
      BEGIN TRY
        INSERT [dbo].[RedemptionLog]
				(voucherId)
			  VALUES
				(@voucherId);

        SELECT @redemptionId = SCOPE_IDENTITY()
      END TRY
      BEGIN CATCH
          EXECUTE [dbo].[uspLogError];
      END CATCH
    END
GO