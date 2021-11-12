package by.epam.jwd3;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static Queue<String> parseStringToList(String inputStr) {

        Queue<String> resultQueue = new ArrayDeque<>();

        Pattern pattern = Pattern.compile("\\(-[0-9]+\\)|([0-9]+)|" + "(\\+|-|\\*|/|\\(|\\))");
        Matcher matcher = pattern.matcher(inputStr);

        while (matcher.find()) {
            String s = matcher.group();

            if (s.matches("(\\(-[0-9]+\\))")) {
                s = s.replaceAll("\\(|\\)", ""); //negative values
            }
            resultQueue.add(s);
        }
        return resultQueue;
    }
}
