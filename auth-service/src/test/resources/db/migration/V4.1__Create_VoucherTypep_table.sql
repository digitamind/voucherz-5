CREATE TABLE VoucherType(
    [id] INT NOT NULL PRIMARY KEY IDENTITY(1,1),
voucherType nvarchar(20)
)

INSERT INTO dbo.VoucherType(voucherType)
VALUES('GiftVoucher'), ('DiscountVoucher'),('ValueVoucher');