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
    @MethodSource("translateOrderUseCases")
    public void testTranslateOrder(BeverageOrder input, String expectedCommand) {
        boolean result = orderTranslator.translateOrder(input);
        Mockito.verify(drinkMaker).makeDrinks(expectedCommand);
        assertTrue(result);
    }

    private static Stream<Arguments> translateOrderUseCases() {
        return Stream.of(
            Arguments.of(new BeverageOrder(BeverageOrder.BeverageType.TEA, 0), "T::"),
            Arguments.of(new BeverageOrder(BeverageOrder.BeverageType.COFFEE, 1), "C:1:0"),
            Arguments.of(new BeverageOrder(BeverageOrder.BeverageType.CHOCOLATE, 2), "H:2:0")
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
