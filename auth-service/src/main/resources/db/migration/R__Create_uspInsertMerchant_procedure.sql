IF OBJECT_ID('dbo.uspInsertMerchant') IS NULL
EXEC('CREATE PROCEDURE dbo.uspInsertMerchant AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspInsertMerchant
    @companyName nvarchar(100),
    @companySize int,
    @isCorporate bit,
    @merchantId BIGINT OUTPUT
AS

   BEGIN
        INSERT INTO Merchant(companyName, dateCreated, companySize, isCorporate)
            VALUES(@companyName, GETDATE(), @companySize, @isCorporate)

        SELECT @merchantId = SCOPE_IDENTITY()

  RETURN
  END
GO