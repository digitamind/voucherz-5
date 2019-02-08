CREATE TABLE Merchant
(
	[id] BIGINT NOT NULL PRIMARY KEY IDENTITY(1,1),
	companyName nvarchar(100) NOT NULL,
	dateCreated Date NOT NULL,
	companySize int NULL,
	isCorporate bit DEFAULT 1,
	[IsDeleted] bit DEFAULT 0
)

