IF OBJECT_ID('uspGetVoucherByCustomer') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspGetVoucherByCustomer] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspGetVoucherByCustomer]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @customerId nvarchar (62)
AS
BEGIN
  BEGIN TRY
    SELECT * FROM [dbo].[GiftVoucher]
    WHERE merchantId = @merchantId
    AND userId = @userId
    AND customerId = @customerId
    AND isDeleted = 0
    IF ROWCOUNT_BIG() = 0
          RAISERROR('No Record Found', 16, 1);
  END TRY
  BEGIN CATCH
      EXECUTE [dbo].[uspLogError];
  END CATCH
END