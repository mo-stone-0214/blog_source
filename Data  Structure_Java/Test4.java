package test;

public class Test4 {
	/**用数组创建栈 */
	public static class Stack<E> {
	    //初始化栈的最大容量为100
		private static final int totall = 100;
		private Object[] datas;
	    //n记录目前堆栈顶端的变量 
		private int n;
	    //初始化堆栈，此时为一个空栈 
		public Stack() {
			this.n = 0;
			this.datas = new Object[totall];
	    }
	
	    /** 入栈
   	    * @param data1 入栈元素
	    **/
	    public void In(E data) {
		    // 判断栈是否满
		    if (n == totall) {
		    	throw new RuntimeException("栈满，无法插入新元素!");
		    }
		    datas[n] = data;
		    n++;
	    }
	    /** 出栈
	    *
	    * @return 返回栈顶元素
	    * 
	    **/
		public E Out() {
	    	// 判断栈是否为空
	    	if (n==0) {
	    		throw new RuntimeException("出栈顶失败，因为此时栈中没有元素！"); 
	    	}
	    	E topdata = (E) datas[n-1];
	    	n--;
	    	return topdata;
	    }
		
		public void print(){
			System.out.print("n="+n+" 元素依次为：");
			for (int i = 0; i <= n-1; i++){
				System.out.print(datas[i]+" ");
			}
			System.out.println();
		}
		/**在栈底部增加
		 * 
		 * @param data 增加的数据
		 * @param stack 需增加的栈
		 */
		public void ButtonIn(E data){
			//判断是否为空栈
			if(this.n==0){
				this.In(data);
				return;
			}
			// 判断栈是否满
		    if (this.n == totall) {
		    	throw new RuntimeException("栈满，无法插入新元素!");
		    }
			//创建一个新过渡栈和过渡数据
			Stack<E> stack1 =new Stack<>();
			E data1;
			//取得增加前栈的数据数量
			int n = this.n;
			//依次将原始栈中元素增加到过渡栈中
			for(int i=0;i<n;i++){
				data1 = this.Out();
				stack1.In(data1);
			}
			//此时原始栈为空，在原始栈增加data
			this.In(data);
			//将过渡栈中的数据增加到原始栈中
			for(int i = 0; i<n; i++){
				data1 =(E) stack1.Out();
				this.In(data1);
			}
		}
		/** 删除栈的底部元素
		 * 
		 * @param stack 需删除元素的栈
		 */
		public void ButtonOut(){
			//判断是否为空栈
			if(this.n==0){
				throw new RuntimeException("出栈顶失败，因为此时栈中没有元素！");
			}
			// 判断栈是否只有一个元素
		    if (n == 1) {
		    	this.Out();
		    	return;
		    }
			//创建一个新过渡栈和过渡数据
			Stack<E> stack1 =new Stack<>();
			E data1;
			//取得减少前栈的数据数量
			int n = this.n-1;
			//依次将原始栈中元素增加到过渡栈中
			for(int i=0;i<n;i++){
				data1 = this.Out();
				stack1.In(data1);
			}
			//此时原始栈只有一个元素，删除
			this.Out();
			//将过渡栈中的数据增加到原始栈中
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
