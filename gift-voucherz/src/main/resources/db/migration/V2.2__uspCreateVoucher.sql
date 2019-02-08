IF OBJECT_ID('dbo.uspCreateGiftVoucher') IS NULL
EXEC('CREATE PROCEDURE dbo.uspCreateGiftVoucher AS SET NOCOUNT ON;')
GO
ALTER PROCEDURE [dbo].[uspCreateGiftVoucher]
    @id bigint = NULL,
    @code [nvarchar](16),
    @amount [bigint],
    @dateCreated [datetime] = NULL,
    @expiryDate [datetime] = NULL,
    @isActive bit = 1,
    @isCorporate bit = 1,
    @campaignId [nvarchar] (62) = 0,
    @customerId [nvarchar] (62) = 0,
    @merchantId [nvarchar] (62),
    @productId [nvarchar] (62) = 0,
    @userId [nvarchar] (62) = 0,
    @merchantStoreId [nvarchar] (62) = NULL
AS
BEGIN
    SET NOCOUNT ON;
    SET @dateCreated = getdate();

    BEGIN TRY
      BEGIN TRANSACTION
        INSERT [dbo].[GiftVoucher]
            (
            code,
            amount,
            expiryDate,
            isActive,
            campaignId,
            customerId,
            merchantId,
            productId,
            userId,
            isCorporate
            )
        VALUES
            (
            @code,
            @amount,
            @expiryDate,
            @isActive,
            @campaignId,
            @customerId,
            @merchantId,
            @productId,
            @userId,
            @isCorporate
            );

        SET @id = SCOPE_IDENTITY();

      IF @isCorporate = 0 AND @merchantStoreId IS NOT NULL
        BEGIN
          INSERT INTO [dbo].[Store] (userId, merchantStoreId, giftVoucherId)
          VALUES (@userId, @merchantStoreId, @id)
        END

      COMMIT
    END TRY
    BEGIN CATCH
        ROLLBACK;
        EXECUTE [dbo].[uspLogError];
    END CATCH
END;
