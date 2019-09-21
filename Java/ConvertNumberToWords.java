package Java;

import java.text.DecimalFormat;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 15/03/19
 * Description:
 */

public class ConvertNumberToWords {

    private static final String[] tensNames = {
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
    };

    private static final String[] numNames = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
    };

    private static String convertLessThanOneThousand(int number) {
        String soFar;

        if (number % 100 < 20) {
            soFar = numNames[number % 100];
            number /= 100;
        } else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;
        }
        if (number == 0) return soFar;
        return numNames[number] + " hundred" + soFar;
    }


    private static String convertLessThanOneThousandWithAnd(int number) {
        String soFar;

        if (number % 100 < 20) {
            soFar = numNames[number % 100];
            number /= 100;
        } else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;
        }
        if (number == 0) return soFar;
        if (!soFar.isEmpty())
            return numNames[number] + " hundred and " + soFar;
        return numNames[number] + " hundred" + soFar;


    }


    public static String convert(long number, boolean withoutAnd) {
        // 0 to 999 999 999 999
        if (number == 0) {
            return "zero";
        }

        // pad with "0" 12
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        String sNumber = df.format(number);

        // XXXnnnnnnnnn
        int billions = Integer.parseInt(sNumber.substring(0, 3));
        // nnnXXXnnnnnn
        int millions = Integer.parseInt(sNumber.substring(3, 6));
        // nnnnnnXXXnnn
        int hundredThousands = Integer.parseInt(sNumber.substring(6, 9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(sNumber.substring(9, 12));

        String tradBillions;
        switch (billions) {
            case 0:
                tradBillions = "";
                break;
            default:
                tradBillions = convertLessThanOneThousand(billions)
                        + " billion ";
        }
        String result = tradBillions;

        String tradMillions;
        switch (millions) {
            case 0:
                tradMillions = "";
                break;
            default:
                tradMillions = convertLessThanOneThousand(millions)
                        + " million ";
        }
        result = result + tradMillions;

        String tradHundredThousands;
        switch (hundredThousands) {
            case 0:
                tradHundredThousands = "";
                break;
            case 1:
                tradHundredThousands = "one thousand ";
                break;
            default:
                tradHundredThousands = convertLessThanOneThousand(hundredThousands)
                        + " thousand ";
        }
        result = result + tradHundredThousands;

        String tradThousand;
        if (!withoutAnd)
            tradThousand = convertLessThanOneThousandWithAnd(thousands);
        else
            tradThousand = convertLessThanOneThousand(thousands);
        result = result + tradThousand;

        // remove extra spaces!
        return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
    }


    static String convert(float number) {

        String temp = String.valueOf(number);

        String v[] = temp.split("\\.");

        String left = convert(Long.parseLong(v[0]), true);

        String right = convert(Long.parseLong(v[1]), true);

        return left + " and " + right;


    }

    /**
     * testing
     *
     * @param args
     */
    public static void main(String[] args) {

//        System.out.println("*** " + convert(334.32f ));


        System.out.println("*** " + convert(0, true));
        System.out.println("*** " + convert(1, true));
        System.out.println("*** " + convert(16, true));
        System.out.println("*** " + convert(100, true));
        System.out.println("*** " + convert(118, true));
        System.out.println("*** " + convert(200, true));
        System.out.println("*** " + convert(219, true));
        System.out.println("*** " + convert(800, true));
        System.out.println("*** " + convert(801, true));
        System.out.println("*** " + convert(1316, true));
        System.out.println("*** " + convert(1000000, true));
        System.out.println("*** " + convert(2000000, true));
        System.out.println("*** " + convert(3000200, true));
        System.out.println("*** " + convert(700000, true));
        System.out.println("*** " + convert(9000000, true));
        System.out.println("*** " + convert(9001000, true));
        System.out.println("*** " + convert(123456789, true));
        System.out.println("*** " + convert(2147483647, true));
        System.out.println("*** " + convert(3000000010L, true));

        /*
         *** zero
         *** one
         *** sixteen
         *** one hundred
         *** one hundred eighteen
         *** two hundred
         *** two hundred nineteen
         *** eight hundred
         *** eight hundred one
         *** one thousand three hundred sixteen
         *** one million
         *** two millions
         *** three millions two hundred
         *** seven hundred thousand
         *** nine millions
         *** nine millions one thousand
         *** one hundred twenty three millions four hundred
         **      fifty six thousand seven hundred eighty nine
         *** two billion one hundred forty seven millions
         **      four hundred eighty three thousand six hundred forty seven
         *** three billion ten
         **/
    }


}
