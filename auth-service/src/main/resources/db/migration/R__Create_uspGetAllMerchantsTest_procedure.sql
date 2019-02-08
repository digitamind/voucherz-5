IF OBJECT_ID('dbo.uspGetAllMerchantTest') IS NULL
EXEC('CREATE PROCEDURE dbo.uspGetAllMerchantTest AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspGetAllMerchantTest
@pageSize INT,
@pageNumber  INT
AS
BEGIN
  SELECT * from dbo.[Merchant]
    ORDER BY [companyName]
    OFFSET (@pageNumber - 1) * @pageSize ROWS
  FETCH NEXT @pageSize ROWS ONLY;
END
GO