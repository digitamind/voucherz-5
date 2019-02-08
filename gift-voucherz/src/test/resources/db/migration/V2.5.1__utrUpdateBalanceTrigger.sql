IF OBJECT_ID ('utrUpdateBalance','TR') IS NOT NULL
   DROP TRIGGER utrUpdateBalance;
GO

CREATE TRIGGER utrUpdateBalance
ON [dbo].[RedemptionLog]
FOR UPDATE, INSERT
AS
BEGIN
	IF (ROWCOUNT_BIG() = 0)
		RETURN;
	DECLARE @totalAmount bigint;
	DECLARE @voucherId bigint;


	SELECT @voucherId = giftVoucherId FROM  INSERTED

	SELECT @totalAmount = SUM(amountRedeemed)
	FROM RedemptionLog
	WHERE giftVoucherId = @voucherId

	UPDATE dbo.GiftVoucher
	SET balance = [amount] - @totalAmount
	WHERE id = @voucherId
END