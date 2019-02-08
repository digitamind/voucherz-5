IF OBJECT_ID('uspDeleteVoucher') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspDeleteVoucher] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspDeleteVoucher]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @code nvarchar (62)
AS
BEGIN
  BEGIN TRY
    BEGIN TRANSACTION
      DECLARE @voucherId bigint;

      SELECT @voucherId = [id] FROM [dbo].[Voucher]
      WHERE merchantId = @merchantId
      AND userId = @userId
      AND code = @code

      IF @voucherId IS NULL
	    RAISERROR ('Voucher Not Found', 16, 1);

      UPDATE [dbo].[Voucher]
      SET [isDeleted] = 1,
          [isActive] = 0
      WHERE [id] = @voucherId

    COMMIT
  END TRY
  BEGIN CATCH
    IF @@TRANCOUNT > 0
    ROLLBACK;
    EXECUTE [dbo].[uspLogError];
  END CATCH
END