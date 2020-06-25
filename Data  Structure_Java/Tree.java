package test;

public class Tree {
	
//������
public static class BTNode<E>{
	private E data;
	private BTNode<E> sonLeft ;
	private BTNode<E> sonRight ;
					
	//�������
	public BTNode(E data , BTNode<E> sonLeft , BTNode<E> sonRight){
		this.data = data ;
		this.sonLeft = sonLeft ;
		this.sonRight = sonRight ;
	}
					
	//��ʼ�����
	public BTNode(E data){
		this(data,null,null);
	}	
}
	
//������������ʵ��
public static class LinkedBTree<E> {

	//�����
	BTNode<E> root;
	public LinkedBTree(E data) {
		root = new BTNode<E>(data);
	}
	
	/**
	*  Ϊָ���������ӽ��
	* @param parent ��Ҫ��ӽڵ��˫�׽�������ֵ
	* @param data �½������
	* @param isLeft �Ƿ��������
	* @return �������
	*/
	public BTNode<E>  add(BTNode<E> parent, E data, boolean isLeft) {
		if(parent == null) throw new RuntimeException(parent+"���Ϊ�գ���������ӽ��!");
		
		BTNode<E> newNode = new BTNode<>(data);
		if (isLeft) {
			parent.sonLeft = newNode;
		} else {
			parent.sonRight = newNode;
		}
	return newNode;
}
	
	public void printChild(BTNode<E> parent) {
		System.out.println("���" + getString(parent) + "�ĺ��ӽ��Ϊ��" + getString(parent.sonLeft) + "��" + getString(parent.sonRight));
	}
	
	private Object getString(BTNode<E> node) {
		if (node == null) {
			return "��";
		} else {
			return node.data;
		}
	}
}
	
//������
public static class Node<E>{
	private E data;
	private Node<E> fath;
	private Node<E> sonLeft ;
	private Node<E> sonRight ;
			
	//�������
	public Node(E data , Node<E> fath , Node<E> sonLeft , Node<E> sonRight){
		this.data = data ;
		this.fath = fath ;
		this.sonLeft = sonLeft ;
		this.sonRight = sonRight ;
	}
			
	//��ʼ�����
	public Node(E data){
		this(data,null,null,null);
	}	
}
	
//��������������
public static class LinkedTree2<E>{

    //��������ʼ��ͷ���
	private class Header{
		private Node<E> next;
		public Header(Node<E> next){
			this.next = next ;
		}
	}
	
	//����������
	private Header header ;
	public LinkedTree2(){
		header = new Header(null);
	}
	
	/**
	 * ��������ĵ�һ�����
	 * @param firNode ���
	 */
	public void addfir(Node<E> firNode){
		//������һ�����
		if(header.next == null){
			header.next = firNode;
		
	    //���ĵ�һ�����
		}else{
			header.next.data = firNode.data ;
		}
	}

	/**
	 * ��ӽ��
	 * @param node ��ӵĽ��
	 * @param fath �������
	 * @param isLeft �Ƿ������
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
 * ������
 * @param Node ������Ľ��
 */
public static void printNode(Node<Integer> Node){

	System.out.print("�������"+Node.data);
	if (Node.fath != null) System.out.print(",����Ϊ"+Node.fath.data);
	if (Node.sonLeft != null) System.out.print(",���Ӽ�Ϊ"+Node.sonLeft.data);
	if (Node.sonRight != null) System.out.print(",���Ӽ�Ϊ"+Node.sonRight.data);
	System.out.println();

}

//�����鴴��������
public static class ArrayTree<E>{
	
	//��ʼ������
	private final int totall = 3 * 3 - 1 ; //(height^2-1)
	private Object[] arraytree ;
	public ArrayTree(){
		this.arraytree = new Object[totall];
	}
	
	/**
	 * ��������ӵ�һ������
	 * @param data ��ӵ�����
	 */
    public void add(E data){
		arraytree[1] = data ;
	}
    
	/**
	 * �������������
	 * @param data ��ӵ�����
	 * @param index ������ݵĸ�����λ��
	 * @param isLeft �Ƿ������
	 */
	public void add(E data , int index , boolean isLeft){
		
		//�жϲ���λ���Ƿ�Ϸ�
		if ( arraytree[index] == null ){
			throw new RuntimeException("����λ��û�и��������Ϸ���");
		}
		
		//����isLeftֵ����xֵ
		int x ;
		if ( isLeft ){
			x = 0;
		} else {
			x = 1 ;
		}
		
		//�ж��Ƿ񳬳����鷶Χ
		if(2 * index + x >= totall){
			throw new RuntimeException("����λ�ó������鷶Χ");
		}
		
		//����Ӧλ���������
		arraytree[ 2 * index + x ] = data ;
	}
	
	/**
	 * ���
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
	//  ���ԣ����ĳ�����ĺ��ӽ��
	linkedBTree.printChild(nodeF);
	}

}
	
