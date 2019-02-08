IF OBJECT_ID('uspGetVoucherByActiveStatus') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspGetVoucherByActiveStatus] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspGetVoucherByActiveStatus]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @isActive bit
AS
BEGIN
    SELECT * FROM [dbo].[Voucher]
    WHERE merchantId = @merchantId
    AND userId = @userId
    AND isActive = @isActive
    AND isDeleted = 0
    RETURN @@ERROR
END