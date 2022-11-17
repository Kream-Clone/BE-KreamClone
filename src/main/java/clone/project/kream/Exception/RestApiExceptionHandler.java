package clone.project.kream.Exception;

import clone.project.kream.service.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

@RestController
@ControllerAdvice // 모든 예외처리를 설정하기 위해
public class RestApiExceptionHandler {

//    @ExceptionHandler(CustomException.class)
//    public ResponseDto<?> validationException(CustomException e){
//        return ResponseDto.fail(e.getErrorCode().getHttpStatus().name(),e.getErrorCode().getErrorMessage(),e.getErrorCode().getHttpStatus());

    /*
    강의코드
     */
//        @ExceptionHandler(CustomException.class)
//        public ErrorCode validationException(CustomException e){
//            return e.getErrorMap();
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ResponseDto<?>> handleApiRequestException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(ResponseDto.fail("BAD_REQUEST", Objects.requireNonNull(ex.getFieldError()).getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ResponseDto<?>> handleApiRequestException(ConstraintViolationException ex) {
        return new ResponseEntity<>(ResponseDto.fail("BAD_REQUEST", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<ResponseDto<?>> handleApiRequestException(CustomException ex) {
        return new ResponseEntity<>(ResponseDto.fail(ex.getErrorCode().getHttpStatus().name(), ex.getErrorCode().getErrorMessage()), ex.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ResponseDto<?>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.fail("BAD_REQUEST", ex.getMessage()));
    }
}
