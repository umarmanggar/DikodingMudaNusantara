-- ============================================================
-- DATABASE SEED UNTUK KODING MUDA BELAJAR
-- ============================================================

-- Buat database jika belum ada
CREATE DATABASE IF NOT EXISTS koding_muda_belajar;
USE koding_muda_belajar;

-- Insert Categories
INSERT INTO categories (id, name, description, slug, icon_class, created_at, updated_at) VALUES
(1, 'Web Development', 'Pelajari pembuatan website dan aplikasi web', 'web-development', 'fa-code', NOW(), NOW()),
(2, 'Data Science', 'Analisis data dan machine learning', 'data-science', 'fa-chart-line', NOW(), NOW()),
(3, 'Cyber Security', 'Keamanan sistem dan jaringan', 'cyber-security', 'fa-shield-alt', NOW(), NOW()),
(4, 'Database', 'Manajemen dan desain database', 'database', 'fa-database', NOW(), NOW()),
(5, 'Mobile Development', 'Pembuatan aplikasi mobile', 'mobile-development', 'fa-mobile-alt', NOW(), NOW())
ON DUPLICATE KEY UPDATE updated_at = NOW();

-- Insert Admin User
INSERT INTO users (id, username, email, password, full_name, phone, role, is_active, created_at, updated_at) VALUES
(1, 'admin', 'admin@kodingmuda.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCu', 'Admin System', '081234567890', 'ADMIN', true, NOW(), NOW())
ON DUPLICATE KEY UPDATE updated_at = NOW();
-- Password: admin123

-- Insert Sample Lecturer
INSERT INTO users (id, username, email, password, full_name, phone, role, is_active, created_at, updated_at) VALUES
(2, 'lecturer1', 'lecturer@kodingmuda.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCu', 'Budi Santoso', '081234567891', 'LECTURER', true, NOW(), NOW())
ON DUPLICATE KEY UPDATE updated_at = NOW();

INSERT INTO lecturers (id, user_id, expertise, bio, created_at, updated_at) VALUES
(1, 2, 'Web Development, JavaScript', 'Expert dalam pengembangan web modern dengan 10+ tahun pengalaman', NOW(), NOW())
ON DUPLICATE KEY UPDATE updated_at = NOW();

-- Insert Sample Student
INSERT INTO users (id, username, email, password, full_name, phone, role, is_active, created_at, updated_at) VALUES
(3, 'student1', 'student@kodingmuda.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhCu', 'Ani Wijaya', '081234567892', 'STUDENT', true, NOW(), NOW())
ON DUPLICATE KEY UPDATE updated_at = NOW();

INSERT INTO students (id, user_id, school, created_at, updated_at) VALUES
(1, 3, 'Universitas Indonesia', NOW(), NOW())
ON DUPLICATE KEY UPDATE updated_at = NOW();

-- Insert Sample Courses
INSERT INTO courses (id, title, slug, description, category_id, lecturer_id, price, level, duration_hours, thumbnail, status, created_at, updated_at) VALUES
(1, 'Fullstack Web Development dengan Spring Boot & React', 'fullstack-web-spring-react', 
'Pelajari cara membuat aplikasi web fullstack menggunakan Spring Boot untuk backend dan React untuk frontend. Course ini cocok untuk pemula hingga intermediate.', 
1, 1, 500000, 'INTERMEDIATE', 40, 'course-1.jpg', 'PUBLISHED', NOW(), NOW()),

(2, 'Mastering MySQL Database', 'mastering-mysql-database', 
'Panduan lengkap belajar MySQL dari dasar hingga advanced. Pelajari query optimization, indexing, dan best practices.', 
4, 1, 350000, 'BEGINNER', 30, 'course-2.jpg', 'PUBLISHED', NOW(), NOW()),

(3, 'Data Science dengan Python', 'data-science-python', 
'Belajar analisis data menggunakan Python, Pandas, NumPy dan Scikit-learn. Termasuk machine learning basics.', 
2, 1, 750000, 'INTERMEDIATE', 50, 'course-3.jpg', 'PUBLISHED', NOW(), NOW())
ON DUPLICATE KEY UPDATE updated_at = NOW();

-- Insert Sample Sections
INSERT INTO sections (id, course_id, title, order_index, created_at, updated_at) VALUES
(1, 1, 'Pengenalan Spring Boot', 1, NOW(), NOW()),
(2, 1, 'Database & JPA', 2, NOW(), NOW()),
(3, 2, 'Dasar-dasar SQL', 1, NOW(), NOW()),
(4, 3, 'Python Fundamentals', 1, NOW(), NOW())
ON DUPLICATE KEY UPDATE updated_at = NOW();

-- Insert Sample Lessons
INSERT INTO lessons (id, section_id, title, content_type, content_url, duration_minutes, order_index, is_preview, created_at, updated_at) VALUES
(1, 1, 'Apa itu Spring Boot?', 'VIDEO', 'https://example.com/video1.mp4', 15, 1, true, NOW(), NOW()),
(2, 1, 'Setup Project Spring Boot', 'VIDEO', 'https://example.com/video2.mp4', 20, 2, true, NOW(), NOW()),
(3, 2, 'Konfigurasi Database', 'VIDEO', 'https://example.com/video3.mp4', 25, 1, false, NOW(), NOW()),
(4, 3, 'SELECT Statement', 'VIDEO', 'https://example.com/video4.mp4', 18, 1, true, NOW(), NOW()),
(5, 4, 'Variables & Data Types', 'VIDEO', 'https://example.com/video5.mp4', 22, 1, true, NOW(), NOW())
ON DUPLICATE KEY UPDATE updated_at = NOW();

-- Insert Sample Enrollments (Student enrolled in course)
INSERT INTO enrollments (id, student_id, course_id, status, enrolled_at, created_at, updated_at) VALUES
(1, 1, 1, 'ACTIVE', NOW(), NOW(), NOW())
ON DUPLICATE KEY UPDATE updated_at = NOW();

-- Insert Sample Reviews
INSERT INTO reviews (id, course_id, student_id, rating, comment, created_at, updated_at) VALUES
(1, 1, 1, 5, 'Course yang sangat bagus! Penjelasan detail dan mudah dipahami.', NOW(), NOW()),
(2, 2, 1, 4, 'Materi lengkap, tapi video bisa lebih pendek-pendek.', NOW(), NOW())
ON DUPLICATE KEY UPDATE updated_at = NOW();

-- Insert Balance for Student
INSERT INTO balances (id, user_id, balance, created_at, updated_at) VALUES
(1, 3, 1000000, NOW(), NOW())
ON DUPLICATE KEY UPDATE updated_at = NOW();

SELECT 'Database seeded successfully!' as message;
