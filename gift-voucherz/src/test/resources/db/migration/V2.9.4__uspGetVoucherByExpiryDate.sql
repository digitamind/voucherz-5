IF OBJECT_ID('uspGetVoucherByExpiryDate') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspGetVoucherByExpiryDate] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspGetVoucherByExpiryDate]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @expiryDate date
AS
BEGIN
  BEGIN TRY
    SELECT * FROM [dbo].[GiftVoucher]
    WHERE merchantId = @merchantId
    AND userId = @userId
    AND @expiryDate = CONVERT(date, expiryDate, 111)
    AND isDeleted = 0
    IF ROWCOUNT_BIG() = 0
          RAISERROR('No Record Found', 16, 1);
  END TRY
  BEGIN CATCH
      EXECUTE [dbo].[uspLogError];
  END CATCH
END