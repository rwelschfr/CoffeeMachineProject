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
            Arguments.of(new BeverageOrder(BeverageOrder.BeverageType.TEA, 0, false), "T::"),
            Arguments.of(new BeverageOrder(BeverageOrder.BeverageType.COFFEE, 1, true), "C:1:1"),
            Arguments.of(new BeverageOrder(BeverageOrder.BeverageType.CHOCOLATE, 2, false), "H:2:0")
        );
    }
}
