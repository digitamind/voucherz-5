IF OBJECT_ID('uspGetVoucherByCampaign') IS NULL
  EXEC('CREATE PROCEDURE [dbo].[uspGetVoucherByCampaign] AS SET NOCOUNT ON')
GO
ALTER PROCEDURE [dbo].[uspGetVoucherByCampaign]
  @merchantId nvarchar (62),
  @userId nvarchar (62),
  @campaignId nvarchar (62)
AS
BEGIN
    SELECT * FROM [dbo].[Voucher]
    WHERE merchantId = @merchantId
    AND userId = @userId
    AND campaignId = @campaignId
    AND isDeleted = 0
    RETURN @@ERROR
END