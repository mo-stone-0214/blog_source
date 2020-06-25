package test;

public class Test_TreeTraver {

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
    
    	//先序
        private void preOrder(Node<E> root){
    		if(root == null) return;
    		System.out.print(root.data + " ");
    		preOrder(root.left);
    		preOrder(root.right);
    	}
    	//中序
    	private void inOrder(Node<E> root){
    		if (root == null) return;
    		inOrder(root.left);
    		System.out.print(root.data + " ");
    	    inOrder(root.right);
    	}
    	//后序
    	private void  postOrder(Node<E> root){
    		if (root == null) return;
    		postOrder(root.left);
    		postOrder(root.right);
    	    System.out.print(root.data + " ");
    	}
    	
    	int lives_count = 0;
    	int count=0;
    	//计算叶子结点和总结点，递归
    	private void count(Node<E> root){
    		if (root == null) return;
    		if (root.right == null && root.left == null) lives_count +=1 ;
    		
    		count(root.left);
    		count(root.right);
    		count+=1;
    	}
    }
    
	@SuppressWarnings("unused")
	public static void main(String[] args){
		LinkedTree<Object> tree = new LinkedTree<>("A");
		
		Node<Object> A = tree.root;
		Node<Object> B = tree.add('B', A, true);
		Node<Object> C = tree.add('C', A, false);
		Node<Object> D = tree.add('D', B, true);
		Node<Object> F = tree.add('F', C, true);
		Node<Object> G = tree.add('G', C, false);

		System.out.print("先序遍历序列：");
		tree.preOrder(A);
		System.out.println();
		
		System.out.print("中序遍历序列：");
		tree.inOrder(A);
		System.out.println();
		
		System.out.print("后序遍历序列：");
		tree.postOrder(A);
		System.out.println();
		
		tree.count(A);
		System.out.print("总结点数：");
		System.out.println(tree.count);
		System.out.print("叶子结点数：");
		System.out.println(tree.lives_count);
	}
    
}
