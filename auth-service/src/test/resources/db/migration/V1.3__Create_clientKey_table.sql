CREATE TABLE ClientKey(
    [id] BIGINT NOT NULL PRIMARY KEY IDENTITY(1,1),
    merchantId BIGINT FOREIGN KEY REFERENCES Merchant(id),
    clientKey nvarchar(100) not null,
    clientId nvarchar(100) not  null
)