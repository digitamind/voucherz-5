IF OBJECT_ID ('utrDeleteVoucher','TR') IS NOT NULL
   DROP TRIGGER utrDeleteVoucher;
GO

CREATE TRIGGER utrDeleteVoucher
ON [dbo].[GiftVoucher]
FOR UPDATE
AS
BEGIN
  IF UPDATE(isDeleted)
  BEGIN
    BEGIN TRY
      DECLARE @dateDeleted datetime;
      DECLARE @voucherId bigint;
      DECLARE @isDeleted bit
      SET @dateDeleted = getdate();
      SELECT @voucherId = id, @isDeleted = isDeleted FROM  INSERTED
      IF (@isDeleted = 1)
      BEGIN
          BEGIN TRANSACTION
          INSERT INTO [dbo].[GiftVoucherDeleted] (giftVoucherId, dateDeleted)
          VALUES (@voucherId, @dateDeleted)
          COMMIT
      END
      ELSE
      BEGIN
        IF EXISTS(SELECT giftVoucherId  FROM [dbo].[GiftVoucherDeleted]
        WHERE giftVoucherId = @voucherId)
          RAISERROR('Cannot Undelete The Specified Record', 16, 1);
      END
    END TRY
    BEGIN CATCH
      IF @@TRANCOUNT > 0
      ROLLBACK;
      EXECUTE [dbo].[uspLogError];
    END CATCH
  END
END
