CREATE TABLE [dbo].[VoucherDeleted](
	[id] [int] IDENTITY(1,1) PRIMARY KEY NOT NULL,
	[voucherId] [bigint] FOREIGN KEY REFERENCES Voucher(id) NOT NULL,
	[dateDeleted] [datetime] NOT NULL,
	)