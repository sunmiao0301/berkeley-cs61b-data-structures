import java.time.temporal.Temporal;

class Solution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        //[5,1,4]
        boolean ret = isValidBST(root);
    }
        //public boolean ret =
        public static boolean isValidBST(TreeNode root) {
            //Stack<Integer> stack = new Stack<>();
            int pre = 0;
            int rootval = root.val;
            Boolean ret = new Boolean(true);
            dfs(root, pre, ret, rootval);
            return ret;
        }
        public static void dfs(TreeNode root, int pre, Boolean ret, int rootval) {
            if (root.left != null)
                dfs(root.left, pre, ret, rootval);
            if (root.val == rootval || root.val > pre)
                pre = root.val;
            else {
                ret = false;
                return;
            }
            if (root.right != null)
                dfs(root.right, pre, ret, rootval);
        }
}