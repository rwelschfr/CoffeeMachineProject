import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
            Arguments.of(new BeverageOrder(BeverageType.TEA, 0, 0.4), "T::"),
            Arguments.of(new BeverageOrder(BeverageType.COFFEE, 1, 0.9), "C:1:0"),
            Arguments.of(new BeverageOrder(BeverageType.CHOCOLATE, 2, 0.8), "H:2:0")
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
            Arguments.of(new BeverageOrder(BeverageType.COFFEE, 1, 0.5), "M:Please insert 0.1 euros"),
            Arguments.of(new BeverageOrder(BeverageType.CHOCOLATE, 2, 0.3), "M:Please insert 0.2 euros")
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
}
