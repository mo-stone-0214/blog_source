package test;

public class QueueList {
	public static class QueueArray<E>{
		
		//�趨���У��ӵ�������ǰ�˺ͺ�ˣ�Ԫ��������
		//ǰ��Ϊ��ͷԪ��λ�ã�����±�Ϊ��βԪ�غ�һ��λ�á�
		private int size = 5 ;
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
		 * @return ��ͷ��Ԫ��
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
		/**
		 * ���
		 */
		public void print(){
			System.out.print("[");
			
			for (int i = 0 ; i<size ; i++){
	
				if (i == size-1){
					System.out.print(que_arr[size-1]+"]");
				}else{
				    System.out.print(que_arr[i]+",");   
				}
			}
			System.out.println("count:"+count+" front:"+front+" rear:"+rear);
		}
	}

	//������������
	public static class QueueLinked<E>{
		
		private int size;
		private Node header;
		//����ָ������ĩ�˽���ָ��
		Node p;
		//��ʼ������
		public QueueLinked(){
			header = new Node();
			size = 0 ;
			p = header;
		}
		//�������
		public class Node{
			//����������͵�ַ��
			private E data;
			private Node next;
			//��������ʹ���Ԫ�صĸı�
			public Node(E data,Node next){
				this.data = data;
				this.next = next;
			}
			//��ʼ�����
			public Node(){
				this(null,null);
			}
		}
		
		/**
		 * ���
		 * @param data ��Ҫ��ӵ�����
		 */
		public void In(E data){
			//�������
			Node newnode = new Node();
			//��������
			newnode.data = data;
			//�������ӵ���ǰ����ĩ��֮��
			p.next = newnode;
			//pָ���½�㣬���ַ��ָ�����
			p = newnode;
			newnode.next = header.next;
			//�����+1
			++size;
		}
		
		public E Out(){
			//�ж϶����Ƿ�Ϊ��
			if (size == 0){
				throw new RuntimeException("�ӿ�");
			}
			//����ָ��ָ��ɾ���Ľ��
			Node del = header.next;
			//�����������
			E data = del.data;
			
			//ɾ�����׽��
			//���������ֻ��һ����㣬ͷ����ַ��null
			if (size == 1){
				header.next = null ;
			//����ͷ����ַ��ָ��ڶ������
			}else{
				header.next = del.next;
			}
			
			//��β��ַ��ָ���¶���
			p.next = header.next;
			//��ɾ���Ľ�����
			del.data = null ;
			del.next = null ;
			//����-1
			--size;
			//���ض�������
			return data;
		}
		
		/**
		 * ��ӡ
		 */
		public void print(){
			Node q = header.next;
			for (int i = 0 ; i<size ; i++){
				if (i == size-1){
					System.out.print(q.data);
				}else{
					System.out.print(q.data+",");
					q = q.next;
				}
			}
			System.out.println("����size:"+size+" ; p.next:"+p.next.data);
		}
	}
	//����������
	public static void main(String[] args){
		/*QueueArray<Object> queue = new QueueArray<>();
		//queue.Out();
		queue.In(1);
		queue.In(2);
		queue.In(3);
		
		queue.print();
		queue.Out();
		queue.print();

		queue.In(4);
		queue.In(5);		
		queue.In(6);
		//queue.In(7);
		queue.print();
		
		queue.Out();
		queue.Out();
		queue.Out();
		queue.print();
		
		queue.In(7);
		queue.Out();
		queue.Out();
		queue.print();*/
		
		QueueLinked<Object> queue2 = new QueueLinked<>();
		//queue2.Out();
		queue2.In(1);
		queue2.In(2);
		queue2.In(3);
		queue2.print();
		
		queue2.Out();
		queue2.Out();
		queue2.print();
		
		
	}
}
