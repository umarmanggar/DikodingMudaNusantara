package koding_muda_nusantara.koding_muda_belajar.config;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Custom Error Controller untuk menangani error pages
 * Menangani error 403, 404, dan 500 dengan halaman custom
 */
@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            
            switch (statusCode) {
                case 403:
                    return "error/403"; // Forbidden - Akses Ditolak
                case 404:
                    return "error/404"; // Not Found - Halaman Tidak Ditemukan
                case 500:
                    return "error/500"; // Internal Server Error
                default:
                    return "error/500"; // Default ke 500 untuk error lainnya
            }
        }
        
        // Default fallback
        return "error/500";
    }
}
