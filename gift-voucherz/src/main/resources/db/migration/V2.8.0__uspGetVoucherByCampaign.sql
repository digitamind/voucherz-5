IF OBJECT_ID('uspGetVoucherByCampaign') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspGetVoucherByCampaign] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspGetVoucherByCampaign]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @campaignId nvarchar (62)
AS
BEGIN
  BEGIN TRY
    SELECT * FROM [dbo].[GiftVoucher]
    WHERE merchantId = @merchantId
    AND userId = @userId
    AND campaignId = @campaignId
    AND isDeleted = 0
    IF ROWCOUNT_BIG() = 0
          RAISERROR('No Record Found', 16, 1);
  END TRY
  BEGIN CATCH
      EXECUTE [dbo].[uspLogError];
  END CATCH
END