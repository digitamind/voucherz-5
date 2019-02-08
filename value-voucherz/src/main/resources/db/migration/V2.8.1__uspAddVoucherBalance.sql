IF OBJECT_ID('uspAddVoucherBalance') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspAddVoucherBalance] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspAddVoucherBalance]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @code nvarchar (62),
  @addAmount bigint
AS
BEGIN
  BEGIN TRY
    BEGIN TRANSACTION
      UPDATE [dbo].[Voucher]
      SET amount = (amount + @addAmount)
      WHERE userId = @userId
      AND merchantId = @merchantId
      AND code = @code
      AND isDeleted = 0
      AND (expiryDate >= getdate() OR expiryDate IS NULL)

      IF @@ROWCOUNT = 1
        RETURN;
      ELSE
        RAISERROR('Voucher Balance Updates Failed.', 16, 1);
    COMMIT
  END TRY
  BEGIN CATCH
    IF @@TRANCOUNT > 0
    ROLLBACK;
    EXECUTE [dbo].[uspLogError];
  END CATCH
END