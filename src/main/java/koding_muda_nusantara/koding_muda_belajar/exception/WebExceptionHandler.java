package koding_muda_nusantara.koding_muda_belajar.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Web Exception Handler untuk menangani exception di web pages (bukan API)
 * Menampilkan error pages yang sudah di-design
 * 
 * TEMPORARY DISABLED - Uncomment jika sudah yakin MySQL connection OK
 */
//@ControllerAdvice(basePackages = "koding_muda_nusantara.koding_muda_belajar.controller")
public class WebExceptionHandler {

    /**
     * Handle 404 - Not Found
     * Muncul ketika URL tidak ditemukan
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNotFound(HttpServletRequest request, NoHandlerFoundException ex) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/404");
        mav.addObject("url", request.getRequestURL());
        mav.addObject("message", "Halaman yang Anda cari tidak ditemukan");
        return mav;
    }

    /**
     * Handle 403 - Forbidden/Access Denied
     * Muncul ketika user tidak punya akses
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDenied(HttpServletRequest request, AccessDeniedException ex) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/403");
        mav.addObject("url", request.getRequestURL());
        mav.addObject("message", "Anda tidak memiliki izin untuk mengakses halaman ini");
        return mav;
    }

    /**
     * Handle ResourceNotFoundException (custom)
     * Untuk resource yang tidak ditemukan (course, user, dll)
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleResourceNotFound(HttpServletRequest request, ResourceNotFoundException ex) {
        // Cek apakah ini request API atau Web
        String accept = request.getHeader("Accept");
        
        // Jika request untuk JSON (API), biarkan GlobalExceptionHandler yang handle
        if (accept != null && accept.contains("application/json")) {
            throw ex; // Re-throw untuk GlobalExceptionHandler
        }
        
        // Untuk web request, tampilkan error page
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/404");
        mav.addObject("url", request.getRequestURL());
        mav.addObject("message", ex.getMessage());
        return mav;
    }

    /**
     * Handle 500 - Internal Server Error
     * Catch-all untuk semua exception lain yang tidak ter-handle
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGeneralException(HttpServletRequest request, Exception ex) {
        // Cek apakah ini request API atau Web
        String accept = request.getHeader("Accept");
        
        // Jika request untuk JSON (API), biarkan GlobalExceptionHandler yang handle
        if (accept != null && accept.contains("application/json")) {
            throw new RuntimeException(ex); // Re-throw untuk GlobalExceptionHandler
        }
        
        // Log error untuk debugging
        System.err.println("=== ERROR 500 ===");
        System.err.println("URL: " + request.getRequestURL());
        System.err.println("Error: " + ex.getMessage());
        ex.printStackTrace();
        System.err.println("==================");
        
        // Untuk web request, tampilkan error page
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error/500");
        mav.addObject("url", request.getRequestURL());
        mav.addObject("message", "Terjadi kesalahan pada server. Tim kami sedang memperbaikinya.");
        return mav;
    }
}
