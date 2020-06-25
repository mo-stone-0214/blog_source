package test;

import java.util.ArrayList;

public class Tree1 {

/**
 * 创建结点，包含数据，左右子结点地址
 */
public static class Node<E>{
	E data;
	Node<E> right;
	Node<E> left;
	
	//相关数据的添加
	public Node(E data , Node<E> right , Node<E> left){
		this.left = left;
		this.right = right;
		this.data = data;
	}
	
	//初始化
	public Node(E data){
		this(data,null,null);
	}
}

/**
 * 创建树
 */
public static class LinkedTree<E>{
	
	//根结点
	Node<E> root;
	
	//初始化树
	public LinkedTree(E data){
		root = new Node<>(data);
	}
	
	/**
	 * 添加结点
	 * @param data 添加的数据
	 * @param father 父级结点地址
	 * @param isLeft 是否为父级结点的左子结点
	 * @return 添加的结点
	 */
	public Node<E> add(E data , Node<E> father , boolean isLeft){
		if(father == null) throw new RuntimeException("无父级，拒绝添加");
		
		Node<E> theNode = new Node<>(data);
		if(isLeft){
			father.left = theNode;
		}else{
			father.right = theNode;
		}
		return theNode;
	}
}

/**
 * 二叉树的遍历
 */
public static class TreeTraver{
	 //先序
	static ArrayList<Object> list = new ArrayList<>();
	public static void preOrder(Node<Object> root){
		if(root == null) return;
		list.add(root.data);
		//System.out.print(root.data + " ");
		preOrder(root.left);
		preOrder(root.right);
	}
	//中序
	private static void inOrder(Node<Object> root){
		if (root == null) return;
		inOrder(root.left);
		list.add(root.data);
		//System.out.print(root.data + " ");
	    inOrder(root.right);
	}
	//后序
	private static void  postOrder(Node<Object> root){
		if (root == null) return;
		postOrder(root.left);
		postOrder(root.right);
		list.add(root.data);
	    //System.out.print(root.data + " ");
	}
	private static void print(){
		for (int i = 0 ; i<list.size() ; i++) System.out.print(list.get(i)+" ");
		list.removeAll(list);
	}
}


public static void main(String[] args){
	LinkedTree<Object> tree = new LinkedTree<>("A");
	
	Node<Object> A = tree.root;
	Node<Object> B = tree.add('B', A, true);
	Node<Object> C = tree.add('C', A, false);
	Node<Object> D = tree.add('D', B, true);
	Node<Object> F = tree.add('F', C, true);
	Node<Object> G = tree.add('G', C, false);
	
	System.out.print("先序遍历序列：");
	TreeTraver.preOrder(A);
	TreeTraver.print();
	System.out.println();
	
	System.out.print("中序遍历序列：");
	TreeTraver.inOrder(A);
	TreeTraver.print();
	System.out.println();
	
	System.out.print("后序遍历序列：");
	TreeTraver.postOrder(A);
	TreeTraver.print();
	System.out.println();
	
}

}
