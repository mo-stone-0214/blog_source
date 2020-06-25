package test;

public class Test_Serach {

/**
 * 顺序表
 */
static class Array<E>{
	int[] array;
	
	//创建
	public Array(int[] arr,int n){
		this.array = new int[n];
		this.array = arr;
	}
	
	/**
	 * 顺序表输出
	 */
	public void print(){
		System.out.print("[");
		for(int i = 0 ; i<array.length-2 ; i++){
			System.out.print(array[i]+",");
		}
		System.out.print(array[array.length-1]+"]");
	}
	
	/**
	 * 顺序表查找
	 * @param start 开始位置
	 * @param end 结束位置
	 * @param x 查找元素
	 * @return 元素位置
	 */
	public int search_arr(int start, int end ,int x){
		while (start<=end){
		int mid = (start+end)/2;
		if(x == array[mid])
			return mid;
		if(x < array[mid]){
			end-=1;
		}else{
			start+=1;
		}
	}
	return -1;
	}
}

/**
 * 单链表
 */
static class LinkedList{
	private int size = 0;
	Node header;
	public  LinkedList() {
		header = new Node();
	}
	
	//创建结点
	private class Node{
		public int data; 
		public Node next; 
        public Node(int data, Node next){ 
        	this.data = data;
        	this.next = next; 
        }
        public Node() {
        	this(0,null);
        }
    }
	
	/**
	 * 在链表末端添加元素
	 * @param data 添加数据
	 */
	private void addLast(int data) {
        Node newNode = new Node();
        Node q = findNode(size);
        q.next = newNode;
        newNode.data = data;
        ++size;
    }
	
	/**
	 * 查找相应位置元素
	 * @param index 位置
	 * @return 结点
	 */
	private Node findNode(int index) {
        Node p = header;
        for (int i = 0; i < index; ++i) {
            p = p.next;
        }
        return p;
    }
	
	/**
	 * int数组添加入链表
	 * @param arr_int int数组
	 */
	public void add(int[] arr_int){
		for (int i = 0 ; i<arr_int.length;i++)
			addLast(arr_int[i]);
	}
	
	/**
	 * 输出链表
	 */
	public void print() {
	    System.out.print("顺序表内部元素为:");
	    Node p = header;
	    for (int i = 0; i < size; i++){
	    	p = p.next;
	    	System.out.print(p.data + " ");
	    }
	}
	
	/**
	 * 链表查找
	 * @param start 开始位置
	 * @param end 结束位置
	 * @param x 查找元素
	 * @return 相应位置
	 */
	public int search_list(int start, int end ,int x){
		while (start<=end){
		int mid = (start+end)/2;
		if(findNode(mid).data == x)
			return mid;
		if(x < findNode(mid).data){
			end-=1;
		}else{
			start+=1;
		}
	}
	return -1;
	}
}

public static <E> void main(String[] args){
	int[] arr_int = {1,4,9,11,15,19,25,36,58,96};
	int x = 25;
	
	//顺序表
	Array<E> arr = new Array<>(arr_int,10);
	int result = arr.search_arr(0,9,x);
	
	System.out.print("查找序列：");
    arr.print();
    System.out.println(",查找元素"+x+"（0为起始）");
	
	if(result == -1){
		System.out.print("未找到");
	}else{
		System.out.print("找到位置："+result);
	}
	System.out.println();
	
	
	x=11; 
	//链表
	LinkedList linkedListA =new LinkedList();
	linkedListA.add(arr_int);
	int result2 = linkedListA.search_list(0,9,x);
	
	linkedListA.print();
    System.out.println(",查找元素"+x+"（1为起始）");
	
	if(result2 == -1){
		System.out.print("未找到");
	}else{
		System.out.print("找到位置："+result2);
	}
	System.out.println();
}

}
