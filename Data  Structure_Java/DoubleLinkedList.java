package test;

public class DoubleLinkedList {
	
	//创建链表 
	public static class DouLinkedList<E> {
		
		//链表结点数据类型定义
		public class Node{
			//数据域，保存数据元素
		    public E data; 
		    //地址域，保存前后结点地址
			public Node next; 
			public Node pre;
		    //构造结点，指定数据元素和后继结点
			public Node(E data, Node next , Node pre){
				this.data = data;
				this.next = next; 
				this.pre = pre; 
		    }
			//初始化结点
			public Node() {
				this(null,null,null);
		    }
		}
		
		//链表大小
		private int num = 0;
		//链表头结点 
		Node header;
		public  DouLinkedList() {
			header = new Node();
		}
		
		/**判断插入位置是否合法
		 * 
		 * @param index 插入位置
		 */
       private void Check(int n) {
           if (n <= 0 || n > num+1) {
               throw new IndexOutOfBoundsException ("插入位置不合法！");
           }
       }
       
       /**在链表头部插入
        * 
        * @param data 插入数据
        */
       public void addFirst(E data) {
    	   //如果是空表
    	   if (num == 0){
    		   addLast(data);
    		   return;
    	   }
    	   //创建一个新结点
  	       Node newNode = new Node();
  	       //将新结点和原结点的next和pre域指向相应结点
  	       newNode.next = header.next;
  	       header.next.pre = newNode;
           header.next = newNode;
           //输入数据
           newNode.data = data;
           //长度+1
           ++num;
      }
       
      /**在链表尾部插入
       * 
       * @param data 插入数据
       */
       public void  addLast(E data) {
          //创建一个新结点
          Node newNode = new Node();
          //找到最后一个结点p
          Node p = findNode(num);
          //各地址域指向相应结点
          p.next = newNode;
          newNode.pre = p;
          //输入数据
          newNode.data = data;
          //链表长度+1
          ++num;
       }
		    	
	   /** 在链表指定 n位置前插入结点
		 * 
		 * @param index 插入位置，从1计数
		 * @param data 插入元素
	   **/
	   public void  add(int n, E data) {
		   //判断插入位置是否合法
		   Check(n);
		   if (n == 1) {
			   addFirst(data);
		       return;
		   }
		   if (n == num+1) {
			   addLast(data);
		       return;
		   }
		   //创建一个新结点
		   Node newNode = new Node();
		   //找到插入位置n前后的结点p,q
		   Node p = findNode(n-1);
		   Node q = findNode(n);
		   //地址域指向相应
		   p.next = newNode;
		   newNode.pre = p;
		   newNode.next = q;
		   q.pre = newNode;
		   //输入数据
		   newNode.data = data;
		   //链表长度+1
		   ++num;
	   }
       
	   /**查找指定位置结点
	    *         
	    * @param n 位置
	    * @return 结点
	    */
	   private Node  findNode(int n) {
           Node p = header;
           for (int i = 1; i <= n; ++i) {
               p = p.next;
           }
           return p;
       }
	   /** 代码6：打印顺序表
		* 
		*  @param index 插入位置，从0计数
		*  @return 指定位置结点
	    **/
	   public void print() {
		   System.out.print("顺序表长度为:"+num+",内部元素为:");
		   Node p = header;
		   for (int i = 0; i < num; i++){
			   p = p.next;
			   System.out.print(p.data + " ");
		   }
		   System.out.println();
	   }
	   
	   //删除算法
	   
	   /** 代码1 删除链表第一个结点
	    * 
	    */
	   public void  removeFirst() {
		   //判断链表是否为空
		   if (header.next == null) {
			   throw new RuntimeException("删除失败，因为链表为空！");
		   }
		   //如果只有一个结点
		   if (num==1){
			   removeLast();
			   return;
		   }
		   //取得第一个结点p
		   Node p = header.next;
		   //相应地址域的改变
		   p.next.pre = null;
		   header.next = p.next;
		   p.next = null;
		   p.pre = null;
		   //链表长度-1
		   --num;
	   }
	   
	   /**删除链表尾元素 
	    * 
	    */
	   public void  removeLast() {
		   //判断链表是否为空
		   if (header.next == null) {
			   throw new RuntimeException("删除失败，因为链表为空!");
		   }
		   //取得倒数第二个结点p,以及最后一个结点q
		   Node p = findNode(num - 1);
		   Node q = p.next;
		   //相应地址域的改变
		   p.next = null;
		   q.pre = null;
		   //长度-1
		   --num;
		}
	   
		/** 删除链表指定位置的结点
		 * 
		 *  @param index 删除位置
		**/
	    public void  remove(int n) {
		   //判断链表是否为空
		   if (header.next == null) {
			   throw new RuntimeException("删除失败，因为链表为空！");
		   }
		   //判断删除位置是否合法
		   CheckForRemove(n);
		   if (n == 0) {
			   removeFirst();
			   return;
		   }
		   if (n == num) {
			   removeLast();
			   return;
		   }
		   //找到删除位置n前后的结点p、r，以及删除位置index的结点q
		   Node p = findNode(n-1);
		   Node q = p.next;
		   Node r = q.next;
		   //相应地址域改变
		   p.next = r;
		   r.pre = p;
		   q.next = null;
		   q.pre = null;
		   //链表长度-1
		   --num;
		}
		/** 判断删除位置是否合法
		 * 
		 * @param index 删除位置
		**/
	    private void  CheckForRemove(int n) {
	    	if (n <= 0 || n > num) {
	    		throw new IndexOutOfBoundsException("删除位置不合法！");
		    }
		}
	}
		    public static void  main(String[] args) {
		    	DouLinkedList<Integer> linkedList = new DouLinkedList<>();
		    	// 在链表头部插入元素
		    	linkedList.addFirst(2);
		    	linkedList.addFirst(6);
		    	linkedList.addFirst(3);
		    	linkedList.print();
		    	// 在链表中间插入元素
		    	linkedList.add(2, 7);
		    	linkedList.print();
		    	// 在链表尾部插入元素
		    	linkedList.addLast(8);
		    	linkedList.print();
		    	// 删除第一个元素
		    	linkedList.removeFirst();
		    	linkedList.print();
		    	// 在链表中间删除元素
		    	linkedList.remove(3);
		    	linkedList.print();
		    	// 删除最后一个元素
		    	linkedList.removeLast();
		    	linkedList.print();
	}
}



