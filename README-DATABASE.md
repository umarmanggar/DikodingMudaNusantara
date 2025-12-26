# Setup Database Koding Muda Belajar

## Cara Setup Database

### 1. Pastikan MySQL Sudah Jalan

**Windows:**
```cmd
net start MySQL80
```

**XAMPP:**
- Buka XAMPP Control Panel
- Start MySQL

### 2. Import Database Seed

**Cara 1: Via MySQL Command Line**
```bash
mysql -u root -p < database-seed.sql
```

**Cara 2: Via MySQL Workbench**
1. Buka MySQL Workbench
2. Koneksi ke database
3. File → Run SQL Script
4. Pilih file `database-seed.sql`
5. Klik Run

**Cara 3: Via phpMyAdmin (XAMPP)**
1. Buka http://localhost/phpmyadmin
2. Pilih database `koding_muda_belajar` atau buat baru
3. Tab Import
4. Choose File → pilih `database-seed.sql`
5. Klik Go

### 3. Jalankan Aplikasi

```bash
./mvnw spring-boot:run
```

### 4. Akses Aplikasi

```
http://localhost:8080
```

## Default User Accounts

### Admin
- Username: `admin`
- Password: `admin123`
- Email: `admin@kodingmuda.com`

### Lecturer
- Username: `lecturer1`
- Password: `admin123`
- Email: `lecturer@kodingmuda.com`

### Student
- Username: `student1`
- Password: `admin123`
- Email: `student@kodingmuda.com`

## Troubleshooting

### Error: Database Connection Failed
1. Pastikan MySQL sudah jalan
2. Cek username/password di `application.properties`
3. Pastikan database `koding_muda_belajar` sudah dibuat

### Error: Table doesn't exist
1. Jalankan aplikasi sekali untuk auto-create tables (ddl-auto=update)
2. Atau import database-seed.sql yang akan create tables

### Halaman Kosong/Tidak Ada Data
1. Import database-seed.sql untuk mendapatkan data sample
2. Atau buat data manual via aplikasi
