CREATE TABLE Payment(
	[id] [bigint] PRIMARY KEY IDENTITY(1,1) NOT NULL,
	[rowguid] [uniqueidentifier] DEFAULT NEWID() NOT NULL,
	[billerId] [bigint] FOREIGN  KEY REFERENCES Biller(id) NOT NULL,
	[merchantId] [nvarchar] (62) NOT NULL,
	[userId] [nvarchar] (62) NOT NULL,
	[amount] [bigint] NOT NULL,
	[voucherId] [bigint]  FOREIGN  KEY REFERENCES Voucher(id) NOT NULL UNIQUE,
	[status] [bit] DEFAULT 1,
	[dateCreated] [datetime] DEFAULT CURRENT_TIMESTAMP NOT NULL,
)