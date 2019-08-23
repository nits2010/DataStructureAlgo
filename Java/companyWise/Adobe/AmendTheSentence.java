package Java.companyWise.Adobe;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-08-24
 * Description: https://www.geeksforgeeks.org/adobe-interview-experience-set-24-on-campus-for-mts/
 * Given an array of characters which is basically a sentence. However there is no space between different words and the first letter of every word is in uppercase. You need to print this sentence after following amendments:
 * (i) Put a single space between these words
 * (ii) Convert the uppercase letters to lowercase.
 *
 * Example: “MyNameIsRam ” , you need to convert this to “my name is ram”
 * Example: “MyNameIsKhansKhanRam ” , you need to convert this to “my name is ram”
 */
public class AmendTheSentence {

    public static void main(String[] args) {
        System.out.println(amendTheSentence("MyNameIsRam"));
        System.out.println(amendTheSentence("MyNameIsKhansKhanRam"));
        System.out.println(amendTheSentence("MyNam2eIsKhansK2hanRam"));
    }

    public static String amendTheSentence(String sentence) {
        if (null == sentence || sentence.isEmpty())
            return sentence;

        StringBuilder response = new StringBuilder();
        for (int i = 0; i < sentence.length(); i++) {

            char c = sentence.charAt(i);
            if (Character.isLetter(c)) { //if string has other than letter

                if (Character.isUpperCase(c))
                    response.append(" ");

                response.append(Character.toLowerCase(c));

            } else
                response.append(c);
        }
        return response.toString().trim();
    }


}
