CREATE TYPE dbo.customer_table_param AS TABLE(
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
  merchantId BIGINT
)