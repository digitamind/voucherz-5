IF OBJECT_ID('dbo.uspInsertAppKey') IS NULL
EXEC('CREATE PROCEDURE dbo.uspInsertAppKey AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspInsertAppKey
    @merchantId BIGINT
AS

   BEGIN
        INSERT INTO ApplicationKey(merchantId, appKey, appId)
            VALUES(@merchantId, NEWID(), NEWID())

  END
GO