IF OBJECT_ID('uspGetVoucherByDateCreated') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspGetVoucherByDateCreated] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspGetVoucherByDateCreated]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @dateCreated date
AS
BEGIN
    SELECT * FROM [dbo].[Voucher]
    WHERE merchantId = @merchantId
    AND userId = @userId
    AND @dateCreated = CONVERT(date, dateCreated, 111)
    AND isDeleted = 0
    RETURN @@ERROR
END