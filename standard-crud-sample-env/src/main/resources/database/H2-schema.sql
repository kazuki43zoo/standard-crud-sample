/* define the schemas. */

CREATE TABLE t_user (
  user_uuid     VARCHAR(36),
  name          NVARCHAR(256) NOT NULL,
  date_of_birth DATE          NOT NULL,
  address       NVARCHAR(256),
  tel           VARCHAR(20),
  email         VARCHAR(256)  NOT NULL,
  status        VARCHAR(32)   NOT NULL,
  version       BIGINT        NOT NULL,
  CONSTRAINT pk_t_user PRIMARY KEY (user_uuid)
);

CREATE TABLE t_user_credential (
  user_uuid      VARCHAR(36),
  user_id        VARCHAR(256) NOT NULL,
  password       CHAR(60)     NOT NULL,
  status         VARCHAR(32)  NOT NULL,
  last_update_at TIMESTAMP    NOT NULL,
  version        BIGINT       NOT NULL,
  CONSTRAINT pk_t_user_credential PRIMARY KEY (user_uuid),
  CONSTRAINT uk_t_user_credential UNIQUE (user_id)
);

CREATE TABLE t_user_roles (
  user_uuid VARCHAR(36),
  role      VARCHAR(64),
  CONSTRAINT pk_t_user_roles PRIMARY KEY (user_uuid, role),
);
