package test;

public class Test5 {
	
	//用链表创建栈
	public static class Stuck<E>{
		
		//创建结点
		public class Node{
			//创建结点的数据域和地址域
			public E data;
			public E next;
			//添加方法修改结点
			public Node(E data,E next){
				this.data = data;
				this.next = next;
			}
			//初始化结点
			public Node(){
				this(null,null);
			}
		}
		
		//初始栈的大小
		private final int totall = 100;
		//初始化当前栈占用的大小
		private int num = 0;
		//创建头结点
		Node header;
		//创建新栈
		public Stuck(){
			header = new Node();
		}
		
		/** 在栈顶部添加数据
		 * 
		 * @param data 添加的数据
		 */
		@SuppressWarnings("unchecked")
		public void In(E data){
			//检查是否栈满
			if (num == totall){
				throw new IndexOutOfBoundsException ("栈满。");
			}
			//创建一个新结点
			Node node = new Node();
			//输入数据
			node.data = data;
			//找到当前顶部结点
			Node topnode = FindNode(num);
			//将顶部结点的next指向新结点
			topnode.next = (E) node;
			//数目+1
			++num;
		}
		/** 查找指定位置的结点
		 * 
		 * @param n 查找位置
		 * @return 该位置结点
		 */
		@SuppressWarnings("unchecked")
		public Node FindNode(int n){
			//创建新结点
			Node thenode = header;
			//循环查找
			for (int i = 0 ; i<n ; i++){
				thenode = (Stuck<E>.Node) thenode.next;
			}
			//返回
			return thenode;
		}
		/**删除顶部数据
		 * 
		 * @return 顶部数据
		 */
		public E Out(){
			//判断是否为空
			if (num == 0){
				throw new IndexOutOfBoundsException ("栈空。");
			}
			//找到顶端的两个结点
			Node p = FindNode(num);
			Node q = FindNode(num-1);
			//将顶部的结点数据赋给其他变量，并删除
			E data = p.data;
			p.data = null;
			//将第二顶端的结点地址域指向null
			q.next = null;
			//数目-1
			--num;
			//返回删除数据
			return data;
		}
		@SuppressWarnings("unchecked")
		public void print(){
			System.out.print("栈中数据数目num="+num+" 分别为：");
			Node p = (Stuck<E>.Node) header.next;
			for (int i = 1 ; i <= num ; i++){
				System.out.print(p.data+" ");
				p = (Stuck<E>.Node) p.next;
			}
			System.out.println();
		}
		
		/** 在栈底部添加数据
		 * 
		 * @param data 添加的数据
		 */
		@SuppressWarnings("unchecked")
		public void InButtom(E data){
			//检查是否栈满
			if (num == totall){
				throw new IndexOutOfBoundsException ("栈满。");
			}//检查是否空栈
			if (num == 0){
				this.In(data);
				return;
			}
			//创建一个新结点
			Node node = new Node();
			//输入数据
			node.data = data;
			//创建一个过渡栈
			Stuck<Integer> stuck = new Stuck<>();
			//用循环将当前栈里元素移动到过渡栈中
			int n = num;
			for (int i = 1 ; i<=n ; i++){
				stuck.In((Integer) this.Out());
			}
			//此时原栈为空，增加新数据
			this.In(data);
			//用循环将过渡栈里数据返回
			for (int i = 1 ; i<=n ; i++){
				this.In((E) stuck.Out());;
			}
			
		}
		/** 在栈底部删除数据
		 * 
		 * @return 删除的数据
		 */
		@SuppressWarnings("unchecked")
		public E OutButtom(){
			//检查是否空栈
			if (num == 0){
				throw new IndexOutOfBoundsException ("栈空。");
			}//检查是否只有一个数据
			if (num == 1){
				return this.Out();
			}
			//创建一个过渡栈
			Stuck<Integer> stuck = new Stuck<>();
			//用循环将当前栈里元素移动到过渡栈中
			int n = num-1;
			for (int i = 1 ; i<=n ; i++){
				stuck.In((Integer) this.Out());
			}
			//此时原栈只有一个数据，删除数据
			E data = this.Out();
			//用循环将过渡栈里数据返回
			for (int i = 1 ; i<=n ; i++){
				this.In((E) stuck.Out());;
			}
			//返回
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
