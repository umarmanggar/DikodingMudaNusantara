# Error Pages Documentation

## 📄 Available Error Pages

Aplikasi ini memiliki 3 custom error pages yang sudah didesain dengan brand NusaTech:

### 1. **404 - Page Not Found**
- **File**: `src/main/resources/templates/error/404.html`
- **Kapan muncul**: URL tidak ditemukan
- **Actions**: 
  - Kembali ke Beranda
  - Lihat Kursus

### 2. **403 - Forbidden**
- **File**: `src/main/resources/templates/error/403.html`
- **Kapan muncul**: User tidak punya akses/izin
- **Actions**: 
  - Kembali ke Beranda
  - Login

### 3. **500 - Internal Server Error**
- **File**: `src/main/resources/templates/error/500.html`
- **Kapan muncul**: Error di server/aplikasi
- **Actions**: 
  - Kembali ke Beranda
  - Coba Lagi (Reload)
- **Special**: Animasi gear berputar

---

## 🎨 Design Features

Semua error pages menggunakan:
- ✅ **Brand Colors**: Merah Maroon (#8B1538) & Gold (#D4A84B)
- ✅ **Custom SVG Illustrations**: Unik untuk setiap error type
- ✅ **Responsive**: Mobile-friendly
- ✅ **Animations**: Gear animation di 500 page
- ✅ **Typography**: Plus Jakarta Sans font
- ✅ **User-Friendly**: Pesan dalam Bahasa Indonesia

---

## 🔧 How It Works

### Automatic Error Handling
Spring Boot secara otomatis akan:
1. Detect error status code (403, 404, 500, dll)
2. Cari template di `templates/error/{status-code}.html`
3. Render halaman error yang sesuai

### Custom Error Controller
File: `CustomErrorController.java`
```java
@Controller
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Menangani routing ke error pages yang sesuai
    }
}
```

---

## 🧪 Testing Error Pages

### Method 1: Test Endpoints (Development)
Gunakan test controller yang sudah dibuat:

```
http://localhost:8080/test-error/404  → Test 404 page
http://localhost:8080/test-error/403  → Test 403 page
http://localhost:8080/test-error/500  → Test 500 page
http://localhost:8080/test-error/throw-error  → Trigger real 500 error
```

### Method 2: Natural Errors
- **404**: Akses URL yang tidak ada
  ```
  http://localhost:8080/halaman-tidak-ada
  ```

- **403**: Login sebagai student, lalu akses admin page
  ```
  http://localhost:8080/admin/dashboard
  ```

- **500**: Trigger error di aplikasi (database down, null pointer, dll)

---

## 📝 Configuration

### application.properties
```properties
# Disable default white label error page
server.error.whitelabel.enabled=false

# Show error details (development only!)
# server.error.include-message=always
# server.error.include-stacktrace=always
```

### Security Configuration
Pastikan error pages accessible tanpa authentication:
```java
http.authorizeHttpRequests()
    .requestMatchers("/error", "/error/**").permitAll()
    .requestMatchers("/test-error/**").permitAll() // Remove in production
```

---

## 🚀 Production Checklist

Sebelum deploy ke production:

- [ ] **Hapus/Comment TestErrorController.java**
  ```java
  // File: TestErrorController.java - HAPUS atau COMMENT
  ```

- [ ] **Disable stack trace** di application.properties
  ```properties
  server.error.include-stacktrace=never
  server.error.include-message=never
  ```

- [ ] **Enable proper logging**
  ```properties
  logging.level.root=WARN
  logging.level.koding_muda_nusantara=INFO
  ```

- [ ] **Test semua error scenarios**
  - 404: URL tidak ada
  - 403: Akses tanpa permission
  - 500: Database down, runtime errors

---

## 🎨 Customization

### Ubah Warna
Edit CSS di setiap error HTML file:
```css
:root {
    --primary: #8B1538;      /* Merah Maroon */
    --primary-hover: #6d102c;
    --secondary: #D4A84B;    /* Gold */
    --bg-light: #fdf8f3;
}
```

### Ubah Pesan
Edit HTML content di setiap file:
```html
<h1 class="error-title">Judul Error</h1>
<p class="error-message">Pesan error Anda di sini</p>
```

### Tambah Error Page Baru
Contoh untuk 401 (Unauthorized):
1. Buat file `templates/error/401.html`
2. Copy struktur dari 403.html
3. Ubah konten sesuai kebutuhan
4. Update CustomErrorController jika perlu

---

## 🐛 Troubleshooting

### Error page tidak muncul?
1. **Check file location**: Harus di `templates/error/`
2. **Check file name**: Harus sesuai status code (404.html, 403.html, 500.html)
3. **Restart aplikasi**: `./mvnw spring-boot:run`
4. **Clear browser cache**: Ctrl+Shift+R

### Masih muncul white label error?
1. Check `application.properties`:
   ```properties
   server.error.whitelabel.enabled=false
   ```
2. Pastikan CustomErrorController aktif (@Controller annotation)
3. Check logs untuk error details

### Error page blank?
1. Check browser console untuk JS/CSS errors
2. Pastikan Bootstrap JS/CSS loaded:
   ```html
   <link th:href="@{/css/bootstrap.min.css}" rel="stylesheet">
   <script th:src="@{/js/bootstrap.bundle.min.js}"></script>
   ```

---

## 📚 References

- [Spring Boot Error Handling](https://docs.spring.io/spring-boot/docs/current/reference/html/web.html#web.servlet.spring-mvc.error-handling)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)
- [HTTP Status Codes](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)
