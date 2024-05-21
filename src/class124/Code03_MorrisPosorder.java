package class124;

import java.util.ArrayList;
import java.util.List;

// 测试链接 : https://leetcode.cn/problems/binary-tree-postorder-traversal/
public class Code03_MorrisPosorder {

	// 不提交这个类
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
	}

	// 提交如下的方法
	public static List<Integer> postorderTraversal(TreeNode head) {
		List<Integer> ans = new ArrayList<>();
		morrisPostorder(head, ans);
		return ans;
	}

	// morris遍历
	// 具体完成后序遍历
	public static void morrisPostorder(TreeNode head, List<Integer> ans) {
		TreeNode cur = head;
		TreeNode mostRight = null;
		while (cur != null) {
			mostRight = cur.left;
			if (mostRight != null) {
				while (mostRight.right != null && mostRight.right != cur) {
					mostRight = mostRight.right;
				}
				if (mostRight.right == null) {
					mostRight.right = cur;
					cur = cur.left;
					continue;
				} else {
					mostRight.right = null;
					collect(cur.left, ans);
				}
			}
			cur = cur.right;
		}
		collect(head, ans);
	}

	public static void collect(TreeNode head, List<Integer> ans) {
		TreeNode tail = reverse(head);
		TreeNode cur = tail;
		while (cur != null) {
			ans.add(cur.val);
			cur = cur.right;
		}
		reverse(tail);
	}

	public static TreeNode reverse(TreeNode from) {
		TreeNode pre = null;
		TreeNode next = null;
		while (from != null) {
			next = from.right;
			from.right = pre;
			pre = from;
			from = next;
		}
		return pre;
	}

}