IF OBJECT_ID('dbo.uspInsertMerchantUser') IS NULL
EXEC('CREATE PROCEDURE dbo.uspInsertMerchantUser AS SET NOCOUNT ON;')
GO

ALTER PROCEDURE dbo.uspInsertMerchantUser
    @firstName nvarchar(50),
    @lastName nvarchar(50),
    @email nvarchar(50),
    @merchantId BIGINT,
    @role varchar(10),
    @companyName nvarchar(100) = NULL,
    @companySize int = NULL,
    @isCorporate bit = NULL,
    @password nvarchar(60),
    @id BIGINT = NULL OUTPUT
AS
BEGIN

    IF(@merchantId is NULL)
        BEGIN TRY
            BEGIN TRANSACTION
              DECLARE @merchantId_new BIGINT
              --DECLARE @merchantUserId BIGINT
              EXECUTE dbo.uspInsertMerchant
                  @companyName, @companySize, @isCorporate, @merchantId_new OUTPUT;
              EXECUTE dbo.uspInsertAppKey
                  @merchantId_new
              EXECUTE dbo.uspInsertClientKey
                  @merchantId_new
              INSERT INTO dbo.[MerchantUser](firstName, lastName, email, merchantId, role, password)
                  VALUES(@firstName, @lastName, @email, @merchantId_new, @role, @password)
              SELECT @id = SCOPE_IDENTITY()
--              SELECT @merchantUserId = SCOPE_IDENTITY()
--
--              EXECUTE  dbo.uspInsertMerchantUserCredential
--                  @password, @email, @merchantUserId

            COMMIT
        END TRY
        BEGIN CATCH
            ROLLBACK
        END CATCH
ELSE
BEGIN
INSERT INTO dbo.[MerchantUser](firstName, lastName, email, merchantId, role, password)
VALUES(@firstName, @lastName, @email, @merchantId, @role, @password)
END

END
GO

