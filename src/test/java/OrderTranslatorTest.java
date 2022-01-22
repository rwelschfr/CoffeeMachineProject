import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class OrderTranslatorTest {

    @InjectMocks
    private OrderTranslator orderTranslator;

    @Mock
    private DrinkMaker drinkMaker;

    @ParameterizedTest
    @MethodSource("translateOrderSuccessUseCases")
    public void testTranslateOrderSuccess(BeverageOrder input, String expectedCommand) {
        boolean result = orderTranslator.translateOrder(input);
        Mockito.verify(drinkMaker).makeDrinks(expectedCommand);
        assertTrue(result);
    }

    private static Stream<Arguments> translateOrderSuccessUseCases() {
        return Stream.of(
            Arguments.of(new BeverageOrder(BeverageType.TEA, 0, new BigDecimal("0.4")), "T::"),
            Arguments.of(new BeverageOrder(BeverageType.COFFEE, 1, new BigDecimal("0.9")), "C:1:0"),
            Arguments.of(new BeverageOrder(BeverageType.CHOCOLATE, 2, new BigDecimal("0.8")), "H:2:0"),
            Arguments.of(new BeverageOrder(BeverageType.ORANGE_JUICE, 0, new BigDecimal("0.8")), "O::"),
            Arguments.of(new BeverageOrder(BeverageType.TEA_EXTRA_HOT, 0, new BigDecimal("0.5")), "Th::"),
            Arguments.of(new BeverageOrder(BeverageType.COFFEE_EXTRA_HOT, 2, new BigDecimal("0.9")), "Ch:2:0"),
            Arguments.of(new BeverageOrder(BeverageType.CHOCOLATE_EXTRA_HOT, 1, new BigDecimal("0.8")), "Hh:1:0")
        );
    }

    @ParameterizedTest
    @MethodSource("translateOrderNotEnoughMoneyUseCases")
    public void testTranslateOrderNotEnoughMoney(BeverageOrder input, String expectedCommand) {
        boolean result = orderTranslator.translateOrder(input);
        Mockito.verify(drinkMaker).makeDrinks(expectedCommand);
        assertFalse(result);
    }

    private static Stream<Arguments> translateOrderNotEnoughMoneyUseCases() {
        return Stream.of(
            Arguments.of(new BeverageOrder(BeverageType.COFFEE, 1, new BigDecimal("0.5")), "M:Please insert 0.1 euros"),
            Arguments.of(new BeverageOrder(BeverageType.CHOCOLATE, 2, new BigDecimal("0.3")), "M:Please insert 0.2 euros"),
            Arguments.of(new BeverageOrder(BeverageType.ORANGE_JUICE, 0, new BigDecimal("0.2")), "M:Please insert 0.4 euros"),
            Arguments.of(new BeverageOrder(BeverageType.CHOCOLATE_EXTRA_HOT, 2, new BigDecimal("0.3")), "M:Please insert 0.2 euros")
        );
    }

    @ParameterizedTest
    @MethodSource("sendMessageUseCases")
    public void testSendMessage(String input, String expectedCommand) {
        boolean result = orderTranslator.sendMessage(input);
        Mockito.verify(drinkMaker).makeDrinks(expectedCommand);
        assertTrue(result);
    }

    private static Stream<Arguments> sendMessageUseCases() {
        return Stream.of(
            Arguments.of("Test message 1", "M:Test message 1"),
            Arguments.of("Test message 2", "M:Test message 2")
        );
    }

    @Test
    public void testEmptyStatistics() {
        DrinkMakerStatistics drinkMakerStatistics = orderTranslator.getDrinkMakerStatistics();
        assertEquals(drinkMakerStatistics.getMoneyEarned(), BigDecimal.ZERO);
        for(Map.Entry<BeverageType, Integer> entry : drinkMakerStatistics.getSalesMap().entrySet()) {
            assertEquals(entry.getValue(), 0);
        }
    }

    @Test
    public void testFullStatistics() {
        orderTranslator.translateOrder(new BeverageOrder(BeverageType.TEA, 0, new BigDecimal("0.4")));
        orderTranslator.translateOrder(new BeverageOrder(BeverageType.COFFEE, 1, new BigDecimal("0.9")));
        orderTranslator.translateOrder(new BeverageOrder(BeverageType.CHOCOLATE, 2, new BigDecimal("0.8")));
        orderTranslator.translateOrder(new BeverageOrder(BeverageType.ORANGE_JUICE, 0, new BigDecimal("0.8")));
        orderTranslator.translateOrder(new BeverageOrder(BeverageType.TEA_EXTRA_HOT, 0, new BigDecimal("0.5")));
        orderTranslator.translateOrder(new BeverageOrder(BeverageType.COFFEE_EXTRA_HOT, 2, new BigDecimal("0.9")));
        orderTranslator.translateOrder(new BeverageOrder(BeverageType.CHOCOLATE_EXTRA_HOT, 1, new BigDecimal("0.8")));
        orderTranslator.translateOrder(new BeverageOrder(BeverageType.TEA, 0, new BigDecimal("0.6")));

        DrinkMakerStatistics drinkMakerStatistics = orderTranslator.getDrinkMakerStatistics();
        assertEquals(drinkMakerStatistics.getMoneyEarned(), new BigDecimal("4.0"));
        Map<BeverageType, Integer> salesMap = drinkMakerStatistics.getSalesMap();
        assertEquals(salesMap.get(BeverageType.TEA), 2);
        assertEquals(salesMap.get(BeverageType.COFFEE), 1);
        assertEquals(salesMap.get(BeverageType.CHOCOLATE), 1);
        assertEquals(salesMap.get(BeverageType.ORANGE_JUICE), 1);
        assertEquals(salesMap.get(BeverageType.TEA_EXTRA_HOT), 1);
        assertEquals(salesMap.get(BeverageType.COFFEE_EXTRA_HOT), 1);
        assertEquals(salesMap.get(BeverageType.CHOCOLATE_EXTRA_HOT), 1);
    }
}
