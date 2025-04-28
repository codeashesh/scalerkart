CREATE TABLE roles (
   id BIGINT NOT NULL AUTO_INCREMENT,
   role_name VARCHAR(60),
   CONSTRAINT pk_roles PRIMARY KEY (id)
);

ALTER TABLE roles ADD CONSTRAINT uc_roles_rolename UNIQUE (role_name);

CREATE TABLE user_role (
   role_id BIGINT NOT NULL,
   user_id BIGINT NOT NULL,
   CONSTRAINT pk_user_role PRIMARY KEY (role_id, user_id)
);

CREATE TABLE users (
  user_id BIGINT NOT NULL AUTO_INCREMENT,
   full_name VARCHAR(100),
   user_name VARCHAR(100),
   email VARCHAR(50),
   password VARCHAR(100) NOT NULL,
   gender VARCHAR(255) NOT NULL,
   phone_number VARCHAR(11),
   image_url CLOB,
   CONSTRAINT pk_users PRIMARY KEY (user_id)
);

ALTER TABLE users ADD CONSTRAINT unique_email UNIQUE (email);

ALTER TABLE users ADD CONSTRAINT unique_phone UNIQUE (phone_number);

ALTER TABLE users ADD CONSTRAINT unique_username UNIQUE (user_name);

ALTER TABLE user_role ADD CONSTRAINT fk_user_role_on_role FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE user_role ADD CONSTRAINT fk_user_role_on_user FOREIGN KEY (user_id) REFERENCES users (user_id);
