/* load the records. */

INSERT INTO t_user
    VALUES('00000000-0000-0000-0000-000000000001', 'テスト 太郎', '1976-01-01', '東京都', '09012345678', 'taro.test@test.com', 'ACTIVE', 0);
INSERT INTO t_user_credential
    VALUES('00000000-0000-0000-0000-000000000001', 'taro.test@test.com', '$2a$10$oxSJl.keBwxmsMLkcT9lPeAIxfNTPNQxpeywMrF7A3kVszwUTqfTK', 'ACTIVE', CURRENT_TIMESTAMP(), 0);
INSERT INTO t_user_roles
    VALUES('00000000-0000-0000-0000-000000000001', 'USER');
INSERT INTO t_user_roles
    VALUES('00000000-0000-0000-0000-000000000001', 'ADMIN');
commit;
