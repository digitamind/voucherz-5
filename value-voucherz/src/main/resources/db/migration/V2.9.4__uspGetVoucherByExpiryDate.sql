IF OBJECT_ID('uspGetVoucherByExpiryDate') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspGetVoucherByExpiryDate] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspGetVoucherByExpiryDate]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @expiryDate date
AS
BEGIN
    SELECT * FROM [dbo].[Voucher]
    WHERE merchantId = @merchantId
    AND userId = @userId
    AND @expiryDate = CONVERT(date, expiryDate, 111)
    AND isDeleted = 0
    RETURN @@ERROR
END