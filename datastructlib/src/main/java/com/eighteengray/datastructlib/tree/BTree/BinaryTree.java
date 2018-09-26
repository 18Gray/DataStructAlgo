package com.eighteengray.datastructlib.tree.BTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.swing.tree.TreeNode;


public class BinaryTree
{
    public Node root;   //有一个根节点
    public static int index;

    // 用数组构建二叉树
    public Node CreateBTree(int[] a){
        Node root = null;
        root = new Node(a[index]);
        index++;
        root.setLChild(CreateBTree(a));
        index++;
        root.setRChild(CreateBTree(a));
        return root;
    }

    // 根据前序遍历序列和中序遍历序列重建二叉树
    public static Node rebuildBinaryTreeByPreAndIn(List<Node> preOrder, List<Node> inOrder) {
        Node root = null;   //定义二叉树根节点
        List<Node> leftPreOrder;    //左子树前序遍历序列
        List<Node> rightPreOrder;   //右子树前序遍历序列
        List<Node> leftInOrder;     //左子树中序遍历序列
        List<Node> rightInOrder;    //右子树中序遍历序列
        int preNum = 0;
        int inNum = 0;

        if((!preOrder.isEmpty()) && (!inOrder.isEmpty())) {
            root = preOrder.get(0); //前序遍历的第一个节点即为根节点

            //根据root的位置，可以确定inOrder左边的是左子树序列，右边的是右子树序列
            inNum = inOrder.indexOf(root);  //找到root在inOrder中的位置
            leftInOrder = inOrder.subList(0, inNum);    //左子树中序遍历序列
            rightInOrder = inOrder.subList(inNum+1, inOrder.size());    //右子树中序遍历序列

            preNum = leftInOrder.size();    //前序序列的分割点
            leftPreOrder = preOrder.subList(1, preNum+1);
            rightPreOrder = preOrder.subList(preNum+1, preOrder.size());

            root.left = rebuildBinaryTreeByPreAndIn(leftPreOrder, leftInOrder);   // root的左子树就是preorder和inorder的左侧区间而形成的树
            root.right = rebuildBinaryTreeByPreAndIn(rightPreOrder, rightInOrder);
        }
        return root;
    }

    // 根据中序和后序遍历序列重建二叉树
    public static Node rebuildBinaryTreeByInAndPost(List<Node> inOrder, List<Node> postOrder) {
        Node root = null;   //新建根节点
        List<Node> leftInOrder;
        List<Node> rightInOrder;
        List<Node> leftPostOrder;
        List<Node> rightPostOrder;
        int inNum = 0;
        int postNum = 0;

        if((inOrder.size() != 0) && (postOrder.size() != 0)) {
            root = postOrder.get(postOrder.size()-1);   //后序遍历序列的最后一个节点即为根节点

            //由root节点的位置可以分割中序遍历序列
            inNum = inOrder.indexOf(root);
            leftInOrder = inOrder.subList(0, inNum);
            rightInOrder = inOrder.subList(inNum+1, inOrder.size());

            postNum = leftInOrder.size();   //后序遍历序列的左右子树分割点
            leftPostOrder = postOrder.subList(0, postNum);
            rightPostOrder = postOrder.subList(postNum, postOrder.size());

            root.left = rebuildBinaryTreeByInAndPost(leftInOrder, leftPostOrder);
            root.right = rebuildBinaryTreeByInAndPost(rightInOrder, rightPostOrder);
        }
        return root;
    }

    // 在二叉树中插入节点
    Node insertNode(Node root, Node node){
        if(root == node){
            return node;
        }
        Node tmp = new Node(0);
        tmp = root;
        Node last = null;
        while(tmp!=null){
            last = tmp;
            if(tmp.data>node.data){
                tmp = tmp.left;
            }else{
                tmp = tmp.right;
            }
        }
        if(last!=null){
            if(last.data>node.data){
                last.left = node;
            }else{
                last.right = node;
            }
        }
        return root;
    }


    //先序遍历
    public void prevOrder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.getData()+",");
        prevOrder(root.getLChild());
        prevOrder(root.getRChild());
    }

    public void prevOrder2(Node root){
        if(root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop(); //出栈顶元素
            System.out.print(cur.data + " ");

            // 关键点：要先压入右孩子，再压入左孩子，这样在出栈时会先打印左孩子再打印右孩子
            if(cur.right != null) {
                stack.push(cur.right);
            }
            if(cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    //中序遍历
    public void inOrder(Node root){
        if(root==null){
            return;
        }
        inOrder(root.getLChild());
        System.out.print(root.getData()+",");
        inOrder(root.getRChild());
    }

    //中序遍历迭代解法，用栈先把根节点的所有左孩子都添加到栈内，然后输出栈顶元素，再处理栈顶元素的右子树
    public void inOrder2(Node root){
        if(root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Node cur = root;

        while (true) {
            while (cur != null) {   // 先添加一个非空节点所有的左孩子到栈
                stack.push(cur);
                cur = cur.left;
            }

            // 因为此时已经没有左孩子了，所以输出栈顶元素
            cur = stack.pop();
            System.out.print(cur.data + " ");

            cur = cur.right;
        }
    }

    //后序遍历
    public void postOrder(Node root){
        if(root==null){
            return;
        }
        postOrder(root.getLChild());
        postOrder(root.getRChild());
        System.out.print(root.getData()+",");
    }

    //后序遍历，迭代
    // 需要用到两个栈，分别将左子树和右子树压入栈1，再取出第一个栈中的元素存放到栈2中，完成后序遍历的逆序输出
    public void postOrder2(Node root){
        if(root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Stack<Node> out = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            out.push(cur);
            if(cur.left != null) {
                stack.push(cur.left);
            }
            if(cur.right != null) {
                stack.push(cur.right);
            }
        }
        while (!out.isEmpty()) {
            System.out.print(out.pop().data + " ");
        }
    }

    //分层遍历二叉树（按层次从上往下，从左往右）迭代
    // 相当于广度优先搜索，使用队列实现。队列初始化，将根节点压入队列。当队列不为空，进行如下操作：弹出一个节点
    public void levelOrder(Node root){
        if(root == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.removeFirst();
            System.out.print(cur.data + " ");

            if(cur.left != null) {
                queue.add(cur.left);
            }
            if(cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    /**
     * 层次遍历，用到队列
     * 取出一个节点，将他入队，
     若队列不为空，取出队列的头部元素并出队，若该元素左孩子不为空，左孩子入队；右孩子不为空，右孩子入队；
     重复②；
     */
    public void levelOrder2(Node root)
    {
        Queue<Node> queue = new LinkedList<Node>();
        LinkedList<Node> list = new LinkedList<Node>();
        queue.offer(root);
        while (!queue.isEmpty())
        {
            Node pre = queue.poll();
            list.add(pre);
            if (pre.getLChild() != null)
                queue.offer(pre.getLChild());
            if (pre.getRChild() != null)
                queue.offer(pre.getRChild());
        }
        Iterator<Node> it = list.iterator();
        while (it.hasNext())
        {
            Node cur = (Node) it.next();
            System.out.print(cur.getData() + ", ");
        }
    }


    //二叉树的节点个数，递归
    public static int getNodesNum1(Node root) {
        if(root == null) {
            return 0;
        }
        int left = getNodesNum1(root.left);
        int right = getNodesNum1(root.right);
        return left + right + 1;
    }

    //二叉树的节点个数，迭代
    // java用LinkedList来模拟queue的用法
    public static int getNodesNum2(Node root) {
        if(root == null) {
            return 0;
        }
        int count = 1;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.removeFirst();
            if(cur.left != null) {
                queue.add(cur.left);
                count++;
            }
            if(cur.right != null) {
                queue.add(cur.right);
                count++;
            }
        }
        return count;
    }

    /**
     * 求二叉树中叶子节点的个数，递归
     */
    public static int getLeafNodeNum1(Node root) {
        if(root == null) {
            return 0;
        }
        if(root.left == null && root.right == null) {
            return 1;
        }
        return getLeafNodeNum1(root.left) + getLeafNodeNum1(root.right);
    }

    /**
     * 求二叉树中叶子节点的个数，迭代
     *  基于层序遍历的思想
     */
    public static int getLeafNodeNum2(Node root) {
        if(root == null) {
            return 0;
        }

        int count = 0;
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node cur = queue.removeFirst();
            if(cur.left == null && cur.right == null) {
                count++;
            }
            if(cur.left != null) {
                queue.add(cur.left);
            }
            if(cur.right != null) {
                queue.add(cur.right);
            }
        }
        return count;
    }

    /**
     * 求二叉树第K层节点的个数
     * （1）如果二叉树为空或者k<1返回0
     * （2）如果二叉树不为空并且k==1，返回1
     * （3）如果二叉树不为空且k>1，返回root左子树中k-1层的节点个数与root右子树k-1层节点个数之和
     *
     * 求以root为根的k层节点数目 等价于 求以root左孩子为根的k-1层（因为少了root那一层）节点数目 加上
     * 以root右孩子为根的k-1层（因为少了root那一层）节点数目
     *
     * 所以遇到树，先把它拆成左子树和右子树，把问题降解
     */
    public static int getKthLevelNodesNum1(Node root, int k) {
        if(root == null || k < 1) {
            return 0;
        }
        if(k == 1) {
            return 1;
        }

        int left = getKthLevelNodesNum1(root.left, k-1);     //求root左子树的k-1层节点数
        int right = getKthLevelNodesNum1(root.right, k-1);
        return left+right;
    }

    /**
     * 求二叉树第K层节点数目，迭代
     *  利用层序遍历的思想
     */
    public static int getKthLevelNodesNum2(Node root, int k) {
        if(root == null || k < 1) {
            return 0;
        }
        if(k == 1) {
            return 1;
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        int currentLevelNodes = 1;
        int nextLevelNodes = 0;
        int i = 1;

        while (!queue.isEmpty() && i < k) {
            Node cur = queue.removeFirst(); //移除队头位置
            currentLevelNodes--;    //当前层节点数减1
            if(cur.left != null) {
                queue.add(cur.left);
                nextLevelNodes++;
            }
            if(cur.right != null) {
                queue.add(cur.right);
                nextLevelNodes++;
            }
            if(currentLevelNodes == 0) {
                currentLevelNodes = nextLevelNodes;
                nextLevelNodes = 0;
                i++;
            }

        }
        return currentLevelNodes;
    }

    //查找某个节点
    public Node findNode(Node root,int x){
        if(root==null){
            return null;
        }else if(root.getData()==x){
            return root;
        }
        Node leftNode = findNode(root.getLChild(),x);
        if(null!=leftNode)
            return leftNode;
        Node rightNode = findNode(root.getRChild(),x);
        if(null!=rightNode)
            return rightNode;
        return null;
    }

    //递归判断一个节点是否在树里
    public static boolean findNode(Node root, Node n) {
        if(root == null || n == null) {
            return false;
        }
        if(root == n) {
            return true;
        }
        //先尝试在左子树里查找
        boolean found = findNode(root.left, n);
        if(!found) {    //如果不在左子树中
            found = findNode(root.right, n);    //在右子树中查找
        }
        return found;
    }

    //返回某节点的父节点
    public Node getParent(Node root, int x)
    {
        if (root == null)
            return null;
        Node childL = root.getLChild();
        Node childR = root.getRChild();
        if (childL != null && childL.getData() == x)
            return root;
        if (childR != null && childR.getData() == x)
            return root;
        Node parentL = getParent(root.getLChild(), x);
        if (parentL != null)
            return parentL;
        Node parentR = getParent(root.getRChild(), x);
        if (parentR != null)
            return parentR;
        return null;
    }

    /**
     * 求二叉树中两个节点的最低公共祖先节点
     * 递归解法：
     * （1）如果两个节点分别在根节点的左子树和右子树，则返回根节点
     * （2）如果两个节点都在左子树，则递归处理左子树；如果两个节点都在右子树，则递归处理右子树
     */
    public static Node getLastCommonParent(Node root, Node n1, Node n2) {
        if(findNode(root.left, n1)) {    //如果节点n1在树的左子树
            if(findNode(root.right, n2)) {  //节点n2在树的右子树
                return root;
            }
            else {  //节点n2也在左子树，则递归处理左子树
                return getLastCommonParent(root.left, n1, n2);
            }
        }
        else {  //n1在右子树
            if(findNode(root.left, n2)) {
                return root;
            }
            else {
                return getLastCommonParent(root.right, n1, n2);
            }
        }
    }

    //求二叉树中两个节点的最低公共祖先节点，更加简便的递归方法
    public static Node getLastCommonParent1(Node root, Node n1, Node n2) {
        if(root == null) {
            return null;
        }
        //如果两者有一个与root 相同
        if(root.equals(n1) || root.equals(n2)) {
            return root;
        }

        Node commonInLeft = getLastCommonParent1(root.left, n1, n2);
        Node commonInRight = getLastCommonParent1(root.right, n1, n2);

        //如果一个在左子树找到一个在右子树找到，则为root
        if(commonInLeft != null && commonInRight != null) {
            return root;
        }
        //其他情况要不然在左子树要不然在右子树
        if(commonInLeft != null) {
            return commonInLeft;
        }
        return commonInRight;
    }

    /**
     * 获取两个节点的最低公共祖先节点，复杂度比较低，也是面试官想看到的解法
     *      算法思路：
     *          1）分别获得一条从根节点到指定节点的路径，该过程需要辅助空间List来存放路径上的节点
     *          2）求这两条路径的最后一个橡胶的节点即为题目想要找到的节点
     *      得到两条路在最坏情况下的时间复杂度是O(n),通常情况下两条路径的长度是O(logn)
     */
    public static Node getLastCommonParent2(Node root, Node n1, Node n2) {
        if(root == null || n1 == null || n2 == null) {
            return null;
        }
        ArrayList<Node> path1 = new ArrayList<>();
        getNodePath(root, n1, path1);
        ArrayList<Node> path2 = new ArrayList<>();
        getNodePath(root, n2, path2);

        return getCommonNode(path1, path2);

    }

    //获得两条路径的最后一个公共节点
    public static Node getCommonNode(List<Node> path1, List<Node> path2) {
        int i = 0;
        Node res = null;
        while (i < path1.size() && i < path2.size()) {
            if(path1.get(i) == path2.get(i)) {
                res = path1.get(i);
                i++;
            }
            else {
                break;
            }
        }
        return res;
    }



    //获得高度
    public static int getHeight(Node root){
        if(root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        return Math.max(left, right)+1;
    }

    public static int getHeight2(Node root){
        Node cur = root;
        int height = 0;
        while(cur!=null){
            cur=cur.getLChild();
            height++;
        }
        return height;
    }

    /**
     * 判断两颗二叉树是否为相同的二叉树,递归
     */
    public static boolean isSameTree1(Node r1, Node r2) {
        // 如果两棵二叉树都为空，返回真
        if(r1 == null && r2 == null) {
            return true;
        }
        // 如果两棵二叉树一棵为空，另一棵不为空，返回假
        else if(r1 == null || r2 == null) {
            return false;
        }

        if(r1.data != r2.data) {
            return false;
        }

        boolean left = isSameTree1(r1.left, r2.left);    //分别比较左子树和右子树是否相等
        boolean right = isSameTree1(r1.right, r2.right);
        return left && right;
    }

    /**
     * 判断两颗二叉树是否相同，迭代解法
     *    分别用两个栈来存储两棵树，采用前序遍历的方法依次比较两颗二叉树的各个节点的值是否相等，
     *    如果不相等直接返回空，相等就继续将后面的节点入栈
     */
    public static boolean isSameTree2(Node r1, Node r2) {
        if(r1 == null && r2 == null) {
            return true;
        }
        else if(r1 == null || r2 == null) {
            return false;
        }

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        s1.add(r1);
        s2.add(r2);
        while (!s1.isEmpty() && !s2.isEmpty()) {
            Node n1 = s1.pop();
            Node n2 = s2.pop();
            if(n1 == null && n2 == null) {
                continue;
            }
            else if(n1!=null && n2 != null && n1.data == n2.data) {
                s1.push(n1.right);
                s1.push(n1.left);
                s2.push(n2.right);
                s2.push(n2.left);
            }
            else {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断二叉树是不是平衡二叉树 递归解法：
     * （1）如果二叉树为空，返回真
     * （2）如果二叉树不为空，如果左子树和右子树都是AVL树并且左子树和右子树高度相差不大于1，返回真，其他返回假
     */
    public static boolean isAVL(Node root) {
        if(root == null) {
            return true;
        }
        if(Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) {
            return false;
        }
        return isAVL(root.left) && isAVL(root.right);
    }

    /**
     * 将二叉查找树变为有序的双向链表 要求不能创建新节点，只调整指针。
     * 节点的左即为链表前一节点，右即为链表后一节点
     * 递归解法：
     * 感觉是最清晰的递归解法，但要注意递归完，root会在链表的中间位置，因此要手动
     * 把root移到链表头或链表尾
     */
    public static Node convertBST2DLL1(Node root) {
        root = convertBST2DLLSub(root);

        // root会在链表的中间位置，因此要手动把root移到链表头
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    /**
     * 递归转换二叉查找树为双向链表(DLL)
     */
    public static Node convertBST2DLLSub(Node root) {
        if(root == null || (root.left == null && root.right == null)) {
            return root;
        }

        Node tmp = null;
        if(root.left != null) {     //处理左子树
            tmp = convertBST2DLLSub(root.left);
            while (tmp.right != null) { //寻找最右节点
                tmp = tmp.right;
            }
            tmp.right = root;   //把左子树处理后结果和root连接
            root.left = tmp;
        }
        if(root.right != null) {    //处理右子树
            tmp = convertBST2DLLSub(root.right);
            while (tmp.left != null) {  //寻找最左节点
                tmp = tmp.left;
            }
            tmp.left = root;    //把右子树处理后结果和root连接
            root.right = tmp;
        }
        return root;
    }

    /**
     * 二叉查找树转换为双向链表，迭代解法
     * 基本思想同中序遍历二叉树
     */
    public static Node convertBST2DLL2(Node root) {
        if(root == null) {
            return null;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = root;    //指向当前正在处理的节点
        Node old = null;    //前一节点
        Node head = null;   //双向链表的头结点

        while (true) {
            while (cur != null) {   //将所有左节点全部入栈
                stack.push(cur);
                cur = cur.left;
            }

            if(stack.isEmpty()) {
                break;
            }

            //由于此时没有左孩子了，所以输出栈顶元素
            cur = stack.pop();
            if(old != null) {
                old.right = cur;
            }
            if(head == null) {  //第一个结点为双向链表头结点
                head = cur;
            }
            old = cur;  //更新old
            cur = cur.right;    //准备处理右子树
        }
        return head;
    }

    /**
     * 求二叉树中节点的最大距离 即二叉树中相距最远的两个节点之间的距离。 (distance / diameter)
     * 递归解法：
     * （1）如果二叉树为空，返回0，同时记录左子树和右子树的深度，都为0
     * （2）如果二叉树不为空，最大距离要么是左子树中的最大距离，要么是右子树中的最大距离，
     * 要么是左子树节点中到根节点的最大距离+右子树节点中到根节点的最大距离，
     * 同时记录左子树和右子树节点中到根节点的最大距离。
     * 计算一个二叉树的最大距离有两个情况:

     情况A: 路径经过左子树的最深节点，通过根节点，再到右子树的最深节点。
     情况B: 路径不穿过根节点，而是左子树或右子树的最大距离路径，取其大者。
     只需要计算这两个情况的路径距离，并取其大者，就是该二叉树的最大距离
     */
    public static Result getMaxDistance(Node root) {
        if(root == null) {
            Result empty = new Result(0, -1);   // 目的是让调用方 +1 后，把当前的不存在的 (NULL) 子树当成最大深度为 0
            return empty;
        }
        //计算出左右子树分别最大距离
        Result lmd = getMaxDistance(root.left);
        Result rmd = getMaxDistance(root.right);

        Result res = new Result();
        res.maxDepth = Math.max(lmd.maxDepth, rmd.maxDepth)+1;  //计算最大深度
        //取情况A和情况B中较大值
        res.maxDistance = Math.max(lmd.maxDepth+rmd.maxDepth, Math.max(lmd.maxDistance, rmd.maxDistance));
        return res;
    }

    private static class Result {
        int maxDistance;
        int maxDepth;
        public Result() {

        }
        public Result(int maxDistance, int maxDepth) {
            this.maxDistance = maxDistance;
            this.maxDepth = maxDepth;
        }
    }

    /**
     * 把从根节点出发到node节点的路径所有经过的节点添加到路径path中
     */
    public static boolean getNodePath(Node root, Node node, ArrayList<Node> path) {
        if(root == null) {
            return false;
        }
        path.add(root); //先将根节点添加到路径中
        if(root == node) {
            return true;
        }
        boolean found = getNodePath(root.left, node, path); //在左子树中找node节点
        if(!found) {    //左子树中没有node节点，在右子树中查找
            found = getNodePath(root.right, node, path);
        }
        if(!found) {    //如果左右子树中都不存在node节点，则将之前加到path中的root节点删除
            path.remove(root);
        }
        return found;
    }

    /**
     * 判断二叉树是否为完全二叉树，迭代
     *  若设二叉树的深度为h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，
     第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。
     有如下算法，按层次（从上到下，从左到右）遍历二叉树，当遇到一个节点的左子树为空时，
     则该节点右子树必须为空，且后面遍历的节点左右子树都必须为空，否则不是完全二叉树。
     */
    public static boolean isCompleteBinaryTree1(Node root) {
        if(root == null) {
            return false;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        boolean mastHaveNoChild = false;
        boolean result = false;

        while (!queue.isEmpty()) {
            Node cur = queue.remove();  //队列先进先出
            if(mastHaveNoChild){    // 已经出现了有空子树的节点了，后面出现的必须为叶节点（左右子树都为空）
                if(cur.left != null || cur.right != null) {
                    result = false;
                    break;
                }
            }
            else {
                if(cur.left == null && cur.right != null) { //如果左子树为空，右子树非空则说明不是完全二叉树
                    result = false;
                    break;
                }
                else if(cur.left != null && cur.right == null) {    //如果左子树非空，右子树为空，则左子节点不能有左右子树
                    mastHaveNoChild = true;
                    queue.add(cur.left);
                }
                else if(cur.left != null && cur.right != null) {    //如果左右子孩子都非空，则加入队列继续循环
                    queue.add(cur.left);
                    queue.add(cur.right);
                }
                else {  // 如果左右子树都为空，则后面的必须也都为空子树
                    mastHaveNoChild = true;
                }
            }
        }
        return result;
    }

    /**
     * 判断二叉树是否是完全二叉树，递归解法
     */
    public static boolean isCompleteBinaryTree2(Node root) {
        return isCompleteTree(root).height != -1;
    }

    public static Pair isCompleteTree(Node root) {
        if(root == null) {
            return new Pair(0, true);
        }
        Pair left = isCompleteTree(root.left);
        Pair right = isCompleteTree(root.right);

        //如果左树是满树，且左右子树同高度，则是唯一可能形成满树（右树也是满树）的情况
        if(left.isFull && left.height == right.height) {
            return new Pair(1+left.height, right.isFull);
        }

        //左树非满，但右树是满树，且左树比右树高度高1
        if(right.isFull && left.height == right.height +1) {
            return new Pair(left.height+1, false);
        }
        //其他情况都不是完全二叉树
        return new Pair(-1, false);
    }

    private static class Pair {
        int height; //树的高度
        boolean isFull; //是否是满树

        public Pair(int height, boolean isFull) {
            this.height = height;
            this.isFull = isFull;
        }
    }

    /**
     * 两颗二叉树A,B，判断B是不是A的子树
     * 解题思路：
     *      1）在树A中找到树B的根节点值一样的节点R
     *      2）判断A中以R为根节点的子树是不是包含和树B一样的结构
     */
    public static boolean isSubTree(Node root1, Node root2) {
        boolean result = false;
        if(root1 != null && root2 != null) {    //两颗二叉树都不为空的时候
            //如果在A中找到和B的根节点值相同的节点R，则调用doseTree1HasTree2做第二步判断
            if(root1.data == root2.data) {
                result = doseTree1HasTree2(root1, root2);
            }
            //如果在A中没有找到和B的根节点相同的节点R，则递归遍历左右子树寻找
            if(!result) {
                result = isSubTree(root1.left, root2);
            }
            if(!result) {
                result = isSubTree(root1.right, root2);
            }
        }
        return result;
    }

    //第二步，判断A中以R为根节点的子树是不是和树B有相同的结构
    public static boolean doseTree1HasTree2(Node root1, Node root2) {
        //这里一定是root2的判断在前，若先判断root1则可能会出现root1和root2都为空的情况，此时返回的是false答案将会是错误的，所以一定要先判断root2
        if(root2 == null) {
            return true;
        }
        if(root1 == null) {
            return false;
        }
        if(root1.data != root2.data) {
            return false;
        }

        //递归判断他们左右子节点的值是否相同
        return doseTree1HasTree2(root1.left, root2.left) &&
                doseTree1HasTree2(root1.right, root2.right);
    }

    /**
     * 求一棵二叉树的镜像
     * 解题过程：（递归）
     *     先前序遍历这棵树的每个节点，如果遍历到的节点有子节点，则交换两个子节点（同时也是交换了它的左右子树），
     *     当交换完所有非叶子结点的子节点以后，就得到了树的镜像
     * 该解法会破坏原二叉树的结构
     */
    public static void mirrorTree(Node root) {
        //如果该树为空树或者是只有一个节点的树，则直接返回
        if(root == null || (root.left == null && root.right == null)) {
            return;
        }
        //交换左右子节点
        Node temp = root.left;
        root.left = root.right;
        root.right = temp;

        if(root.left != null) { //如果左子节点存在
            //递归遍历左子树
            mirrorTree(root.left);
        }
        if(root.right != null) {
            mirrorTree(root.right);
        }
    }

    /**
     * 求一棵二叉树的镜像
     *  迭代解法
     *  仍然采用前序遍历的方法，用栈来实现
     */
    public static void mirrorTree2(Node root) {
        if(root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();

            Node temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;

            if(cur.right != null) { //前序遍历，先压入右节点，再压入左节点
                stack.push(cur.right);
            }
            if(cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    //不改变原二叉树的迭代解法
    public static Node mirrorTree3(Node root) {
        if(root == null) {
            return null;
        }
        Node newRoot = new Node(root.data);

        Stack<Node> stack = new Stack<>();
        Stack<Node> newStack = new Stack<>();
        stack.push(root);
        newStack.push(newRoot);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            Node newCur = newStack.pop();

            if(cur.right != null) {
                stack.push(cur.right);
                newCur.left = new Node(cur.right.data);
                newStack.push(newCur.left);
            }
            if(cur.left != null) {
                stack.push(cur.left);
                newCur.right = new Node(cur.left.data);
                newStack.push(newCur.right);
            }
        }
        return newRoot;
    }

    /**
     * 判断一个序列是否是二叉搜索树的后序遍历序列
     *  根据后序遍历序列的规则，最后一个元素即根元素，比根元素小的是左子树，大的是右子树，然后递归判断
     */
    public static boolean verifySequenceOfBST(int[] sequence, int start, int end) {
        if(sequence == null || start < 0 || end <= 0) {
            return false;
        }

        int root = sequence[end];   //根节点就是最后一个元素
        //在二叉搜索树中左子树的节点都比右子树小
        int i = 0;
        for (; i < end; i++) {
            if(sequence[i] > root) {
                break;
            }
        }

        //在二叉搜索树中右子树的节点大于根节点
        int j = i;  //右子树的第一个元素（序列中的元素）
        for (; j < end; j++) {
            if(sequence[j] < root) {
                return false;
            }
        }

        //判断左子树是不是二叉搜索树
        boolean left = true;
        i--;
        if(i > 0) {
            left = verifySequenceOfBST(sequence, 0, i);
        }

        //判断右子树是不是二叉搜索树
        boolean right = true;
        i++;
        if(i < end) {
            right = verifySequenceOfBST(sequence, i, end-1);
        }
        return (left && right);
    }

    /**
     * 求二叉树中和为某一值的路径
     *  题目描述：从树的根节点开始往下一直到叶节点所经过的节点形成一条路径
     * 解题思路：
     *      用前序遍历的方式访问某一节点时，把该节点加入到路径上，并且累加该节点的值。
     *      如果该节点为叶子节点并且路径中节点值的和刚好等于输入的整数，则当前路径符合要求，可以打印出来；
     *      如果当前节点不是叶子节点，则继续访问它的子节点。
     *      当前节点访问结束后，递归函数自动回到它的父节点。（实际可以用栈来满足）
     *      因此在退出之前要在路径上删除当前节点，并且减去当前节点的值，以确保返回父节点时路径刚好是从根节点到父节点的路径
     */
    public static void findPath(Node root, int sum) {
        if(root == null) {
            return;
        }
        int currentSum = 0;
        //用java里面LinkedList的add和removeLast方法实现栈的先进后出特性，这样方便和面打印路径
        LinkedList<Integer> path = new LinkedList<>();    //用于存储路径


        findPathTemp(root, sum, path, currentSum);
    }

    public static void findPathTemp(Node root, int sum, LinkedList<Integer> path, int currentSum) {
        currentSum += root.data;
        path.addLast(root.data);

        //如果是叶子节点，并且路径上节点值的和等于输入的整数
        boolean isLeaf = false;
        if(root.left == null && root.right == null) {
            isLeaf = true;
        }
        if(currentSum == sum && isLeaf) {
            System.out.println("A path is found:");
            for (int i = 0; i < path.size(); i++) {
                System.out.printf("%d\t", path.get(i));
            }
            System.out.println();
        }

        //如果不是叶子节点，则遍历它的子节点
        if(root.left != null) {
            findPathTemp(root.left, sum, path, currentSum);
        }
        if(root.right != null) {
            findPathTemp(root.right, sum, path, currentSum);
        }
        //在返回父节点之前，在路径上删除当前节点
        path.removeLast();
    }

}
