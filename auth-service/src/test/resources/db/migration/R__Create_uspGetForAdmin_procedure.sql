IF OBJECT_ID('dbo.uspGetMerchants') IS NULL
EXEC('CREATE PROCEDURE dbo.uspGetMerchants AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspGetMerchants
AS
BEGIN
Select M.companyName as companyName, count(Mu.id) as merchantUsers, M.dateCreated as dateCreated,
       M.companySize as companySize from dbo.[Merchant] as M INNER JOIN dbo.[MerchantUser] as Mu
  ON M.id = Mu.merchantId
Group by M.id, M.companyName, M.dateCreated, M.companySize
END
GO