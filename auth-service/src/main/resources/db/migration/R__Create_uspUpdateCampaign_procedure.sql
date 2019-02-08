IF OBJECT_ID('dbo.uspUpdateCampaign') IS NULL
EXEC('CREATE PROCEDURE dbo.uspUpdateCampaign AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspUpdateCampaign
@name nvarchar(16),
@description nvarchar(50),
@activate [BIT],
@startDate DATE,
@expirationDate DATE,
@voucherType int,
@merchantId BIGINT
AS
BEGIN
  UPDATE Campaign set
    description = @description,
    activate = @activate,
    startDate = @startdate,
    expirationDate = @expirationDate,
    voucherType = @voucherType
  where lower(name) = lower(@name) AND merchantId = @merchantId
END
GO