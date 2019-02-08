IF OBJECT_ID('dbo.uspCreateVouchers') IS NULL
EXEC('CREATE PROCEDURE dbo.uspCreateVouchers AS SET NOCOUNT ON;')
GO
ALTER PROCEDURE [dbo].[uspCreateVouchers]
   (@vouchers dbo.voucher_table_param READONLY)
AS
BEGIN
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
            isCorporate,
	          additionalInfo,
	          category
            )
        SELECT
            v.code,
            v.amount,
            v.expiryDate,
            v.isActive,
            v.campaignId,
            v.customerId,
            v.merchantId,
            v.userId,
            v.isCorporate,
            v.additionalInfo,
            v.category
		FROM @vouchers AS v

    COMMIT
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
        ROLLBACK;
        EXECUTE [dbo].[uspLogError];
    END CATCH
END;
