IF OBJECT_ID('dbo.uspInsertMerchantUserCredential') IS NULL
EXEC('CREATE PROCEDURE dbo.uspInsertMerchantUserCredential AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspInsertMerchantUserCredential
    @password nvarchar(60),
    @username nvarchar(50),
    @merchantUserId BIGINT,
@id BIGINT = NULL OUTPUT

AS
BEGIN

    INSERT INTO LoginCredential(password, username, merchantUserId)
        VALUES(@password, @username, @merchantUserId)
SELECT @id = SCOPE_IDENTITY()
END
GO