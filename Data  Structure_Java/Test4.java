package test;

public class Test4 {
	/**�����鴴��ջ */
	public static class Stack<E> {
	    //��ʼ��ջ���������Ϊ100
		private static final int totall = 100;
		private Object[] datas;
	    //n��¼Ŀǰ��ջ���˵ı��� 
		private int n;
	    //��ʼ����ջ����ʱΪһ����ջ 
		public Stack() {
			this.n = 0;
			this.datas = new Object[totall];
	    }
	
	    /** ��ջ
   	    * @param data1 ��ջԪ��
	    **/
	    public void In(E data) {
		    // �ж�ջ�Ƿ���
		    if (n == totall) {
		    	throw new RuntimeException("ջ�����޷�������Ԫ��!");
		    }
		    datas[n] = data;
		    n++;
	    }
	    /** ��ջ
	    *
	    * @return ����ջ��Ԫ��
	    * 
	    **/
		public E Out() {
	    	// �ж�ջ�Ƿ�Ϊ��
	    	if (n==0) {
	    		throw new RuntimeException("��ջ��ʧ�ܣ���Ϊ��ʱջ��û��Ԫ�أ�"); 
	    	}
	    	E topdata = (E) datas[n-1];
	    	n--;
	    	return topdata;
	    }
		
		public void print(){
			System.out.print("n="+n+" Ԫ������Ϊ��");
			for (int i = 0; i <= n-1; i++){
				System.out.print(datas[i]+" ");
			}
			System.out.println();
		}
		/**��ջ�ײ�����
		 * 
		 * @param data ���ӵ�����
		 * @param stack �����ӵ�ջ
		 */
		public void ButtonIn(E data){
			//�ж��Ƿ�Ϊ��ջ
			if(this.n==0){
				this.In(data);
				return;
			}
			// �ж�ջ�Ƿ���
		    if (this.n == totall) {
		    	throw new RuntimeException("ջ�����޷�������Ԫ��!");
		    }
			//����һ���¹���ջ�͹�������
			Stack<E> stack1 =new Stack<>();
			E data1;
			//ȡ������ǰջ����������
			int n = this.n;
			//���ν�ԭʼջ��Ԫ�����ӵ�����ջ��
			for(int i=0;i<n;i++){
				data1 = this.Out();
				stack1.In(data1);
			}
			//��ʱԭʼջΪ�գ���ԭʼջ����data
			this.In(data);
			//������ջ�е��������ӵ�ԭʼջ��
			for(int i = 0; i<n; i++){
				data1 =(E) stack1.Out();
				this.In(data1);
			}
		}
		/** ɾ��ջ�ĵײ�Ԫ��
		 * 
		 * @param stack ��ɾ��Ԫ�ص�ջ
		 */
		public void ButtonOut(){
			//�ж��Ƿ�Ϊ��ջ
			if(this.n==0){
				throw new RuntimeException("��ջ��ʧ�ܣ���Ϊ��ʱջ��û��Ԫ�أ�");
			}
			// �ж�ջ�Ƿ�ֻ��һ��Ԫ��
		    if (n == 1) {
		    	this.Out();
		    	return;
		    }
			//����һ���¹���ջ�͹�������
			Stack<E> stack1 =new Stack<>();
			E data1;
			//ȡ�ü���ǰջ����������
			int n = this.n-1;
			//���ν�ԭʼջ��Ԫ�����ӵ�����ջ��
			for(int i=0;i<n;i++){
				data1 = this.Out();
				stack1.In(data1);
			}
			//��ʱԭʼջֻ��һ��Ԫ�أ�ɾ��
			this.Out();
			//������ջ�е��������ӵ�ԭʼջ��
			for(int i = 0; i<n; i++){
				data1 =(E) stack1.Out();
				this.In(data1);
			}
		}
		
	}
	
	
	
	public static void main(String[] args){
		Stack<Integer> stack =new Stack<>();
		
		stack.In(4);
		stack.In(5);
		stack.In(6);
		stack.In(7);
		stack.In(8);
		stack.In(7);
		stack.In(6);
		stack.print();
		
		Object x=stack.Out();
		stack.print();

		stack.ButtonIn(9);
		stack.print();
		
		stack.ButtonOut();
		stack.print();
		
	}
	

}
