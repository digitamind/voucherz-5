CREATE TABLE DiscountVoucher(
  [id] [bigint] PRIMARY KEY IDENTITY(1,1),
	[rowguid] [uniqueidentifier] DEFAULT NEWID() NOT NULL,
	[code] [nvarchar](16) NOT NULL  UNIQUE ,
  [type] [smallint] NOT NULL,
  [value] decimal(18, 2) NULL,
  [dateCreated] date DEFAULT GETDATE() NOT NULL,
  [expiryDate] date NULL,
  [isActive] bit DEFAULT 1 NOT NULL,
  [isDeleted] bit DEFAULT 0,
  [campaignId] [nvarchar] (62) DEFAULT 0 NOT NULL,
  [customerId] [nvarchar] (62) DEFAULT 0 NOT NULL,
  [merchantId] [nvarchar] (62) NOT NULL,
  [productId] [nvarchar] (62) DEFAULT 0 NOT NULL,
  [userId] [nvarchar] (62) NOT NULL,
  [category] [nvarchar] (100),
  [additionalInfo][nvarchar] (100)
)