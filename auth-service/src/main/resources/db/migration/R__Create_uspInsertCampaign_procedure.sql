IF OBJECT_ID('dbo.uspInsertCampaign') IS NULL
EXEC('CREATE PROCEDURE dbo.uspInsertCampaign AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspInsertCampaign
  @name nvarchar(16),
  @description nvarchar(50),
  @activate BIT,
  @merchantUserId BIGINT,
  @startDate DATE,
  @expirationDate DATE,
  @voucherType int,
  @merchantId BIGINT,
@id BIGINT = NULL OUTPUT

AS
  BEGIN

    INSERT INTO Campaign(name, description, merchantUserId, startDate, expirationDate,
    voucherType, merchantId, activate)
      VALUES (@name, @description, @merchantUserId, @startDate,
        @expirationDate, @voucherType, @merchantId, @activate)

    SELECT @id = SCOPE_IDENTITY()
  END
GO