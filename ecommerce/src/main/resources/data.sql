-- ロールの初期データ
INSERT INTO roles (name) VALUES ('ROLE_USER') ON DUPLICATE KEY UPDATE name = VALUES(name);
INSERT INTO roles (name) VALUES ('ROLE_ADMIN') ON DUPLICATE KEY UPDATE name = VALUES(name);

-- カテゴリの初期データ
INSERT INTO categories (name, description) VALUES ('Electronics', 'Electronic devices and accessories') ON DUPLICATE KEY UPDATE name = VALUES(name);
INSERT INTO categories (name, description) VALUES ('Clothing', 'Apparel and fashion items') ON DUPLICATE KEY UPDATE name = VALUES(name);
INSERT INTO categories (name, description) VALUES ('Books', 'Books and publications') ON DUPLICATE KEY UPDATE name = VALUES(name);

-- サンプル商品の初期データ
-- INSERT INTO products (name, description, price, stock, image_url, category_id) VALUES (...);
