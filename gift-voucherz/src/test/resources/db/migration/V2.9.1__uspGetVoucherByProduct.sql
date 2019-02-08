IF OBJECT_ID('uspGetVoucherByProduct') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspGetVoucherByProduct] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspGetVoucherByProduct]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @productId nvarchar (62)
AS
BEGIN
  BEGIN TRY
    SELECT * FROM [dbo].[GiftVoucher]
    WHERE merchantId = @merchantId
    AND userId = @userId
    AND productId = @productId
    AND isDeleted = 0
    IF ROWCOUNT_BIG() = 0
          RAISERROR('No Record Found', 16, 1);
  END TRY
  BEGIN CATCH
      EXECUTE [dbo].[uspLogError];
  END CATCH
END