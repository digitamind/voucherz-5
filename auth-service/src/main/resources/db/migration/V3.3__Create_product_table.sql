CREATE TABLE Product(
	[id] [bigint] PRIMARY KEY IDENTITY(1,1) NOT NULL,
  [name] [nvarchar](50) NOT NULL,
  [price] [money] NOT NULL,
  [description] [nvarchar] (50),
  [createdAt] [date] NOT NULL,
  [IsDeleted] bit DEFAULT 0
)