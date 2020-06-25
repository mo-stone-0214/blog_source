package test;

import java.util.Scanner;

public class Test_QueueList {

	public static class QueueArray<E>{
		
		//设定队列，队的容量，前端和后端，队列中元素数量。
		//前端为队头元素位置，后端下标为队尾元素后一个位置。
		private int size = 2 ;
		private Object[] que_arr ;
		private int front ;
		private int rear ;
		private int count ;
		//初始化队列
		public QueueArray(){
			que_arr =new Object [size] ;
			this.front = 0 ;
			this.rear = -1 ;
			this.count = 0;
		}
		/**
		 * 入队
		 * @param data
		 */
		public void In(E data){
			
			//队尾标号的增加
			if (rear+1 == size){
				rear = 0;
			}else{
			    rear+=1;
			}
			
			//添加数据
			que_arr[rear] = data ;

			//count+1
			count+=1;
		}
		
		/**
		 * 出队
		 * @return 队头的元素
		 */
		public Object Out(){
			
			Object data = que_arr[front];
			que_arr[front] = null ;

			
			//队首指针的增加
			if (front+1 == size){
				front = 0;
			}else{
			    front+=1;
			}
			
			//count-1
			count-=1;
			
			return data;
		}
		
	}

	//主函数测试
	public static void main(String[] args){
		
		QueueArray<String> queue = new QueueArray<>();
		boolean flag = true;
		while (flag){
			System.out.println("<1>入队");
			System.out.println("<2>出队");
			System.out.println("<3>显示");
			System.out.println("<4>退出");
			System.out.println("请输入指令编号：");
			
			Scanner sc1 = new Scanner(System.in); 
	        String input = sc1.nextLine();
	        
			switch(input){
			case "1" :
				
				//判断是否队满
				if (queue.count == queue.size){
					System.out.println("队满");
					System.out.println();
					break;
				}
				
				System.out.println("请输入队数据：");
				sc1 = new Scanner(System.in); 
		        input = sc1.nextLine();
		        
		        queue.In(input);
		        
				System.out.println("已将"+input+"入队");
				System.out.println();
				break;
				
			case "2" :
				if (queue.count == 0){
					System.out.println("队空");
					System.out.println();
					break;
				}
				System.out.println("已将元素"+queue.Out()+"出队");
				System.out.println();
				break;
				
			case "3" :
				
				System.out.print("当前队列为：[");
				
				for (int i = 0 ; i<queue.size ; i++){
					if (i == queue.size-1){
						System.out.println(queue.que_arr[queue.size-1]+"]");
					}else{
					    System.out.print(queue.que_arr[i]+",");   
					}
				}
				System.out.println("count:"+queue.count+" front:"+queue.front+" rear:"+queue.rear);
				System.out.println();
				break;
			
			case "4" :
				System.out.println("已退出");
				flag = false;
				break;
			
			default :
				System.out.println("未知指令，重新输入");
				System.out.println();
				break;
			}
		}
	}
}
