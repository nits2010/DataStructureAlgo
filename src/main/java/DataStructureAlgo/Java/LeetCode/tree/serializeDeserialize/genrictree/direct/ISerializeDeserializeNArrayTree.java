package DataStructureAlgo.Java.LeetCode.tree.serializeDeserialize.genrictree.direct;

import DataStructureAlgo.Java.helpers.templates.NArrayTreeNode;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-29
 * Description:
 */
public interface ISerializeDeserializeNArrayTree {

    String NULL_INDICATOR = "$";
    String SEPARATOR = ",";

    String serialize(NArrayTreeNode root);

    NArrayTreeNode deserialize(String data);
}
