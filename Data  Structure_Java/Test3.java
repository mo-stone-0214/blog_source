package test;

public class Test3 {

	    //���������� 
	    public static class LinkedList<E> {
	    	//�����С
	    	private int size = 0;
	    	//����ͷ��� 
	    	Node header;
	    	public  LinkedList() {
	    		header = new Node();
	    	}
	    	
	        //���������������Ͷ���
	    	public class Node{
	    		//�����򣬱�������Ԫ��
	    		public E data; 
	            //��ַ�򣬱����̽���ַ
	    		public Node next; 
	    		//�����㣬ָ������Ԫ�غͺ�̽��
	            public Node(E data, Node next){ 
	            	this.data = data;
	            	this.next = next; 
	            }
	            public Node() {
	            	this(null,null);
	            }
	        }
	    	
	    	/** ����3�� �������м�ָ�� index λ��ǰ������
	          * @param index ����λ�ã���0����
	          * @param data ����Ԫ��
	         **/
	    	public void  add(int index, E data) {
	            // 1. �жϲ���λ���Ƿ�Ϸ�
	            rangeCheckForAdd(index);
	            if (index == 0) {
	                addFirst(data);
	                return;
	            }
	            if (index == size) {
	                addLast(data);
	                return;
	            }
	            // 2. ����һ���½��
	            Node newNode = new Node();
	            // 3. �ҵ�����λ��indexǰ�Ľ��p
	            Node p = findNode(index);
	            // 4. ���½���next��ָ��p����next��
	            newNode.next = p.next;
	            // 5. ��p����next��ָ���½��
	            p.next = newNode;
	            // 6. ��������
	            newNode.data = data;
	            // 7. ������+1
	            ++size;
	        }
		
	        /** ����2�� ������ͷ��������
	          * @param data ����Ԫ��
	         **/
	        public void addFirst(E data) {
	    	    // 1. ����һ���½��
	    	    Node newNode = new Node();
	    	    // 2. ���½���next��ָ��Ϊheader����next��
	    	    newNode.next = header.next;
	            // 3. ��header���ָ���½��
	            header.next = newNode;
	            // 4. ��������
	            newNode.data = data;
	            // 5. ������+1
	            ++size;
	        }
	        
	        /** ����4  ������β��������
	          * @param data ����Ԫ��
	         **/
	        public void  addLast(E data) {
	            // 1. ����һ���½��
	            Node newNode = new Node();
	            // 2. �ҵ����һ�����q
	            Node q = findNode(size);
	            // 3. ��q����next��ָ���½��
	            q.next = newNode;
	            // 4. ��������
	            newNode.data = data;
	            // 5. ������+1
	            ++size;
	        }
	        
	        /** ����1���жϲ���λ���Ƿ�Ϸ�
	          * @param index ����λ��
	         **/
	        private void  rangeCheckForAdd(int index) {
	            if (index < 0 || index > size) {
	                throw new IndexOutOfBoundsException ("����λ�ò��Ϸ���");
	            }
	        }
	        
	        /** ����5���ҵ�ָ��λ��indexǰ�Ľ��P
	         ** @param index ����λ�ã���0����
	         ** @return ָ��λ�ý��
	         **/
	        private Node  findNode(int index) {
	            Node p = header;
	            for (int i = 0; i < index; ++i) {
	                p = p.next;
	            }
	            return p;
	        }
	        
	        /** ����6����ӡ˳���
	         ** @param index ����λ�ã���0����
	         ** @return ָ��λ�ý��
	         **/
	        public void print() {
	    	    System.out.print("˳�����Ϊ:"+size+",�ڲ�Ԫ��Ϊ:");
	    	    Node p = header;
	    	    for (int i = 0; i < size; i++){
	    	    	p = p.next;
	    	    	System.out.print(p.data + " ");
	    	    }
	    	    System.out.println();
	    	}
	        
	        
	        //ɾ���㷨
	        /** ����1 ɾ�������һ�����
	         **/
	        public void  removeFirst() {
	            // 1. �ж������Ƿ�Ϊ��
	            if (header.next == null) {
	                throw new RuntimeException("ɾ��ʧ�ܣ���Ϊ����Ϊ�գ�");
	            }
	            // 2. ȡ�õ�һ�����p
	            Node p = header.next;
	            // 3. �� header �� next ��ָ��p���� next��
	            header.next = p.next;
	            // 4. ��p p ���� next ����Ϊ null ������p�ڴ�
	            p.next = null;
	            //  5.  ������- -1 1
	            --size;
	        }
	        
	        /** ����2 ��ɾ������βԪ�� 
	         **/
	        public void  removeLast() {
	            // 1. �ж������Ƿ�Ϊ��
	            if (header.next == null) {
	                throw new RuntimeException("ɾ��ʧ�ܣ���Ϊ����Ϊ��!");
	            }
	            // 2. ȡ�õ����ڶ������p,�Լ����һ�����q
	            Node p = findNode(size - 1);
	            Node q = p.next;
	            // 3. ��p��next��ָ��q��next��
	            p.next = q.next;
	            // 4. ������-1
	            --size;
	        }
	        
	        /** ����3��ɾ������ָ��λ�õĽ��
	          * @param index ɾ��λ��
	         **/
	        public void  remove(int index) {
	            // 1. �ж������Ƿ�Ϊ��
	            if (header.next == null) {
	                throw new RuntimeException("ɾ��ʧ�ܣ���Ϊ����Ϊ�գ�");
	            }
	            // 2. �ж�ɾ��λ���Ƿ�Ϸ�
	            rangeCheckForRemove(index);

	            if (index == 0) {
	                removeFirst();
	                return;
	            }
	            if (index == size - 1) {
	                removeLast();
	                return;
	            }
	            // 3. �ҵ�ɾ��λ��indexǰ�Ľ��p���Լ�ɾ��λ��index�Ľ��q
	            Node p = findNode(index);
	            Node q = p.next;
	            // 4. ��p����next��ָ��q����next��
	            p.next = q.next;
	            // 5. ��q����next����Ϊnull������q�ڴ�
	            q.next = null;
	            // 6. ������-1
	            --size;
	        }
	        
	        /** ����4�� �ж�ɾ��λ���Ƿ�Ϸ�
	          * @param index ɾ��λ��
	         **/
	        private void  rangeCheckForRemove(int index) {
	            if (index < 0 || index > size - 1) {
	                throw new IndexOutOfBoundsException("ɾ��λ�ò��Ϸ���");
	            }
	        }
	        
	        /** ����5����ӡ˳�����ͬ�ϣ��ԣ�*/
	        
	        //����
	        
	  
	    }
	    public static void  main(String[] args) {
	    	/*LinkedList<Integer> linkedList = new LinkedList<>();
	    	// ������ͷ������Ԫ��
	    	linkedList.addFirst(2);
	    	linkedList.addFirst(6);
	    	linkedList.addFirst(3);
	    	linkedList.print();
	    	// �������м����Ԫ��
	    	linkedList.add(2, 7);
	    	linkedList.print();
	    	// ������β������Ԫ��
	    	linkedList.addLast(8);
	    	linkedList.print();
	    	// ɾ����һ��Ԫ��
	    	linkedList.removeFirst();
	    	linkedList.print();
	    	// �������м�ɾ��Ԫ��
	    	linkedList.remove(3);
	    	linkedList.print();
	    	// ɾ�����һ��Ԫ��
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
	    //ɾ��������ż�����㷨
	    public static LinkedList<Integer> DeleteSingle(LinkedList<Integer> a){
	    	//����ָ��pa
	    	LinkedList<Integer>.Node pa = a.header.next;
	    	//��������Ϊ�գ�ֱ�ӷ���
	    	if (pa == null){
	    		return a;
	    	}
	    	//��������������ֻ��һ��������ɾ���󷵻�
	    	if (pa.next == null && (pa.data%2==1)){
	    		a.header.next = null;
	    		--a.size;
	    		return a;
	    	}
	    	//ѭ���������ڶ�������
	    	while (pa.next != null){
	    		//�����һ������Ϊ������ɾ����
	    		if ( (pa.next.data) %2 ==1 ){
	    			LinkedList<Integer>.Node pb = pa.next;
	    			pa.next =pb.next;
	    			pb.next = null;
	    			--a.size;
	    		//�����ż����������	
	    		}else{
	    			pa = pa.next;
	    		}
	    	}
	    	return a;
	    }
		public static LinkedList<Integer> Connection(LinkedList<Integer> a, LinkedList<Integer> b){
	        LinkedList<Integer> c = new LinkedList<>();
	        //����ָ�룬��ָ���һ�����
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
