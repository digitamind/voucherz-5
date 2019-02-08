INSERT INTO dbo.Role(roleName)
VALUES('ADMIN'), ('USER'),('SYSADMIN');

INSERT INTO dbo.MerchantUser(firstName, lastName, email, [role], active, password, enabled)
  VALUES('victor', 'abidoye', 'swissvic95@gmail.com', 'SYSADMIN', 1, '$2a$10$B1Wg6OgXv6bf4kTDH6Zec.aTPcJ1B4GabLS2bDuj9PsY8qfKteIEK', 1)