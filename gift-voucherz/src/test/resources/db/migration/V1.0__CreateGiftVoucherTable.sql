CREATE TABLE GiftVoucher(
  [id] [bigint] PRIMARY KEY IDENTITY(1,1),
	[rowguid] [uniqueidentifier] DEFAULT NEWID() NOT NULL,
	[code] [nvarchar](16) NOT NULL,
	[amount] [bigint] NOT NULL,
	[balance] [bigint] CHECK([balance] >=0) NULL,
	[dateCreated] [datetime] DEFAULT CURRENT_TIMESTAMP NOT NULL,
	[expiryDate] [datetime] NULL,
	[isActive] bit DEFAULT 1 NOT NULL,
	[isCorporate] bit DEFAULT 1 NOT NULL,
	[isDeleted] bit DEFAULT 0 NOT NULL,
	[category] [nvarchar] (100) NULL,
	[campaignId] [nvarchar] (62) DEFAULT 0 NOT NULL,
	[customerId] [nvarchar] (62) DEFAULT 0 NOT NULL,
	[merchantId] [nvarchar] (62) NOT NULL,
	[productId] [nvarchar] (62) DEFAULT 0 NOT NULL,
	[userId] [nvarchar] (62) NOT NULL,
	[additionalInfo] [nvarchar] (160) NULL
)