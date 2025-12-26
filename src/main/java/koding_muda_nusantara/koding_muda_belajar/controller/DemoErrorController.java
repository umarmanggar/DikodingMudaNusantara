package koding_muda_nusantara.koding_muda_belajar.controller;

import jakarta.servlet.http.HttpServletRequest;
import koding_muda_nusantara.koding_muda_belajar.exception.ResourceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Demo Controller untuk testing berbagai error scenarios
 * 
 * CATATAN: Hapus atau comment controller ini setelah testing selesai
 * 
 * Test URLs:
 * 1. 404 Natural: http://localhost:8080/demo-error/not-found
 * 2. 404 Custom: http://localhost:8080/demo-error/course/999999
 * 3. 500 Error: http://localhost:8080/demo-error/server-error
 * 4. 403 Forbidden: http://localhost:8080/demo-error/admin-only (tanpa login)
 * 
 * TEMPORARY DISABLED
 */
//@Controller
//@RequestMapping("/demo-error")
public class DemoErrorController {

    /**
     * Demo: Resource Not Found (404)
     * Contoh: Cari course dengan ID yang tidak ada
     */
    @GetMapping("/course/{id}")
    public String courseNotFound(@PathVariable Long id) {
        // Simulasi pencarian course yang tidak ada
        throw new ResourceNotFoundException("Course dengan ID " + id + " tidak ditemukan");
    }

    /**
     * Demo: Server Error (500)
     * Trigger exception
     */
    @GetMapping("/server-error")
    public String serverError() {
        // Simulasi error server
        throw new RuntimeException("Simulasi Server Error - Database connection timeout");
    }

    /**
     * Demo: Null Pointer Exception (500)
     * Error yang sering terjadi
     */
    @GetMapping("/null-pointer")
    public String nullPointerError() {
        String str = null;
        // Ini akan throw NullPointerException
        return "result: " + str.length();
    }

    /**
     * Demo: Division by Zero (500)
     */
    @GetMapping("/division-zero")
    public String divisionZero() {
        int result = 100 / 0; // ArithmeticException
        return "result: " + result;
    }

    /**
     * Demo: Access Denied (403)
     * Simulasi akses halaman yang butuh permission khusus
     * 
     * CATATAN: Ini hanya contoh. Untuk bekerja, butuh Spring Security configuration
     * yang proper. Saat ini akan return view biasa karena security di-disable.
     */
    @GetMapping("/admin-only")
    public String adminOnly() {
        // Dalam real scenario dengan security, ini akan throw AccessDeniedException
        // untuk user yang bukan admin
        return "admin/dashboard";
    }

    /**
     * Info page untuk demo
     */
    @GetMapping("/info")
    public String info() {
        return "demo-error-info";
    }
}
