CREATE TABLE [dbo].[VoucherDeleted](
	[id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[voucherId] [bigint] FOREIGN KEY REFERENCES DiscountVoucher(id) NOT NULL UNIQUE,
	[dateDeleted] [datetime] NOT NULL,
	)