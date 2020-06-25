package test;

public class Test5 {
	
	//��������ջ
	public static class Stuck<E>{
		
		//�������
		public class Node{
			//��������������͵�ַ��
			public E data;
			public E next;
			//��ӷ����޸Ľ��
			public Node(E data,E next){
				this.data = data;
				this.next = next;
			}
			//��ʼ�����
			public Node(){
				this(null,null);
			}
		}
		
		//��ʼջ�Ĵ�С
		private final int totall = 100;
		//��ʼ����ǰջռ�õĴ�С
		private int num = 0;
		//����ͷ���
		Node header;
		//������ջ
		public Stuck(){
			header = new Node();
		}
		
		/** ��ջ�����������
		 * 
		 * @param data ��ӵ�����
		 */
		@SuppressWarnings("unchecked")
		public void In(E data){
			//����Ƿ�ջ��
			if (num == totall){
				throw new IndexOutOfBoundsException ("ջ����");
			}
			//����һ���½��
			Node node = new Node();
			//��������
			node.data = data;
			//�ҵ���ǰ�������
			Node topnode = FindNode(num);
			//����������nextָ���½��
			topnode.next = (E) node;
			//��Ŀ+1
			++num;
		}
		/** ����ָ��λ�õĽ��
		 * 
		 * @param n ����λ��
		 * @return ��λ�ý��
		 */
		@SuppressWarnings("unchecked")
		public Node FindNode(int n){
			//�����½��
			Node thenode = header;
			//ѭ������
			for (int i = 0 ; i<n ; i++){
				thenode = (Stuck<E>.Node) thenode.next;
			}
			//����
			return thenode;
		}
		/**ɾ����������
		 * 
		 * @return ��������
		 */
		public E Out(){
			//�ж��Ƿ�Ϊ��
			if (num == 0){
				throw new IndexOutOfBoundsException ("ջ�ա�");
			}
			//�ҵ����˵��������
			Node p = FindNode(num);
			Node q = FindNode(num-1);
			//�������Ľ�����ݸ���������������ɾ��
			E data = p.data;
			p.data = null;
			//���ڶ����˵Ľ���ַ��ָ��null
			q.next = null;
			//��Ŀ-1
			--num;
			//����ɾ������
			return data;
		}
		@SuppressWarnings("unchecked")
		public void print(){
			System.out.print("ջ��������Ŀnum="+num+" �ֱ�Ϊ��");
			Node p = (Stuck<E>.Node) header.next;
			for (int i = 1 ; i <= num ; i++){
				System.out.print(p.data+" ");
				p = (Stuck<E>.Node) p.next;
			}
			System.out.println();
		}
		
		/** ��ջ�ײ��������
		 * 
		 * @param data ��ӵ�����
		 */
		@SuppressWarnings("unchecked")
		public void InButtom(E data){
			//����Ƿ�ջ��
			if (num == totall){
				throw new IndexOutOfBoundsException ("ջ����");
			}//����Ƿ��ջ
			if (num == 0){
				this.In(data);
				return;
			}
			//����һ���½��
			Node node = new Node();
			//��������
			node.data = data;
			//����һ������ջ
			Stuck<Integer> stuck = new Stuck<>();
			//��ѭ������ǰջ��Ԫ���ƶ�������ջ��
			int n = num;
			for (int i = 1 ; i<=n ; i++){
				stuck.In((Integer) this.Out());
			}
			//��ʱԭջΪ�գ�����������
			this.In(data);
			//��ѭ��������ջ�����ݷ���
			for (int i = 1 ; i<=n ; i++){
				this.In((E) stuck.Out());;
			}
			
		}
		/** ��ջ�ײ�ɾ������
		 * 
		 * @return ɾ��������
		 */
		@SuppressWarnings("unchecked")
		public E OutButtom(){
			//����Ƿ��ջ
			if (num == 0){
				throw new IndexOutOfBoundsException ("ջ�ա�");
			}//����Ƿ�ֻ��һ������
			if (num == 1){
				return this.Out();
			}
			//����һ������ջ
			Stuck<Integer> stuck = new Stuck<>();
			//��ѭ������ǰջ��Ԫ���ƶ�������ջ��
			int n = num-1;
			for (int i = 1 ; i<=n ; i++){
				stuck.In((Integer) this.Out());
			}
			//��ʱԭջֻ��һ�����ݣ�ɾ������
			E data = this.Out();
			//��ѭ��������ջ�����ݷ���
			for (int i = 1 ; i<=n ; i++){
				this.In((E) stuck.Out());;
			}
			//����
			return data;
			
		}
	}
	public static void main(String[] args){
		Stuck<Integer> stuck = new Stuck<>();
		stuck.In(1);
		stuck.In(2);
		stuck.In(3);
		stuck.In(4);
		stuck.print();
		
		stuck.Out();
		stuck.print();
		
		stuck.InButtom(0);
		stuck.print();
		
		Object x = stuck.OutButtom();
		System.out.print(x);
		stuck.print();
	}

}
