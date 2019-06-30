package Java.LeetCode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-30
 * Description:
 */
public class StringCompressionRunLengthEncoding {


    public static void main (String args[]){
       test1();
       System.out.println();
       test2();
    }

    public static void test1(){
        char [] chars = {'a','a','b','b','c','c','c'};
        int len = compress(chars);

        for (int i=0; i<len; i++)
            System.out.print(chars[i]+" ");
    }

    public static void test2(){
        char [] chars = {'a','b','c'};
        int len = compress(chars);

        for (int i=0; i<len; i++)
            System.out.print(chars[i]+" ");
    }
    public static int compress(char[] chars) {
        int anchor = 0, write = 0;
        for (int read = 0; read < chars.length; read++) {
            if (read + 1 == chars.length || chars[read + 1] != chars[read]) {
                chars[write++] = chars[anchor];
                if (read > anchor) {
                    for (char c: ("" + (read - anchor + 1)).toCharArray()) {
                        chars[write++] = c;
                    }
                }
                anchor = read + 1;
            }
        }
        return write;
    }
}
