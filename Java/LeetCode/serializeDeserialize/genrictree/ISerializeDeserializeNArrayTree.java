package Java.LeetCode.serializeDeserialize.genrictree;

/**
 * Author: Nitin Gupta(nitin.gupta@walmart.com)
 * Date: 2019-07-29
 * Description:
 */
public interface ISerializeDeserializeNArrayTree {

    String seperator = "$";

    String serialize(NArrayTreeNode root);

    NArrayTreeNode deserialize(String data);
}
