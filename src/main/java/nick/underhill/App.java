package nick.underhill;

import java.util.Scanner;

public class App {
    final String ERROR_MESSAGE = "Error";

    public static void main(String[] args) {
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

        result = ConversionUtil.convert(numberInput, fromRadix, toRadix);
        System.out.println(String.format(
                "The result of converting %s from radix %s to radix %s :",
                numberInput, fromRadix, toRadix));
        System.out.println(result);
    }
}