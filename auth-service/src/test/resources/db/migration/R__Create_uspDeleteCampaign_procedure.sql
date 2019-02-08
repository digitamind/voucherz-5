IF OBJECT_ID('dbo.uspDeleteCampaign') IS NULL
EXEC('CREATE PROCEDURE dbo.uspDeleteCampaign AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspDeleteCampaign
@name nvarchar(100),
@merchantId BIGINT
AS
BEGIN
  UPDATE dbo.[Campaign] SET isDeleted = 1 WHERE name = @name AND merchantId = @merchantId
END
GO
