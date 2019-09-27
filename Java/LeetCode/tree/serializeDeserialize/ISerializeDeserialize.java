package Java.LeetCode.tree.serializeDeserialize;

import Java.LeetCode.templates.TreeNode;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-29
 * Description:
 */
public interface ISerializeDeserialize {

    String NULL_INDICATOR = "$";
    String SEPARATOR = ",";
    String serialize(TreeNode root);

    TreeNode deserialize(String data);
}
