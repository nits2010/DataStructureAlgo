package Java;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-30
 * Description:http://javabypatel.blogspot.com/2016/07/string-compression-in-place-run-length-encoding-program.html
 * aacccddd ->a2c3d3
 * acc -> a1c2
 */
public class StringCompression2 {

    private static final char SPACE = ' ';

    public static void main(String args[]) {
        System.out.println(compress("abcde     "));
        System.out.println(compress("abcdeeeeeeeefffffg"));
        System.out.println(compress("abcdeeeeeeeeeeeeeeeeeeefffffg"));

        System.out.println(compress("aacccddd"));
        System.out.println(compress("acc "));
        System.out.println(compress("aaaaaaaa     "));

    }

    static String compress(String toCompress) {

        if (toCompress == null || toCompress.isEmpty())
            return toCompress;

        char str[] = toCompress.toCharArray();
        int n = str.length;


        compressMultiOccurrence(str, n);

        compressSingleOccurrence(str, n);


        return new String(str);

    }

    private static void compressMultiOccurrence(char[] str, int n) {

        for (int i = 0; i < n; i++) {

            if(str[i]==SPACE)
                break;

            char c = str[i];
            int j = i + 1;

            //Find the occurrence of this char 'c' and push space in between ' '
            while (j < n && str[j] == c ) {
                str[j] = SPACE;
                j++;

            }
            //if this occurred more than one ?
            if (j != i + 1) {

                //if yes, then transform it to aaa to a3 or aaaaaaaaaaa -> a11
                String count = "" + (j - i);
                for (char t : count.toCharArray())
                    str[++i] = t;

                i = j - 1;
            }
        }
    }


    private static void compressSingleOccurrence(char[] str, int n) {

        for (int i = 0; i < n; i++) {

            char c = str[i];


            //if next character is a character only, means we need to put 1 in between to these char [ rem we already compressed multi chars ]
            if (Character.isLetter(c)) {

                //Since this is a letter, then we need to move this chunk ahead to make a room for '1'
                /**
                 * for example we have
                 * a b c d 6 _ _ _ _ o 3 _
                 * convert it to
                 * a b b c d 6 _ _ _ o 3 _
                 * i will be pointing to 'a' and j will be pointing to 'b' [first b ]
                 */
                if (i < n - 1 && Character.isLetter(str[i + 1])) {
                    int j = i + 1;
                    char toMove = str[j];
                    char backup;
                    j++;
                    while (toMove != SPACE) {
                        backup = str[j];
                        str[j] = toMove;
                        toMove = backup;
                        j++;

                    }
                    str[i + 1] = '1';
                    i++;//push 1 to next char a b b c d 6 _ _ _ o 3 _ -> a 1 b c d 6 _ _ _ o 3 _
                }else if (i < n-1 && str[i+1] == SPACE){
                    str[i+1] = '1';
                    i++;
                }
            } else if (c == SPACE) {
                // if its a space
                /**              i j
                 * a 1 b 1 c 1 d 6 _ _ o 3
                 * then find a first char where its not a space and move those here
                 * a 1 b 1 c 1 d 6 o 3 _ _
                 */
                int j = i + 1;
                while (j < n && str[j] == SPACE)
                    j++;
                /**              i     j
                 * a 1 b 1 c 1 d 6 _ _ o 3
                 * =>
                 *                   i     j=n
                 * a 1 b 1 c 1 d 6 o 3 _ _
                 */
                if (j < n) {
                    int x = i;
                    while (j < n && str[j] != SPACE) {
                        str[x++] = str[j];
                        str[j++] = SPACE;
                    }
                    if (str[i + 1] == SPACE && Character.isLetter(str[i])) {
                        str[i + 1] = '1';
                    }

                }


            }
        }

    }


}
