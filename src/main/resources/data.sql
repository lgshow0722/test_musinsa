-- 카테고리 샘플 데이터 삽입
INSERT INTO CATEGORY (ID, NAME) VALUES (1, '상의');
INSERT INTO CATEGORY (ID, NAME) VALUES (2, '아우터');
INSERT INTO CATEGORY (ID, NAME) VALUES (3, '바지');
INSERT INTO CATEGORY (ID, NAME) VALUES (4, '스니커즈');
INSERT INTO CATEGORY (ID, NAME) VALUES (5, '가방');
INSERT INTO CATEGORY (ID, NAME) VALUES (6, '모자');
INSERT INTO CATEGORY (ID, NAME) VALUES (7, '양말');
INSERT INTO CATEGORY (ID, NAME) VALUES (8, '액세서리');

ALTER TABLE CATEGORY ALTER COLUMN ID RESTART WITH 9;

-- 브랜드 샘플 데이터 삽입
INSERT INTO BRAND (ID, NAME) VALUES (1, 'A');
INSERT INTO BRAND (ID, NAME) VALUES (2, 'B');
INSERT INTO BRAND (ID, NAME) VALUES (3, 'C');
INSERT INTO BRAND (ID, NAME) VALUES (4, 'D');
INSERT INTO BRAND (ID, NAME) VALUES (5, 'E');
INSERT INTO BRAND (ID, NAME) VALUES (6, 'F');
INSERT INTO BRAND (ID, NAME) VALUES (7, 'G');
INSERT INTO BRAND (ID, NAME) VALUES (8, 'H');
INSERT INTO BRAND (ID, NAME) VALUES (9, 'I');

ALTER TABLE BRAND ALTER COLUMN ID RESTART WITH 10;

-- 브랜드 A 데이터
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (1, 1, 1, 11200);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (2, 2, 1, 5500);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (3, 3, 1, 4200);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (4, 4, 1, 9000);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (5, 5, 1, 2000);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (6, 6, 1, 1700);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (7, 7, 1, 1800);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (8, 8, 1, 2300);

-- 브랜드 B 데이터
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (9, 1, 2, 10500);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (10, 2, 2, 5900);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (11, 3, 2, 3800);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (12, 4, 2, 9100);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (13, 5, 2, 2100);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (14, 6, 2, 2000);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (15, 7, 2, 2000);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (16, 8, 2, 2200);

-- 브랜드 C 데이터
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (17, 1, 3, 10000);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (18, 2, 3, 6200);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (19, 3, 3, 3300);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (20, 4, 3, 9200);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (21, 5, 3, 2200);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (22, 6, 3, 1900);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (23, 7, 3, 2200);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (24, 8, 3, 2100);

-- 브랜드 D 데이터
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (25, 1, 4, 10100);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (26, 2, 4, 5100);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (27, 3, 4, 3000);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (28, 4, 4, 9500);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (29, 5, 4, 2500);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (30, 6, 4, 1500);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (31, 7, 4, 2400);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (32, 8, 4, 2000);

-- 브랜드 E 데이터
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (33, 1, 5, 10700);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (34, 2, 5, 5000);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (35, 3, 5, 3800);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (36, 4, 5, 9900);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (37, 5, 5, 2300);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (38, 6, 5, 1800);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (39, 7, 5, 2100);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (40, 8, 5, 2100);

-- 브랜드 F 데이터
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (41, 1, 6, 11200);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (42, 2, 6, 7200);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (43, 3, 6, 4000);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (44, 4, 6, 9300);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (45, 5, 6, 2100);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (46, 6, 6, 1600);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (47, 7, 6, 2300);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (48, 8, 6, 1900);

-- 브랜드 G 데이터
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (49, 1, 7, 10500);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (50, 2, 7, 5800);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (51, 3, 7, 3900);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (52, 4, 7, 9000);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (53, 5, 7, 2200);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (54, 6, 7, 1700);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (55, 7, 7, 2100);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (56, 8, 7, 2000);

-- 브랜드 H 데이터
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (57, 1, 8, 10800);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (58, 2, 8, 6300);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (59, 3, 8, 3100);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (60, 4, 8, 9700);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (61, 5, 8, 2100);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (62, 6, 8, 1600);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (63, 7, 8, 2000);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (64, 8, 8, 2000);

-- 브랜드 I 데이터
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (65, 1, 9, 11400);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (66, 2, 9, 6700);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (67, 3, 9, 3200);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (68, 4, 9, 9500);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (69, 5, 9, 2400);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (70, 6, 9, 1700);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (71, 7, 9, 1700);
INSERT INTO MERCHANDISE (ID, CATEGORY_ID, BRAND_ID, PRICE) VALUES (72, 8, 9, 2400);

ALTER TABLE MERCHANDISE ALTER COLUMN ID RESTART WITH 73;