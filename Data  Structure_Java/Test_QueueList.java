package test;

import java.util.Scanner;

public class Test_QueueList {

	public static class QueueArray<E>{
		
		//�趨���У��ӵ�������ǰ�˺ͺ�ˣ�������Ԫ��������
		//ǰ��Ϊ��ͷԪ��λ�ã�����±�Ϊ��βԪ�غ�һ��λ�á�
		private int size = 2 ;
		private Object[] que_arr ;
		private int front ;
		private int rear ;
		private int count ;
		//��ʼ������
		public QueueArray(){
			que_arr =new Object [size] ;
			this.front = 0 ;
			this.rear = -1 ;
			this.count = 0;
		}
		/**
		 * ���
		 * @param data
		 */
		public void In(E data){
			
			//��β��ŵ�����
			if (rear+1 == size){
				rear = 0;
			}else{
			    rear+=1;
			}
			
			//�������
			que_arr[rear] = data ;

			//count+1
			count+=1;
		}
		
		/**
		 * ����
		 * @return ��ͷ��Ԫ��
		 */
		public Object Out(){
			
			Object data = que_arr[front];
			que_arr[front] = null ;

			
			//����ָ�������
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

	//����������
	public static void main(String[] args){
		
		QueueArray<String> queue = new QueueArray<>();
		boolean flag = true;
		while (flag){
			System.out.println("<1>���");
			System.out.println("<2>����");
			System.out.println("<3>��ʾ");
			System.out.println("<4>�˳�");
			System.out.println("������ָ���ţ�");
			
			Scanner sc1 = new Scanner(System.in); 
	        String input = sc1.nextLine();
	        
			switch(input){
			case "1" :
				
				//�ж��Ƿ����
				if (queue.count == queue.size){
					System.out.println("����");
					System.out.println();
					break;
				}
				
				System.out.println("����������ݣ�");
				sc1 = new Scanner(System.in); 
		        input = sc1.nextLine();
		        
		        queue.In(input);
		        
				System.out.println("�ѽ�"+input+"���");
				System.out.println();
				break;
				
			case "2" :
				if (queue.count == 0){
					System.out.println("�ӿ�");
					System.out.println();
					break;
				}
				System.out.println("�ѽ�Ԫ��"+queue.Out()+"����");
				System.out.println();
				break;
				
			case "3" :
				
				System.out.print("��ǰ����Ϊ��[");
				
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
				System.out.println("���˳�");
				flag = false;
				break;
			
			default :
				System.out.println("δָ֪���������");
				System.out.println();
				break;
			}
		}
	}
}
