# 🧪 Cara Test Error Pages

## ⚠️ PENTING! Baca ini dulu:

Error pages **HANYA MUNCUL** ketika ada error yang **REAL TERJADI**, bukan ketika Anda langsung akses file HTML-nya.

---

## 📋 Step-by-Step Testing

### **Step 1: Pastikan Aplikasi Berjalan**

```bash
# Jalankan aplikasi
./mvnw spring-boot:run

# Tunggu sampai muncul:
# "Started KodingMudaBelajarApplication in X.XXX seconds"
```

### **Step 2: Test dengan URL Khusus**

Saya sudah buatkan endpoint khusus untuk testing:

#### **Test 404 Page:**
```
http://localhost:8080/test-error/404
```
Atau akses URL yang tidak ada:
```
http://localhost:8080/halaman-tidak-ada-12345
```

#### **Test 403 Page:**
```
http://localhost:8080/test-error/403
```

#### **Test 500 Page:**
```
http://localhost:8080/test-error/500
```
Atau trigger error real:
```
http://localhost:8080/test-error/throw-error
```

---

## 🔍 Troubleshooting

### ❌ Problem: Error page tidak muncul, malah blank/404

**Solusi:**

1. **Pull perubahan terbaru:**
   ```bash
   git pull origin main
   ```

2. **Restart aplikasi:**
   - Stop aplikasi (Ctrl+C)
   - Jalankan ulang: `./mvnw spring-boot:run`

3. **Clear browser cache:**
   - Tekan `Ctrl + Shift + R` (Windows/Linux)
   - Atau `Cmd + Shift + R` (Mac)

---

### ❌ Problem: Muncul "Whitelabel Error Page"

**Solusi:**

Cek file `application.properties` harus ada:
```properties
server.error.whitelabel.enabled=false
```

Jika belum ada, tambahkan, lalu restart aplikasi.

---

### ❌ Problem: Error "Template might not exist"

**Solusi:**

1. Pastikan file error pages ada di:
   ```
   src/main/resources/templates/error/
   ├── 403.html
   ├── 404.html
   └── 500.html
   ```

2. Check di terminal/IDE Anda:
   ```bash
   ls src/main/resources/templates/error/
   ```

3. Jika tidak ada, pull dari git:
   ```bash
   git pull origin main
   ```

---

## 📸 Cara Test yang BENAR

### ✅ BENAR:
```
1. Buka browser
2. Akses: http://localhost:8080/test-error/404
3. Lihat halaman error 404 yang cantik
```

### ❌ SALAH:
```
1. Buka file: src/main/resources/templates/error/404.html
2. Langsung buka di browser (file:///...)
   → Ini tidak akan jalan karena butuh Thymeleaf processing!
```

---

## 🎯 Expected Results

Ketika akses test URLs, Anda harus melihat:

### 404 Page:
- ✅ Logo NusaTech di atas
- ✅ Angka "404" besar dengan gradient merah-gold
- ✅ Ilustrasi wajah sedih (SVG)
- ✅ Teks "Halaman Tidak Ditemukan"
- ✅ Button "Kembali ke Beranda" (merah)
- ✅ Button "Lihat Kursus" (outline)

### 403 Page:
- ✅ Logo NusaTech di atas
- ✅ Angka "403" besar dengan gradient merah-gold
- ✅ Ilustrasi gembok (SVG)
- ✅ Teks "Akses Ditolak"
- ✅ Button "Kembali ke Beranda" (merah)
- ✅ Button "Login" (outline)

### 500 Page:
- ✅ Logo NusaTech di atas
- ✅ Angka "500" besar dengan gradient merah-gold
- ✅ Ilustrasi gear berputar (ANIMATED!)
- ✅ Teks "Terjadi Kesalahan Server"
- ✅ Button "Kembali ke Beranda" (merah)
- ✅ Button "Coba Lagi" (outline)

---

## 📝 Quick Commands

```bash
# 1. Pull latest changes
git pull origin main

# 2. Restart aplikasi
./mvnw spring-boot:run

# 3. Test di browser (satu-satu):
# http://localhost:8080/test-error/404
# http://localhost:8080/test-error/403
# http://localhost:8080/test-error/500
```

---

## 🆘 Masih Tidak Muncul?

Screenshot dan kirim:
1. URL yang Anda akses
2. Output di terminal/console
3. Error di browser console (F12 → Console tab)

Atau cek log aplikasi:
```bash
# Lihat log saat akses error page
tail -f logs/application.log
```

---

## ✨ Bonus: Test Natural Errors

Setelah yakin error pages bekerja, test dengan cara natural:

1. **404 Natural:**
   - Akses URL random: `http://localhost:8080/asdfghjkl`
   
2. **403 Natural:**
   - Login sebagai student
   - Akses admin page: `http://localhost:8080/admin/dashboard`
   
3. **500 Natural:**
   - Stop MySQL
   - Akses home page
   - Error 500 akan muncul karena database connection failed

---

**Good luck!** 🚀
