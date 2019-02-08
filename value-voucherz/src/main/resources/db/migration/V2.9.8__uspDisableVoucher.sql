IF OBJECT_ID('uspDisableVoucher') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspDisableVoucher] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspDisableVoucher]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @code nvarchar (62)
AS
BEGIN
  BEGIN TRY
      UPDATE [dbo].[Voucher]
      SET isActive = 0
      WHERE userId = @userId
      AND merchantId = @merchantId
      AND code = @code

      IF @@ROWCOUNT = 1
        RETURN;
      ELSE
        RAISERROR('Voucher Cannot be Disabled.', 16, 1);
  END TRY
  BEGIN CATCH
    IF @@TRANCOUNT > 0
    ROLLBACK;
    EXECUTE [dbo].[uspLogError];
  END CATCH
END