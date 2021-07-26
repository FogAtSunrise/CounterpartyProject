package ru.vereshchagina.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.vereshchagina.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс обработки исключений
 */
@ControllerAdvice
public class CounterpartyExceptionHandler {

    /**
     * Метод для обработки исключения, когда контрагент не найден в БД
     *
     * @param exception вызванное исключение
     * @return страницу http со статусом NOT_FOUND и сообщением исключения
     */

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFound(ResourceNotFoundException exception) {
        return new ResponseEntity<>("ResourceNotFoundException: " + exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Метод обработки исключений, не описанных в классе
     *
     * @param exception вызванное исключение
     * @return страницу http со статусом INTERNAL_SERVER_ERROR и сообщением исключения
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        return new ResponseEntity<>("Caught exception: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Метод обрабоки исключения, когда аргумент не прошел валидацию
     *
     * @param ex вызванное исключение
     * @return страницу http со статусом BAD_REQUEST и сообщением исключения
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationError(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            List<String> message = new ArrayList<>();
            for (FieldError e : errors) {
                message.add("@" + e.getField().toUpperCase() + ":" + e.getDefaultMessage());
            }
            return new ResponseEntity<>("Caught exception: " + message, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
