------------------------------ Users Data -----------------------------
INSERT INTO users(pk, name, id, password, description, role) values (null, '테스트 이름1', '테스트 아이디1', '테스트 비밀번호1', '테스트 설명1', 'REGISTER')
INSERT INTO users(pk, name, id, password, description, role) values (null, '테스트 이름2', '테스트 아이디2', '테스트 비밀번호2', '테스트 설명2', 'ADMIN')
INSERT INTO users(pk, name, id, password, description, role) values (null, '테스트 이름3', '테스트 아이디3', '테스트 비밀번호3', '테스트 설명3', 'USER')

------------------------------ Article Data -----------------------------
INSERT INTO article(pk, title, content, hits) values (null, '테스트 타이틀1', '테스트 내용1', 3)
INSERT INTO article(pk, title, content, hits) values (null, '테스트 타이틀2', '테스트 내용2', 2)
INSERT INTO article(pk, title, content, hits) values (null, '테스트 타이틀3', '테스트 내용3', 1)
