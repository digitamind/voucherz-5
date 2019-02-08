IF OBJECT_ID ('utrUpdatePayment','TR') IS NOT NULL
   DROP TRIGGER utrUpdatePayment;
GO

CREATE TRIGGER utrUpdatePayment
ON [dbo].[RedemptionLog]
FOR INSERT
AS
BEGIN
    BEGIN TRY
      BEGIN TRANSACTION
      DECLARE @paymentId bigint;
      DECLARE @voucherId bigint;

      SELECT @paymentId = paymentId, @voucherId = voucherId FROM  INSERTED

      UPDATE Payment
      SET status = 0
      WHERE id = @paymentId
      AND voucherId = @voucherId

      UPDATE Voucher
      SET isActive = 0
      WHERE id = @voucherId

      COMMIT
    END TRY
    BEGIN CATCH
      IF @@TRANCOUNT > 0
      ROLLBACK;
      EXECUTE [dbo].[uspLogError];
    END CATCH
END
