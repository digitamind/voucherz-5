IF OBJECT_ID('dbo.uspCheckMerchantUserByEmail') IS NULL
EXEC('CREATE PROCEDURE dbo.uspCheckMerchantUserByEmail AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspCheckMerchantUserByEmail
@email nvarchar(50)
AS
BEGIN
  SELECT * FROM dbo.[MerchantUser] WHERE email = @email
END
GO