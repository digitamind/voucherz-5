IF OBJECT_ID('dbo.uspCreateGiftVouchers') IS NULL
EXEC('CREATE PROCEDURE dbo.uspCreateGiftVouchers AS SET NOCOUNT ON;')
GO
ALTER PROCEDURE [dbo].[uspCreateGiftVouchers]
   (@vouchers dbo.voucher_table_param READONLY)
AS
BEGIN
    BEGIN TRY
      BEGIN TRANSACTION
        INSERT [dbo].[GiftVoucher]
            (
            code,
            amount,
            balance,
            expiryDate,
            isActive,
            campaignId,
            customerId,
            merchantId,
            productId,
            userId,
            isCorporate
            )
        SELECT
            v.code,
            v.amount,
            v.amount,
            v.expiryDate,
            v.isActive,
            v.campaignId,
            v.customerId,
            v.merchantId,
            v.productId,
            v.userId,
            v.isCorporate
		FROM @vouchers AS v

    COMMIT
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
        ROLLBACK;
        EXECUTE [dbo].[uspLogError];
    END CATCH
END;
