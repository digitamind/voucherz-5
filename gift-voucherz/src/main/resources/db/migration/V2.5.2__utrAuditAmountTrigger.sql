IF OBJECT_ID ('utrAuditAmount','TR') IS NOT NULL
   DROP TRIGGER utrAuditAmount;
GO

CREATE TRIGGER utrAuditAmount
ON [dbo].[GiftVoucher]
FOR UPDATE
AS
BEGIN
	IF (ROWCOUNT_BIG() = 0)
		RETURN;
	IF UPDATE(amount)
	DECLARE @totalRedeemedAmount bigint;
	DECLARE @amount bigint;
	DECLARE @voucherId bigint;
	DECLARE @oldAmount bigint


	SELECT @voucherId = id, @amount = amount FROM INSERTED
	SELECT @oldAmount = amount FROM DELETED

	SELECT @totalRedeemedAmount = SUM(amountRedeemed)
	FROM RedemptionLog
	WHERE giftVoucherId = @voucherId

  IF @totalRedeemedAmount IS NOT NULL
  BEGIN
    UPDATE dbo.GiftVoucher
    SET amount = @oldAmount+ @amount, balance = @oldAmount+ @amount - @totalRedeemedAmount
    WHERE id = @voucherId
  END
  ELSE
  BEGIN
    UPDATE dbo.GiftVoucher
    SET balance = @oldAmount+ @amount, amount = @oldAmount+ @amount
    WHERE id = @voucherId
  END
END