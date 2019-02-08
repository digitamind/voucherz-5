IF OBJECT_ID('dbo.uspInsertCustomer') IS NULL
EXEC('CREATE PROCEDURE dbo.uspInsertCustomer AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspInsertCustomer
  @firstName NVARCHAR(100),
  @lastName NVARCHAR(100),
  @email NVARCHAR(100),
  @address NVARCHAR(100),
  @city NVARCHAR(50),
  @country NVARCHAR(50),
  @amountOfOrders BIGINT,
  @numberOfOrders BIGINT,
  @lastOrderAmount BIGINT,
  @lastOrderDate DATE,
  @dateJoined DATE,
  @kpi INT,
  @merchantId BIGINT,
  @id BIGINT = NULL OUTPUT

AS
BEGIN

  INSERT INTO [Customer](firstName, lastName, email, address, city, country, amountOfOrders,
    numberOfOrders, lastOrderAmount,
          lastOrderDate, dateJoined, kpi, merchantId)
    VALUES (@firstName, @lastName, @email, @address, @city, @country, @amountOfOrders, @numberOfOrders,
    @lastOrderAmount, @lastOrderDate, @dateJoined, @kpi, @merchantId)
    SELECT @id = SCOPE_IDENTITY()
END
GO