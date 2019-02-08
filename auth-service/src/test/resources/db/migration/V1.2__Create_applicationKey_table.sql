CREATE TABLE ApplicationKey(
    [id] BIGINT NOT NULL PRIMARY KEY IDENTITY(1,1),
    merchantId BIGINT FOREIGN KEY REFERENCES Merchant(id),
    appKey nvarchar(100) not null,
    appId nvarchar(100) not  null
)