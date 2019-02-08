CREATE TABLE Segment(
	[id] [bigint] PRIMARY KEY IDENTITY(1,1) NOT NULL,
  [name] [nvarchar](50) NOT NULL,
  [description] [nvarchar] (100),
  merchantId BIGINT FOREIGN KEY REFERENCES [Merchant](id)
)