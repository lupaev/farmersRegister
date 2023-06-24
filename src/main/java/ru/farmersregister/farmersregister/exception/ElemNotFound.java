package ru.farmersregister.farmersregister.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.farmersregister.farmersregister.loger.FormLogInfo;

/**
 * Исключение
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElemNotFound extends RuntimeException {

  public ElemNotFound() {
  }

  public ElemNotFound(String message) {
    super("Exception: " + message + FormLogInfo.getInfo());
    System.err.println("Exception: " + message + FormLogInfo.getException());
  }
}
