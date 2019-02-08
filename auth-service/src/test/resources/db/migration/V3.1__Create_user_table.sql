CREATE TABLE [Customer]
(
	[id] BIGINT NOT NULL PRIMARY KEY IDENTITY(1,1),
	rowguid uniqueidentifier DEFAULT newid() NOT NULL,
	firstName NVARCHAR(100),
	lastName NVARCHAR(100),
	email NVARCHAR(100),
	address NVARCHAR(100),
	city NVARCHAR(50),
	country NVARCHAR(50),
	amountOfOrders BIGINT,
	numberOfOrders BIGINT,
	lastOrderAmount BIGINT,
	lastOrderDate DATE,
	dateJoined DATE,
	kpi INT,
	merchantId BIGINT FOREIGN KEY REFERENCES [Merchant](id),
  isDeleted bit default 0
)
