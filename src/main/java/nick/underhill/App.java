package nick.underhill;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    // TODO: 04.09.2020 Refactor to OOP Style
    public static void main(String[] args) {
        final String ERROR_MESSAGE = "Error";

        String numberInput = "";
        String result;

        int fromRadix = 1;
        int toRadix = 1;

        Scanner sc = new Scanner(System.in);

        // TODO: 04.09.2020 Input validation
        System.out.println("Enter the base of input number (between 1 and 36):");
        fromRadix = sc.nextInt();

        System.out.println("Enter a number to convert (1 - z, fractions allowed):");
        numberInput = sc.next();

        System.out.println("Enter the base of output number (between 1 and 36):");
        toRadix = sc.nextInt();

        if (numberInput.contains(".")) {
            String[] parts;
            parts = numberInput.split("\\.");
            String integerPart = parts[0];
            String fractionalPart = parts[1];
            result = convertInteger(fromRadix, toRadix, integerPart) + "." +
                    convertFraction(fromRadix, toRadix, fractionalPart);
        } else {
            result = convertInteger(fromRadix, toRadix, numberInput);
        }
        System.out.println(String.format(
                "The result of converting %s from radix %s to radix %s :",
                numberInput, fromRadix, toRadix));
        System.out.println(result);
    }

    static String convertInteger(int fromRadix, int toRadix, String numberString) {
        int decimalInput;
        if (fromRadix == 1) {
            decimalInput = numberString.length();
        } else {
            decimalInput = Integer.parseInt(numberString, fromRadix);
        }
        if (toRadix == 1) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < numberString.length(); i++) {
                result.append("1");
            }
            return result.toString();
        } else {
            return Integer.toString(decimalInput, toRadix);
        }
    }

    static String convertFraction(int fromRadix, int toRadix, String numberString) {
        double decimalResult = 0;
        StringBuilder result = new StringBuilder();

        if (fromRadix != 10) {
            char[] addends = numberString.toCharArray();
            for (int i = 0; i < addends.length; i++) {
                double numRepresentation = Character.getNumericValue(addends[i]);
                decimalResult += numRepresentation / Math.pow(fromRadix, i + 1.0);
            }
        } else {
            decimalResult = Double.parseDouble("0." + numberString);
        }

        int integerPart;
        for (int i = 0; i < 5; i++) {
            decimalResult = decimalResult * toRadix; // Multiply fractional part by radix
            integerPart = (int) decimalResult; // Extract integer part from result
            result.append(Character.forDigit(integerPart, toRadix)); // Convert integer part to desired radix and append to result String
            decimalResult -= integerPart; // Subtract integer part from result
        }

        return result.toString();
    }

    static boolean isValidRadix(String s) {
        int radix;
        try {
            radix = Integer.parseInt(s);
        } catch (InputMismatchException | NumberFormatException e) {
            return false;
        }
        return radix > 0 && radix <= 36;

    }
}