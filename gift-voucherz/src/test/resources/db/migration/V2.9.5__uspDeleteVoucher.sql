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
      DECLARE @giftVoucherId bigint;

      SELECT @giftVoucherId = [id] FROM [dbo].[GiftVoucher]
      WHERE merchantId = @merchantId
      AND userId = @userId
      AND code = @code

      IF @giftVoucherId IS NULL
	    RAISERROR ('Gift Voucher Not Found', 16, 1);

      UPDATE [dbo].[GiftVoucher]
      SET [isDeleted] = 1,
          [isActive] = 0
      WHERE [id] = @giftVoucherId

    COMMIT
  END TRY
  BEGIN CATCH
    IF @@TRANCOUNT > 0
    ROLLBACK;
    EXECUTE [dbo].[uspLogError];
  END CATCH
END