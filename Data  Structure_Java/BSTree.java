package test;

import java.util.ArrayList;
import java.util.Stack;

import test.Tree2.Node;

public class BSTree {

//���
static class Node{
	int data;
	Node left;
	Node right;
	public Node(int data){
		this.data = data;
	}
}

//����������
static class Tree{
	
	/**
	 * �������
	 * @param root �����
	 * @param data �������
	 * @return �����
	 */
	private Node add(Node root , int data){
		//���Ϊ������ֱ�����
		if (root == null) {
			return new Node(data);
			
		//������С�ڸ���������ʱ��������������У���֮���������������
		//���ʱ���λ��������
		} else if (data < root.data) {
			root.left = add(root.left, data);
		} else {
			root.right = add(root.right, data);
		}
		
		return root;
	}
	
	/**
	* ����ָ��ֵ�Ľ��
	* @param root ���ڵ�
	* @param data ����ֵ
	* @return true�����ҵ�
	*/
	private boolean search(Node root, int  data) {
		if (root == null) {return false;}
		if (data == root.data) {return true;}
		else if (data < root.data) {
			return search(root.left,  data);}
		else {
			return search(root.right,  data);}
	}	
	
	/**
	 * ɾ��ָ�����
	 * @param root �����
	 * @param data ɾ�������ݣ����������д����ظ����ݣ�����ɾ����������Ľ�㣩
	 */
	private Node remove(Node root , int data){
		//����ָ�����
		Node theNode = search_Node(root,data);
		//��û�ҵ��������в���
		if (theNode == null) {
			System.out.println("δ�ҵ�"+data+"��㣬�����в���");
			return root;
		}
		
		int thedata = theNode.data;
		
		//�ҵ���ĸ���
		Node parNode = search_parentNode(root,theNode);
		
		//���ý��ΪҶ�ӽڵ㣬ֱ��ɾ������ɾ��������ĸ����ָ��ɾ��
		if(theNode.left == null && theNode.right == null){
			
			//��Ϊ����㣬ɾ��Ψһ�Ľ��root
			if(parNode == null){
				root = null;
				
			//��������������ĸ�������ӽ��ɾ��
			}else if(parNode.left == theNode){
				parNode.left = null;
				
			//��������������ĸ�������ӽ��ɾ��
			}else{
				parNode.right = null;
			}
			
			//ָ�����
			theNode = null;
			
			//�����������ظ����
			System.out.println("���"+thedata+"��ɾ��");
			return root;
		}
		
		//���ý��û������������������ֱ��ɾ���ý�㣬�����丸ĸ�ڵ��ָ���Ϊ���������������������
		if(theNode.left == null || theNode.right == null){
			
			//����ý��û��������
			if (theNode.left == null){
				
				//����Ǹ���㣬�¸�����Ϊ���������ӽ��
				if(parNode == null){
					root = theNode.right;
					
				//������丸ĸ�������ӽ�㣬���ӽ����Ϊ�ý������ӽڵ�
				}else if(parNode.left == theNode){
					parNode.left = theNode.right;
					
				//������丸ĸ�������ӽڵ㣬���ӽ����Ϊ�ý������ӽڵ�
				}else{
					parNode.right = theNode.right;
				}
				
				//���ָ��
				theNode = null;
				
				//�����������ظ����
				System.out.println("���"+thedata+"��ɾ��");
				return root;
				
			//����ý��û������������������������
			}else{
				if(parNode == null){
					root = theNode.left;
				}else if(parNode.left == theNode){
					parNode.left = theNode.left;
				}else{
					parNode.right = theNode.left;
				}
				theNode = null;
				System.out.println("���"+thedata+"��ɾ��");
				return root;
			}
		}
		
		//���ý���������������ҵ������������ֵ���棬����ԭ���ֵ�Ľ��λ��������������������
		if(theNode.left != null && theNode.right != null){
			
			//�ҵ��ý�������������Ľڵ㣬��Ϊ����Ľ��
			Node reNode = search_MaxNode(theNode);
			
			//������ĸ�ĸ�������������Ϊ��������
			search_parentNode(root,reNode).right = reNode.left;

			//����ý��Ϊ����㣬�¸������Ϊ������
			if(parNode == null){
				root = reNode;
			}
			
			//����ý�㲻�Ǹ���㣬��ӵ�и�ĸ��㣬�������丸ĸ�������ӽ��
			//�ý��ĸ�ĸ�������ӽ���Ϊ������
	        else if(parNode.left == theNode){
				parNode.left = reNode;
				
			//����ý�㲻�Ǹ���㣬��ӵ�и�ĸ��㣬�������丸ĸ�������ӽ��
			//�ý��ĸ�ĸ�������ӽ���Ϊ������
			}else{
				parNode.left = reNode;
			}
			
			//��������㲻�Ǹý������ӽ�㣬����������ӽ����Ϊ�ý������ӽ��
			//��ʱ������Ӧ�ڸý��λ��
			if(theNode.left != reNode){
				reNode.left = theNode.left;
			}
			
			//����������ӽڵ���Ϊ�ý������ӽڵ�
			reNode.right = theNode.right;
			
			//���ָ��
			theNode = null;
			
			//�����������ظ����
			System.out.println("���"+thedata+"��ɾ��");
			return root;
		}
		throw new RuntimeException("ɾ������");
	}
	
	/**
	 * Ѱ�Ҹ�ĸ���
	 * @param root �����
	 * @param theNode ��Ҫ�ҵĽ��
	 * @return ��ĸ��㣨���û����Ϊnull��
	 */
	private Node search_parentNode(Node root,Node theNode) {
		if (root.data == theNode.data){return null;}
		while(root.left != theNode && root.right != theNode){
			if (root.data <= theNode.data) {root = root.right;}
			else {root = root.left;}
		}
		return root;
	}

	/**
	 * ���ҵ�ָ����ֵ�Ľ�㣬������
	 * @param root �����
	 * @param data ָ����ֵ
	 * @return ӵ��ָ����ֵ�Ľ��
	 */
	private Node search_Node(Node root , int data){
		if (root == null) {return null;}
		if (data == root.data) {return root;}
		else if (data < root.data) {
			return search_Node(root.left,  data);}
		else {
			return search_Node(root.right,  data);}
	}
	
	/**
	 * ����ָ���ڵ�������������ֵ�Ľ��
	 * @param root ָ���ڵ�
	 * @return �����ֵ�Ľ��
	 */
	private Node search_MaxNode(Node root){
		Node p = root.left;
		while(p.right != null){
			p = p.right;
		}
		return p;
	}
	
}

/**
 * ����2
 * @param root ���ĸ����
 * @return ���������������
 */
public static ArrayList<Object> preOrder2(Node root){
	
	//�ж���rootΪ�������Ƿ�Ϊ��
	if(root==null) return null;

	//�����洢����ջ
	Stack<Node> stack = new Stack<>();
	
	//��������������д�����б�
	ArrayList<Object> list = new ArrayList<>();
	
	//����ָ��
	Node p;
	
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
public static ArrayList<Object> inOrder(Node root){

	//�ж���rootΪ�������Ƿ�Ϊ��
	if(root==null) return null;
	
	//�����洢����ջ
	Stack<Node> stack = new Stack<>();
	
	//��������������д�����б�
	ArrayList<Object> list = new ArrayList<>();
	
	//����ָ�룬��ָ������
	Node p = root;
	
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

//����������
public static void main(String[] args){
	Node root = new Node(80);
	int[] datas = {50,120,60,110,55,70,53};
	Tree BSTree = new Tree();
	for(int i = 0 ; i<datas.length ; i++){
		BSTree.add(root,datas[i]);
	}
	
	ArrayList<Object> list = inOrder(root);
	
	System.out.print("�����������Ϊ��[");
	for (int i = 0 ; i<list.size() ; i++){
		if(i == list.size()-1) {System.out.println(list.get(i)+"]");break;}
		
		System.out.print(list.get(i)+",");
	}
	
    list = preOrder2(root);
	
	System.out.print("�����������Ϊ��[");
	for (int i = 0 ; i<list.size() ; i++){
		if(i == list.size()-1) {System.out.println(list.get(i)+"]");break;}
		
		System.out.print(list.get(i)+",");
	}
	
	int find = 2;
	if (BSTree.search(root, find)) {
		System.out.println("����"+root.data+"Ϊ�����Ķ����������У��ҵ�"+find+"Ԫ��");
	} else {
		System.out.println("����"+root.data+"Ϊ�����Ķ����������У�δ�ҵ�"+find+"Ԫ��");
	}
	
	int find1 = 10;
	if (BSTree.search(root, find1)) {
		System.out.println("����"+root.data+"Ϊ�����Ķ����������У��ҵ�"+find1+"Ԫ��");
	} else {
		System.out.println("����"+root.data+"Ϊ�����Ķ����������У�δ�ҵ�"+find1+"Ԫ��");
	}
	
	//ɾ��
	root = BSTree.remove(root,53);
	root = BSTree.remove(root,50);
	root = BSTree.remove(root,120);
	root = BSTree.remove(root,80);
	root = BSTree.remove(root,80);


	list = inOrder(root);
	System.out.print("�����������Ϊ��[");
	for (int i = 0 ; i<list.size() ; i++){
		if(i == list.size()-1) {System.out.println(list.get(i)+"]");break;}
		
		System.out.print(list.get(i)+",");
	}
	
    list = preOrder2(root);
	
	System.out.print("�����������Ϊ��[");
	for (int i = 0 ; i<list.size() ; i++){
		if(i == list.size()-1) {System.out.println(list.get(i)+"]");break;}
		
		System.out.print(list.get(i)+",");
	}
}

}
