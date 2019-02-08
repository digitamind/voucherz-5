IF OBJECT_ID('dbo.uspChangeMerchantUserPassword') IS NULL
EXEC('CREATE PROCEDURE dbo.uspChangeMerchantUserPassword AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspChangeMerchantUserPassword
@email nvarchar(50),
@password nvarchar(60)
AS
BEGIN

  UPDATE dbo.[MerchantUser] SET password = @password WHERE email = @email

END
GO