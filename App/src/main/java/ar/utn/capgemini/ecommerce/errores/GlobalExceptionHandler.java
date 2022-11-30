package ar.utn.capgemini.ecommerce.errores;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String objetoMalArmado(IllegalArgumentException ex){
        return ex.getLocalizedMessage();
    }
}
