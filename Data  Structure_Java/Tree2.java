package test;

import java.util.ArrayList;
import java.util.Stack;

public class Tree2 {

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
 * ���� ˫ջ
 * @param root ���ĸ����
 * @return ���������������
 */
public static ArrayList<Object> postOrder(Node<Object> root){
	
	//�ж���rootΪ�������Ƿ�Ϊ��
	if(root==null) return null;
	
	//������ʱ�洢����ջ1���Լ��洢��㵹���ջ2
	Stack<Node<Object>> stack = new Stack<>();
	Stack<Object> stack2 = new Stack<>();
	
	//��������������д�����б�
	ArrayList<Object> list = new ArrayList<>();
	
	//����ָ��
	Node<Object> p;
	
	//���������ջ1
	stack.push(root);
	
	//ѭ����ջ1Ϊ�գ���ջ1������ݾ���ջ2
	while(!stack.isEmpty()){
		
		/*
		 * ʼ�����ݺ���������ص㣺���ӽڵ�����ӽ���p���
		 * ����ջ������������ջ˳��p�������ӽ������ӽڵ�
		 */
		
		//����ջ1���˽��
		p = stack.pop();
		
		//�����Ƿ���������ӽڵ㣬����p���ҡ����˳����ջ2
		stack2.push(p.data);
		if( p.left != null) stack.push(p.left);
		if( p.right != null) stack.push(p.right);
	}
	
	//��ջ2���Ԫ����������������
	while (!stack2.isEmpty()){
		list.add(stack2.pop());
	}
	
	//���غ��������������
	return list;
}

/**
 * ���� ���
 * @param root ���ĸ����
 * @return ���������������
 */
public static ArrayList<Object> postOrder2(Node<Object> root){
	
	//�ж���rootΪ�������Ƿ�Ϊ��
		if(root==null) return null;
		
		//��������������������ı�ǵ�ջ���˴���������Ϊ-1����Ϊ1.
		Stack<Node<Object>> stack = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();

		//��������������д�����б�
		ArrayList<Object> list = new ArrayList<>();
		
		//����ָ��
		Node<Object> p = root;

	//ѭ��ֱ�����ջΪ�պ�ָ��Ϊnull
	while( p != null || !stack.isEmpty()){
		
		//��ָ�벻Ϊnull���������ջ�������Ϊ��������-1����ѭ����pָ��null
		while (p!=null){
			stack.push(p);
			stack2.push(-1);
			p = p.left;
		}
		
		//��pָ��Ϊnull,ջ��Ϊ�գ����ѱ�����ɣ�����ѭ����
		if(stack.isEmpty()) break;
		
		/*
		 * ��ָ��ָ�����һ����㣬�����ջ���˵Ľ������������
		 * ��ýڵ����ҽ������ѱ�������ȻҲ�п���Ϊ�գ��������ұ�ǣ������ý�����
		 */
	    if(stack2.peek() == 1){
			stack2.pop();
			list.add(stack.pop().data);
		}
		
		/* 
		 * ��ָ��ָ�����һ����㣬�����ջ���˵Ľ������������
		 * �򵯳����ǣ�ָ��ָ��ýڵ���ҽ�㣬�����Ϊ��������1����
		 * 
		 * ������Ҫע�⣬ָ���ҽ����п��ܸýڵ������������
		 * ������Ҫ�ٴ�����ǰ�������ڵ��ѭ����
		 * ������Ҫ��ѭ�����
		 */    
	    else if(stack2.peek() == -1){
			stack2.pop();
			p = stack.peek().right;
			stack2.push(1);
		}
	}
	
	//ѭ�����������غ��������������
	return list;
}

/**
 * ���
 */
@SuppressWarnings("unchecked")
public static ArrayList<Object> levelOrder(Node<Object> root){
	
	//�ж���rootΪ�������Ƿ�Ϊ��
	if(root==null) return null;
	
	//����������Ķ���
	QueueArray<Node<Object>> quearr = new QueueArray<>();
	
	//��������������д��������
	ArrayList<Object> list = new ArrayList<>();
	
	//����������
	quearr.In(root);
	
	//������ǰ����ָ��,ָ��ָ����׽��
	int flag = 0;
	Node<Object> p = (Node<Object>) quearr.que_arr[flag];
	
	//ѭ��������Ϊ��
	while (quearr.count != 0){
		
		/*
		 * ָ��Ӷ�����������ƶ������ν��ýڵ�������ӽڵ����
		 * ��ָ������ƶ���֮ǰ�Ľ�����
		 */
		if(p.left!=null) quearr.In(p.left);
		if(p.right!=null) quearr.In(p.right);
		
		//��������
		list.add(((Node<Object>)quearr.Out()).data);
			
		//flag��ŵ�����
		if (flag+1 >= quearr.size){
			flag = 0;
		}else{
			++flag;
		}
		
		//ָ������ƶ�
		p = (Node<Object>) quearr.que_arr[flag];
	}
	
	//���ز�α�����������
	return list;
}

/**
 * ѭ������
 */
static class QueueArray<E>{
	
	//�趨���У��ӵ�������ǰ�˺ͺ�ˣ�Ԫ��������
	//ǰ��Ϊ��ͷԪ��λ�ã�����±�Ϊ��βԪ�غ�һ��λ�á�
	private int size = 20 ;
	private Object[] que_arr ;
	private int front ;
	private int rear ;
	private int count ;
	//��ʼ������
	public QueueArray(){
		que_arr =new Object [size] ;
		this.front = 0 ;
		this.rear = 0 ;
		this.count = 0;
	}
	/**
	 * ���
	 * @param data
	 */
	public void In(E data){
		//�ж��Ƿ����
		if (count == size){
			throw new RuntimeException("����");
		}
		//������ݣ�Ԫ������+1
		que_arr[rear] = data ;
		++count;
		//��β��ŵ�����
		if (rear+1 >= size){
			rear = 0;
		}else{
		    ++rear;
		}
	}
	
	/**
	 * ����
	 * @return ��ͷԪ��
	 */
	public Object Out(){
		if (count == 0){
			throw new RuntimeException("�ӿ�");
		}
		Object data = que_arr[front];
		que_arr[front] = null ;
		--count;
		if (front+1 >= size){
			front = 0;
		}else{
		    ++front;
		}
		return data;
	}
}

/**
 * ���
 * @param list �����������
 */
public static void print(ArrayList<Object> list){
	for (int i = 0 ; i<list.size() ; i++) System.out.print(list.get(i)+" ");
	System.out.println();
}

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
	
	System.out.print("����������У���һ��");
	print(preOrder(A));
	
	System.out.print("����������У�������");
	print(preOrder2(A));
	
	System.out.print("�����������               ��");
	print(inOrder(A));
	
	System.out.print("����������У�˫ջ��");
	print(postOrder(A));
	
	System.out.print("����������У���ǣ�");
	print(postOrder2(A));
	
	System.out.print("��α�������               ��");
	print(levelOrder(A));

}

}
