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
      DECLARE @giftVoucherId bigint;

      SELECT @giftVoucherId = [id] FROM [dbo].[GiftVoucher]
      WHERE merchantId = @merchantId
      AND userId = @userId
      AND code = @code
      AND isDeleted = 1
      IF @giftVoucherId IS NOT NULL
      BEGIN
        BEGIN TRANSACTION
          DELETE FROM [dbo].[GiftVoucherDeleted]
          WHERE [giftVoucherId] = @giftVoucherId

          UPDATE [dbo].[GiftVoucher]
          SET [isDeleted] = 0,
              [isActive] = 0
          WHERE [id] = @giftVoucherId
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