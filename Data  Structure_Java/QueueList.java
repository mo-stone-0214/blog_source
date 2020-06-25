package test;

public class QueueList {
	public static class QueueArray<E>{
		
		//设定队列，队的容量，前端和后端，元素数量。
		//前端为队头元素位置，后端下标为队尾元素后一个位置。
		private int size = 5 ;
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
		 * @return 队头的元素
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
		/**
		 * 输出
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

	//用链表创建队列
	public static class QueueLinked<E>{
		
		private int size;
		private Node header;
		//创建指向链表末端结点的指针
		Node p;
		//初始化链表
		public QueueLinked(){
			header = new Node();
			size = 0 ;
			p = header;
		}
		//创建结点
		public class Node{
			//创建数据域和地址域
			private E data;
			private Node next;
			//构建方法使结点元素的改变
			public Node(E data,Node next){
				this.data = data;
				this.next = next;
			}
			//初始化结点
			public Node(){
				this(null,null);
			}
		}
		
		/**
		 * 入队
		 * @param data 需要入队的数据
		 */
		public void In(E data){
			//创建结点
			Node newnode = new Node();
			//赋予数据
			newnode.data = data;
			//将结点添加到当前链表末端之后
			p.next = newnode;
			//p指向新结点，其地址域指向队首
			p = newnode;
			newnode.next = header.next;
			//结点数+1
			++size;
		}
		
		public E Out(){
			//判断队列是否为空
			if (size == 0){
				throw new RuntimeException("队空");
			}
			//创建指针指向删除的结点
			Node del = header.next;
			//队首数据输出
			E data = del.data;
			
			//删除队首结点
			//如果队列中只有一个结点，头结点地址域null
			if (size == 1){
				header.next = null ;
			//否则头结点地址域指向第二个结点
			}else{
				header.next = del.next;
			}
			
			//队尾地址域指向新队首
			p.next = header.next;
			//将删除的结点清空
			del.data = null ;
			del.next = null ;
			//总数-1
			--size;
			//返回队首数据
			return data;
		}
		
		/**
		 * 打印
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
			System.out.println("——size:"+size+" ; p.next:"+p.next.data);
		}
	}
	//主函数测试
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
