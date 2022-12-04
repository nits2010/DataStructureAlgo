package DataStructureAlgo.Java.LeetCode.tree.serializeDeserialize.genrictree;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Nitin Gupta
 * Date: 2019-08-22
 * Description:
 */
public class NArrayTreeNode {

    public int val;
    public List<NArrayTreeNode> children = new ArrayList<>();

    public NArrayTreeNode() {
    }

    public NArrayTreeNode(int v) {
        this.val = v;
    }

    public NArrayTreeNode(int _val, List<NArrayTreeNode> _children) {
        val = _val;
        children = _children;
    }
}
