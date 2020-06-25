package test;

import java.util.ArrayList;
import java.util.Stack;

public class Test_TreeTraver2 {
	/**
	 * 创建结点，包含数据，左右子结点地址
	 */
    static class Node<E>{
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
	static class LinkedTree<E>{
			
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
	 * 先序1
	 * @param root 树的根结点
	 * @return 先序遍历序列数组
	 */
	public static ArrayList<Object> preOrder(Node<Object> root){
		
		//判断以root为根的树是否为空
		if(root==null) return null;
		
		//创建暂时存储结点的栈
		Stack<Node<Object>> stack = new Stack<>();
		
		//创建先序遍历序列储存的列表
		ArrayList<Object> list = new ArrayList<>();
		
		//创建指针，并指向root
		Node<Object> p = root;
		
		//循环至栈空
		while(p!=null || !stack.isEmpty() ){
			
			/*
			 * 循环将p结点和左子结点入栈入组，直至p为null
			 */
			while(p!=null){
				stack.add(p);
				list.add(p.data);
				p = p.left;
			}
			
			/*
			 * 如果p为null，栈为空，表明遍历结束
			 * 如果栈不为空，弹出栈顶结点，p指针指向该节点的右子节点（有可能为null）
			 * 继续循环将p的左子节点入栈入组
			 */
			if(!stack.isEmpty()){
				p = stack.pop();
				p = p.right;
			}
		}
		
		//返回后序遍历序列数组
		return list;
	}

	/**
	 * 先序2
	 * @param root 树的根结点
	 * @return 先序遍历序列数组
	 */
	public static ArrayList<Object> preOrder2(Node<Object> root){
		
		//判断以root为根的树是否为空
		if(root==null) return null;

		//创建存储结点的栈
		Stack<Node<Object>> stack = new Stack<>();
		
		//创建先序遍历序列储存的列表
		ArrayList<Object> list = new ArrayList<>();
		
		//创建指针
		Node<Object> p;
		
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
	public static ArrayList<Object> inOrder(Node<Object> root){

		//判断以root为根的树是否为空
		if(root==null) return null;
		
		//创建存储结点的栈
		Stack<Node<Object>> stack = new Stack<>();
		
		//创建中序遍历序列储存的列表
		ArrayList<Object> list = new ArrayList<>();
		
		//创建指针，并指向根结点
		Node<Object> p = root;
		
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

	/**
	 * 输出
	 * @param list 需输出的数组
	 */
	public static void print(ArrayList<Object> list){
		for (int i = 0 ; i<list.size() ; i++) System.out.print(list.get(i)+" ");
		System.out.println();
	}

	/**
	 * 计算叶子结点、总结点、层数，递归
	 */
	static class TreeInf{
		
		static int lives_count = 0;
        static int count = 0;
    	private static void count(Node<?> root){
    		if (root == null) return;
    		if (root.right == null && root.left == null) lives_count +=1 ;
    		count(root.left);
    		count(root.right);
    		count+=1;
    	}
    	
    	static int level = 0;
    	static int theLevel;
    	private static void level(Node<?> root){
    		if (root == null){
    			return;
    		}
    		theLevel += 1 ;
    		level(root.left);
    		level(root.right);
    		if (root.right == null && root.left == null){
    			if (theLevel>level) level = theLevel;
    		}
    		theLevel -= 1 ;
    	}
    	
    	private static void print(Node<?> root){
    		count(root);
    		level(root);
    		
    		System.out.println("叶子结点数为："+lives_count);
    		System.out.println("结点总数为："+count);
    		System.out.println("高度为："+level);
    	}
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args){
		LinkedTree<Object> tree = new LinkedTree<>("A");
		
		Node<Object> A = tree.root;
		Node<Object> B = tree.add('B', A, true);
		Node<Object> C = tree.add('C', A, false);
		Node<Object> D = tree.add('D', B, true);
		Node<Object> E = tree.add('E', B, false);
		Node<Object> F = tree.add('F', C, false);
		Node<Object> G = tree.add('G', D, true);
		Node<Object> H = tree.add('H', E, true);
		Node<Object> I = tree.add('I', E, false);
		Node<Object> J = tree.add('J', I, false);
		
		System.out.println("在以"+tree.root.data+"为根结点的二叉树中：");
		
		System.out.print("先序遍历序列，法一：");
		print(preOrder(A));
		
		System.out.print("先序遍历序列，法二：");
		print(preOrder2(A));
		
		System.out.print("中序遍历序列               ：");
		print(inOrder(A));
		
		TreeInf.print(A);
	}

}
