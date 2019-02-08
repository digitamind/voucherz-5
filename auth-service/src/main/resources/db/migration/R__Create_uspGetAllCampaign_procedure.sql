IF OBJECT_ID('dbo.uspGellAllCampaign') IS NULL
EXEC('CREATE PROCEDURE dbo.uspGellAllCampaign AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspGellAllCampaign
@merchantId BIGINT
AS
BEGIN
  SELECT * FROM dbo.[Campaign] WHERE merchantId = @merchantId AND isDeleted = 0
END
GO