IF OBJECT_ID('uspEnableVoucher') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspEnableVoucher] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspEnableVoucher]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @code nvarchar (62)
AS
BEGIN
  BEGIN TRY
      UPDATE [dbo].[Voucher]
      SET isActive = 1
      WHERE userId = @userId
      AND merchantId = @merchantId
      AND code = @code
      AND isDeleted = 0

      IF @@ROWCOUNT = 1
        RETURN;
      ELSE
        RAISERROR('Operation Failed', 16, 1);
  END TRY
  BEGIN CATCH
    IF @@TRANCOUNT > 0
    ROLLBACK;
    EXECUTE [dbo].[uspLogError];
  END CATCH
END