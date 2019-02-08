IF OBJECT_ID('uspValidateVoucher') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspValidateVoucher] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspValidateVoucher]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @code nvarchar (62)
AS
BEGIN
    BEGIN TRY
      SELECT * FROM [dbo].[DiscountVoucher]
      WHERE merchantId = @merchantId
      AND userId = @userId
      AND code = @code
      AND isActive = 1

      IF ROWCOUNT_BIG() = 0
        RAISERROR('DiscountVoucher Is Invalid', 16, 1);
    END TRY
    BEGIN CATCH
          EXECUTE [dbo].[uspLogError];
    END CATCH
END