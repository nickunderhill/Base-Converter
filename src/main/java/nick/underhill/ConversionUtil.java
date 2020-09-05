package nick.underhill;

public class ConversionUtil {

    public static String convert (String inputNumberString, int inputRadix, int outputRadix) {
        String result;
        if (inputNumberString.contains(".")) {
            String[] parts;
            parts = inputNumberString.split("\\.");
            String integerPart = parts[0];
            String fractionalPart = parts[1];
            result = convertInteger(integerPart, inputRadix, outputRadix) + "." +
                    ConversionUtil.convertFraction(fractionalPart, inputRadix, outputRadix);
        } else {
            result = ConversionUtil.convertInteger(inputNumberString, inputRadix, outputRadix);
        }
        return result;
    }

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

    static String convertFraction(String inputNumberString, int inputRadix, int outputRadix) {
        double decimalResult = 0;
        StringBuilder result = new StringBuilder();

        if (inputRadix != 10) {
            char[] addends = inputNumberString.toCharArray();
            for (int i = 0; i < addends.length; i++) {
                double numRepresentation = Character.getNumericValue(addends[i]);
                decimalResult += numRepresentation / Math.pow(inputRadix, i + 1.0);
            }
        } else {
            decimalResult = Double.parseDouble("0." + inputNumberString);
        }

        int integerPart;
        for (int i = 0; i < 5; i++) {
            decimalResult = decimalResult * outputRadix; // Multiply fractional part by radix
            integerPart = (int) decimalResult; // Extract integer part from result
            result.append(Character.forDigit(integerPart, outputRadix)); // Convert integer part to desired radix and append to result String
            decimalResult -= integerPart; // Subtract integer part from result
        }

        return result.toString();
    }
}
