package koding_muda_nusantara.koding_muda_belajar.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Test Controller untuk testing error pages
 * Hapus atau comment setelah testing selesai
 * 
 * Usage:
 * - http://localhost:8080/test-error/404
 * - http://localhost:8080/test-error/403
 * - http://localhost:8080/test-error/500
 */
@Controller
@RequestMapping("/test-error")
public class TestErrorController {

    @GetMapping("/404")
    public String test404(HttpServletRequest request) {
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.NOT_FOUND.value());
        return "error/404";
    }

    @GetMapping("/403")
    public String test403(HttpServletRequest request) {
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.FORBIDDEN.value());
        return "error/403";
    }

    @GetMapping("/500")
    public String test500(HttpServletRequest request) {
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "error/500";
    }
    
    @GetMapping("/throw-error")
    public String throwError() {
        // Ini akan trigger real error 500
        throw new RuntimeException("Test error 500 - This is intentional for testing");
    }
}
