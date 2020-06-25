package test;

public class ListReverse {
	
	//创建链表
	public static class LinkedList<E>{
		
		@SuppressWarnings("hiding")
		//创建结点
		class Node<E>{
			E data;
			Node<E> next;
			//修改节点数据
			public Node(E data,Node<E> next){
				this.data = data ;
				this.next = next;
			}
			//初始化结点
			public Node(){
				this(null,null);
			}
		}
		
		//头结点和链表长度
		Node<E> header ;
		int size;
		//初始化链表
		public LinkedList(){
			header = new Node<>();
			size = 0;
		}
		
		/**
		 * 添加结点
		 * (由于主要展现转置，所以只有添加结点)
		 * @param data 数据
		 */
		public void add(E data){
			Node<E> theNode = new Node<>();
			theNode.data = data;
			findNode(size).next = theNode;
			size++;
		}
		
		/**
		 * 查找指定位置结点
		 * @param size 指定位置
		 * @return 指定位置结点
		 */
		private Node<E> findNode(int index) {
			Node<E> point = header ;
			for (int i = 0 ; i < index ; i++) point = point.next;
			return point;
		}
		
		/**
		 * 打印
		 */
		public void print(){
			Node<E> point = header.next;
			for (int i = 1 ; i<=size ; i++){
				System.out.print(point.data + " ");
				point = point.next;
			}
			System.out.println();
		}
		
		/**
		 * 转置
		 */
		public void reverse(){
			
			//若链表长度为0或1，直接返回
			if (size == 0 || size == 1){
				return;
			}
			//创建交换的两个结点，以及过渡数据
			Node<E> Node1 = header.next;
			Node<E> Node2;
			E data;
			//创建交换结点的位置
			int i = this.size;
			int j = 0 ;
			//循环至链表的一半处
			while(i > j){
				//查找到i位置的结点
				Node2 = findNode(i); 
				//交换数据
				data = Node1.data;
				Node1.data = Node2.data;
				Node2.data = data;
				//移动指针，交换结点位置分别加减1
				Node1 = Node1.next;
				i--;
				j++;
			}
		}
	}
	
	public static void main(String[] args){
		LinkedList<Character> linkedlist = new LinkedList<>();
		linkedlist.add('A');
		linkedlist.add('B');
		linkedlist.add('C');
		linkedlist.add('D');
		linkedlist.add('E');
		System.out.print("转置前链表：");
		linkedlist.print();
		System.out.print("转置后链表：");
		linkedlist.reverse();
		linkedlist.print();
	}

}
