package DataStructureAlgo.Java.LeetCode.tree.serializeDeserialize;

import  DataStructureAlgo.Java.helpers.templates.TreeNode;

/**
 * Author: Nitin Gupta
 * Date: 2019-07-29
 * Description:
 */
public interface ISerializeDeserialize {

    String NULL_INDICATOR = "$";
    String SEPARATOR = ",";
    String serialize(TreeNode root);

    TreeNode deserialize(String data);
}
