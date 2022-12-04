package DataStructureAlgo.Java.LeetCode;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-02
 * Description: https://leetcode.com/problems/valid-number/submissions/
 */
public class IsValidInteger {


    public static void main(String []args) {
        SolutionIsValidInteger solution = new SolutionIsValidInteger();

       /* "0" =>true
        " 0.1 " =>true
        "abc" =>false
        "1 a" =>false
        "2e10" =>true
        " -90e3   " =>true
        " 1e" =>false
        "e3" =>false
        " 6e-1" =>true
        " 99e2.5 " =>false
        "53.5e93" =>true
        " --6 " =>false
        "-+3" =>false
        "95a54e53" =>false

*/

        System.out.println(solution.isNumber("3-2"));
        System.out.println(solution.isNumber("3+2"));
        System.out.println(solution.isNumber("32-"));
        System.out.println(solution.isNumber("-32"));
        System.out.println(solution.isNumber(" "));
        System.out.println(solution.isNumber("0"));
        System.out.println(solution.isNumber(" 0.1 "));

        System.out.println(solution.isNumber("abc"));
        System.out.println(solution.isNumber("1 a"));

        System.out.println(solution.isNumber("2e10"));
        System.out.println(solution.isNumber(" -90e3   "));

        System.out.println(solution.isNumber(" 1e"));
        System.out.println(solution.isNumber("e3"));


        System.out.println(solution.isNumber(" 6e-1"));
        System.out.println(solution.isNumber(" 99e2.5 "));

        System.out.println(solution.isNumber("53.5e93"));
        System.out.println(solution.isNumber(" --6 "));

        System.out.println(solution.isNumber("-+3"));
        System.out.println(solution.isNumber("95a54e53"));


    }
}

class SolutionIsValidInteger {

    //this will cover lot of corner cases
    public static boolean isNumber(String s) {
        s = s.trim();
        int id = 0;
        int len = s.length();
        if (len == 0)
            return false;
        if (id < len && s.charAt(id) == '+' || s.charAt(id) == '-')
            id++;
        if (id < len && s.charAt(id) >= '0' && s.charAt(id) <= '9') {
            while (id < len && s.charAt(id) >= '0' && s.charAt(id) <= '9')
                id++;
        } else if (id < len && s.charAt(id) == '.')
            ;
        else
            return false;
        if (id < len && s.charAt(id) == '.') {
            id++;
            if (id < len && s.charAt(id) >= '0' && s.charAt(id) <= '9') {
                while (id < len && s.charAt(id) >= '0' && s.charAt(id) <= '9')
                    id++;
            } else if (id < len && s.charAt(id) == 'e' && id > 1 && s.charAt(id - 2) >= '0' && s.charAt(id - 2) <= '9')
                ;
            else
                return id == len && len > 1 && s.charAt(id - 2) >= '0' && s.charAt(id - 2) <= '9';
        }

        if (id < len && s.charAt(id) == 'e') {
            id++;
            if (id < len && (s.charAt(id) == '+' || s.charAt(id) == '-'))
                id++;
            if (id < len && s.charAt(id) >= '0' && s.charAt(id) <= '9') {
                while (id < len && s.charAt(id) >= '0' && s.charAt(id) <= '9')
                    id++;
            } else
                return false;
        }
        return id == len;
    }

    public boolean isNumberLessCoverage(String s) {

        if (s == null)
            return false;

        // System.out.println(s);
        s = s.trim();

        if (s.isEmpty())
            return false;

        int n = s.length();
        char str[] = s.toCharArray();

        //if single letter string, then it has to be a number otherwise it is not valid
        if (n == 1)
            return isDigit(str[0]);


        boolean foundDotE = false;
        boolean doubleOp = false;

        for (int i = 0; i < n; i++) {
            char c = str[i];


            if (!isValid(c))
                return false;

            if (doubleOp && c == '-' || c == '+')
                return false;
            else doubleOp = false;

            if (c == '-' || c == '+')
                doubleOp = true;

            if (c == '-' || c == '+') {

                if (i == n - 1)
                    return false;

                if ((i > 0 && isDigit(str[i - 1]) && (i < n - 1 && isDigit(str[i + 1]))))
                    return false;
            }

            if (c == '.') {

                //if we found a dot after e, then its not valid or we found multiple '.'
                if (foundDotE)
                    return false;

                //if this is last char, then its not valid 5.
                //  if (i == n - 1)
                //     return false;

                //if this is not followed by a number then its not valid 5.e or 5.
                if (i < n - 1 && !isDigit(str[i + 1]))
                    return false;

                //otherwise its valid

                foundDotE = true;
            }

            //if we found e and previous character is not correct then not valid
            //e3
            else if (c == 'e') {

                //invalid e3
                if (i == 0)
                    return false;

                //invalid 343e
                if (i == n - 1)
                    return false;


                //invalid en or e. but e-<> or e+<> is valid like e-3 which is e^(-3)
                if (str[i + 1] != '+' && str[i + 1] != '-' && !Character.isDigit(str[i + 1]))
                    return false;

                foundDotE = true;
            }

        }
        return true;


    }

    public boolean isDigit(char c) {
        if (c >= '0' && c <= '9')
            return true;
        return false;
    }

    public boolean isValid(char c) {
        if (c != '+' && c != '-' && c != 'e' && c != '.' && c != '.' && !isDigit(c))
            return false;
        return true;
    }
}

