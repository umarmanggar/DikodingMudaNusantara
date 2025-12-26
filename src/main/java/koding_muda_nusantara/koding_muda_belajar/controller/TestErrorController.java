package koding_muda_nusantara.koding_muda_belajar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Test Controller untuk testing error pages
 * Hapus atau comment setelah testing selesai
 */
@Controller
@RequestMapping("/test-error")
public class TestErrorController {

    @GetMapping("/404")
    public String test404() {
        return "error/404";
    }

    @GetMapping("/403")
    public String test403() {
        return "error/403";
    }

    @GetMapping("/500")
    public String test500() {
        return "error/500";
    }
    
    @GetMapping("/throw-error")
    public String throwError() {
        // Ini akan trigger error 500
        throw new RuntimeException("Test error 500");
    }
}
