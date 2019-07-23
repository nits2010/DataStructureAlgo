package Java.companyWise.facebook;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-20
 * Description: https://leetcode.com/problems/similar-rgb-color/
 * <p>
 * In the following, every capital letter represents some hexadecimal digit from 0 to f.
 * The red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand.  For example, "#15c" is shorthand for the color "#1155cc".
 * Now, say the similarity between two colors "#ABCDEF" and "#UVWXYZ" is -(AB - UV)^2 - (CD - WX)^2 - (EF - YZ)^2.
 * Given the color "#ABCDEF", return a 7 character color that is most similar to #ABCDEF, and has a shorthand (that is, it can be represented as some "#XYZ"
 * Example 1:
 * Input: color = "#09f166"
 * Output: "#11ee66"
 * Explanation:
 * The similarity is -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73.
 * This is the highest among any shorthand color.
 * Note:
 * color is a string of length 7.
 * color is a valid RGB color: for i > 0, color[i] is a hexadecimal digit from 0 to f
 * Any answer which has the same (highest) similarity as the best answer will be accepted.
 * All inputs and outputs should use lowercase letters, and the output is 7 characters.
 * <p>
 * LeetCode 800. Similar RGB Color
 *
 * [GOOGLE] [FACEBOOK]
 */
public class SimilarRGBColor {

    public static void main(String args[]) {
        ClosestRGBColor closestRGBColor = new ClosestRGBColor();
        System.out.println(closestRGBColor.closestRGB("#09f166"));
        System.out.println(closestRGBColor.closestRGBLook("#09f166"));
        System.out.println(closestRGBColor.closestRGBLook("#99ff66"));
    }

}

class ClosestRGBColor {

    /**
     * Observations: The very important points given in question are
     * 1. It should be at most 7 character long (including #, so its 6 char long)
     * 2. It should have shorthand ( means in every pair, the char are duplicate since for aa -> a is shorthand )
     * 3. Color[i] is hexadecimal digit from 0 to f only [ we have in total; 0,1,2,3,4,5,6,7,8,9,1,b,c,d,e,f] total 16.
     * <p>
     * BruteForce:
     * We can generate all the pair (in total 3) whose char a duplicate within the pair. Once we generate #RGB then take the difference from original color
     * find the minimum one.
     * Since we need to generate a pair (of duplicate) then we left only 16 choices (from 0 to f) and combine them
     * each pair has 16 choices so there will be 3^16 choices.
     * <p>
     * O(type^16) where type says how many color type we can have (here we have type=3)
     * <p>
     * Optimization:
     * In brute force, we did not take the advantage of 1 and 2nd point.
     * if we combine then we are left with follow possible pairs only;
     * [00,11,22,33,44,55,66,77,88,99,aa,bb,cc,dd,ee,ff] total 16.
     * since R!=G!=B means For R there is 16 choice, B there is 16-1=15 choice and B = 15-1 = 14 choice.
     * <p>
     * Take each of them find the minimum distance between them and given color,choose the minimum one
     *
     * @param color
     * @return
     */
    public String closestRGB(String color) {

        if (null == color || color.isEmpty())
            return color;

        final String choices[] = {"00", "11", "22", "33", "44", "55", "66", "77", "88", "99", "aa", "bb", "cc", "dd", "ee", "ff"};

        int minDistance = Integer.MAX_VALUE;
        String response = "";
        for (String r : choices) {

            for (String g : choices) {

                for (String b : choices) {
                    int distance = distance("#" + r + g + b, color);
                    if (distance < minDistance) {
                        minDistance = distance;
                        response = "#" + r + g + b;
                    }
                }
            }
        }

        return response;
    }


    /**
     * Greedy: Find the closest string index [ index of choices] which is the nearest of this;
     * If you see, after "99" in the choice, it change its pattern to "aa".
     * and each number should be in range of [0,15] ; total 16 index; so take module by 17.
     *
     * @param color
     * @return
     */
    public String closestRGBLook(String color) {
        if (null == color || color.isEmpty())
            return color;

        final String choices[] = {"00", "11", "22", "33", "44", "55", "66", "77", "88", "99", "aa", "bb", "cc", "dd", "ee", "ff"};

        String colorRGB = color.substring(1);

        String p1 = colorRGB.substring(0, 2);
        String p2 = colorRGB.substring(2, 4);
        String p3 = colorRGB.substring(4);


        p1 = find(p1, choices);
        p2 = find(p2, choices);
        p3 = find(p3, choices);

        return "#" + p1 + p2 + p3;

    }

    /**
     * Greedy: Find the closest string index [ index of choices] which is the nearest of this;
     * If you see, after "99" in the choice, it change its pattern to "aa".
     * and each number should be in range of [0,15] ; total 16 index; so take module by 17.
     *
     * @param p
     * @return
     */
    private String find(String p, final String choices[]) {

        if (p.charAt(0) != p.charAt(1)) { //we don't need to calculate for color like 66 / 55 / ff etc because they are already at their best place.

            int x = Integer.parseInt(p, 16);

            int index1 = x / 17;// choices are 16, then x/17 will always under 0 to 16.
            int index2 = (x % 17 >= 9 ? 1 : 0);  //index for that it change, since at 9 it change, if it equal take 1 otherwise index1 would have give the right pos
            return choices[index1 + index2];

        }

        return p;
    }

    /**
     * Find the distance, extract 2 chars (3 pairs) and apply formula
     * "#ABCDEF" and "#UVWXYZ" is (AB - UV)^2 + (CD - WX)^2 + (EF - YZ)^2.
     *
     * @param c1
     * @param c2
     * @return
     */
    private final int distance(String c1, String c2) {


        //Remove initial #
        String c1RGB = c1.substring(1);
        String c2RGB = c2.substring(1);

        //Find part of the value in hexa
        int c1Part1 = Integer.parseInt(c1RGB.substring(0, 2), 16);
        int c1Part2 = Integer.parseInt(c1RGB.substring(2, 4), 16);
        int c1Part3 = Integer.parseInt(c1RGB.substring(4), 16);

        int c2Part1 = Integer.parseInt(c2RGB.substring(0, 2), 16);
        int c2Part2 = Integer.parseInt(c2RGB.substring(2, 4), 16);
        int c2Part3 = Integer.parseInt(c2RGB.substring(4), 16);


        return (int) (Math.pow((c1Part1 - c2Part1), 2) + Math.pow((c1Part2 - c2Part2), 2) + Math.pow((c1Part3 - c2Part3), 2));


    }
}