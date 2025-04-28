CREATE TABLE IF NOT EXISTS roles (
   id SERIAL PRIMARY KEY,
   role_name VARCHAR(60) UNIQUE
);

CREATE TABLE IF NOT EXISTS users (
   id SERIAL PRIMARY KEY,
   first_name VARCHAR(100),
   last_name VARCHAR(100),
   username VARCHAR(100) UNIQUE,
   password VARCHAR(100) NOT NULL,
   email VARCHAR(100) UNIQUE,
   gender VARCHAR(10),
   age VARCHAR(10),
   phone_number VARCHAR(13) UNIQUE,
   image_url TEXT
);

CREATE TABLE IF NOT EXISTS user_role (
   role_id INT NOT NULL REFERENCES roles(id) ON DELETE CASCADE,
   user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
   PRIMARY KEY (role_id, user_id)
);

