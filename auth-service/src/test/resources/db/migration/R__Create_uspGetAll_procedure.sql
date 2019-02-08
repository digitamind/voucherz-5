IF OBJECT_ID('dbo.uspGetAllMerchants') IS NULL
EXEC('CREATE PROCEDURE dbo.uspGetAllMerchants AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspGetAllMerchants
@pageSize INT,
@pageNumber  INT
AS
BEGIN
SELECT id, companyName, dateCreated, companySize FROM Merchant
ORDER BY [companyName]
OFFSET (@pageNumber - 1) * @pageSize ROWS
FETCH NEXT @pageSize ROWS ONLY;
END
GO


IF OBJECT_ID('dbo.uspGetAllMerchantUser') IS NULL
EXEC('CREATE PROCEDURE dbo.uspGetAllMerchantUser AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspGetAllMerchantUser
@pageSize INT = 2,
@pageNumber  INT = 1
AS
BEGIN
SELECT id, firstName, lastName, email, merchantId, role FROM MerchantUser
ORDER BY [firstName]
OFFSET (@pageNumber - 1) * @pageSize ROWS
FETCH NEXT @pageSize ROWS ONLY;
END
GO