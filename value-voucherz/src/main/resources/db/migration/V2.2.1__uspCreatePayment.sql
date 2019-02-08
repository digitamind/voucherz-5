IF OBJECT_ID('dbo.uspCreatePayment') IS NULL
EXEC('CREATE PROCEDURE dbo.uspCreatePayment AS SET NOCOUNT ON;')
GO
ALTER PROCEDURE [dbo].[uspCreatePayment]
  @code nvarchar(16),
  @billerName nvarchar(62),
	@amount [bigint],
	@merchantId nvarchar(62),
	@userId nvarchar(62)
AS
BEGIN
DECLARE @paymentId bigint;
DECLARE @billerId [bigint] = NULL;
DECLARE @voucherId bigint = NULL;
    BEGIN TRY
      SELECT @voucherId = id
      FROM [dbo].[Voucher]
      WHERE code = @code
      AND amount = @amount
      AND merchantId = @merchantId
      AND userId = @userId

      IF (@voucherId IS NULL)
        RAISERROR('Voucher is not valid.', 16, 1);

      SELECT @billerId = id FROM Biller WHERE [name] = @billerName;
      IF (@billerId IS NULL)
        RAISERROR('Biller not found.', 16, 1);


      BEGIN TRANSACTION
        INSERT [dbo].[Payment]
            (
            billerId,
            merchantId,
            userId,
            amount,
	          voucherId
            )
        VALUES(
            @billerId,
            @merchantId,
            @userId,
            @amount,
	          @voucherId
        )
        SET @paymentId = SCOPE_IDENTITY();
        IF (@paymentId IS NULL)
        RAISERROR('Payment Failed', 16, 1);

        SELECT * FROM Payment WHERE id = @paymentId;
      COMMIT
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
        ROLLBACK;
        EXECUTE [dbo].[uspLogError];
    END CATCH
END;
