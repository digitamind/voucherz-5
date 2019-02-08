IF OBJECT_ID('dbo.uspUpdateRedemption') IS NULL
EXEC('CREATE PROCEDURE dbo.uspUpdateRedemption AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspUpdateRedemption
    @voucherId bigint,
    @redemptionId bigint = NULL OUTPUT
AS
BEGIN
  BEGIN TRY
    BEGIN TRANSACTION
      UPDATE [dbo].[RedemptionLog]
      SET status = 1
      WHERE voucherId = @voucherId
      AND id = @redemptionId

      IF @@ROWCOUNT = 1
        RETURN;
      ELSE
        RAISERROR('Redemption Failed.', 16, 1);
    COMMIT
  END TRY
  BEGIN CATCH
    IF @@TRANCOUNT > 0
    ROLLBACK;
    EXECUTE [dbo].[uspLogError];
  END CATCH
END