INSERT INTO USER (user_Name, user_Email, user_Password) VALUES ('김대훈', 'bbq9234@naver.com', 'kdo12341');
INSERT INTO USER (user_Name, user_Email, user_Password) VALUES ('박재성', 'javajigi@gmail.com', 'pass');
INSERT INTO USER (user_Name, user_Email, user_Password) VALUES ('한재엽', 'ljyhanll@gmail.com', 'woduq');
INSERT INTO USER (user_Name, user_Email, user_Password) VALUES ('최원영', '1@gmail.com', '1');
INSERT INTO USER (user_Name, user_Email, user_Password) VALUES ('권용재', '2@gmail.com', '2');
INSERT INTO USER (user_Name, user_Email, user_Password) VALUES ('장우현', '3@gmail.com', '3');
INSERT INTO USER (user_Name, user_Email, user_Password) VALUES ('이도형', '4@gmail.com', '4');

-- TEST DATA --
INSERT INTO USER_FRIEND_USERS (user_id, friend_users_id) VALUES (3, 4); -- 재엽, 원영 친구관계
INSERT INTO USER_FRIEND_USERS (user_id, friend_users_id) VALUES (3, 1); -- 재엽, 대훈 친구관계
INSERT INTO USER_FRIEND_USERS (user_id, friend_users_id) VALUES (4, 3); -- 원영, 재엽 친구관계
INSERT INTO USER_FRIEND_USERS (user_id, friend_users_id) VALUES (1, 3); -- 대훈, 재엽 친구관계

INSERT INTO USER_REQUESTS_TO_ME (user_id, requests_to_me_id) VALUES (3, 5); -- 재엽에게 용재가 친구 요청
INSERT INTO USER_REQUESTS_TO_USER (user_id, requests_to_user_id) VALUES (3, 6); -- 재엽이 우현이에게 친구 요청
INSERT INTO USER_REQUESTS_TO_USER (user_id, requests_to_user_id) VALUES (3, 7); -- 재엽이 도형이형에게 친구 요청
INSERT INTO USER_MET_USERS (user_id, met_users_id) VALUES (3, 2); -- 재엽이 교수님을 만난적이 있음

--INSERT INTO MEETING (capacity, contents, current_participants_number, location, writer_id, create_date, expire_date, meeting_date) VALUES (5, '홍대에서 코딩할 건데 같이 하실 분?', 3, '홍대 카페 거리', 1, now(), now(), now());
--INSERT INTO MEETING (capacity, contents, current_participants, location, writer_id) VALUES (8, '강남에서 술 한잔 하실 분~', 5, '맛있는 양꼬치집', 2);
--INSERT INTO MEETING (capacity, contents, current_participants, location, writer_id) VALUES (30, '9x 년생 코더들 다 모여라~', 17, '강남 토즈', 3);