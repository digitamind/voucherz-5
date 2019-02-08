CREATE TYPE dbo.voucher_table_param AS TABLE(
	  code [nvarchar](16),
    amount [bigint],
	  dateCreated [datetime] DEFAULT GETDATE(),
    expiryDate [datetime],
    isActive bit DEFAULT 1,
    isCorporate bit DEFAULT 1,
	  category [nvarchar] (160),
    campaignId [nvarchar] (62) DEFAULT 0 NULL,
    customerId [nvarchar] (62) DEFAULT 0 NULL,
    merchantId [nvarchar] (62),
    userId [nvarchar] (62) DEFAULT 0,
    merchantStoreId [bigint] NULL,
	  additionalInfo [nvarchar] (62) NULL
)