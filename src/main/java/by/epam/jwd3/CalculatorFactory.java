package by.epam.jwd3;

public class CalculatorFactory {

    private static final CalculatorFactory calculatorFactory = new CalculatorFactory();
    private final Calculator integerCalculator = new IntegerCalculator();

    private CalculatorFactory() {
    }

    public Calculator getCalculator() {
        return integerCalculator;
    }

    public static CalculatorFactory getInstance() {
        return calculatorFactory;
    }
}
