package ru.farmersregister.farmersregister.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.farmersregister.farmersregister.loger.FormLogInfo;

/**
 * Исключение
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElementNotFound extends RuntimeException {

    public ElementNotFound() {
    }

    public ElementNotFound(String message) {
        super("Exception: " + message + FormLogInfo.getInfo());
        System.err.println("Exception: " + message + FormLogInfo.getException());
    }
}
