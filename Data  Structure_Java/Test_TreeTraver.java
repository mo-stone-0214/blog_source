package test;

public class Test_TreeTraver {

	/**
	 * ������㣬�������ݣ������ӽ���ַ
	 */
    static class Node<E>{
		E data;
		Node<E> right;
		Node<E> left;
		
		//������ݵ����
		public Node(E data , Node<E> right , Node<E> left){
			this.left = left;
			this.right = right;
			this.data = data;
		}
		
		//��ʼ��
		public Node(E data){
			this(data,null,null);
		}
	}
	
    /**
     * ������
     */
    static class LinkedTree<E>{
    	
    	//�����
    	Node<E> root;
    	
    	//��ʼ����
    	public LinkedTree(E data){
    		root = new Node<>(data);
    	}
    	
    	/**
    	 * ��ӽ��
    	 * @param data ��ӵ�����
    	 * @param father ��������ַ
    	 * @param isLeft �Ƿ�Ϊ�����������ӽ��
    	 * @return ��ӵĽ��
    	 */
    	public Node<E> add(E data , Node<E> father , boolean isLeft){
    		if(father == null) throw new RuntimeException("�޸������ܾ����");
    		
    		Node<E> theNode = new Node<>(data);
    		if(isLeft){
    			father.left = theNode;
    		}else{
    			father.right = theNode;
    		}
    		return theNode;
    	}
    
    	//����
        private void preOrder(Node<E> root){
    		if(root == null) return;
    		System.out.print(root.data + " ");
    		preOrder(root.left);
    		preOrder(root.right);
    	}
    	//����
    	private void inOrder(Node<E> root){
    		if (root == null) return;
    		inOrder(root.left);
    		System.out.print(root.data + " ");
    	    inOrder(root.right);
    	}
    	//����
    	private void  postOrder(Node<E> root){
    		if (root == null) return;
    		postOrder(root.left);
    		postOrder(root.right);
    	    System.out.print(root.data + " ");
    	}
    	
    	int lives_count = 0;
    	int count=0;
    	//����Ҷ�ӽ����ܽ�㣬�ݹ�
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

		System.out.print("����������У�");
		tree.preOrder(A);
		System.out.println();
		
		System.out.print("����������У�");
		tree.inOrder(A);
		System.out.println();
		
		System.out.print("����������У�");
		tree.postOrder(A);
		System.out.println();
		
		tree.count(A);
		System.out.print("�ܽ������");
		System.out.println(tree.count);
		System.out.print("Ҷ�ӽ������");
		System.out.println(tree.lives_count);
	}
    
}
