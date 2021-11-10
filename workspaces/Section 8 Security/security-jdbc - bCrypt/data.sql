DROP TABLE user_bcrypt IF EXISTS;

CREATE TABLE user_bcrypt (
  id INT,
  username VARCHAR(255),
  password_hash VARCHAR(255),
  salt VARCHAR(255),
  iteration_count INT,
  role VARCHAR(255)
);

INSERT INTO user_bcrypt (id, username, password_hash, salt, iteration_count, role) VALUES (1, 'admin', 'J44yk4nlyGAq/MkMHj5FWrtZojuAcpQ=', 'RNEaihyGIDuOpCpm/s476A==', 10, 'admin');
INSERT INTO user_bcrypt (id, username, password_hash, salt, iteration_count, role) VALUES (2, 'user','J44yk4nlyGAq/MkMHj5FWrtZojuAcpQ=', 'RNEaihyGIDuOpCpm/s476A==', 10, 'user');
