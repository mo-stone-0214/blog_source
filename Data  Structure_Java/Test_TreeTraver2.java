package test;

import java.util.ArrayList;
import java.util.Stack;

public class Test_TreeTraver2 {
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
	}

	/**
	 * ����1
	 * @param root ���ĸ����
	 * @return ���������������
	 */
	public static ArrayList<Object> preOrder(Node<Object> root){
		
		//�ж���rootΪ�������Ƿ�Ϊ��
		if(root==null) return null;
		
		//������ʱ�洢����ջ
		Stack<Node<Object>> stack = new Stack<>();
		
		//��������������д�����б�
		ArrayList<Object> list = new ArrayList<>();
		
		//����ָ�룬��ָ��root
		Node<Object> p = root;
		
		//ѭ����ջ��
		while(p!=null || !stack.isEmpty() ){
			
			/*
			 * ѭ����p�������ӽ����ջ���飬ֱ��pΪnull
			 */
			while(p!=null){
				stack.add(p);
				list.add(p.data);
				p = p.left;
			}
			
			/*
			 * ���pΪnull��ջΪ�գ�������������
			 * ���ջ��Ϊ�գ�����ջ����㣬pָ��ָ��ýڵ�����ӽڵ㣨�п���Ϊnull��
			 * ����ѭ����p�����ӽڵ���ջ����
			 */
			if(!stack.isEmpty()){
				p = stack.pop();
				p = p.right;
			}
		}
		
		//���غ��������������
		return list;
	}

	/**
	 * ����2
	 * @param root ���ĸ����
	 * @return ���������������
	 */
	public static ArrayList<Object> preOrder2(Node<Object> root){
		
		//�ж���rootΪ�������Ƿ�Ϊ��
		if(root==null) return null;

		//�����洢����ջ
		Stack<Node<Object>> stack = new Stack<>();
		
		//��������������д�����б�
		ArrayList<Object> list = new ArrayList<>();
		
		//����ָ��
		Node<Object> p;
		
		//���������ջ
		stack.push(root);
		
		//ѭ����ջ��
		while(!stack.isEmpty()){
			
			/*
			 * ��������������ص㣺p�������ӽڵ�����ӽ��
			 */
			
			//pָ��ָ�򵯳�ջ���Ľ��
			p = stack.pop();
			
			//p������������
			list.add(p.data);
			
			//������ҽ�㣬��ջ
			if( p.right != null) stack.push(p.right);
			
			//�������ڵ㣬��ջ��pָ��ָ�����ӽڵ�
			if( p.left != null) {
				stack.push(p.left);
				p=p.left;
			}
		}
		
		//�������������������
		return list;
	}

	/**
	 * ����
	 * @param root ���ĸ����
	 * @return ���������������
	 */
	public static ArrayList<Object> inOrder(Node<Object> root){

		//�ж���rootΪ�������Ƿ�Ϊ��
		if(root==null) return null;
		
		//�����洢����ջ
		Stack<Node<Object>> stack = new Stack<>();
		
		//��������������д�����б�
		ArrayList<Object> list = new ArrayList<>();
		
		//����ָ�룬��ָ������
		Node<Object> p = root;
		
		//ѭ����pָ��Ϊnull���Լ�ջΪ�ա�
		while(p!=null || !stack.isEmpty() ){
			
			//���ν������������ջ��ֱ��Ϊnull
			while(p!=null){
				stack.add(p);
				p = p.left;
			}
			
			/*
			 * ���ָ��p��ջ��Ϊ�գ���������
			 * ���ջ��Ϊ�գ�������ջ����㣬�������飬pָ��ָ��ýڵ����ӽڵ�
			 */
			if(!stack.isEmpty()){
				p = stack.pop();
				list.add(p.data);
				p = p.right;
			}
		}
		
		//�������������������
		return list;
	}

	/**
	 * ���
	 * @param list �����������
	 */
	public static void print(ArrayList<Object> list){
		for (int i = 0 ; i<list.size() ; i++) System.out.print(list.get(i)+" ");
		System.out.println();
	}

	/**
	 * ����Ҷ�ӽ�㡢�ܽ�㡢�������ݹ�
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
    		
    		System.out.println("Ҷ�ӽ����Ϊ��"+lives_count);
    		System.out.println("�������Ϊ��"+count);
    		System.out.println("�߶�Ϊ��"+level);
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
		
		System.out.println("����"+tree.root.data+"Ϊ�����Ķ������У�");
		
		System.out.print("����������У���һ��");
		print(preOrder(A));
		
		System.out.print("����������У�������");
		print(preOrder2(A));
		
		System.out.print("�����������               ��");
		print(inOrder(A));
		
		TreeInf.print(A);
	}

}
