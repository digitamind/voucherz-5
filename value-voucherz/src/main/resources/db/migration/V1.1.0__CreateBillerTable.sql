CREATE TABLE Biller(
	[id] [bigint] PRIMARY KEY IDENTITY(1,1) NOT NULL,
	[rowguid] [uniqueidentifier] DEFAULT NEWID() NOT NULL,
	[description] [nvarchar] (100) NOT NULL,
	[name] [nvarchar] (62) NOT NULL UNIQUE ,
)