package nick.underhill;

import java.util.Scanner;

public class ConversionUtil {

    private static final int PRECISION = 5;

    private ConversionUtil() {
    }

    /**
     * Chooses number conversion logic depending on inputNumberString format.
     *
     * @param inputNumberString - String value of number to convert
     * @param inputRadix        - String value of base of inputted number
     * @param outputRadix       - String value of desired base
     * @return String number in given outputRadix
     */
    public static String convert(String inputNumberString, String inputRadix, String outputRadix) {
        String result;
        if (inputNumberString.contains(".")) {
            String[] parts = inputNumberString.split("\\.");
            String integerPart = parts[0];
            String fractionalPart = parts[1];
            result = convertInteger(
                    integerPart,
                    Integer.parseInt(inputRadix),
                    Integer.parseInt(outputRadix))
                    + "."
                    + convertFraction(
                            fractionalPart,
                    Integer.parseInt(inputRadix),
                    Integer.parseInt(outputRadix));
        } else {
            result = ConversionUtil.convertInteger(
                    inputNumberString,
                    Integer.parseInt(inputRadix),
                    Integer.parseInt(outputRadix));
        }
        return result;
    }

    /**
     * Converts decimal number from inputRadix base to outputRadix base.
     *
     * @param inputNumberString - number to convert
     * @param inputRadix        - base of inputted number
     * @param outputRadix       - desired base
     * @return String number in given outputRadix
     */
    public static String convertInteger(String inputNumberString, int inputRadix, int outputRadix) {
        int decimalInput;
        if (inputRadix == 1) {
            decimalInput = inputNumberString.length();
        } else {
            decimalInput = Integer.parseInt(inputNumberString, inputRadix);
        }

        if (outputRadix == 1) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < inputNumberString.length(); i++) {
                result.append("1");
            }
            return result.toString();
        } else {
            return Integer.toString(decimalInput, outputRadix);
        }
    }

    /**
     * Converts fractional part of a number from inputRadix base to outputRadix base.
     *
     * @param inputNumberString - number to convert
     * @param inputRadix        - base of inputted number
     * @param outputRadix       - desired base
     * @return String number in given outputRadix
     */
    static String convertFraction(String inputNumberString, int inputRadix, int outputRadix) {
        double decimalResult = 0;
        StringBuilder result = new StringBuilder();

        // Convert to decimal if the number is not in decimal base
        if (inputRadix != 10) {
            char[] addends = inputNumberString.toCharArray();
            for (int i = 0; i < addends.length; i++) {
                double numRepresentation = Character.getNumericValue(addends[i]);
                decimalResult += numRepresentation / Math.pow(inputRadix, i + 1.0);
            }
        } else {
            decimalResult = Double.parseDouble("0." + inputNumberString);
        }

        //Conversion algorithm to transform number to a given base with PRECISION digits after the floating-point
        for (int i = 0; i < PRECISION; i++) {
            decimalResult = decimalResult * outputRadix; // Multiply fractional part by radix
            int integerPart = (int) decimalResult; // Extract integer part from result
            result.append(Character.forDigit(integerPart, outputRadix)); // Convert integer part to desired radix and append to result String
            decimalResult -= integerPart; // Subtract integer part from result
        }
        return result.toString();
    }

    /**
     * Validates input using given regular expression.
     * @param scanner - Scanner object;
     * @param regex - regular expression which must be satisfied in order to pass input value;
     * @param msg - text message shown before the input;
     * @return String
     */
    static String validateInputRegEx(Scanner scanner, String regex, String msg) {
        String result;
        do {
            System.out.println(msg);
            result = scanner.next();
        } while (!result.matches(regex));
        return result;
    }

    /**
     * Returns true if the given number is in the given radix scope, false if not.
     * @param number - number to check;
     * @param radix - extimated radix;
     * @return boolean
     */
    public static boolean isConvertible(String number, String radix) {
        try {
            Integer.parseInt(number, Integer.parseInt(radix));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
