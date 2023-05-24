package jmp.amarchuk.web.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Custom HandlerException implementation, which in case of controller exception
 * just send a simple text response to the client with a brief description of the error.
 *
 * @author Anastasiya Marchuk
 *
 */

@ControllerAdvice
public class HandlerException extends Throwable {

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String exIndexOfBounds(Model model, IndexOutOfBoundsException ex) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String anyException(Model model, Exception ex) {
        model.addAttribute("message", ex.getMessage());
        return "error";
    }
}
