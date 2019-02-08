IF OBJECT_ID('uspGetVoucherByActiveStatus') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspGetVoucherByActiveStatus] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspGetVoucherByActiveStatus]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @isActive bit
AS
BEGIN
BEGIN TRY
    SELECT * FROM [dbo].[GiftVoucher]
    WHERE merchantId = @merchantId
    AND userId = @userId
    AND isActive = @isActive
    AND isDeleted = 0
    IF ROWCOUNT_BIG() = 0
          RAISERROR('No Record Found', 16, 1);
  END TRY
  BEGIN CATCH
      EXECUTE [dbo].[uspLogError];
  END CATCH
END