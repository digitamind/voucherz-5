IF OBJECT_ID('dbo.uspChangeMerchantUserStatus') IS NULL
EXEC('CREATE PROCEDURE dbo.uspChangeMerchantUserStatus AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspChangeMerchantUserStatus
@email nvarchar(50),
@enabled INT
AS
BEGIN
    UPDATE dbo.[MerchantUser] SET enabled=@enabled WHERE email=@email
END
GO