package Java.LeetCode;

import java.util.*;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-06-29
 * Description:https://leetcode.com/problems/brace-expansion-ii/
 * https://leetcode.com/problems/brace-expansion-ii/discuss/322741/Explanation-and-thought-process-to-solve-Using-stacks-java
 * <p>
 * Under a grammar given below, strings can represent a set of lowercase words.  Let's use R(expr) to denote the set of words the expression represents.
 * <p>
 * Grammar can best be understood through simple examples:
 * <p>
 * Single letters represent a singleton set containing that word.
 * R("a") = {"a"}
 * R("w") = {"w"}
 * When we take a comma delimited list of 2 or more expressions, we take the union of possibilities.
 * R("{a,b,c}") = {"a","b","c"}
 * R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
 * When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.
 * R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
 * R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
 * Formally, the 3 rules for our grammar:
 * <p>
 * For every lowercase letter x, we have R(x) = {x}
 * For expressions e_1, e_2, ... , e_k with k >= 2, we have R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
 * For expressions e_1 and e_2, we have R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}, where + denotes concatenation, and × denotes the cartesian product.
 * Given an expression representing a set of words under the given grammar, return the sorted list of words that the expression represents.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: "{a,b}{c,{d,e}}"
 * Output: ["ac","ad","ae","bc","bd","be"]
 * Example 2:
 * <p>
 * Input: "{{a,z},a{b,c},{ab,z}}"
 * Output: ["a","ab","ac","z"]
 * Explanation: Each distinct word is written only once in the final answer.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= expression.length <= 50
 * expression[i] consists of '{', '}', ','or lowercase English letters.
 * The given expression represents a set of words based on the grammar given in the description.
 *
 * [Google]
 *
 * https://rosettacode.org/wiki/Brace_expansion#Java
 */
public class BraceExpansionII {


    public static void main(String args[]) {
        System.out.println(braceExpansionII("{{a,z},a{b,c},{ab,z}}"));
        System.out.println(braceExpansionII("{a{b,c}}{{d,e}f{g,h}}"));
    }

    public static List<String> braceExpansionII(String expression) {
        if (expression.isEmpty()) return null;

        return braceExpansionIIHelper(expression.trim());
    }

    /**
     * lets understand the problem with an example;
     * {{a,z},a{b,c},{ab,z}}
     * <p>
     * first what we can easily see is there is extra parenthesis which need to be remove in order to proceed ahead, lets suppose our algo
     * does this and it gives us back [ count braces and remove ]
     * <p>
     * {a,z},a{b,c},{ab,z}
     * <p>
     * now, we can see below things
     * 1. component (separated by "," ) : made of equal number of parenthesis ({, }) here we have [ {a,z} ] [a{b,c} ] , [{ab,z}]
     * 2. each component could have multiple parts in it
     * like [ a{b,c} ] has [a] [ {b,c}] or
     * like [ {b,c} ] has [b] and [c]
     * 3. a component could be a single char only like [a] which result to only 'a'
     * <p>
     * now our task boil down to first find the component and then resolve using R(x) = {x}
     * <p>
     * resolving R(x) = {x} is trivial
     * <p>
     * Now lets talk about above two ground aspects
     * 1. Finding component (separated by "," )
     * 2. Finding each component could have multiple parts in it
     * <p>
     * Lets talk about
     * 1. Finding component (separated by "," )
     * How do we find it? what is the intuition it gives us, lets take an example
     * {a,z},a{b,c},{ab,z}
     * <p>
     * We can observe that each component is bounded by equal number of { and }
     * hence, we'll apply this logic and find the components, then we'll get
     * 1. [a,z] ,
     * 2. [a [ b,c ] ]
     * 3. [ab,z]
     * <p>
     * Lets talk about
     * 2. Finding each component could have multiple parts in it
     * each component it self has multiple part in it
     * like [a,z] has [a], [z]
     * we can observe that they are seperated by "," hence to reduce it to third equation (a character only) we;ll use ","
     * <p>
     * but the component like
     * [a [b,c] ] is complex , we need to further drill down it with using product ("*") logic
     * we can divided this component as
     * [a] [b,c] and process them together ( merge ), how do we know that we need to apply "*" or just ","
     * we can find out as when we drilling this component we assume they are "*" only until we find ",".
     * <p>
     * <p>
     * <p>
     * How do we merge them ?
     * [a] , [b,c]
     * we can simply iterate and append to it
     * <p>
     * <p>
     * <p>
     * Notice, here each component could be a single char, or multiple char or multiple component it self, hence we need List
     *
     * @param expression
     * @return
     */
    public static List<String> braceExpansionIIHelper(String expression) {


        Stack<List<String>> components = new Stack<>(); // each stack element is a component since component could be component it self, so its List

        int parenthesis = 0;
        int startOfComponent = -1;
        char expressionHandler = ',';
        int n = expression.length();

        for (int i = 0; i < n; i++) {

            char currentChar = expression.charAt(i);

            //means a potential component start here
            if (currentChar == '{') {

                int endOfComponentIndex = i;
                parenthesis = 1;

                //find where is the end of this component
                while (expression.charAt(endOfComponentIndex) != '}' || parenthesis != 0) { //parenthesis!=0 to make sure the are equal { and }

                    endOfComponentIndex++;
                    char x = expression.charAt(endOfComponentIndex);

                    if (x == '}') parenthesis--;
                    if (x == '{') parenthesis++;
                }

                startOfComponent = i + 1;
                //we found a component, process it similar as above until it drill down to R(x) = {x}
                String component = expression.substring(startOfComponent, endOfComponentIndex);
                List<String> componentLists = braceExpansionIIHelper(component);

                //evaluate what operation we need to apply for this component list
                if (expressionHandler == '*') {
                    //means merge them
                    List<String> mergedComponentLists = merge(components.pop(), componentLists);
                    components.push(mergedComponentLists);
                } else {
                    //means they are need to appended only because our expressionHandler was ","
                    components.push(componentLists);
                }

                i = endOfComponentIndex; // find next component
                expressionHandler = '*';// assume we always gonna multiple the component , why? what happen when we have {ab} and they divided into [a] [b] we need to multiply this component to make it again [ab]


            } else if (Character.isLetter(currentChar)) {
                //means a potential single char component we found here
                //create a component list with this char
                List<String> innerList = new ArrayList<>();
                innerList.add("" + currentChar);

                //check does this need to merge with previous component lists ?
                if (expressionHandler == '*') {
                    //means merge them
                    List<String> mergedComponentLists = merge(components.pop(), innerList);
                    components.push(mergedComponentLists);
                } else {
                    //means they are need to appended only because our expressionHandler was ","
                    components.push(innerList);
                }

                expressionHandler = '*';


            } else if (currentChar == ',') {
                expressionHandler = ',';
            } else if (i == expression.length() - 1) { //expression handeld, means we need to join them only
                expressionHandler = ',';
            }


        }

        List<String> unionedComponents = union(components);
        Collections.sort(unionedComponents);


        return unionedComponents;
    }

    private static List<String> union(Stack<List<String>> components) {

        Set<String> uniqueComponents = new HashSet<>();

        while (!components.isEmpty()) {
            for (String cop : components.pop()) {

                if (!uniqueComponents.contains(cop))
                    uniqueComponents.add(cop);
            }
        }

        return new ArrayList<>(uniqueComponents);
    }

    private static List<String> merge(List<String> a, List<String> b) {


        List<String> uniqueComponents = new ArrayList<>();

        for (String e : a) {

            for (String p : b) {

                String current = e + p;
                if (!uniqueComponents.contains(current))
                    uniqueComponents.add(current);
            }
        }
        return uniqueComponents;
    }

}
