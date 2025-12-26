package koding_muda_nusantara.koding_muda_belajar.config;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;

// Disable this controller to avoid ambiguous mapping with Spring Boot's BasicErrorController.
// Spring Boot will render templates placed under `templates/error/*` automatically.
public class CustomErrorController {

    // If you need custom error handling, either implement ErrorController properly
    // or register an ErrorViewResolver. For now, keep this helper method unused.
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == 404) {
                return "error/404";
            } else if (statusCode == 403) {
                return "error/403";
            } else if (statusCode == 500) {
                return "error/500";
            }
        }
        return "error/500";
    }
}
