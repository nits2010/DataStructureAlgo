package DataStructureAlgo.Java.helpers.templates;

public class TreeNodeWithRandom extends TreeNode {
    public TreeNode random;

    public TreeNodeWithRandom(int value) {
        super(value);
    }

    public TreeNodeWithRandom(int value, TreeNode random) {
        super(value);
        this.random = random;
    }

    public TreeNodeWithRandom(int value, TreeNode left, TreeNode right, TreeNode random) {
        super(value);
        this.random = random;
        this.left = left;
        this.right = right;
    }


}
