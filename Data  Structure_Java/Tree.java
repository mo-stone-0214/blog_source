package test;

public class Tree {
	
//定义结点
public static class BTNode<E>{
	private E data;
	private BTNode<E> sonLeft ;
	private BTNode<E> sonRight ;
					
	//创建结点
	public BTNode(E data , BTNode<E> sonLeft , BTNode<E> sonRight){
		this.data = data ;
		this.sonLeft = sonLeft ;
		this.sonRight = sonRight ;
	}
					
	//初始化结点
	public BTNode(E data){
		this(data,null,null);
	}	
}
	
//二叉树的链表实现
public static class LinkedBTree<E> {

	//根结点
	BTNode<E> root;
	public LinkedBTree(E data) {
		root = new BTNode<E>(data);
	}
	
	/**
	*  为指定结点添加子结点
	* @param parent 需要添加节点的双亲结点的索引值
	* @param data 新结点数据
	* @param isLeft 是否添加左孩子
	* @return 新增结点
	*/
	public BTNode<E>  add(BTNode<E> parent, E data, boolean isLeft) {
		if(parent == null) throw new RuntimeException(parent+"结点为空，不能添加子结点!");
		
		BTNode<E> newNode = new BTNode<>(data);
		if (isLeft) {
			parent.sonLeft = newNode;
		} else {
			parent.sonRight = newNode;
		}
	return newNode;
}
	
	public void printChild(BTNode<E> parent) {
		System.out.println("结点" + getString(parent) + "的孩子结点为：" + getString(parent.sonLeft) + "和" + getString(parent.sonRight));
	}
	
	private Object getString(BTNode<E> node) {
		if (node == null) {
			return "空";
		} else {
			return node.data;
		}
	}
}
	
//定义结点
public static class Node<E>{
	private E data;
	private Node<E> fath;
	private Node<E> sonLeft ;
	private Node<E> sonRight ;
			
	//创建结点
	public Node(E data , Node<E> fath , Node<E> sonLeft , Node<E> sonRight){
		this.data = data ;
		this.fath = fath ;
		this.sonLeft = sonLeft ;
		this.sonRight = sonRight ;
	}
			
	//初始化结点
	public Node(E data){
		this(data,null,null,null);
	}	
}
	
//用链表创建二叉树
public static class LinkedTree2<E>{

    //创建并初始化头结点
	private class Header{
		private Node<E> next;
		public Header(Node<E> next){
			this.next = next ;
		}
	}
	
	//创建二叉树
	private Header header ;
	public LinkedTree2(){
		header = new Header(null);
	}
	
	/**
	 * 新增或更改第一个结点
	 * @param firNode 结点
	 */
	public void addfir(Node<E> firNode){
		//新增第一个结点
		if(header.next == null){
			header.next = firNode;
		
	    //更改第一个结点
		}else{
			header.next.data = firNode.data ;
		}
	}

	/**
	 * 添加结点
	 * @param node 添加的结点
	 * @param fath 父级结点
	 * @param isLeft 是否在左侧
	 */
	public void add(Node<E> node , Node<E> fath , boolean isLeft){
		node.fath = fath ;
		if(isLeft){
			fath.sonLeft = node ;
		}else{
			fath.sonRight = node ;
		}
	}	
}

/**
 * 输出结点
 * @param Node 需输出的结点
 */
public static void printNode(Node<Integer> Node){

	System.out.print("结点数据"+Node.data);
	if (Node.fath != null) System.out.print(",父级为"+Node.fath.data);
	if (Node.sonLeft != null) System.out.print(",左子级为"+Node.sonLeft.data);
	if (Node.sonRight != null) System.out.print(",右子级为"+Node.sonRight.data);
	System.out.println();

}

//用数组创建二叉树
public static class ArrayTree<E>{
	
	//初始化数组
	private final int totall = 3 * 3 - 1 ; //(height^2-1)
	private Object[] arraytree ;
	public ArrayTree(){
		this.arraytree = new Object[totall];
	}
	
	/**
	 * 向树里添加第一个数据
	 * @param data 添加的数据
	 */
    public void add(E data){
		arraytree[1] = data ;
	}
    
	/**
	 * 向树里添加数据
	 * @param data 添加的数据
	 * @param index 添加数据的父级的位置
	 * @param isLeft 是否在左侧
	 */
	public void add(E data , int index , boolean isLeft){
		
		//判断插入位置是否合法
		if ( arraytree[index] == null ){
			throw new RuntimeException("插入位置没有父级，不合法。");
		}
		
		//根据isLeft值赋予x值
		int x ;
		if ( isLeft ){
			x = 0;
		} else {
			x = 1 ;
		}
		
		//判断是否超出数组范围
		if(2 * index + x >= totall){
			throw new RuntimeException("插入位置超出数组范围");
		}
		
		//在相应位置添加数据
		arraytree[ 2 * index + x ] = data ;
	}
	
	/**
	 * 输出
	 */
	public void print(){
		
		System.out.print("[");
		
		for ( int i = 0 ; i < totall ; i++ ){
			
			if ( i == totall - 1){
				
			    System.out.print(arraytree[i]+"]");
			}else{
				
			    System.out.print(arraytree[i]+",");   
			}
		}	
		
		System.out.println();
		
	}
	
}

public static void main(String[] args){
	
	/*ArrayTree<Integer> arrtr = new ArrayTree<>();
	arrtr.add(1);
	//arrtr.add(4,2,true);
	arrtr.add(2, 1, true);
	arrtr.add(3, 1, false);
	arrtr.print();
	
	arrtr.add(4, 2, true);
	arrtr.add(5, 2, false);
	arrtr.add(6, 3, true);
	arrtr.add(7, 3, false);

	arrtr.print();
	arrtr.add(8, 4, true);
	
	LinkedTree2<Integer> litree = new LinkedTree2<>();
	Node<Integer> A = new Node<>(1);
	litree.addfir(A);
	printNode(A);
	
	Node<Integer> A1 = new Node<>(10);
	litree.addfir(A1);
	printNode(A1);
	
	Node<Integer> B = new Node<>(2);
	litree.add(B, A, true);
	Node<Integer> C = new Node<>(3);
	litree.add(C, A, false);
	printNode(A);
	printNode(B);
	printNode(C);
	
	Node<Integer> D = new Node<>(4);
	litree.add(D, B, true);
	Node<Integer> E = new Node<>(5);
	litree.add(E, B, false);
	printNode(D);
	
	Node<Integer> G = new Node<>(7);
	litree.add(G, C, false);
	printNode(C);*/
	
	LinkedBTree<Character> linkedBTree = new LinkedBTree<>('A');
	BTNode<Character> nodeB = linkedBTree.add(linkedBTree.root, 'B',true);
	BTNode<Character> nodeC = linkedBTree.add(linkedBTree.root, 'C',false);
	BTNode<Character> nodeD = linkedBTree.add(nodeB, 'D', true);
	BTNode<Character> nodeE = linkedBTree.add(nodeB, 'E', false);
	BTNode<Character> nodeF = linkedBTree.add(nodeC, 'F', false);
	BTNode<Character> nodeG = linkedBTree.add(nodeE, 'G', true);
	//  测试，输出某个结点的孩子结点
	linkedBTree.printChild(nodeF);
	}

}
	
