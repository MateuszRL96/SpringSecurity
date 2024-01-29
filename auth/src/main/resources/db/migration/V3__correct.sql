INSERT INTO users (uuid, login, email, password, role, isLock, isEnabled)
VALUES
    ('1a2a3a', 'user', 'user1@example.com', '1234', 'USER', false, true),
    ('1b2b3b', 'admin', 'admin@example.com', '1234', 'ADMIN', true, true),
    ('1c2c3c', 'mateusz', 'mati.kulec12@gmail.com', '1234', 'USER', false, false);