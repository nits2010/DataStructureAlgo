package Java.companyWise.facebook;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-19
 * Description:https://cheonhyangzhang.wordpress.com/2016/12/22/158-leetcode-java-read-n-characters-given-read4-ii-call-multiple-times-add-to-list-questioneditorial-solution-hard/
 * Problem:
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * <p>
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 * <p>
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 * <p>
 * Note:
 * The read function may be called multiple times.
 * <p>
 * <p>
 * Input:
 * "filetestbuffer"
 * read(6)
 * read(5)
 * read(4)
 * read(3)
 * read(2)
 * read(1)
 * read(10)
 * Output:
 * 6, buf = "filete"
 * 5, buf = "stbuf"
 * 3, buf = "fer"
 * 0, buf = ""
 * 0, buf = ""
 * 0, buf = ""
 * 0, buf = ""
 *
 *
 * Input:
 * "abcdef"
 * read(1)
 * read(5)
 * Output:
 * 1, buf = "a"
 * 5, buf = "bcdef"
 */
public class ReadNCharactersGivenRead4 {

    static String str = null;

    static int read4Pointer = 0;
    static char[] buffer = new char[4];
    static int bufferIndex = 0;
    static int bufferLength = 0;


    public static boolean isNullEmpty(String s) {
        if (null == s || s.isEmpty())
            return true;
        return false;
    }

    public static int read4(char buf[]) {

        int end = (str.length() - read4Pointer <= 4) ? str.length() : read4Pointer + 4;
        String subStr = str.substring(read4Pointer, end);
        read4Pointer = end;

        int count = subStr.length();
        if (count == 0)
            return 0;

        int i;
        for (i = 0; i < count; i++) {
            buf[i] = subStr.charAt(i);
        }
        return i;


    }

    public static int read(char buf[], int n) {
        StringBuilder output = new StringBuilder();

        int i = 0;
        while (i < n) {
            // no character in own buffer
            if (bufferIndex == 0 || bufferIndex == bufferLength) {
               // buffer = new char[4];
                bufferLength = read4(buffer);
                bufferIndex = 0;
            }
            //no data available neither in temp buffer nor returned by read4 api
            if (bufferLength == 0)
                break;
            
            //copy data from buffer
            if (bufferIndex < bufferLength)
                buf[i++] = buffer[bufferIndex++];

        }
        return i;

    }


    public static void main(String args[]) {

        System.out.println("Testing ->1");
        test1("filetestbuffer");

        System.out.println("Testing ->2");
        buffer = new char[4];
        bufferIndex = bufferLength = 0;
        read4Pointer = 0;
        test2("abcdef");
    }

    public static void test2(String test){
        str = test;
        int n ;
        String s;

        n = 1;
        s = testRead(n);
        System.out.println("n -> " + n + " " + s.length() + " " + s);

        n = 5;
        s = testRead(n);
        System.out.println("n -> " + n + " " + s.length() + " " + s);

        n = 5;
        s = testRead(n);
        System.out.println("n -> " + n + " " + s.length() + " " + s);

    }

    public static void test1(String test) {

        str = test;
        int n ;
        String s;

        n = 6;
        s = testRead(n);
        System.out.println("n -> " + n + " " + s.length() + " " + s);

        n = 5;
        s = testRead(n);
        System.out.println("n -> " + n + " " + s.length() + " " + s);

        n = 4;
        s = testRead(n);
        System.out.println("n -> " + n + " " + s.length() + " " + s);

        n = 3;
        s = testRead(n);
        System.out.println("n -> " + n + " " + s.length() + " " + s);

        n = 2;
        s = testRead(n);
        System.out.println("n -> " + n + " " + s.length() + " " + s);

        n = 1;
        s = testRead(n);
        System.out.println("n -> " + n + " " + s.length() + " " + s);

        n = 10;
        s = testRead(n);
        System.out.println("n -> " + n + " " + s.length() + " " + s);
    }

    public static String testRead(int n) {
        char[] buf = new char[n];
        int read = read(buf, n);
        String s = buildString(buf, read);
        return s;
    }

    public void testRead4() {
        char buf[] = new char[4];
        int i = read4(buf);
        System.out.println(i + " " + buildString(buf, i));

        buf = new char[4];
        i = read4(buf);
        System.out.println(i + " " + buildString(buf, i));

        buf = new char[4];
        i = read4(buf);
        System.out.println(i + " " + buildString(buf, i));

        buf = new char[4];
        i = read4(buf);
        System.out.println(i + " " + buildString(buf, i));

        buf = new char[4];
        i = read4(buf);
        System.out.println(i + " " + buildString(buf, i));

        buf = new char[4];
        i = read4(buf);
        System.out.println(i + " " + buildString(buf, i));


    }

    private static String buildString(char buf[], int size) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < size; i++) {
            str.append(buf[i]);
        }
        return str.toString();
    }
}
