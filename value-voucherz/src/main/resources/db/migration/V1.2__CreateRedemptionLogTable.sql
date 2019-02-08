CREATE TABLE RedemptionLog(
	[id] [bigint] PRIMARY KEY IDENTITY(1,1) NOT NULL,
	[rowguid] [uniqueidentifier] DEFAULT NEWID() NOT NULL,
	[voucherId] [bigint] FOREIGN KEY REFERENCES Voucher(id) NOT NULL,
	[paymentId] [bigint] FOREIGN KEY REFERENCES Payment(id) NOT NULL,
	[redemptionDate] [datetime] DEFAULT CURRENT_TIMESTAMP,
)