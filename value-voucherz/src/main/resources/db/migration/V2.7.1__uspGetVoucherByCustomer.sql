IF OBJECT_ID('uspGetVoucherByCustomer') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspGetVoucherByCustomer] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspGetVoucherByCustomer]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @customerId nvarchar (62)
AS
BEGIN
    SELECT * FROM [dbo].[Voucher]
    WHERE merchantId = @merchantId
    AND userId = @userId
    AND isDeleted = 0
    AND customerId = @customerId
    RETURN @@ERROR
END