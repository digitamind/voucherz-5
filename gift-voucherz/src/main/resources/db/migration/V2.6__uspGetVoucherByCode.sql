IF OBJECT_ID('uspGetVoucherByCode') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspGetVoucherByCode] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspGetVoucherByCode]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @code nvarchar (62)
AS
BEGIN
    BEGIN TRY
      SELECT * FROM [dbo].[GiftVoucher]
      WHERE merchantId = @merchantId
      AND userId = @userId
      AND code = @code
      AND isDeleted = 0

      IF ROWCOUNT_BIG() = 0
          RAISERROR('No Record Found', 16, 1);
    END TRY
    BEGIN CATCH
        EXECUTE [dbo].[uspLogError];
    END CATCH

END