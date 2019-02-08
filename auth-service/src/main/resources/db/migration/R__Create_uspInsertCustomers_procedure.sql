IF OBJECT_ID('dbo.uspInsertCustomers') IS NULL
EXEC('CREATE PROCEDURE dbo.uspInsertCustomers AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspInsertCustomers
  (@customers dbo.customer_table_param READONLY)
AS
BEGIN
  BEGIN TRY
    BEGIN TRANSACTION
      INSERT [dbo].[Customer]
      (
        firstName,
        lastName,
        email,
        address,
        city,
        country,
        amountOfOrders,
        numberOfOrders,
        lastOrderAmount,
        lastOrderDate,
        dateJoined,
        kpi,
        merchantId
      )

      SELECT
        c.firstName,
        c.lastName,
        c.email,
        c.address,
        c.city,
        c.country,
        c.amountOfOrders,
        c.numberOfOrders,
        c.lastOrderAmount,
        c.lastOrderDate,
        c.dateJoined,
        c.kpi,
        c.merchantId
      FROM @customers as c
    COMMIT
    END TRY
    BEGIN CATCH
    IF @@TRANCOUNT > 0
      ROLLBACK;

    END CATCH
END
GO