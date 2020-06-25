package test;

public class DoubleLinkedList {
	
	//�������� 
	public static class DouLinkedList<E> {
		
		//�������������Ͷ���
		public class Node{
			//�����򣬱�������Ԫ��
		    public E data; 
		    //��ַ�򣬱���ǰ�����ַ
			public Node next; 
			public Node pre;
		    //�����㣬ָ������Ԫ�غͺ�̽��
			public Node(E data, Node next , Node pre){
				this.data = data;
				this.next = next; 
				this.pre = pre; 
		    }
			//��ʼ�����
			public Node() {
				this(null,null,null);
		    }
		}
		
		//�����С
		private int num = 0;
		//����ͷ��� 
		Node header;
		public  DouLinkedList() {
			header = new Node();
		}
		
		/**�жϲ���λ���Ƿ�Ϸ�
		 * 
		 * @param index ����λ��
		 */
       private void Check(int n) {
           if (n <= 0 || n > num+1) {
               throw new IndexOutOfBoundsException ("����λ�ò��Ϸ���");
           }
       }
       
       /**������ͷ������
        * 
        * @param data ��������
        */
       public void addFirst(E data) {
    	   //����ǿձ�
    	   if (num == 0){
    		   addLast(data);
    		   return;
    	   }
    	   //����һ���½��
  	       Node newNode = new Node();
  	       //���½���ԭ����next��pre��ָ����Ӧ���
  	       newNode.next = header.next;
  	       header.next.pre = newNode;
           header.next = newNode;
           //��������
           newNode.data = data;
           //����+1
           ++num;
      }
       
      /**������β������
       * 
       * @param data ��������
       */
       public void  addLast(E data) {
          //����һ���½��
          Node newNode = new Node();
          //�ҵ����һ�����p
          Node p = findNode(num);
          //����ַ��ָ����Ӧ���
          p.next = newNode;
          newNode.pre = p;
          //��������
          newNode.data = data;
          //������+1
          ++num;
       }
		    	
	   /** ������ָ�� nλ��ǰ������
		 * 
		 * @param index ����λ�ã���1����
		 * @param data ����Ԫ��
	   **/
	   public void  add(int n, E data) {
		   //�жϲ���λ���Ƿ�Ϸ�
		   Check(n);
		   if (n == 1) {
			   addFirst(data);
		       return;
		   }
		   if (n == num+1) {
			   addLast(data);
		       return;
		   }
		   //����һ���½��
		   Node newNode = new Node();
		   //�ҵ�����λ��nǰ��Ľ��p,q
		   Node p = findNode(n-1);
		   Node q = findNode(n);
		   //��ַ��ָ����Ӧ
		   p.next = newNode;
		   newNode.pre = p;
		   newNode.next = q;
		   q.pre = newNode;
		   //��������
		   newNode.data = data;
		   //������+1
		   ++num;
	   }
       
	   /**����ָ��λ�ý��
	    *         
	    * @param n λ��
	    * @return ���
	    */
	   private Node  findNode(int n) {
           Node p = header;
           for (int i = 1; i <= n; ++i) {
               p = p.next;
           }
           return p;
       }
	   /** ����6����ӡ˳���
		* 
		*  @param index ����λ�ã���0����
		*  @return ָ��λ�ý��
	    **/
	   public void print() {
		   System.out.print("˳�����Ϊ:"+num+",�ڲ�Ԫ��Ϊ:");
		   Node p = header;
		   for (int i = 0; i < num; i++){
			   p = p.next;
			   System.out.print(p.data + " ");
		   }
		   System.out.println();
	   }
	   
	   //ɾ���㷨
	   
	   /** ����1 ɾ�������һ�����
	    * 
	    */
	   public void  removeFirst() {
		   //�ж������Ƿ�Ϊ��
		   if (header.next == null) {
			   throw new RuntimeException("ɾ��ʧ�ܣ���Ϊ����Ϊ�գ�");
		   }
		   //���ֻ��һ�����
		   if (num==1){
			   removeLast();
			   return;
		   }
		   //ȡ�õ�һ�����p
		   Node p = header.next;
		   //��Ӧ��ַ��ĸı�
		   p.next.pre = null;
		   header.next = p.next;
		   p.next = null;
		   p.pre = null;
		   //������-1
		   --num;
	   }
	   
	   /**ɾ������βԪ�� 
	    * 
	    */
	   public void  removeLast() {
		   //�ж������Ƿ�Ϊ��
		   if (header.next == null) {
			   throw new RuntimeException("ɾ��ʧ�ܣ���Ϊ����Ϊ��!");
		   }
		   //ȡ�õ����ڶ������p,�Լ����һ�����q
		   Node p = findNode(num - 1);
		   Node q = p.next;
		   //��Ӧ��ַ��ĸı�
		   p.next = null;
		   q.pre = null;
		   //����-1
		   --num;
		}
	   
		/** ɾ������ָ��λ�õĽ��
		 * 
		 *  @param index ɾ��λ��
		**/
	    public void  remove(int n) {
		   //�ж������Ƿ�Ϊ��
		   if (header.next == null) {
			   throw new RuntimeException("ɾ��ʧ�ܣ���Ϊ����Ϊ�գ�");
		   }
		   //�ж�ɾ��λ���Ƿ�Ϸ�
		   CheckForRemove(n);
		   if (n == 0) {
			   removeFirst();
			   return;
		   }
		   if (n == num) {
			   removeLast();
			   return;
		   }
		   //�ҵ�ɾ��λ��nǰ��Ľ��p��r���Լ�ɾ��λ��index�Ľ��q
		   Node p = findNode(n-1);
		   Node q = p.next;
		   Node r = q.next;
		   //��Ӧ��ַ��ı�
		   p.next = r;
		   r.pre = p;
		   q.next = null;
		   q.pre = null;
		   //������-1
		   --num;
		}
		/** �ж�ɾ��λ���Ƿ�Ϸ�
		 * 
		 * @param index ɾ��λ��
		**/
	    private void  CheckForRemove(int n) {
	    	if (n <= 0 || n > num) {
	    		throw new IndexOutOfBoundsException("ɾ��λ�ò��Ϸ���");
		    }
		}
	}
		    public static void  main(String[] args) {
		    	DouLinkedList<Integer> linkedList = new DouLinkedList<>();
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
		    	linkedList.print();
	}
}



