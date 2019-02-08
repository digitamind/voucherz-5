CREATE TABLE [MerchantUser]
(
	  [id] BIGINT NOT NULL PRIMARY KEY IDENTITY(1,1),
	  firstName nvarchar(50) NOT NULL,
    lastName nvarchar(50) NOT NULL,
    email nvarchar(50) NOT NULL,
    merchantId BIGINT FOREIGN KEY REFERENCES Merchant(id),
    [role] varchar(10) NOT NULL,
    active INT NOT NULL DEFAULT 0,
    isLocked BIT NOT NULL DEFAULT 0,
    isExpired BIT NOT NULL DEFAULT 0,
    enabled BIT NOT NULL DEFAULT 0,
    password nvarchar(60) NOT NULL,
    --roleId INT FOREIGN KEY REFERENCES Role(id),
    [IsDeleted] [bit] DEFAULT 0
)



