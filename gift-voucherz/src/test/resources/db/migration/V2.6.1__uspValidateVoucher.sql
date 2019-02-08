IF OBJECT_ID('uspValidateVoucher') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspValidateVoucher] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspValidateVoucher]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @code nvarchar (62),
  @amount bigint
AS
BEGIN
    BEGIN TRY
      SELECT * FROM [dbo].[GiftVoucher]
      WHERE merchantId = @merchantId
      AND userId = @userId
      AND code = @code
      AND isActive = 1
      AND amount >= @amount
      IF ROWCOUNT_BIG() = 0
        RAISERROR('Request Is Invalid', 16, 1);
      RETURN @@ERROR
    END TRY
    BEGIN CATCH
        EXECUTE [dbo].[uspLogError];
    END CATCH
END