IF OBJECT_ID('uspUpdateVoucher') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspUpdateVoucher] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspUpdateVoucher]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @code nvarchar (62),
  @amount bigint
AS
BEGIN
  BEGIN TRY
    BEGIN TRANSACTION
      UPDATE [dbo].[GiftVoucher]
      SET [amount] = @amount
      WHERE userId = @userId
      AND merchantId = @merchantId
      AND code = @code
      AND isDeleted = 0
      AND (expiryDate >= getdate() OR expiryDate IS NULL)

      IF @@ROWCOUNT = 1
        RETURN;
      ELSE
        RAISERROR('DiscountVoucher Updates Failed.', 16, 1);
    COMMIT
  END TRY
  BEGIN CATCH
    IF @@TRANCOUNT > 0
    ROLLBACK;
    EXECUTE [dbo].[uspLogError];
  END CATCH
END