IF OBJECT_ID('uspGetVoucherByMerchantUser') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspGetVoucherByMerchantUser] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspGetVoucherByMerchantUser]
  @merchantId nvarchar (62),
  @userId nvarchar (62)
AS
BEGIN
    SELECT * FROM [dbo].[Voucher]
    WHERE merchantId = @merchantId AND userId = @userId
    AND isDeleted = 0
    RETURN @@ERROR
END