IF OBJECT_ID('uspUnDeleteVoucher') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspUnDeleteVoucher] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspUnDeleteVoucher]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @code nvarchar (62)
AS
BEGIN
  BEGIN TRY
      DECLARE @voucherId bigint;

      SELECT @voucherId = [id] FROM [dbo].[Voucher]
      WHERE merchantId = @merchantId
      AND userId = @userId
      AND code = @code
      AND isDeleted = 1

      IF @voucherId IS NOT NULL
      BEGIN
        BEGIN TRANSACTION
          DELETE FROM [dbo].[VoucherDeleted]
          WHERE [voucherId] = @voucherId

          UPDATE [dbo].[Voucher]
          SET [isDeleted] = 0,
              [isActive] = 0
          WHERE [id] = @voucherId
        COMMIT
      END
      ELSE
      BEGIN
        RAISERROR('No record found', 16, 1);
      END
  END TRY
  BEGIN CATCH
    IF @@TRANCOUNT > 0
    ROLLBACK;
    EXECUTE [dbo].[uspLogError];
  END CATCH
END