CREATE TYPE dbo.voucher_table_param AS TABLE(
  [code] [nvarchar](16) NOT NULL,
  [type] [smallint] NOT NULL,
  [value] [int] NOT NULL,
  [expiryDate] [datetime] NULL,
  [campaignId] [nvarchar] (62) DEFAULT 0 NOT NULL,
  [customerId] [nvarchar] (62) DEFAULT 0 NOT NULL,
  [merchantId] [nvarchar] (62) NOT NULL,
  [productId] [nvarchar] (62) DEFAULT 0 NOT NULL,
  [userId] [nvarchar] (62) NOT NULL,
  [isActive] [bit] DEFAULT 1,
  [category] [nvarchar] (100),
  [additionalInfo][nvarchar] (100)
)