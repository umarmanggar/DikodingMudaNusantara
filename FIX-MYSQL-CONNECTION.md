# 🔧 Fix MySQL Connection Problem

Database Anda sudah nyala tapi aplikasi tidak bisa connect. Mari kita debug!

---

## 📋 Troubleshooting Steps

### **Step 1: Cek MySQL Jalan di Port Berapa?**

**Buka MySQL Workbench atau Command Prompt:**

```bash
# Login ke MySQL
mysql -u root -p

# Setelah login, cek port:
SHOW VARIABLES LIKE 'port';
```

**Expected Output:**
```
+---------------+-------+
| Variable_name | Value |
+---------------+-------+
| port          | 3306  |
+---------------+-------+
```

✅ **Jika port 3306** → Lanjut ke Step 2  
❌ **Jika port BUKAN 3306** → Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:XXXX/koding_muda_belajar?useSSL=false&serverTimezone=Asia/Jakarta&allowPublicKeyRetrieval=true
```
(Ganti XXXX dengan port yang benar)

---

### **Step 2: Cek Database Sudah Dibuat?**

```sql
-- Login MySQL dulu
mysql -u root -p

-- Lihat semua database
SHOW DATABASES;

-- Cari database: koding_muda_belajar
```

✅ **Jika database ADA** → Lanjut ke Step 3  
❌ **Jika database TIDAK ADA** → Buat databasenya:

```sql
CREATE DATABASE koding_muda_belajar;
```

Atau import file seed yang sudah saya buat:
```bash
mysql -u root -p < database-seed.sql
```

---

### **Step 3: Cek Username & Password**

Di `application.properties`:
```properties
spring.datasource.username=root
spring.datasource.password=
```

**Test login MySQL manual:**
```bash
mysql -u root -p
# Jika password kosong, langsung tekan Enter
```

✅ **Jika bisa login** → Lanjut ke Step 4  
❌ **Jika tidak bisa** → Ada 2 kemungkinan:

**A. Username bukan root:**
```properties
spring.datasource.username=GANTI_DENGAN_USERNAME_ANDA
```

**B. Ada password:**
```properties
spring.datasource.password=PASSWORD_ANDA
```

---

### **Step 4: Cek Bind Address MySQL**

MySQL mungkin hanya listen di 127.0.0.1, bukan localhost.

**Test dengan 127.0.0.1:**

Update `application.properties` sementara:
```properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/koding_muda_belajar?useSSL=false&serverTimezone=Asia/Jakarta&allowPublicKeyRetrieval=true
```

Lalu coba run lagi.

---

### **Step 5: Cek MySQL Allow Connection dari Localhost**

```sql
-- Login ke MySQL
mysql -u root -p

-- Cek user permissions
SELECT user, host FROM mysql.user WHERE user='root';
```

**Expected:**
```
+------+-----------+
| user | host      |
+------+-----------+
| root | localhost |
| root | 127.0.0.1 |
| root | ::1       |
+------+-----------+
```

Jika root hanya ada untuk `%` atau host lain, grant permission:
```sql
GRANT ALL PRIVILEGES ON koding_muda_belajar.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

---

## 🚀 Quick Fix Solutions

### **Solution A: Buat Database Dulu**

```sql
-- 1. Login MySQL
mysql -u root -p

-- 2. Buat database
CREATE DATABASE IF NOT EXISTS koding_muda_belajar;

-- 3. Exit
exit;

-- 4. Run aplikasi lagi
```

---

### **Solution B: Import Database Seed**

```bash
# Dari folder project
mysql -u root -p < database-seed.sql
```

File `database-seed.sql` akan:
- ✅ Buat database otomatis
- ✅ Buat semua tables
- ✅ Insert sample data

---

### **Solution C: Ganti ke 127.0.0.1**

Edit `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/koding_muda_belajar?useSSL=false&serverTimezone=Asia/Jakarta&allowPublicKeyRetrieval=true
```

---

### **Solution D: Test Connection Manual**

Buat file test: `TestConnection.java`

```java
import java.sql.Connection;
import java.sql.DriverManager;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/koding_muda_belajar?useSSL=false";
        String user = "root";
        String password = "";
        
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ CONNECTION SUCCESS!");
            conn.close();
        } catch (Exception e) {
            System.out.println("❌ CONNECTION FAILED!");
            e.printStackTrace();
        }
    }
}
```

Compile & run:
```bash
javac TestConnection.java
java TestConnection
```

---

## 🎯 Most Common Issues

### **Issue #1: Database Belum Dibuat**
```sql
CREATE DATABASE koding_muda_belajar;
```

### **Issue #2: Port Salah (XAMPP biasa 3306, tapi bisa beda)**
Cek di XAMPP Config atau MySQL Workbench

### **Issue #3: Password Tidak Cocok**
```properties
spring.datasource.password=YOUR_ACTUAL_PASSWORD
```

### **Issue #4: MySQL di WSL/Docker**
Jika pakai WSL atau Docker, gunakan IP WSL/Docker, bukan localhost

---

## 📝 Check Configuration

**File: `application.properties`**

Pastikan ada:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/koding_muda_belajar?useSSL=false&serverTimezone=Asia/Jakarta&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
```

**Arti `ddl-auto=update`:**
- Hibernate akan otomatis buat/update tables
- Database harus sudah ada
- Tables dibuat otomatis

---

## 🔍 Debug Mode

Jalankan aplikasi dengan debug logging:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--logging.level.org.hibernate.SQL=DEBUG --logging.level.com.zaxxer.hikari=DEBUG"
```

Ini akan show detail kenapa connection gagal.

---

## ✅ After Fix Checklist

- [ ] Database `koding_muda_belajar` sudah dibuat
- [ ] Username & password sudah benar
- [ ] Port MySQL sudah benar (default 3306)
- [ ] Bisa login MySQL manual dengan `mysql -u root -p`
- [ ] Run aplikasi: `./mvnw spring-boot:run`
- [ ] Aplikasi start tanpa error
- [ ] Akses http://localhost:8080 - halaman muncul

---

## 🆘 Masih Gagal?

Kirim output dari:

1. **MySQL Version:**
```bash
mysql --version
```

2. **Test Connection:**
```bash
mysql -u root -p
SHOW DATABASES;
```

3. **Application Error:**
Copy error message lengkap dari terminal

4. **MySQL Config:**
Buka file `my.ini` atau `my.cnf`, cek:
```ini
[mysqld]
port=3306
bind-address=127.0.0.1
```

---

**Good luck!** 🍀
