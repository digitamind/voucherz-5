CREATE TABLE RedemptionLog(
	[id] [bigint] PRIMARY KEY IDENTITY(1,1) NOT NULL,
	[rowguid] [uniqueidentifier] DEFAULT NEWID() NOT NULL,
	[giftVoucherId] [bigint] FOREIGN KEY REFERENCES GiftVoucher(id) NOT NULL,
	[amountRedeemed] [bigint] NOT NULL,
	[merchantStoreId] [nvarchar] (62) NOT NULL,
	[redemptionDate] [datetime] DEFAULT CURRENT_TIMESTAMP,
)