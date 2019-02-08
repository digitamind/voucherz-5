IF OBJECT_ID('uspGetVoucherByDateCreated') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspGetVoucherByDateCreated] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspGetVoucherByDateCreated]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @dateCreated date
AS
BEGIN
  BEGIN TRY
    SELECT * FROM [dbo].[GiftVoucher]
    WHERE merchantId = @merchantId
    AND userId = @userId
    AND @dateCreated = CONVERT(date, dateCreated, 111)
    AND isDeleted = 0
    IF ROWCOUNT_BIG() = 0
          RAISERROR('No Record Found', 16, 1);
  END TRY
  BEGIN CATCH
      EXECUTE [dbo].[uspLogError];
  END CATCH
END