package ru.farmersregister.farmersregister.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.farmersregister.farmersregister.loger.FormLogInfo;

/**
 * Исключение
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MoveToAchive extends RuntimeException {

  public MoveToAchive() {
  }

  public MoveToAchive(String message) {
    super("Exception: " + message + FormLogInfo.getInfo());
    System.err.println("Exception: " + message + FormLogInfo.getException());
  }
}
