IF OBJECT_ID('dbo.uspCreateVoucher') IS NULL
EXEC('CREATE PROCEDURE dbo.uspCreateVoucher AS SET NOCOUNT ON;')
GO
ALTER PROCEDURE [dbo].[uspCreateVoucher]
    (
    @id [bigint] = NULL,
    @code [nvarchar](16),
    @type [smallint],
    @value [bigint] ,
    @expiryDate [date] = NULL,
    @campaignId [nvarchar] (62) = 0,
    @customerId [nvarchar] (62) = 0,
    @merchantId [nvarchar] (62),
    @productId [nvarchar] (62) = 0,
    @userId [nvarchar] (62),
    @category [nvarchar] (100) = NULL,
    @additionalInfo [nvarchar] (100) = NULL
	)
AS
BEGIN
    BEGIN TRY
      BEGIN TRANSACTION
        INSERT [dbo].[DiscountVoucher]
            (
            [code],
            [type],
            [value] ,
            [expiryDate],
            [campaignId],
            [customerId],
            [merchantId],
            [productId],
            [userId],
            [category],
            [additionalInfo]
            )
        VALUES
            (
            @code,
            @type,
            @value ,
            @expiryDate,
            @campaignId,
            @customerId,
            @merchantId,
            @productId,
            @userId,
            @category,
            @additionalInfo
            );

        SET @id = SCOPE_IDENTITY();
      COMMIT
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
        ROLLBACK;
        EXECUTE [dbo].[uspLogError];
    END CATCH
END;