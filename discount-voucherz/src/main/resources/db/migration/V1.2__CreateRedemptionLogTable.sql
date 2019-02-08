CREATE TABLE RedemptionLog(
	[id] [bigint] PRIMARY KEY IDENTITY(1,1) NOT NULL,
	[rowguid] [uniqueidentifier] DEFAULT NEWID() NOT NULL,
	[voucherId] [bigint] FOREIGN KEY REFERENCES DiscountVoucher(id) NOT NULL UNIQUE,
	[redemptionDate] [datetime] DEFAULT CURRENT_TIMESTAMP,
	[status] [bit] DEFAULT 0
)