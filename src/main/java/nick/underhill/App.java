package nick.underhill;

import java.util.Scanner;

public class App {
    static final String MESSAGE_RADIX_INPUT = "Enter the base of input number (between 1 and 36):";
    static final String MESSAGE_RADIX_OUTPUT = "Enter the base of the output number (between 1 and 36):";
    static final String MESSAGE_NUMBER_INPUT = "Enter a number to convert (fractions allowed):";
    static final String MESSAGE_CONVERSION_RESULT = "The result of converting %s from radix %s to radix %s: %s";
    static final String ERROR_MESSAGE_NUMBER_FORMAT = "Enter a number to convert (fractions allowed):";
    static final String REGEX_RADIX = "^([1-9]|[12]\\d|3[0-6])$";
    static final String REGEX_NUMBER = "^[+-]?([a-zA-Z0-9]*[.])?[a-zA-Z0-9]+$";

    public static void main(String[] args) {
        String numberInput;
        String fromRadix;
        String toRadix;
        String result;
        Scanner sc = new Scanner(System.in);

        fromRadix = ConversionUtil.validateInputRegEx(sc, REGEX_RADIX, MESSAGE_RADIX_INPUT);

        numberInput = ConversionUtil.validateInputRegEx(sc, REGEX_NUMBER, MESSAGE_NUMBER_INPUT);
        if (!ConversionUtil.isConvertible(numberInput, fromRadix)) {
            System.out.print(ERROR_MESSAGE_NUMBER_FORMAT);
            numberInput = ConversionUtil.validateInputRegEx(sc, REGEX_NUMBER, MESSAGE_NUMBER_INPUT);
        }

        toRadix = ConversionUtil.validateInputRegEx(sc, REGEX_RADIX, MESSAGE_RADIX_OUTPUT);

        result = ConversionUtil.convert(numberInput, fromRadix, toRadix);
        System.out.println(String.format(
                MESSAGE_CONVERSION_RESULT,
                numberInput, fromRadix, toRadix, result));
    }
}