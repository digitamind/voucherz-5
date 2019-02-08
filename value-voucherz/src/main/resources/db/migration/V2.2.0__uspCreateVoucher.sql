IF OBJECT_ID('dbo.uspCreateVoucher') IS NULL
EXEC('CREATE PROCEDURE dbo.uspCreateVoucher AS SET NOCOUNT ON;')
GO
ALTER PROCEDURE [dbo].[uspCreateVoucher]
    @id bigint = NULL,
    @code [nvarchar](16),
    @amount [bigint],
    @expiryDate [datetime] = NULL,
    @isActive bit = 0,
    @isCorporate bit = 1,
    @campaignId [nvarchar] (62) = 0,
    @customerId [nvarchar] (62) = 0,
    @merchantId [nvarchar] (62),
    @userId [nvarchar] (62) = 0
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
      BEGIN TRANSACTION
        INSERT [dbo].[Voucher]
            (
            code,
            amount,
            expiryDate,
            isActive,
            campaignId,
            customerId,
            merchantId,
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
            @userId,
            @isCorporate
            );

        SET @id = SCOPE_IDENTITY();
      SELECT * FROM Voucher WHERE id = @id;
      COMMIT
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
        ROLLBACK;
        EXECUTE [dbo].[uspLogError];
    END CATCH
END;
