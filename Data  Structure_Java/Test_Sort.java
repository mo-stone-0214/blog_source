package test;

public class Test_Sort {
	
	/**
	 * ֱ�Ӳ����㷨������
	 * @param arr ��Ҫ�����int����
	 * @return �ź����int����
	 */
	public static void insertSort(int[] arr){
		
		int exchange = 0;
		int compare = 0;
		
		//���������û�л�ֻ��һ��Ԫ�أ���������ֱ�ӷ��ء�
		if (arr.length == 0 || arr.length == 1) {
			System.out.print("ֱ�Ӳ�������exchange="+exchange+" compare="+compare+" ���������Ϊ��");
			print(arr);
			return;
		}
		
		int j;
		
		//��������λ��Ϊ1�����ݿ�ʼ��ѭ�������һ��
		for (int i = 1 ; i<arr.length ; i++){
			
			//���浱ǰ��ѡ����
			int theNum = arr[i];
			
			//�ӵ�ǰ������ǰ��ȡ����������ƶ����жϣ�ֱ��ѭ����ϻ����������С�����ݣ����浱ǰλ��j
			for (j = i-1 ; j>=0 ; j--){
				compare+=1;
				arr[j+1] = arr[j];
				if (arr[j]<theNum)break;
			}
			
			//������������jλ��
			arr[j+1] = theNum;
			if(j+1!=i)exchange+=1;
		}
		
		//������ź��������
		System.out.print("ֱ�Ӳ�������exchange="+exchange+" compare="+compare+" ���������Ϊ��");
		print(arr);
	}

	/**
	 * ֱ��ѡ����������
	 * @param arr ��Ҫ�����int����
	 * @return �ź����int����
	 */
	public static void straight_selectSort(int[] arr){
		
		int exchange = 0;
		int compare = 0;
		
		//������鳤��Ϊ0��1��ֱ�ӷ���
		if(arr.length == 0 || arr.length == 1) {
			System.out.print("ֱ��ѡ������exchange="+exchange+" compare="+compare+" ���������Ϊ��");
			print(arr);
			return;
		}
		
		int midNum;
		
		//ѭ�������鳤��-2��������
		for (int i = 0 ; i<arr.length-1 ; i++){
			
			//����δ���򲿷���С����λ��
			int min_index = i;
			
			//ѭ��Ѱ��δ���򲿷ֵ���С����
			for (int j = i+1 ; j<arr.length ; j++){
				
				compare+=1;
				if(arr[j]<arr[min_index]) min_index = j;
				
			}
			
			//δ���򲿷ֵ���ǰ����������С���ݵ����������������򲿷�
			exchange +=1;
			midNum = arr[i];
			arr[i] = arr[min_index];
			arr[min_index] = midNum;
		}
		
		//������ź��������
		System.out.print("ֱ��ѡ������exchange="+exchange+" compare="+compare+" ���������Ϊ��");
		print(arr);
	}	

	/**
	 * ð����������
	 * @param arr �����е�int����
	 * @return �����е�int����
	 */
	public static void bubbleSort(int[] arr){
		
		int exchange = 0;
		int compare = 0;
		
		if (arr.length == 0 || arr.length == 1) {
			System.out.print("ð������exchange="+exchange+" compare="+compare+" ���������Ϊ��");
			print(arr);
			return;
		}
		
		int j,midNum;
		
		//���һ���ж��Ƿ񽻻��ı�־�������ѭ��������ĳһ��ѭ��������Ԫ��û�н�������˵���Ѿ��ź��򣬲��ҿ�����ǰ����ѭ��
		boolean isExchange;
		
		for (int i = 0 ; i<arr.length-1 ; i++){
			
			isExchange=false;
			
			for ( j=0 ; j < arr.length-1-i; j++){
				
				compare+=1;
				
				if (arr[j]>arr[j+1]){
					exchange+=1;
					midNum = arr[j+1];
					arr[j+1] = arr [j];
					arr[j] = midNum;
					
					isExchange = true;
				}
			}
			
			if(!isExchange) break;
			
		}
		midNum = -1;
		
		System.out.print("ð������exchange="+exchange+" compare="+compare+" ���������Ϊ��");
		print(arr);
	}

	/**
	 * ���
	 * @param arr �������int����
	 */
	public static void print(int[] arr) {
		// TODO Auto-generated method stub
		
		if(arr == null)System.out.println("�б�Ϊnull");
		
		System.out.print("[");
		for (int i = 0 ; i < arr.length ; i++){
			if(i == arr.length -1 ) {
				System.out.println(arr[i]+"]");
				break;
			}
			System.out.print(arr[i]+",");
		}
	}
		
	public static void main(String[] args){
		
		int[] arr = {32,26,87,72,26,17};
		System.out.print("����ǰ����Ϊ��");
		print(arr);
		insertSort(arr);
		System.out.println();
		
		int[] arr1 = {38,97,26,19,38,15};
		System.out.print("����ǰ����Ϊ��");
		print(arr1);
		straight_selectSort(arr1);
		System.out.println();
		
		int[] arr2 = {32,26,87,72,26,17};
		System.out.print("����ǰ����Ϊ��");
		print(arr2);
		bubbleSort(arr2);
		System.out.println();
		
	}
}
