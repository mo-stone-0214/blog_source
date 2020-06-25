package test;

public class ListReverse {
	
	//��������
	public static class LinkedList<E>{
		
		@SuppressWarnings("hiding")
		//�������
		class Node<E>{
			E data;
			Node<E> next;
			//�޸Ľڵ�����
			public Node(E data,Node<E> next){
				this.data = data ;
				this.next = next;
			}
			//��ʼ�����
			public Node(){
				this(null,null);
			}
		}
		
		//ͷ����������
		Node<E> header ;
		int size;
		//��ʼ������
		public LinkedList(){
			header = new Node<>();
			size = 0;
		}
		
		/**
		 * ��ӽ��
		 * (������Ҫչ��ת�ã�����ֻ����ӽ��)
		 * @param data ����
		 */
		public void add(E data){
			Node<E> theNode = new Node<>();
			theNode.data = data;
			findNode(size).next = theNode;
			size++;
		}
		
		/**
		 * ����ָ��λ�ý��
		 * @param size ָ��λ��
		 * @return ָ��λ�ý��
		 */
		private Node<E> findNode(int index) {
			Node<E> point = header ;
			for (int i = 0 ; i < index ; i++) point = point.next;
			return point;
		}
		
		/**
		 * ��ӡ
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
		 * ת��
		 */
		public void reverse(){
			
			//��������Ϊ0��1��ֱ�ӷ���
			if (size == 0 || size == 1){
				return;
			}
			//����������������㣬�Լ���������
			Node<E> Node1 = header.next;
			Node<E> Node2;
			E data;
			//������������λ��
			int i = this.size;
			int j = 0 ;
			//ѭ���������һ�봦
			while(i > j){
				//���ҵ�iλ�õĽ��
				Node2 = findNode(i); 
				//��������
				data = Node1.data;
				Node1.data = Node2.data;
				Node2.data = data;
				//�ƶ�ָ�룬�������λ�÷ֱ�Ӽ�1
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
		System.out.print("ת��ǰ����");
		linkedlist.print();
		System.out.print("ת�ú�����");
		linkedlist.reverse();
		linkedlist.print();
	}

}
