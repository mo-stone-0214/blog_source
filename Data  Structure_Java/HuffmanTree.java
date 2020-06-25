package test;

public class HuffmanTree {
	
/*
 * �������
 * ��������Ȩ�أ����������Ӽ������Ӽ����
 */
static class Node<E>{
	Node<E> parent,left,right;
	int weight;
	public Node(int weight,Node<E> parent,Node<E> left,Node<E> right){
		this.weight = weight;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}
	public Node(int weight){
		this(weight,null,null,null);
	}
}

/**
 * ������������
 * @param weights Ҷ�ӽڵ��Ȩ�ؼ��ϵ�����
 * @return �������������
 */
public static Node<?> BuildHTree(int[] weights){
	
	//�ж��Ƿ�Ϊ��
	if(weights == null) throw new RuntimeException("Ҷ�ӽڵ�Ϊ��");
	
	//�����������
	Node<Integer>[] HTree = new Node[weights.length];
	
	//������Ȩ�ص���һ���������뵽ɭ��������
	for (int i = 0 ; i<weights.length ; i++){
		Node<Integer> theNode = new Node<Integer>(weights[i]);
		HTree[i] = theNode;
	}
	
	//���ֻ��һ���ڵ㣬ֱ�ӷ���
	if(weights.length == 1) return HTree[0];
	
	//����ָ�룬��ǰ��С������Ȩ��ֵ����λ��
	Node<Integer> p = null , q = null;
	int x1 = 0,x2 = 0;
	int index1 = 0 , index2 = 0;
	
	//ѭ����Ҷ�ӽڵ����-1����
	for (int i = 0 ; i<weights.length-1;i++){
		
		//x1��x2ȡ���ֵ
		x1 = Integer.MAX_VALUE;
		x2 = Integer.MAX_VALUE;
		
		//ѭ���ҳ���С������Ȩ�ؼ���λ�ã��������Ƴ�ɭ��
		for (int j = 0 ; j<HTree.length ; j++){
			if (HTree[j] == null) continue;
			if (HTree[j].weight <= x1){
				p = HTree[j];
				x1 = HTree[j].weight;
				index1 = j;
			}
		}
		HTree[index1] = null;
		
		for (int j = 0 ; j<HTree.length ; j++){
			if (HTree[j] == null) continue;
			if (HTree[j].weight <= x2){
				q = HTree[j];
				x2 = HTree[j].weight;
				index2 = j;
			}
		}

		HTree[index2] = null;
		
		
		//�����µĽ�㣬�������µ���
		Node<Integer> newNode = new Node<>(p.weight+q.weight);
		newNode.left = p;
		newNode.right = q;
		p.parent = newNode;
		q.parent = newNode;
		
		//�������뵽ɭ����
		HTree[index1] = newNode;
		
	}
	
	//�ҵ����һ������λ��
	for (int i = 0 ; i<weights.length ; i++){
		if (HTree[i] != null) {x1 = i ; break;}
	}
	
	//��������
	return HTree[x1];
}


/**
 * �������ı�����ʹ�õ�֮ǰ�ĵݹ������
 */
public static class TreeTraver{
	 //����
	public static void preOrder(Node<?> root){
		if(root == null) return;
		System.out.print(root.weight + " ");
		preOrder(root.left);
		preOrder(root.right);
	}
	//����
	private static void inOrder(Node<?> root){
		if (root == null) return;
		inOrder(root.left);
		System.out.print(root.weight + " ");
	    inOrder(root.right);
	}
	//����
	private static void  postOrder(Node<?> root){
		if (root == null) return;
		postOrder(root.left);
		postOrder(root.right);
	    System.out.print(root.weight + " ");
	}
}

//����������
public static void main(String[] args){
	int[] weights = {1,3,5,7,6};
	Node<?> BTree = BuildHTree(weights);
		
	System.out.print("����������У�");
	TreeTraver.preOrder(BTree);
	System.out.println();
	
	System.out.print("����������У�");
	TreeTraver.inOrder(BTree);
	System.out.println();
	
	System.out.print("����������У�");
	TreeTraver.postOrder(BTree);
	System.out.println();
		
	}
}
