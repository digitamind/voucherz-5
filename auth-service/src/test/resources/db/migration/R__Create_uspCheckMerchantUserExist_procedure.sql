IF OBJECT_ID('dbo.uspCheckMerchantUserExist') IS NULL
EXEC('CREATE PROCEDURE dbo.uspCheckMerchantUserExist AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspCheckMerchantUserExist
  @username nvarchar(50),
  @password nvarchar(60)

AS
BEGIN
  DECLARE @crypted_passwd VARCHAR(60)


  SELECT @crypted_passwd = password FROM dbo.[LoginCredential] WHERE username = @username

  IF(@crypted_passwd = @password)
  BEGIN
    SELECT * FROM dbo.[MerchantUser] WHERE email = @username
  END


END
GO