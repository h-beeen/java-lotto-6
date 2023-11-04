package lotto.exception;

import lotto.view.OutputView;

public class LottoException extends IllegalArgumentException {
    private LottoException(
            ErrorMessage errorMessage,
            Exception exception
    ) {
        super(errorMessage.getMessage(), exception);
        OutputView.println(errorMessage.getMessage());
    }

    private LottoException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
        OutputView.println(errorMessage.getMessage());
    }

    public static LottoException of(
            ErrorMessage errorMessage,
            Exception exception
    ) {
        return new LottoException(errorMessage, exception);
    }

    public static LottoException from(
            ErrorMessage errorMessage
    ) {
        return new LottoException(errorMessage);
    }
}
