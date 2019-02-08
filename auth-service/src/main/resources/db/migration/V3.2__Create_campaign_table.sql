CREATE TABLE Campaign(
	[id] [bigint] PRIMARY KEY IDENTITY(1,1) NOT NULL,
  [name] [nvarchar](100) NOT NULL,
  [description] [text],
  [activate] BIT NOT NULL,
  startDate DATE,
  expirationDate DATE,
  voucherType int Not null,
  isDeleted BIT default 0,
  merchantUserId BIGINT FOREIGN KEY REFERENCES [MerchantUser](id),
  merchantId BIGINT FOREIGN KEY REFERENCES [Merchant](id)
)