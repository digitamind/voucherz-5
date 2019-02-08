CREATE TABLE Store(
	[id] [int] PRIMARY KEY IDENTITY(1,1) NOT NULL,
	[rowguid] [uniqueidentifier] DEFAULT NEWID() NOT NULL,
	[userId] [bigint] NOT NULL,
	[merchantStoreId] [bigint] NOT NULL,
	[giftVoucherId] [bigint] NOT NULL,
)