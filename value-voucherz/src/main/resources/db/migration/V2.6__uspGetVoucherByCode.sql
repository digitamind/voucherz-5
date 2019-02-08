IF OBJECT_ID('uspGetVoucherByCode') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspGetVoucherByCode] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspGetVoucherByCode]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @code nvarchar (62)
AS
BEGIN
    SELECT * FROM [dbo].[Voucher]
    WHERE merchantId = @merchantId
    AND userId = @userId
    AND code = @code
    AND isDeleted = 0
    RETURN @@ERROR
END