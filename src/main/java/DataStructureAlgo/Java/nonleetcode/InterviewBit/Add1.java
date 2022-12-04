package DataStructureAlgo.Java.nonleetcode.InterviewBit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-05-20
 * Description:
 */
public class Add1 {
    public static void main(String []args) {
        ArrayList<Integer> inp = new ArrayList<>();
        inp.add(0);
        inp.add(0);
        System.out.println(plusOne(inp));
    }

    public static ArrayList<Integer> plusOne(ArrayList<Integer> A) {

        int carry = 1;

        Collections.reverse(A);
        ArrayList<Integer> out = new ArrayList<>();
        int it;
        for (it = 0; it < A.size(); it++) {
            Integer i = A.get(it);
            int sum = i + carry;

            if (sum > 9) {
                carry = 1;
                out.add(0);
            } else {
                out.add(sum);
                it++;
                carry = 0;
                break;

            }
        }

        while (it < A.size()) {
            out.add(A.get(it++));
        }
        if (carry != 0)
            out.add(carry);

        Collections.reverse(out);
        int x;
        for (x = 0; x < out.size(); x++) {
            if (out.get(x) == 0) continue;
            else break;
        }


        if (x != 0) {
            out = new ArrayList<>(out.subList(x, out.size()));
        }

        return out;
    }
}
