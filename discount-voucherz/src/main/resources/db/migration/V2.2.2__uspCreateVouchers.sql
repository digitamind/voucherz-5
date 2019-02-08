IF OBJECT_ID('dbo.uspCreateVouchers') IS NULL
EXEC('CREATE PROCEDURE dbo.uspCreateVouchers AS SET NOCOUNT ON;')
GO
ALTER PROCEDURE [dbo].[uspCreateVouchers]
   (@vouchers dbo.voucher_table_param READONLY)
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
        SELECT
            v.code,
            v.type,
            v.value ,
            v.expiryDate,
            v.campaignId,
            v.customerId,
            v.merchantId,
            v.productId,
            v.userId,
            v.category,
            v.additionalInfo
		FROM @vouchers AS v
    COMMIT
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
        ROLLBACK;
        EXECUTE [dbo].[uspLogError];
    END CATCH
END;
