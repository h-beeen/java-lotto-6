package lotto.view;

import lotto.domain.prize.constants.PrizeGrade;
import lotto.dto.FinalResultResponse;
import lotto.dto.LottoNumberResponse;
import lotto.dto.LottoNumberResponses;
import lotto.view.constants.PrintMessage;
import lotto.view.constants.PrintablePrizeType;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Objects;

import static lotto.view.constants.PrintFormat.SEPARATOR_FORMAT;
import static lotto.view.constants.PrintMessage.*;

public class OutputView {
    public static void printMessage(final PrintMessage message) {
        println(message.getMessage());
    }

    public static void println(final Object data) {
        System.out.println(data);
    }

    public static void printNewLine() {
        System.out.println();
    }

    public static void printPurchaseLottoNumbers(final LottoNumberResponses responses) {
        responses.lottoResponses()
                .stream()
                .map(LottoNumberResponse::numbers)
                .forEach(OutputView::println);

        printNewLine();
    }

    public static void printPurchaseCount(final LottoNumberResponses responses) {
        String formattedMessage = String.format(RESPONSE_PURCHASE_COUNT.getMessage(), responses.purchaseCount());
        println(formattedMessage);
    }

    public static void printFinalResult(final FinalResultResponse response) {
        printNewLine();
        printMessage(RESPONSE_PRIZE_STATISTICS);
        printMessage(RESPONSE_SEPARATOR);

        String formattedYieldMessage = String.format(
                RESPONSE_YIELD.getMessage(), response.getFormattedYield());
        printMatcingResult(response);
        println(formattedYieldMessage);
    }

    private static void printMatcingResult(FinalResultResponse response) {
        EnumMap<PrizeGrade, Integer> prizeGradeIntegerEnumMap = response.prizeResultCount();
        PrintablePrizeType[] printablePrizeTypes = PrintablePrizeType.values();

        Arrays.stream(printablePrizeTypes)
                .forEach(type -> printEachMatchingResult(type, prizeGradeIntegerEnumMap));
    }

    private static void printEachMatchingResult(
            PrintablePrizeType type,
            EnumMap<PrizeGrade, Integer> prizeGradeIntegerEnumMap
    ) {
        PrizeGrade grade = type.getGrade();

        DecimalFormat seperatedFormat = SEPARATOR_FORMAT.getFormat();

        String format = seperatedFormat.format(grade.getPrizeAmount());
        Integer integer = prizeGradeIntegerEnumMap.get(type.getGrade());

        if (Objects.isNull(integer)) {
            integer = 0;
        }

        String format1 = String.format(type.getFormat(), grade.getPrizeMatchingCount(), format, integer);
        System.out.println(format1);
    }
}
