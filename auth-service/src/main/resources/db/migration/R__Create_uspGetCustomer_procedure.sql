IF OBJECT_ID('dbo.uspGetCustomer') IS NULL
EXEC('CREATE PROCEDURE dbo.uspGetCustomer AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspGetCustomer
@rowguid uniqueidentifier,
@merchantId BIGINT
AS
BEGIN
SELECT * FROM dbo.[Customer] where rowguid = @rowguid
AND merchantId = @merchantId
AND isDeleted = 0
END
GO


IF OBJECT_ID('dbo.uspGetAllCustomers') IS NULL
EXEC('CREATE PROCEDURE dbo.uspGetAllCustomers AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspGetAllCustomers
@merchantId BIGINT
AS
BEGIN
SELECT * FROM dbo.[Customer] where merchantId = @merchantId AND isDeleted = 0
END
GO


