package test;

public class Test_Sort {
	
	/**
	 * 直接插入算法（升序）
	 * @param arr 需要排序的int数组
	 * @return 排好序的int数组
	 */
	public static void insertSort(int[] arr){
		
		int exchange = 0;
		int compare = 0;
		
		//如果数组中没有或只有一个元素，无需排序，直接返回。
		if (arr.length == 0 || arr.length == 1) {
			System.out.print("直接插入排序：exchange="+exchange+" compare="+compare+" 排序后序列为：");
			print(arr);
			return;
		}
		
		int j;
		
		//从数组中位置为1的数据开始，循环至最后一个
		for (int i = 1 ; i<arr.length ; i++){
			
			//储存当前所选数据
			int theNum = arr[i];
			
			//从当前数据往前读取，依次向后移动并判断，直至循环完毕或遇到比其更小的数据，储存当前位置j
			for (j = i-1 ; j>=0 ; j--){
				compare+=1;
				arr[j+1] = arr[j];
				if (arr[j]<theNum)break;
			}
			
			//将数据输入至j位置
			arr[j+1] = theNum;
			if(j+1!=i)exchange+=1;
		}
		
		//输出已排好序的数组
		System.out.print("直接插入排序：exchange="+exchange+" compare="+compare+" 排序后序列为：");
		print(arr);
	}

	/**
	 * 直接选择排序，升序
	 * @param arr 需要排序的int数组
	 * @return 排好序的int数组
	 */
	public static void straight_selectSort(int[] arr){
		
		int exchange = 0;
		int compare = 0;
		
		//如果数组长度为0或1，直接返回
		if(arr.length == 0 || arr.length == 1) {
			System.out.print("直接选择排序：exchange="+exchange+" compare="+compare+" 排序后序列为：");
			print(arr);
			return;
		}
		
		int midNum;
		
		//循环（数组长度-2）次排序
		for (int i = 0 ; i<arr.length-1 ; i++){
			
			//创建未排序部分最小数据位置
			int min_index = i;
			
			//循环寻找未排序部分的最小数据
			for (int j = i+1 ; j<arr.length ; j++){
				
				compare+=1;
				if(arr[j]<arr[min_index]) min_index = j;
				
			}
			
			//未排序部分的最前端数据与最小数据调换，并并入已排序部分
			exchange +=1;
			midNum = arr[i];
			arr[i] = arr[min_index];
			arr[min_index] = midNum;
		}
		
		//输出已排好序的数组
		System.out.print("直接选择排序：exchange="+exchange+" compare="+compare+" 排序后序列为：");
		print(arr);
	}	

	/**
	 * 冒泡排序，升序
	 * @param arr 需排列的int数组
	 * @return 已排列的int数组
	 */
	public static void bubbleSort(int[] arr){
		
		int exchange = 0;
		int compare = 0;
		
		if (arr.length == 0 || arr.length == 1) {
			System.out.print("冒泡排序：exchange="+exchange+" compare="+compare+" 排序后序列为：");
			print(arr);
			return;
		}
		
		int j,midNum;
		
		//添加一个判定是否交换的标志，如果在循环过程中某一次循环的所有元素没有交换，则说明已经排好序，并且可以提前结束循环
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
		
		System.out.print("冒泡排序：exchange="+exchange+" compare="+compare+" 排序后序列为：");
		print(arr);
	}

	/**
	 * 输出
	 * @param arr 需输出的int数组
	 */
	public static void print(int[] arr) {
		// TODO Auto-generated method stub
		
		if(arr == null)System.out.println("列表为null");
		
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
		System.out.print("排序前序列为：");
		print(arr);
		insertSort(arr);
		System.out.println();
		
		int[] arr1 = {38,97,26,19,38,15};
		System.out.print("排序前序列为：");
		print(arr1);
		straight_selectSort(arr1);
		System.out.println();
		
		int[] arr2 = {32,26,87,72,26,17};
		System.out.print("排序前序列为：");
		print(arr2);
		bubbleSort(arr2);
		System.out.println();
		
	}
}
