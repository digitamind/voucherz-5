IF OBJECT_ID('dbo.uspInsertClientKey') IS NULL
EXEC('CREATE PROCEDURE dbo.uspInsertClientKey AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspInsertClientKey
    @merchantId BIGINT
AS

   BEGIN
        INSERT INTO ClientKey(merchantId, clientKey, clientId)
            VALUES(@merchantId, NEWID(), NEWID())
  END
GO