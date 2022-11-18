package clone.project.kream.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
     /*
    400 Bad Request
     */

    INVALID_USERNAME(HttpStatus.BAD_REQUEST, "아이디는 2자 이상 20자 이하여야 합니다"),
    FRIEND_IS_ALREADY_BE_REGISTER(HttpStatus.BAD_REQUEST,"이미 등록되어있습니다."),
    CAN_NOT_REGISTER(HttpStatus.BAD_REQUEST,"자신은 등록할 수 없습니다."),

    /*
   401 UNAUTHORIZED : 인증되지 않은 사용자
   */
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST,"아이디를 확인해주세요."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 유효하지 않습니다");
    private final HttpStatus httpStatus;
    private final String errorMessage;

    ErrorCode(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
