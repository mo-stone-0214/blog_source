package test;

import java.util.ArrayList;
import java.util.Stack;

public class Tree2 {

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
public static class LinkedTree<E>{
		
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
 * 后序 双栈
 * @param root 树的根结点
 * @return 后序遍历序列数组
 */
public static ArrayList<Object> postOrder(Node<Object> root){
	
	//判断以root为根的树是否为空
	if(root==null) return null;
	
	//创建暂时存储结点的栈1，以及存储结点倒序的栈2
	Stack<Node<Object>> stack = new Stack<>();
	Stack<Object> stack2 = new Stack<>();
	
	//创建后序遍历序列储存的列表
	ArrayList<Object> list = new ArrayList<>();
	
	//创建指针
	Node<Object> p;
	
	//将根结点入栈1
	stack.push(root);
	
	//循环至栈1为空，即栈1结点数据均入栈2
	while(!stack.isEmpty()){
		
		/*
		 * 始终依据后序遍历的特点：左子节点→右子结点→p结点
		 * 由于栈是先入后出，入栈顺序，p结点→右子结点→左子节点
		 */
		
		//弹出栈1顶端结点
		p = stack.pop();
		
		//根据是否存在左右子节点，按照p→右→左的顺序入栈2
		stack2.push(p.data);
		if( p.left != null) stack.push(p.left);
		if( p.right != null) stack.push(p.right);
	}
	
	//将栈2里的元素依次输入至数组
	while (!stack2.isEmpty()){
		list.add(stack2.pop());
	}
	
	//返回后序遍历序列数组
	return list;
}

/**
 * 后序 标记
 * @param root 树的根结点
 * @return 后序遍历序列数组
 */
public static ArrayList<Object> postOrder2(Node<Object> root){
	
	//判断以root为根的树是否为空
		if(root==null) return null;
		
		//创建储存结点和左右子树的标记的栈，此处左子树记为-1，右为1.
		Stack<Node<Object>> stack = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();

		//创建后序遍历序列储存的列表
		ArrayList<Object> list = new ArrayList<>();
		
		//创建指针
		Node<Object> p = root;

	//循环直至结点栈为空和指针为null
	while( p != null || !stack.isEmpty()){
		
		//若指针不为null，将结点入栈，并标记为左子树（-1），循环至p指向null
		while (p!=null){
			stack.push(p);
			stack2.push(-1);
			p = p.left;
		}
		
		//若p指针为null,栈中为空，则已遍历完成，跳出循环。
		if(stack.isEmpty()) break;
		
		/*
		 * 若指针指向的上一个结点，即结点栈顶端的结点在右子树，
		 * 则该节点左右结点结点均已遍历（当然也有可能为空），弹出右标记，并将该结点入表
		 */
	    if(stack2.peek() == 1){
			stack2.pop();
			list.add(stack.pop().data);
		}
		
		/* 
		 * 若指针指向的上一个结点，即结点栈顶端的结点在左子树，
		 * 则弹出左标记，指针指向该节点的右结点，并标记为右子树（1）。
		 * 
		 * 这里需要注意，指向右结点后有可能该节点存在左子树，
		 * 所以需要再次利用前方添加左节点的循环，
		 * 所以需要在循环最后。
		 */    
	    else if(stack2.peek() == -1){
			stack2.pop();
			p = stack.peek().right;
			stack2.push(1);
		}
	}
	
	//循环结束，返回后序遍历序列数组
	return list;
}

/**
 * 层次
 */
@SuppressWarnings("unchecked")
public static ArrayList<Object> levelOrder(Node<Object> root){
	
	//判断以root为根的树是否为空
	if(root==null) return null;
	
	//创建储存结点的队列
	QueueArray<Node<Object>> quearr = new QueueArray<>();
	
	//创建后序遍历序列储存的数组
	ArrayList<Object> list = new ArrayList<>();
	
	//将根结点入队
	quearr.In(root);
	
	//创建当前结点和指针,指针指向队首结点
	int flag = 0;
	Node<Object> p = (Node<Object>) quearr.que_arr[flag];
	
	//循环至队列为空
	while (quearr.count != 0){
		
		/*
		 * 指针从队首依次向后移动，依次将该节点的左右子节点入队
		 * 若指针向后移动，之前的结点出队
		 */
		if(p.left!=null) quearr.In(p.left);
		if(p.right!=null) quearr.In(p.right);
		
		//出队入组
		list.add(((Node<Object>)quearr.Out()).data);
			
		//flag标号的增加
		if (flag+1 >= quearr.size){
			flag = 0;
		}else{
			++flag;
		}
		
		//指针向后移动
		p = (Node<Object>) quearr.que_arr[flag];
	}
	
	//返回层次遍历序列数组
	return list;
}

/**
 * 循环队列
 */
static class QueueArray<E>{
	
	//设定队列，队的容量，前端和后端，元素数量。
	//前端为队头元素位置，后端下标为队尾元素后一个位置。
	private int size = 20 ;
	private Object[] que_arr ;
	private int front ;
	private int rear ;
	private int count ;
	//初始化队列
	public QueueArray(){
		que_arr =new Object [size] ;
		this.front = 0 ;
		this.rear = 0 ;
		this.count = 0;
	}
	/**
	 * 入队
	 * @param data
	 */
	public void In(E data){
		//判断是否队满
		if (count == size){
			throw new RuntimeException("队满");
		}
		//添加数据，元素总数+1
		que_arr[rear] = data ;
		++count;
		//队尾标号的增加
		if (rear+1 >= size){
			rear = 0;
		}else{
		    ++rear;
		}
	}
	
	/**
	 * 出队
	 * @return 队头元素
	 */
	public Object Out(){
		if (count == 0){
			throw new RuntimeException("队空");
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
 * 输出
 * @param list 需输出的数组
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
	
	System.out.print("先序遍历序列，法一：");
	print(preOrder(A));
	
	System.out.print("先序遍历序列，法二：");
	print(preOrder2(A));
	
	System.out.print("中序遍历序列               ：");
	print(inOrder(A));
	
	System.out.print("后序遍历序列，双栈：");
	print(postOrder(A));
	
	System.out.print("后序遍历序列，标记：");
	print(postOrder2(A));
	
	System.out.print("层次遍历序列               ：");
	print(levelOrder(A));

}

}
