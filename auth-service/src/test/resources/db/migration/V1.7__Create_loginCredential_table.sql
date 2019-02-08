CREATE TABLE LoginCredential(
    [id] BIGINT NOT NULL PRIMARY KEY IDENTITY(1,1),
    username nvarchar(50),
    password nvarchar(60),
    merchantUserId BIGINT FOREIGN KEY REFERENCES [MerchantUser](id)
)