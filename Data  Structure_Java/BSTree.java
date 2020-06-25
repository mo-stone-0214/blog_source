package test;

import java.util.ArrayList;
import java.util.Stack;

import test.Tree2.Node;

public class BSTree {

//结点
static class Node{
	int data;
	Node left;
	Node right;
	public Node(int data){
		this.data = data;
	}
}

//二叉排列树
static class Tree{
	
	/**
	 * 添加数据
	 * @param root 根结点
	 * @param data 添加数据
	 * @return 根结点
	 */
	private Node add(Node root , int data){
		//如果为空树，直接添加
		if (root == null) {
			return new Node(data);
			
		//当数据小于根结点的数据时，添加至左子树中，反之则添加至右子树中
		//相等时结点位于右子树
		} else if (data < root.data) {
			root.left = add(root.left, data);
		} else {
			root.right = add(root.right, data);
		}
		
		return root;
	}
	
	/**
	* 查找指定值的结点
	* @param root 父节点
	* @param data 查找值
	* @return true代表找到
	*/
	private boolean search(Node root, int  data) {
		if (root == null) {return false;}
		if (data == root.data) {return true;}
		else if (data < root.data) {
			return search(root.left,  data);}
		else {
			return search(root.right,  data);}
	}	
	
	/**
	 * 删除指定结点
	 * @param root 根结点
	 * @param data 删除的数据（若二叉树中存在重复数据，优先删除最靠近根结点的结点）
	 */
	private Node remove(Node root , int data){
		//查找指定结点
		Node theNode = search_Node(root,data);
		//若没找到，不进行操作
		if (theNode == null) {
			System.out.println("未找到"+data+"结点，不进行操作");
			return root;
		}
		
		int thedata = theNode.data;
		
		//找到父母结点
		Node parNode = search_parentNode(root,theNode);
		
		//若该结点为叶子节点，直接删除，并删除并将父母结点的指向删除
		if(theNode.left == null && theNode.right == null){
			
			//若为根结点，删除唯一的结点root
			if(parNode == null){
				root = null;
				
			//若在左子树，父母结点的左子结点删除
			}else if(parNode.left == theNode){
				parNode.left = null;
				
			//若在右子树，父母结点的右子结点删除
			}else{
				parNode.right = null;
			}
			
			//指针清空
			theNode = null;
			
			//输出结果并返回根结点
			System.out.println("结点"+thedata+"已删除");
			return root;
		}
		
		//若该结点没有左子树或右子树，直接删除该结点，并将其父母节点的指向变为其右子树或左子树根结点
		if(theNode.left == null || theNode.right == null){
			
			//如果该结点没有左子树
			if (theNode.left == null){
				
				//如果是根结点，新根结点变为根结点的右子结点
				if(parNode == null){
					root = theNode.right;
					
				//如果是其父母结点的左子结点，左子结点变更为该结点的右子节点
				}else if(parNode.left == theNode){
					parNode.left = theNode.right;
					
				//如果是其父母结点的右子节点，右子结点变更为该结点的右子节点
				}else{
					parNode.right = theNode.right;
				}
				
				//清空指针
				theNode = null;
				
				//输出结果并返回根结点
				System.out.println("结点"+thedata+"已删除");
				return root;
				
			//如果该结点没有右子树，其余与上者类似
			}else{
				if(parNode == null){
					root = theNode.left;
				}else if(parNode.left == theNode){
					parNode.left = theNode.left;
				}else{
					parNode.right = theNode.left;
				}
				theNode = null;
				System.out.println("结点"+thedata+"已删除");
				return root;
			}
		}
		
		//若该结点右左右子树，找到左子树中最大值代替，并将原最大值的结点位置由其左子树根结点代替
		if(theNode.left != null && theNode.right != null){
			
			//找到该结点左子树中最大的节点，作为代替的结点
			Node reNode = search_MaxNode(theNode);
			
			//代替结点的父母结点的右子树变更为其左子树
			search_parentNode(root,reNode).right = reNode.left;

			//如果该结点为根结点，新根结点变更为代替结点
			if(parNode == null){
				root = reNode;
			}
			
			//如果该结点不是根结点，即拥有父母结点，并且是其父母结点的左子结点
			//该结点的父母结点的左子结点变为代替结点
	        else if(parNode.left == theNode){
				parNode.left = reNode;
				
			//如果该结点不是根结点，即拥有父母结点，并且是其父母结点的右子结点
			//该结点的父母结点的右子结点变为代替结点
			}else{
				parNode.left = reNode;
			}
			
			//如果代替结点不是该结点的左子结点，代替结点的左子结点变更为该结点的左子结点
			//此时代替结点应在该结点位置
			if(theNode.left != reNode){
				reNode.left = theNode.left;
			}
			
			//代替结点的右子节点变更为该结点的右子节点
			reNode.right = theNode.right;
			
			//清空指针
			theNode = null;
			
			//输出结果并返回根结点
			System.out.println("结点"+thedata+"已删除");
			return root;
		}
		throw new RuntimeException("删除错误");
	}
	
	/**
	 * 寻找父母结点
	 * @param root 根结点
	 * @param theNode 需要找的结点
	 * @return 父母结点（如果没有则为null）
	 */
	private Node search_parentNode(Node root,Node theNode) {
		if (root.data == theNode.data){return null;}
		while(root.left != theNode && root.right != theNode){
			if (root.data <= theNode.data) {root = root.right;}
			else {root = root.left;}
		}
		return root;
	}

	/**
	 * 查找到指定数值的结点，并返回
	 * @param root 根结点
	 * @param data 指定数值
	 * @return 拥有指定数值的结点
	 */
	private Node search_Node(Node root , int data){
		if (root == null) {return null;}
		if (data == root.data) {return root;}
		else if (data < root.data) {
			return search_Node(root.left,  data);}
		else {
			return search_Node(root.right,  data);}
	}
	
	/**
	 * 查找指定节点的左子树中最大值的结点
	 * @param root 指定节点
	 * @return 最大数值的结点
	 */
	private Node search_MaxNode(Node root){
		Node p = root.left;
		while(p.right != null){
			p = p.right;
		}
		return p;
	}
	
}

/**
 * 先序2
 * @param root 树的根结点
 * @return 先序遍历序列数组
 */
public static ArrayList<Object> preOrder2(Node root){
	
	//判断以root为根的树是否为空
	if(root==null) return null;

	//创建存储结点的栈
	Stack<Node> stack = new Stack<>();
	
	//创建先序遍历序列储存的列表
	ArrayList<Object> list = new ArrayList<>();
	
	//创建指针
	Node p;
	
	//将根结点入栈
	stack.push(root);
	
	//循环至栈空
	while(!stack.isEmpty()){
		
		/*
		 * 依据先序遍历的特点：p结点→左子节点→右子结点
		 */
		
		//p指针指向弹出栈顶的结点
		p = stack.pop();
		
		//p结点的数据入组
		list.add(p.data);
		
		//如果有右结点，入栈
		if( p.right != null) stack.push(p.right);
		
		//如果有左节点，入栈并p指针指向左子节点
		if( p.left != null) {
			stack.push(p.left);
			p=p.left;
		}
	}
	
	//返回先序遍历序列数组
	return list;
}


/**
 * 中序
 * @param root 树的根结点
 * @return 中序遍历序列数组
 */
public static ArrayList<Object> inOrder(Node root){

	//判断以root为根的树是否为空
	if(root==null) return null;
	
	//创建存储结点的栈
	Stack<Node> stack = new Stack<>();
	
	//创建中序遍历序列储存的列表
	ArrayList<Object> list = new ArrayList<>();
	
	//创建指针，并指向根结点
	Node p = root;
	
	//循环至p指针为null，以及栈为空。
	while(p!=null || !stack.isEmpty() ){
		
		//依次将左子树结点入栈，直至为null
		while(p!=null){
			stack.add(p);
			p = p.left;
		}
		
		/*
		 * 如果指针p和栈均为空，则遍历完成
		 * 如果栈不为空，弹出的栈顶结点，数据入组，p指针指向该节点右子节点
		 */
		if(!stack.isEmpty()){
			p = stack.pop();
			list.add(p.data);
			p = p.right;
		}
	}
	
	//返回中序遍历序列数组
	return list;
}

//主函数测试
public static void main(String[] args){
	Node root = new Node(80);
	int[] datas = {50,120,60,110,55,70,53};
	Tree BSTree = new Tree();
	for(int i = 0 ; i<datas.length ; i++){
		BSTree.add(root,datas[i]);
	}
	
	ArrayList<Object> list = inOrder(root);
	
	System.out.print("中序遍历序列为：[");
	for (int i = 0 ; i<list.size() ; i++){
		if(i == list.size()-1) {System.out.println(list.get(i)+"]");break;}
		
		System.out.print(list.get(i)+",");
	}
	
    list = preOrder2(root);
	
	System.out.print("先序遍历序列为：[");
	for (int i = 0 ; i<list.size() ; i++){
		if(i == list.size()-1) {System.out.println(list.get(i)+"]");break;}
		
		System.out.print(list.get(i)+",");
	}
	
	int find = 2;
	if (BSTree.search(root, find)) {
		System.out.println("在以"+root.data+"为根结点的二叉排列树中，找到"+find+"元素");
	} else {
		System.out.println("在以"+root.data+"为根结点的二叉排列树中，未找到"+find+"元素");
	}
	
	int find1 = 10;
	if (BSTree.search(root, find1)) {
		System.out.println("在以"+root.data+"为根结点的二叉排列树中，找到"+find1+"元素");
	} else {
		System.out.println("在以"+root.data+"为根结点的二叉排列树中，未找到"+find1+"元素");
	}
	
	//删除
	root = BSTree.remove(root,53);
	root = BSTree.remove(root,50);
	root = BSTree.remove(root,120);
	root = BSTree.remove(root,80);
	root = BSTree.remove(root,80);


	list = inOrder(root);
	System.out.print("中序遍历序列为：[");
	for (int i = 0 ; i<list.size() ; i++){
		if(i == list.size()-1) {System.out.println(list.get(i)+"]");break;}
		
		System.out.print(list.get(i)+",");
	}
	
    list = preOrder2(root);
	
	System.out.print("先序遍历序列为：[");
	for (int i = 0 ; i<list.size() ; i++){
		if(i == list.size()-1) {System.out.println(list.get(i)+"]");break;}
		
		System.out.print(list.get(i)+",");
	}
}

}
