package test;

public class Sort {

/**
 * 直接插入算法（升序）
 * @param arr 需要排序的int数组
 * @return 排好序的int数组
 */
public static int[] insertSort(int[] arr){
	
	//如果数组中没有或只有一个元素，无需排序，直接返回。
	if (arr.length == 0 || arr.length == 1) return arr;
	
	int j;
	
	//从数组中位置为1的数据开始，循环至最后一个
	for (int i = 1 ; i<arr.length ; i++){
		
		//储存当前所选数据
		int theNum = arr[i];
		
		//从当前数据往前读取，依次向后移动并判断，直至循环完毕或遇到比其更小的数据，储存当前位置j
		for (j = i-1 ; j>=0 ; j--){
			arr[j+1] = arr[j];
			if (arr[j]<theNum)break;
		}
		
		//将数据输入至j位置
		arr[j+1] = theNum;
		
	}
	
	//返回已排好序的数组
	return arr;
}

/**
 * 希尔算法（升序）
 * @param arr 需要排序的int数组
 * @return 排好序的int数组
 */
public static int[] shellSort(int[] arr){
	
	//如果数组中没有或只有一个元素，无需排序，直接返回。
	if (arr.length == 0 || arr.length == 1) return arr;
	
	int n;
	
	//将步长n取为当前数组长度一半，向下取整，之后依次循环/2，直至n=1
	for(n = (int)(Math.floor(arr.length / 2));n>=1;n = (int)(Math.floor(n / 2))){
						
		//与直接插入排序类似，只是步长从1改为n
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
	
	//返回已排好序的数组
	return arr;
}

/**
 * 冒泡排序，升序
 * @param arr 需排列的int数组
 * @return 已排列的int数组
 */
public static int[] bubbleSort(int[] arr){
	
	if (arr.length == 0 || arr.length == 1) return arr;
	
	int j,midNum;
	
	//添加一个判定是否交换的标志，如果在循环过程中某一次循环的所有元素没有交换，则说明已经排好序，并且可以提前结束循环
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
 * 快速排序，递归，升序
 * @param arr 所需排序的数组(需保证输入符合规范)
 * @param start 开始位置
 * @param end 结束位置
 * @return 已部分或完全排好序的数组
 */
public static int[] quickSort(int[] arr , int start , int end){
	
	//如果起始位置和终止位置相同，直接返回
	if(start == end) return arr ;
	
	//如果满足开始位置和结束位置在0~数组长度-1的返回内，并且开始位置小于结束位置时
	if(start<end && start>=0 && start<=arr.length-1 && end>=0 && end<=arr.length-1){
		
		//创建首尾指针
		int i = start;
		int j = end;
		
		//取出基准值
		int theNum = arr[i];
		
		//循环至首尾指针相等
		while(i!=j){
			
			//首指针不动，尾指针循环从右往左寻找比基准值小的数据，并将尾指针所指数据赋予首指针所指位置
			while(i<j && arr[j]>=theNum) j-=1;
			if(i<j) {arr[i]=arr[j];i+=1;}
			
			//尾指针不动，首指针循环从左往右寻找比基准值大的数据，并将首指针所指数据赋予尾指针所指位置
			while(i<j && arr[i]<=theNum) i+=1;
			if(i<j) {arr[j]=arr[i];j-=1;}
			
		}
		
		//此时首尾指针相等，将基准值赋予该位置
		arr[i] = theNum;
		
		//递归运算
		quickSort(arr,start,i-1);
		quickSort(arr,i+1,end);
	}
	
	//返回部分或完全已排序好的数组
	return arr;
}

/**
 * 直接选择排序，升序
 * @param arr 需要排序的int数组
 * @return 排好序的int数组
 */
public static int[] straight_selectSort(int[] arr){
	
	//如果数组长度为0或1，直接返回
	if(arr.length == 0 || arr.length == 1) return arr;
	
	int midNum;
	
	//循环（数组长度-2）次排序
	for (int i = 0 ; i<arr.length-1 ; i++){
		
		//创建未排序部分最小数据位置
		int min_index = i;
		
		//循环寻找未排序部分的最小数据
		for (int j = i+1 ; j<arr.length ; j++){
			
			if(arr[j]<arr[min_index]) min_index = j;
			
		}
		
		//未排序部分的最前端数据与最小数据调换，并并入已排序部分
		midNum = arr[i];
		arr[i] = arr[min_index];
		arr[min_index] = midNum;
	}
	
	//返回已排好序的数组
	return arr;
}

/**
 * 堆排序，升序
 * @param arr 需排列的int数组
 * @return 已排好列的int数组
 */
public static int[] heapSort(int[] arr){
	
	//将拥有n个数据的数组创建为最大堆
	//注：在循环过程中始终保证根结点为arr[i]的堆为最大堆，下方同理
	for(int i = arr.length/2-1 ; i>=0 ; i--){
		to_maxheap(arr,i,arr.length-1);
	}

	int midNum;
	
	//每次将n个数据的最大堆的最大值（根结点，第0位置）与数组最后一个数调换，并将前n-1个重新排为最大堆。
	//依次类推，共循环n次
	for(int i = arr.length-1 ; i>=1 ; i--){
		midNum = arr[i];
		arr[i] = arr[0];
		arr[0] = midNum;
		
		to_maxheap(arr,0,i-1);
	}
	
	//数据初始化并返回
	midNum = 0;
	return arr;
	
}

/**
 * 创建最大堆
 * @param arr int数组
 * @param root 堆的根结点
 * @param end 范围
 */
private static void to_maxheap(int[] arr,int root,int end) {
	// TODO Auto-generated method stub
	
	//寻找根结点最大子结点，初始化为左子结点
	int max_child = root * 2 + 1;
	int maxNum;
	
	//当最大子结点在root~end范围内时循环
	while(max_child<=end){
		
		//如果根结点右子结点比左子结点大，最大子变更为右子结点
		if(max_child<end && arr[max_child]<arr[max_child+1]/*如果需要为降序，此处为>符号*/) max_child+=1;
		
		//如果子结点数据比根结点数据大，结点数据交换，并将root、max_child指针向下层移动
		if(arr[root]<arr[max_child]/*如果需要为降序，此处为>符号*/){
			maxNum = arr[root];
			arr[root] = arr[max_child];
			arr[max_child] = maxNum;
			
			root = max_child;
			max_child = 2*root+1;
			
		//否则，跳出循环
		}else{
			break;
		}
	}
	//数据初始化
	maxNum = 0;
}

/**
 * 归并排序，数组中的一段
 * @param arr 数组
 * @param start1 第1开始位置
 * @param start2 第2开始位置
 * @param n 需排序的数组总长度
 * @return 已排序的数组
 */
private static int[] mergeSort_1(int[] arr,int start1,int start2,int n){
	
	//创建过渡数组
	int[] arr2 =new int[n];
	
	//将数组看成两个部分，并创建指针
	int p = start1,q = start2;
	int k = 0;
	
	//循环至p，q指针其中一个指到各部分末尾
	while(p<start2 && q<start1+n){
		
		//取指针所指数据大的添加入过渡数组中，并取出数据的指针向后移动
		if(arr[p]<arr[q]){
			arr2[k++]=arr[p++];
		}else{
			arr2[k++]=arr[q++];
		}
	}

	//如果各部分还有未添加入过渡数组的数据，直接按顺序加入
	if(p!=start2){
		for (int i = p ; i<start2 ; i++) arr2[k++] = arr[i];
	}
	if(q!=start1+n){
		for (int i = q ; i<start1+n ; i++) arr2[k++] = arr[i];
	}
	
	//将过渡数组中已排好序的数据添加回原来需排序数组的位置
	p = start1;
	for(int i = 0 ; i<n ; i++) arr[start1++]=arr2[i];
	
	//返回已部分排好序的数组
	return arr;
}

/**
 * 归并排序，升序
 * @param arr 需排列的int序列
 * @return 已排列的int序列
 */
public static int[] mergeSort(int[] arr){
	
	//如果长度为0或1，直接返回输出
	if(arr.length==0 || arr.length==1) return arr;
	
	//将需排序的序列分为元素数为n的子序列，i为子序列开始位置
	int n = 1,i = 0;
	
	//循环至子序列元素数n大于需排序序列
	while(n<arr.length){
		
		//依次取两个子序列进行归并，结束后取之后的两个子序列
		for(i = 0; i<arr.length ; i+=2*n) {
			
			//若第一个子序列能取n个元素，而第二个不能取，则这两个长度不等的子序列进行归并
			if(n<(arr.length-i) && 2*n>(arr.length-i)){
				mergeSort_1(arr,i,i+n,arr.length%(2*n));
				
			//若两个子序列均完整有n个数据，则直接归并
		    }else if(i+2*n<=arr.length){
		    	mergeSort_1(arr,i,i+n,2*n);
		    }
			//若第一个子序列无法取完整的n个元素，则跳过
		}
		
		//子序列元素数×2
		n*=2;
	}

	//返回已排好序的序列
	return arr;
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
	
	int[] arr = {81,49,19,38,97,76,13,19};
	System.out.print("排序前：");
	print(arr);
	
	System.out.println("排序后：");
	//print(insertSort(arr));
	//print(shellSort(arr));
	//print(bubbleSort(arr));
	//print(quickSort(arr,0,arr.length-1));
	//print(straight_selectSort(arr));
	//print(heapSort(arr));
	
	print(mergeSort(arr));
}
}
