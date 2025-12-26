# 🧪 Testing Error Pages - Complete Guide

## ✅ Apa yang Sudah Diperbaiki?

Sekarang **SEMUA ERROR** otomatis menampilkan error page yang cantik:
- ✅ 404 - Halaman tidak ditemukan
- ✅ 403 - Akses ditolak
- ✅ 500 - Server error

---

## 🚀 Cara Testing

### **Step 1: Pull & Restart**

```bash
# Pull perubahan terbaru
git pull origin main

# Restart aplikasi
./mvnw spring-boot:run
```

### **Step 2: Test Error Pages**

Pilih salah satu cara testing:

---

## 📋 Method A: Test dengan Demo URLs (Recommended)

### **1. Test 404 - Not Found**

#### Cara 1: URL yang tidak ada
```
http://localhost:8080/halaman-tidak-ada-12345
```
**Expected:** Error page 404 dengan design NusaTech

#### Cara 2: Resource tidak ditemukan
```
http://localhost:8080/demo-error/course/999999
```
**Expected:** Error page 404 dengan pesan "Course dengan ID 999999 tidak ditemukan"

---

### **2. Test 500 - Server Error**

#### Cara 1: Simulasi server error
```
http://localhost:8080/demo-error/server-error
```
**Expected:** Error page 500 dengan animated gears

#### Cara 2: Null pointer exception
```
http://localhost:8080/demo-error/null-pointer
```
**Expected:** Error page 500

#### Cara 3: Division by zero
```
http://localhost:8080/demo-error/division-zero
```
**Expected:** Error page 500

---

### **3. Test 403 - Forbidden**

**Cara 1: Akses admin tanpa permission (jika security enabled)**
```
http://localhost:8080/admin/dashboard
```
Jika tidak login sebagai admin → Error page 403

**Note:** Karena security di-disable untuk development, 403 mungkin tidak muncul.
Akan muncul di production dengan proper security.

---

## 📋 Method B: Test dengan Test URLs

URL khusus untuk testing (lebih direct):

```
✅ Test 404: http://localhost:8080/test-error/404
✅ Test 403: http://localhost:8080/test-error/403
✅ Test 500: http://localhost:8080/test-error/500
```

---

## 🎯 Expected Results

Ketika error terjadi, Anda akan melihat:

### **404 Page:**
```
┌─────────────────────────────┐
│      [Logo NusaTech]        │
│                             │
│      [Sad Face Icon]        │
│                             │
│          404                │ ← Merah-Gold Gradient
│   Halaman Tidak Ditemukan   │
│                             │
│  Maaf, halaman yang Anda    │
│  cari tidak ada atau telah  │
│  dipindahkan...             │
│                             │
│  [Kembali ke Beranda]       │ ← Button Merah
│  [Lihat Kursus]             │ ← Button Outline
└─────────────────────────────┘
```

### **403 Page:**
```
┌─────────────────────────────┐
│      [Logo NusaTech]        │
│                             │
│      [Lock Icon]            │
│                             │
│          403                │ ← Merah-Gold Gradient
│      Akses Ditolak          │
│                             │
│  Anda tidak memiliki izin   │
│  untuk mengakses halaman    │
│  ini...                     │
│                             │
│  [Kembali ke Beranda]       │ ← Button Merah
│  [Login]                    │ ← Button Outline
└─────────────────────────────┘
```

### **500 Page:**
```
┌─────────────────────────────┐
│      [Logo NusaTech]        │
│                             │
│    [Animated Gears] ⚙️      │ ← BERPUTAR!
│                             │
│          500                │ ← Merah-Gold Gradient
│  Terjadi Kesalahan Server   │
│                             │
│  Maaf, terjadi kesalahan    │
│  pada server kami...        │
│                             │
│  [Kembali ke Beranda]       │ ← Button Merah
│  [Coba Lagi]                │ ← Button Outline
└─────────────────────────────┘
```

---

## ✨ Fitur Error Pages

- 🎨 **Brand Consistent**: Warna Merah Maroon + Gold NusaTech
- 📱 **Responsive**: Mobile friendly
- ✨ **Animated**: Gear berputar di 500 page
- 🖼️ **Custom SVG Icons**: Unique untuk setiap error
- 🇮🇩 **Bahasa Indonesia**: User-friendly messages
- 🔗 **Helpful Actions**: Button untuk navigation

---

## 🔧 Technical Details

### File Structure:
```
src/main/java/.../exception/
├── WebExceptionHandler.java       ← Handle web page errors
└── GlobalExceptionHandler.java    ← Handle API errors (JSON)

src/main/java/.../config/
└── CustomErrorController.java     ← Route error to pages

src/main/resources/templates/error/
├── 403.html
├── 404.html
└── 500.html

src/main/java/.../controller/
├── TestErrorController.java       ← Test URLs
└── DemoErrorController.java       ← Demo scenarios
```

### Configuration:
```properties
# application.properties
server.error.whitelabel.enabled=false
spring.mvc.throw-exception-if-no-handler-found=true
```

---

## 📸 Screenshot Checklist

Untuk memastikan semuanya bekerja, test dan screenshot:

- [ ] 404 page dengan URL tidak ada
- [ ] 404 page dengan resource not found
- [ ] 500 page dengan server error
- [ ] 500 page dengan null pointer
- [ ] Logo NusaTech muncul di semua page
- [ ] Gear berputar di 500 page
- [ ] Button "Kembali ke Beranda" berfungsi
- [ ] Responsive di mobile (resize browser)

---

## 🐛 Troubleshooting

### ❌ Masih muncul Whitelabel Error Page

**Solusi:**
1. Pastikan sudah pull latest: `git pull origin main`
2. Restart aplikasi
3. Clear browser cache: `Ctrl + Shift + R`
4. Check application.properties ada:
   ```properties
   server.error.whitelabel.enabled=false
   ```

### ❌ Error page tidak muncul, malah blank

**Solusi:**
1. Open browser console (F12)
2. Check errors di Console tab
3. Check Network tab - lihat status code
4. Pastikan file error HTML ada di `templates/error/`

### ❌ CSS/styling tidak muncul

**Solusi:**
1. Check bootstrap.min.css ada di `static/css/`
2. Check browser console untuk 404 errors
3. Hard refresh: `Ctrl + Shift + R`

### ❌ 404 tidak trigger

**Solusi:**
Pastikan di application.properties ada:
```properties
spring.mvc.throw-exception-if-no-handler-found=true
```

---

## 🎬 Demo Scenarios

### Natural Error Testing:

1. **404 Natural:**
   - Ketik URL random: `http://localhost:8080/asdqwe123`
   
2. **500 Natural:**
   - Stop MySQL
   - Akses home page
   - Error 500 akan muncul
   
3. **403 Natural (with Security):**
   - Login sebagai student
   - Akses: `http://localhost:8080/admin/dashboard`
   - Akan redirect atau show 403

---

## 🗑️ Cleanup After Testing

Setelah testing selesai dan yakin error pages bekerja:

### Option 1: Comment controllers (Recommended)
```java
// File: TestErrorController.java
// File: DemoErrorController.java
// Tambah "//" di depan @Controller
```

### Option 2: Delete controllers
```bash
rm src/main/java/.../controller/TestErrorController.java
rm src/main/java/.../controller/DemoErrorController.java
```

⚠️ **JANGAN HAPUS:**
- WebExceptionHandler.java
- CustomErrorController.java
- File error HTML (403.html, 404.html, 500.html)

---

## 📚 Additional Resources

- Error page HTML: `templates/error/`
- Documentation: `README-ERROR-PAGES.md`
- Database setup: `README-DATABASE.md`

---

## ✅ Production Checklist

Before deploying:

- [ ] Remove/comment TestErrorController
- [ ] Remove/comment DemoErrorController
- [ ] Set proper logging level
- [ ] Test all error scenarios
- [ ] Disable stack trace in production:
  ```properties
  server.error.include-stacktrace=never
  ```

---

**Selamat Testing!** 🎉

Jika ada masalah, check file log atau console output.
