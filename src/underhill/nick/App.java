package underhill.nick;

import java.util.Scanner;

public class App {
    static final String MESSAGE_WELCOME = "WELCOME TO THE BASE-CONVERTER APPLICATION!";
    static final String MESSAGE_RADIX_INPUT = "\nEnter the base of input number (between 1 and 36):";
    static final String MESSAGE_RADIX_OUTPUT = "\nEnter the base of the output number (between 1 and 36):";
    static final String MESSAGE_NUMBER_INPUT = "\nEnter a number to convert (fractions allowed):";
    static final String MESSAGE_CONVERSION_RESULT = "\nThe result of converting %s from radix %s to radix %s: \n%s";
    static final String ERROR_MESSAGE_NUMBER_FORMAT = "The given number is not in the given base. ";

    static final String REGEX_RADIX = "^([1-9]|[12]\\d|3[0-6])$";
    static final String REGEX_NUMBER = "^[+-]?([a-zA-Z0-9]*[.])?[a-zA-Z0-9]+$";

    public static void main(String[] args) {
        String numberInput;
        String fromRadix;
        String toRadix;
        String result;
        Scanner sc = new Scanner(System.in);

        System.out.println(MESSAGE_WELCOME);

        do {
            fromRadix = ConversionUtil.validateInputRegEx(sc, REGEX_RADIX, MESSAGE_RADIX_INPUT);

            numberInput = ConversionUtil.validateInputRegEx(sc, REGEX_NUMBER, MESSAGE_NUMBER_INPUT);
            if (!ConversionUtil.isConvertible(numberInput, fromRadix)) {
                numberInput = ConversionUtil.validateInputRegEx(sc, REGEX_NUMBER,
                        ERROR_MESSAGE_NUMBER_FORMAT + MESSAGE_NUMBER_INPUT);
            }

            toRadix = ConversionUtil.validateInputRegEx(sc, REGEX_RADIX, MESSAGE_RADIX_OUTPUT);

            result = ConversionUtil.convert(numberInput, fromRadix, toRadix);
            System.out.println(String.format(
                    MESSAGE_CONVERSION_RESULT,
                    numberInput, fromRadix, toRadix, result));
            System.out.println("\nConvert another number? (1 - yes, 0 - exit):");
        } while (!sc.next().equals("0"));
    }
}