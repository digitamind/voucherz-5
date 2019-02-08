IF OBJECT_ID('dbo.uspDeleteCustomer') IS NULL
EXEC('CREATE PROCEDURE dbo.uspDeleteCustomer AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspDeleteCustomer
@merchantId BIGINT,
@rowguid uniqueidentifier
AS
BEGIN
UPDATE dbo.[Customer] SET isDeleted = 1 WHERE merchantId = @merchantId and rowguid = @rowguid
END
GO
