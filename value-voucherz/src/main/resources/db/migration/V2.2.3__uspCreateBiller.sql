IF OBJECT_ID('dbo.uspCreateBiller') IS NULL
EXEC('CREATE PROCEDURE dbo.uspCreateBiller AS SET NOCOUNT ON;')
GO
ALTER PROCEDURE [dbo].[uspCreateBiller]
	@description [nvarchar](100),
	@name [nvarchar](62)
AS
BEGIN
DECLARE @id bigint;
    BEGIN TRY
      BEGIN TRANSACTION
        INSERT [dbo].[Biller]
            (
            [description],
            [name]
            )
        VALUES(
            @description,
            @name
        )
        SET @id = SCOPE_IDENTITY();
        SELECT * FROM Biller WHERE id = @id;
      COMMIT
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
        ROLLBACK;
        EXECUTE [dbo].[uspLogError];
    END CATCH
END;
