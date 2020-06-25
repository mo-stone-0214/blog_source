package test;

public class Sort {

/**
 * ֱ�Ӳ����㷨������
 * @param arr ��Ҫ�����int����
 * @return �ź����int����
 */
public static int[] insertSort(int[] arr){
	
	//���������û�л�ֻ��һ��Ԫ�أ���������ֱ�ӷ��ء�
	if (arr.length == 0 || arr.length == 1) return arr;
	
	int j;
	
	//��������λ��Ϊ1�����ݿ�ʼ��ѭ�������һ��
	for (int i = 1 ; i<arr.length ; i++){
		
		//���浱ǰ��ѡ����
		int theNum = arr[i];
		
		//�ӵ�ǰ������ǰ��ȡ����������ƶ����жϣ�ֱ��ѭ����ϻ����������С�����ݣ����浱ǰλ��j
		for (j = i-1 ; j>=0 ; j--){
			arr[j+1] = arr[j];
			if (arr[j]<theNum)break;
		}
		
		//������������jλ��
		arr[j+1] = theNum;
		
	}
	
	//�������ź��������
	return arr;
}

/**
 * ϣ���㷨������
 * @param arr ��Ҫ�����int����
 * @return �ź����int����
 */
public static int[] shellSort(int[] arr){
	
	//���������û�л�ֻ��һ��Ԫ�أ���������ֱ�ӷ��ء�
	if (arr.length == 0 || arr.length == 1) return arr;
	
	int n;
	
	//������nȡΪ��ǰ���鳤��һ�룬����ȡ����֮������ѭ��/2��ֱ��n=1
	for(n = (int)(Math.floor(arr.length / 2));n>=1;n = (int)(Math.floor(n / 2))){
						
		//��ֱ�Ӳ����������ƣ�ֻ�ǲ�����1��Ϊn
		for(int i = n ; i < arr.length ; i+=n){
			
			int theNum = arr[i];
			int j;
			
			for (j = i-n ; j >= 0 ; j-=n){
				
				arr[j+n] = arr[j];
				
				if (theNum>arr[j]) break;
				
			}
			arr[j+n]= theNum;
		}
	}
	
	//�������ź��������
	return arr;
}

/**
 * ð����������
 * @param arr �����е�int����
 * @return �����е�int����
 */
public static int[] bubbleSort(int[] arr){
	
	if (arr.length == 0 || arr.length == 1) return arr;
	
	int j,midNum;
	
	//���һ���ж��Ƿ񽻻��ı�־�������ѭ��������ĳһ��ѭ��������Ԫ��û�н�������˵���Ѿ��ź��򣬲��ҿ�����ǰ����ѭ��
	boolean isExchange;
	
	for (int i = 0 ; i<arr.length-1 ; i++){
		
		isExchange=false;
		
		for ( j=0 ; j < arr.length-1-i; j++){
			
			if (arr[j]>arr[j+1]){
				midNum = arr[j+1];
				arr[j+1] = arr [j];
				arr[j] = midNum;
				
				isExchange = true;
			}
		}
		
		if(!isExchange) break;
		
	}
	midNum = -1;
	
	return arr;
}

/**
 * �������򣬵ݹ飬����
 * @param arr �������������(�豣֤������Ϲ淶)
 * @param start ��ʼλ��
 * @param end ����λ��
 * @return �Ѳ��ֻ���ȫ�ź��������
 */
public static int[] quickSort(int[] arr , int start , int end){
	
	//�����ʼλ�ú���ֹλ����ͬ��ֱ�ӷ���
	if(start == end) return arr ;
	
	//������㿪ʼλ�úͽ���λ����0~���鳤��-1�ķ����ڣ����ҿ�ʼλ��С�ڽ���λ��ʱ
	if(start<end && start>=0 && start<=arr.length-1 && end>=0 && end<=arr.length-1){
		
		//������βָ��
		int i = start;
		int j = end;
		
		//ȡ����׼ֵ
		int theNum = arr[i];
		
		//ѭ������βָ�����
		while(i!=j){
			
			//��ָ�벻����βָ��ѭ����������Ѱ�ұȻ�׼ֵС�����ݣ�����βָ����ָ���ݸ�����ָ����ָλ��
			while(i<j && arr[j]>=theNum) j-=1;
			if(i<j) {arr[i]=arr[j];i+=1;}
			
			//βָ�벻������ָ��ѭ����������Ѱ�ұȻ�׼ֵ������ݣ�������ָ����ָ���ݸ���βָ����ָλ��
			while(i<j && arr[i]<=theNum) i+=1;
			if(i<j) {arr[j]=arr[i];j-=1;}
			
		}
		
		//��ʱ��βָ����ȣ�����׼ֵ�����λ��
		arr[i] = theNum;
		
		//�ݹ�����
		quickSort(arr,start,i-1);
		quickSort(arr,i+1,end);
	}
	
	//���ز��ֻ���ȫ������õ�����
	return arr;
}

/**
 * ֱ��ѡ����������
 * @param arr ��Ҫ�����int����
 * @return �ź����int����
 */
public static int[] straight_selectSort(int[] arr){
	
	//������鳤��Ϊ0��1��ֱ�ӷ���
	if(arr.length == 0 || arr.length == 1) return arr;
	
	int midNum;
	
	//ѭ�������鳤��-2��������
	for (int i = 0 ; i<arr.length-1 ; i++){
		
		//����δ���򲿷���С����λ��
		int min_index = i;
		
		//ѭ��Ѱ��δ���򲿷ֵ���С����
		for (int j = i+1 ; j<arr.length ; j++){
			
			if(arr[j]<arr[min_index]) min_index = j;
			
		}
		
		//δ���򲿷ֵ���ǰ����������С���ݵ����������������򲿷�
		midNum = arr[i];
		arr[i] = arr[min_index];
		arr[min_index] = midNum;
	}
	
	//�������ź��������
	return arr;
}

/**
 * ����������
 * @param arr �����е�int����
 * @return ���ź��е�int����
 */
public static int[] heapSort(int[] arr){
	
	//��ӵ��n�����ݵ����鴴��Ϊ����
	//ע����ѭ��������ʼ�ձ�֤�����Ϊarr[i]�Ķ�Ϊ���ѣ��·�ͬ��
	for(int i = arr.length/2-1 ; i>=0 ; i--){
		to_maxheap(arr,i,arr.length-1);
	}

	int midNum;
	
	//ÿ�ν�n�����ݵ����ѵ����ֵ������㣬��0λ�ã����������һ��������������ǰn-1��������Ϊ���ѡ�
	//�������ƣ���ѭ��n��
	for(int i = arr.length-1 ; i>=1 ; i--){
		midNum = arr[i];
		arr[i] = arr[0];
		arr[0] = midNum;
		
		to_maxheap(arr,0,i-1);
	}
	
	//���ݳ�ʼ��������
	midNum = 0;
	return arr;
	
}

/**
 * ��������
 * @param arr int����
 * @param root �ѵĸ����
 * @param end ��Χ
 */
private static void to_maxheap(int[] arr,int root,int end) {
	// TODO Auto-generated method stub
	
	//Ѱ�Ҹ��������ӽ�㣬��ʼ��Ϊ���ӽ��
	int max_child = root * 2 + 1;
	int maxNum;
	
	//������ӽ����root~end��Χ��ʱѭ��
	while(max_child<=end){
		
		//�����������ӽ������ӽ�������ӱ��Ϊ���ӽ��
		if(max_child<end && arr[max_child]<arr[max_child+1]/*�����ҪΪ���򣬴˴�Ϊ>����*/) max_child+=1;
		
		//����ӽ�����ݱȸ�������ݴ󣬽�����ݽ���������root��max_childָ�����²��ƶ�
		if(arr[root]<arr[max_child]/*�����ҪΪ���򣬴˴�Ϊ>����*/){
			maxNum = arr[root];
			arr[root] = arr[max_child];
			arr[max_child] = maxNum;
			
			root = max_child;
			max_child = 2*root+1;
			
		//��������ѭ��
		}else{
			break;
		}
	}
	//���ݳ�ʼ��
	maxNum = 0;
}

/**
 * �鲢���������е�һ��
 * @param arr ����
 * @param start1 ��1��ʼλ��
 * @param start2 ��2��ʼλ��
 * @param n ������������ܳ���
 * @return �����������
 */
private static int[] mergeSort_1(int[] arr,int start1,int start2,int n){
	
	//������������
	int[] arr2 =new int[n];
	
	//�����鿴���������֣�������ָ��
	int p = start1,q = start2;
	int k = 0;
	
	//ѭ����p��qָ������һ��ָ��������ĩβ
	while(p<start2 && q<start1+n){
		
		//ȡָ����ָ���ݴ���������������У���ȡ�����ݵ�ָ������ƶ�
		if(arr[p]<arr[q]){
			arr2[k++]=arr[p++];
		}else{
			arr2[k++]=arr[q++];
		}
	}

	//��������ֻ���δ����������������ݣ�ֱ�Ӱ�˳�����
	if(p!=start2){
		for (int i = p ; i<start2 ; i++) arr2[k++] = arr[i];
	}
	if(q!=start1+n){
		for (int i = q ; i<start1+n ; i++) arr2[k++] = arr[i];
	}
	
	//���������������ź����������ӻ�ԭ�������������λ��
	p = start1;
	for(int i = 0 ; i<n ; i++) arr[start1++]=arr2[i];
	
	//�����Ѳ����ź��������
	return arr;
}

/**
 * �鲢��������
 * @param arr �����е�int����
 * @return �����е�int����
 */
public static int[] mergeSort(int[] arr){
	
	//�������Ϊ0��1��ֱ�ӷ������
	if(arr.length==0 || arr.length==1) return arr;
	
	//������������з�ΪԪ����Ϊn�������У�iΪ�����п�ʼλ��
	int n = 1,i = 0;
	
	//ѭ����������Ԫ����n��������������
	while(n<arr.length){
		
		//����ȡ���������н��й鲢��������ȡ֮�������������
		for(i = 0; i<arr.length ; i+=2*n) {
			
			//����һ����������ȡn��Ԫ�أ����ڶ�������ȡ�������������Ȳ��ȵ������н��й鲢
			if(n<(arr.length-i) && 2*n>(arr.length-i)){
				mergeSort_1(arr,i,i+n,arr.length%(2*n));
				
			//�����������о�������n�����ݣ���ֱ�ӹ鲢
		    }else if(i+2*n<=arr.length){
		    	mergeSort_1(arr,i,i+n,2*n);
		    }
			//����һ���������޷�ȡ������n��Ԫ�أ�������
		}
		
		//������Ԫ������2
		n*=2;
	}

	//�������ź��������
	return arr;
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
	
	int[] arr = {81,49,19,38,97,76,13,19};
	System.out.print("����ǰ��");
	print(arr);
	
	System.out.println("�����");
	//print(insertSort(arr));
	//print(shellSort(arr));
	//print(bubbleSort(arr));
	//print(quickSort(arr,0,arr.length-1));
	//print(straight_selectSort(arr));
	//print(heapSort(arr));
	
	print(mergeSort(arr));
}
}
