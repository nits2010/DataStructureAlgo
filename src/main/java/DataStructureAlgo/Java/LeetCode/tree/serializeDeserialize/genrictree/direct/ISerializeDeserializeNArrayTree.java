package DataStructureAlgo.Java.LeetCode.tree.serializeDeserialize.genrictree.direct;

import DataStructureAlgo.Java.helpers.templates.NArrayTreeNode;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-29
 * Question Title: I Serialize Deserialize N Array Tree
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

public interface ISerializeDeserializeNArrayTree {

    String NULL_INDICATOR = "$";
    String SEPARATOR = ",";

    String serialize(NArrayTreeNode root);

    NArrayTreeNode deserialize(String data);
}
