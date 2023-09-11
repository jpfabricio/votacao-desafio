package br.com.brq.votacao.controller.handler;

import br.com.brq.votacao.controller.dto.ErroDTO;
import br.com.brq.votacao.exception.BadGateway;
import br.com.brq.votacao.exception.BadRequest;
import br.com.brq.votacao.util.CodigosErros;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

import static java.text.MessageFormat.format;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class RouteExceptionHandler {

    @ResponseBody
    @ExceptionHandler(BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    BadRequest badRequest(BadRequest ex) {
        return ex;
    }

    @ResponseBody
    @ExceptionHandler(BadGateway.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    BadGateway feignException(BadGateway ex) {
        return ex;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDTO> handleMethodArgumentNotValidExceptions(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        log.error(e.getMessage());
        return ResponseEntity
                .status(status)
                .body(ErroDTO
                        .builder()
                        .status(status.value())
                        .error("Method argument not valid")
                        .message(e.getBindingResult()
                                .getAllErrors()
                                .stream()
                                .map(this::formatErrorsMethodArguments)
                                .sorted()
                                .collect(Collectors.toList()))
                        .path(request.getRequestURI())
                        .code(CodigosErros.ERRO_DE_VALIDACAO_DO_DTO)
                        .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErroDTO> handleConstraintViolationExceptions(ConstraintViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        log.error(e.getMessage());
        return ResponseEntity
                .status(status)
                .body(ErroDTO
                        .builder()
                        .status(status.value())
                        .error("Constraint violation")
                        .message(e.getConstraintViolations()
                                .stream()
                                .map(RouteExceptionHandler::formatErrorsConstraints)
                                .sorted()
                                .collect(Collectors.toList()))
                        .path(request.getRequestURI())
                        .code(CodigosErros.ERRO_DE_VALIDACAO_DO_DTO)
                        .build());
    }

    private static String formatErrorsConstraints(ConstraintViolation<?> constraintViolation) {
        return format("{0}: {1}", constraintViolation.getPropertyPath(), constraintViolation.getMessage());
    }

    public String formatErrorsMethodArguments(ObjectError objectError){
        return format("{0}: {1}", ((FieldError) objectError).getField(), (objectError).getDefaultMessage());
    }
}
