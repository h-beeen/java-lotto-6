package lotto.exception;

import lotto.domain.constants.LottoConstraint;
import lotto.domain.parser.constants.InputConstraint;

import static java.lang.String.format;

public enum ErrorMessage {
    /**
     * Client Input Error
     */
    REQUEST_NOT_INTEGER("정수가 입력되어야 합니다."),

    NOT_ENOUGH_PAYMENT(
            format(
                    "구입 비용은 %d원 이상으로 입력되어야 합니다.",
                    LottoConstraint.UNIT_PRICE.getValue())
    ),
    NUMBER_OUT_OF_RANGE(
            format(
                    "번호는 %d에서 %d사이의 숫자여야 합니다.",
                    LottoConstraint.NUMBER_LOWER_BOUND.getValue(),
                    LottoConstraint.NUMBER_UPPER_BOUND.getValue())
    ),
    PAYMENT_NOT_DIVISIBLE_BY_UNIT_PRICE(
            format(
                    "구입 비용은 %d원 단위로 입력되어야 합니다.",
                    LottoConstraint.UNIT_PRICE.getValue())
    ),
    ENDS_WITH_DELIMITER(
            format(
                    "구분자(%s)로 끝나도록 입력할 수 없습니다.",
                    InputConstraint.DELIMITER.getValue())
    ),
    EMPTY_REQUEST("값을 정상적으로 입력해주세요"),


    /**
     * Internal Server Error
     */
    NUMBER_COUNT_INVALID("시스템이 뽑은 로또의 갯수는 전역 설정과 다릅니다."),
    NUMBER_DUPLICATED("자동으로 뽑은 로또 중 중복된 숫자가 있습니다.");


    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
