-- Insert roles only if they do not exist
INSERT INTO roles (role_name)
SELECT 'USER' WHERE NOT EXISTS (SELECT 1 FROM roles WHERE role_name = 'USER');

INSERT INTO roles (role_name)
SELECT 'ADMIN' WHERE NOT EXISTS (SELECT 1 FROM roles WHERE role_name = 'ADMIN');

