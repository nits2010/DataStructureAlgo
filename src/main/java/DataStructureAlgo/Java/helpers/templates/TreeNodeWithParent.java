package DataStructureAlgo.Java.helpers.templates;

/**
 * Author: Nitin Gupta
 * Date:16/08/24
 * Question Category:
 * Description:
 * <p>
 * File reference
 * -----------
 * Duplicate {@link}
 * Similar {@link}
 * extension {@link }
 * <p>
 * Tags
 * -----
 *
 *
 * <p>
 * Company Tags
 * -----
 *
 * @Editorial
 */

public class TreeNodeWithParent extends TreeNode {
    public TreeNode parent;

    public TreeNodeWithParent(int val) {
        super(val);
    }

    public TreeNodeWithParent(int val, TreeNode parent) {
        super(val);
        this.parent = parent;
    }
}

