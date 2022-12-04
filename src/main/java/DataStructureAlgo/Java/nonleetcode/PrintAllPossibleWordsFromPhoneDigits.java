package DataStructureAlgo.Java.nonleetcode;

import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class PrintAllPossibleWordsFromPhoneDigits {
    public static String[][] a = new String[10][];
    public static int[] n;
    public static int l;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = 2, j;
        String[] s;

        for (; i <= 9; i++) {
            s = br.readLine().split(" ");
            a[i] = new String[s.length];
            for (j = 0; j < s.length; j++) {
                a[i][j] = s[j];
            }
        }
        s = br.readLine().split(" ");
        l = s.length - 1;
        n = new int[s.length];
        for (i = 0; i < s.length; i++) {
            n[i] = Integer.parseInt(s[i]);
        }

        process(0, "");
    }

    public static void process(int i, String s) {
        if (i <= l) {
            int len = a[n[i]].length - 1;
            String s1;
            for (int j = 0; j <= len; j++) {
                s1 = s;
                s1 += a[n[i]][j];
                process(i + 1, s1);
            }

        } else {
            System.out.println(s);
        }
    }
}