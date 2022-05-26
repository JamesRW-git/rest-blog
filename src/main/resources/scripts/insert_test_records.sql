INSERT INTO users (username, password, email, role, created_at) VALUES ('test_user', 'test123', 'test@test.com', 'USER', NOW());

INSERT INTO posts (user_id, title, content) VALUES (1, 'Babys First Post', 'Do not be alarmed. This is only a test.');

INSERT INTO categories (name)
VALUES ('MUSIC'),
       ('FOOD'),
       ('PROGRAMMING');

INSERT INTO post_category (post_id, category_id) VALUES (1, 1);

SELECT * FROM users;

SELECT * FROM posts;
SELECT * FROM post_category;