IF OBJECT_ID('dbo.uspGetCampaignByName') IS NULL
EXEC('CREATE PROCEDURE dbo.uspGetCampaignByName AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspGetCampaignByName
@name nvarchar(100),
@merchantId BIGINT
AS
BEGIN
  SELECT * FROM dbo.[Campaign] WHERE lower(name) = lower(@name)
  AND merchantId = @merchantId
  AND isDeleted = 0
END
GO


