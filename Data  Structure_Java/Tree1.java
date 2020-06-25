package test;

import java.util.ArrayList;

public class Tree1 {

/**
 * ������㣬�������ݣ������ӽ���ַ
 */
public static class Node<E>{
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
public static class LinkedTree<E>{
	
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
}

/**
 * �������ı���
 */
public static class TreeTraver{
	 //����
	static ArrayList<Object> list = new ArrayList<>();
	public static void preOrder(Node<Object> root){
		if(root == null) return;
		list.add(root.data);
		//System.out.print(root.data + " ");
		preOrder(root.left);
		preOrder(root.right);
	}
	//����
	private static void inOrder(Node<Object> root){
		if (root == null) return;
		inOrder(root.left);
		list.add(root.data);
		//System.out.print(root.data + " ");
	    inOrder(root.right);
	}
	//����
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
	
	System.out.print("����������У�");
	TreeTraver.preOrder(A);
	TreeTraver.print();
	System.out.println();
	
	System.out.print("����������У�");
	TreeTraver.inOrder(A);
	TreeTraver.print();
	System.out.println();
	
	System.out.print("����������У�");
	TreeTraver.postOrder(A);
	TreeTraver.print();
	System.out.println();
	
}

}
