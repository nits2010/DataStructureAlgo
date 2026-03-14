package DataStructureAlgo.Java.LeetCode.tree.serializeDeserialize;

import  DataStructureAlgo.Java.helpers.templates.TreeNode;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-29
 * Question Title: I Serialize Deserialize
 * Link: TODO: Add Link
 * Description:
 * Description:
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * DP-BaseProblem {@link }
 * <p><p>
 * Tags
 * -----
 *
 <p><p>
 * Company Tags
 * -----
 * <p>
 * -----
 * @Editorial <p><p>
 * -----
 * @OptimalSolution {@link }
 */

public interface ISerializeDeserialize {

    String NULL_INDICATOR = "$";
    String SEPARATOR = ",";
    String serialize(TreeNode root);

    TreeNode deserialize(String data);
}
