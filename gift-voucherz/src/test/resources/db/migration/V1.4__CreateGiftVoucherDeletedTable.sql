CREATE TABLE [dbo].[GiftVoucherDeleted](
	[id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[giftVoucherId] [bigint] FOREIGN KEY REFERENCES GiftVoucher(id) NOT NULL,
	[dateDeleted] [datetime] NOT NULL,
	)