package by.epam.jwd3;

public class Validator {

    public static boolean validate(String str) {

        boolean res;

        res = str.matches("^(\\(|\\d)(\\d|\\+|-|\\*|/|\\(|\\))(\\d|\\))$");

        return !res;
    }
}
