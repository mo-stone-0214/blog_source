package test;

public class Test3 {

	    //创建单链表 
	    public static class LinkedList<E> {
	    	//链表大小
	    	private int size = 0;
	    	//链表头结点 
	    	Node header;
	    	public  LinkedList() {
	    		header = new Node();
	    	}
	    	
	        //单链表结点数据类型定义
	    	public class Node{
	    		//数据域，保存数据元素
	    		public E data; 
	            //地址域，保存后继结点地址
	    		public Node next; 
	    		//构造结点，指定数据元素和后继结点
	            public Node(E data, Node next){ 
	            	this.data = data;
	            	this.next = next; 
	            }
	            public Node() {
	            	this(null,null);
	            }
	        }
	    	
	    	/** 代码3： 在链表中间指定 index 位置前插入结点
	          * @param index 插入位置，从0计数
	          * @param data 插入元素
	         **/
	    	public void  add(int index, E data) {
	            // 1. 判断插入位置是否合法
	            rangeCheckForAdd(index);
	            if (index == 0) {
	                addFirst(data);
	                return;
	            }
	            if (index == size) {
	                addLast(data);
	                return;
	            }
	            // 2. 创建一个新结点
	            Node newNode = new Node();
	            // 3. 找到插入位置index前的结点p
	            Node p = findNode(index);
	            // 4. 将新结点的next域指向p结点的next域
	            newNode.next = p.next;
	            // 5. 将p结点的next域指向新结点
	            p.next = newNode;
	            // 6. 输入数据
	            newNode.data = data;
	            // 7. 链表长度+1
	            ++size;
	        }
		
	        /** 代码2： 在链表头部插入结点
	          * @param data 插入元素
	         **/
	        public void addFirst(E data) {
	    	    // 1. 创建一个新结点
	    	    Node newNode = new Node();
	    	    // 2. 将新结点的next域指向为header结点的next域
	    	    newNode.next = header.next;
	            // 3. 将header结点指向新结点
	            header.next = newNode;
	            // 4. 输入数据
	            newNode.data = data;
	            // 5. 链表长度+1
	            ++size;
	        }
	        
	        /** 代码4  在链表尾部插入结点
	          * @param data 插入元素
	         **/
	        public void  addLast(E data) {
	            // 1. 创建一个新结点
	            Node newNode = new Node();
	            // 2. 找到最后一个结点q
	            Node q = findNode(size);
	            // 3. 将q结点的next域指向新结点
	            q.next = newNode;
	            // 4. 输入数据
	            newNode.data = data;
	            // 5. 链表长度+1
	            ++size;
	        }
	        
	        /** 代码1：判断插入位置是否合法
	          * @param index 插入位置
	         **/
	        private void  rangeCheckForAdd(int index) {
	            if (index < 0 || index > size) {
	                throw new IndexOutOfBoundsException ("插入位置不合法！");
	            }
	        }
	        
	        /** 代码5：找到指定位置index前的结点P
	         ** @param index 插入位置，从0计数
	         ** @return 指定位置结点
	         **/
	        private Node  findNode(int index) {
	            Node p = header;
	            for (int i = 0; i < index; ++i) {
	                p = p.next;
	            }
	            return p;
	        }
	        
	        /** 代码6：打印顺序表
	         ** @param index 插入位置，从0计数
	         ** @return 指定位置结点
	         **/
	        public void print() {
	    	    System.out.print("顺序表长度为:"+size+",内部元素为:");
	    	    Node p = header;
	    	    for (int i = 0; i < size; i++){
	    	    	p = p.next;
	    	    	System.out.print(p.data + " ");
	    	    }
	    	    System.out.println();
	    	}
	        
	        
	        //删除算法
	        /** 代码1 删除链表第一个结点
	         **/
	        public void  removeFirst() {
	            // 1. 判断链表是否为空
	            if (header.next == null) {
	                throw new RuntimeException("删除失败，因为链表为空！");
	            }
	            // 2. 取得第一个结点p
	            Node p = header.next;
	            // 3. 将 header 的 next 域指向p结点的 next域
	            header.next = p.next;
	            // 4. 将p p 结点的 next 域设为 null ，回收p内存
	            p.next = null;
	            //  5.  链表长度- -1 1
	            --size;
	        }
	        
	        /** 代码2 ：删除链表尾元素 
	         **/
	        public void  removeLast() {
	            // 1. 判断链表是否为空
	            if (header.next == null) {
	                throw new RuntimeException("删除失败，因为链表为空!");
	            }
	            // 2. 取得倒数第二个结点p,以及最后一个结点q
	            Node p = findNode(size - 1);
	            Node q = p.next;
	            // 3. 将p的next域指向q的next域
	            p.next = q.next;
	            // 4. 链表长度-1
	            --size;
	        }
	        
	        /** 代码3：删除链表指定位置的结点
	          * @param index 删除位置
	         **/
	        public void  remove(int index) {
	            // 1. 判断链表是否为空
	            if (header.next == null) {
	                throw new RuntimeException("删除失败，因为链表为空！");
	            }
	            // 2. 判断删除位置是否合法
	            rangeCheckForRemove(index);

	            if (index == 0) {
	                removeFirst();
	                return;
	            }
	            if (index == size - 1) {
	                removeLast();
	                return;
	            }
	            // 3. 找到删除位置index前的结点p，以及删除位置index的结点q
	            Node p = findNode(index);
	            Node q = p.next;
	            // 4. 将p结点的next域指向q结点的next域
	            p.next = q.next;
	            // 5. 将q结点的next域设为null，回收q内存
	            q.next = null;
	            // 6. 链表长度-1
	            --size;
	        }
	        
	        /** 代码4： 判断删除位置是否合法
	          * @param index 删除位置
	         **/
	        private void  rangeCheckForRemove(int index) {
	            if (index < 0 || index > size - 1) {
	                throw new IndexOutOfBoundsException("删除位置不合法！");
	            }
	        }
	        
	        /** 代码5：打印顺序表。（同上，略）*/
	        
	        //连接
	        
	  
	    }
	    public static void  main(String[] args) {
	    	/*LinkedList<Integer> linkedList = new LinkedList<>();
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
	    	linkedList.print();*/
	    	
	    	LinkedList<Integer> linkedListA =new LinkedList<>();
	    	linkedListA.addLast(3);
	    	linkedListA.addLast(5);
	    	linkedListA.addLast(8);
	    	linkedListA.addLast(11);
	    	linkedListA.print();
	    	
	    	LinkedList<Integer> linkedListB =new LinkedList<>();
	    	linkedListB.addLast(2);
	    	linkedListB.addLast(6);
	    	linkedListB.addLast(8);
	    	linkedListB.addLast(9);
	    	linkedListB.addLast(11);
	    	linkedListB.print();
		    
		    LinkedList<Integer> linkedListC = new LinkedList<>();
		    linkedListC=Connection(linkedListA,linkedListB);
		    linkedListC.print();
		    
		    LinkedList<Integer> linkedListD = new LinkedList<>();
		    linkedListD=DeleteSingle(linkedListC);
		    linkedListD.print();

		    
	    }
	    //删除单链表偶数的算法
	    public static LinkedList<Integer> DeleteSingle(LinkedList<Integer> a){
	    	//创建指针pa
	    	LinkedList<Integer>.Node pa = a.header.next;
	    	//若单链表为空，直接返回
	    	if (pa == null){
	    		return a;
	    	}
	    	//若单链表中数据只有一个单数，删除后返回
	    	if (pa.next == null && (pa.data%2==1)){
	    		a.header.next = null;
	    		--a.size;
	    		return a;
	    	}
	    	//循环至倒数第二个数据
	    	while (pa.next != null){
	    		//如果下一个数据为单数，删除。
	    		if ( (pa.next.data) %2 ==1 ){
	    			LinkedList<Integer>.Node pb = pa.next;
	    			pa.next =pb.next;
	    			pb.next = null;
	    			--a.size;
	    		//如果是偶数，跳过。	
	    		}else{
	    			pa = pa.next;
	    		}
	    	}
	    	return a;
	    }
		public static LinkedList<Integer> Connection(LinkedList<Integer> a, LinkedList<Integer> b){
	        LinkedList<Integer> c = new LinkedList<>();
	        //创建指针，并指向第一个结点
	        LinkedList<Integer>.Node pa=a.header.next;
	        LinkedList<Integer>.Node pb=b.header.next;
	        while (pa != null && pb != null){
	        	if (pa.data >= pb.data){
	        		c.addLast(pb.data);
	        	    pb = pb.next;
	        	}else {
	        		c.addLast(pa.data);
	        		pa = pa.next;
	        	}
	        }
	        if (pa==null){
	        	while(pb!=null){
	        		c.addLast(pb.data);
	        		pb=pb.next;
	        	}
	        }else if(pb==null){
	        	while(pa!=null){
	        		c.addLast(pa.data);
	        		pa=pa.next;
	        	}
	        }
	        
	        return c;
	    }
		
}
