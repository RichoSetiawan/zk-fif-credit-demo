-- V2__Insert_initial_data.sql
-- Insert roles
INSERT INTO "role" (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO "role" (id, name) VALUES (2, 'ROLE_ADMIN');

-- Insert users (note: 'user' is quoted because it's a reserved keyword in PostgreSQL)
INSERT INTO "user" (id, username, password) VALUES (1, 'admin', '$2a$10$oRm5ddTjHMhNsi.NVKRLQOiUXz6qs1zPUhkZGd5TM/7Y0F/MLkBae');
INSERT INTO "user" (id, username, password) VALUES (2, 'user', '$2a$10$4koVDNa1I8P6u2LqN4fx5e1hSgdkabthueqrWpTJGi9GFIIDUD4AC');

-- Insert user-role relationships
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 1);
