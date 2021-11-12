package by.epam.jwd3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class IntegerCalculator implements Calculator {

    private Double result;
    private Queue<String> inputQueue;

    @Override
    public double calculate(String inputString) {

        if (!Validator.validate("inputString")) {
            throw new RuntimeException("Incorrect line");
        }

        inputQueue = Parser.parseStringToList(inputString);
        result = Double.parseDouble(calculateQueueList(inputQueue));

        return result;
    }

    public static String calculateQueueList(Queue<String> inputQueue) {

        Queue<String> localQueue = new ArrayDeque<>();

        do {
            if (inputQueue.element().equals("(")) {
                inputQueue.remove();
                localQueue.add(calculateQueueList(inputQueue));
            }
            if (!inputQueue.isEmpty() && !inputQueue.element().equals(")")) {
                localQueue.add(inputQueue.remove());
            }
        } while (!inputQueue.isEmpty() && !inputQueue.element().equals(")"));
        if (!inputQueue.isEmpty()) {
            inputQueue.remove();
        }
        return math(localQueue);
    }

    public static String math(Queue<String> inputQueue) {

        String res = "";
        Deque<String> resultQueue = new ArrayDeque<>();

        while (inputQueue.contains("*") || inputQueue.contains("/")) {
            if (inputQueue.element().equals("*") || inputQueue.element().equals("/")) {
                res = multiplicationAndDiv(resultQueue.removeLast(), inputQueue.remove(), inputQueue.remove());
                resultQueue.addLast(res);
            } else {
                resultQueue.add(inputQueue.remove());
            }
        }

        resultQueue.addAll(inputQueue);

        while (resultQueue.size() >= 2) {
            res = additionAndSubtraction(resultQueue.removeFirst(), resultQueue.removeFirst(), resultQueue.removeFirst());
            resultQueue.addFirst(res);
        }
        return res;
    }

    public static String multiplicationAndDiv(String firstD, String op, String secD) {

        double doubleRes;
        double doubleFirstD = Double.parseDouble(firstD);
        double doubleSecondD = Double.parseDouble(secD);

        if (op.equals("*")) {
            doubleRes = doubleFirstD * doubleSecondD;
        } else if (op.equals("/")) {
            doubleRes = doubleFirstD / doubleSecondD;
        } else {
            throw new RuntimeException("incorrect operator");
        }
        return Double.toString(doubleRes);
    }

    public static String additionAndSubtraction(String firstD, String op, String secD) {

        double doubleRes;
        double doubleFirstD = Double.parseDouble(firstD);
        double doubleSecondD = Double.parseDouble(secD);

        if (op.equals("+")) {
            doubleRes = doubleFirstD + doubleSecondD;
        } else if (op.equals("-")) {
            doubleRes = doubleFirstD - doubleSecondD;
        } else {
            throw new RuntimeException("incorrect operator");
        }
        return Double.toString(doubleRes);
    }
}
