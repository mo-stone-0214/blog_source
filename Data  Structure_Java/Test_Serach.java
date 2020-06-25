package test;

public class Test_Serach {

/**
 * ˳���
 */
static class Array<E>{
	int[] array;
	
	//����
	public Array(int[] arr,int n){
		this.array = new int[n];
		this.array = arr;
	}
	
	/**
	 * ˳������
	 */
	public void print(){
		System.out.print("[");
		for(int i = 0 ; i<array.length-2 ; i++){
			System.out.print(array[i]+",");
		}
		System.out.print(array[array.length-1]+"]");
	}
	
	/**
	 * ˳������
	 * @param start ��ʼλ��
	 * @param end ����λ��
	 * @param x ����Ԫ��
	 * @return Ԫ��λ��
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
 * ������
 */
static class LinkedList{
	private int size = 0;
	Node header;
	public  LinkedList() {
		header = new Node();
	}
	
	//�������
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
	 * ������ĩ�����Ԫ��
	 * @param data �������
	 */
	private void addLast(int data) {
        Node newNode = new Node();
        Node q = findNode(size);
        q.next = newNode;
        newNode.data = data;
        ++size;
    }
	
	/**
	 * ������Ӧλ��Ԫ��
	 * @param index λ��
	 * @return ���
	 */
	private Node findNode(int index) {
        Node p = header;
        for (int i = 0; i < index; ++i) {
            p = p.next;
        }
        return p;
    }
	
	/**
	 * int�������������
	 * @param arr_int int����
	 */
	public void add(int[] arr_int){
		for (int i = 0 ; i<arr_int.length;i++)
			addLast(arr_int[i]);
	}
	
	/**
	 * �������
	 */
	public void print() {
	    System.out.print("˳����ڲ�Ԫ��Ϊ:");
	    Node p = header;
	    for (int i = 0; i < size; i++){
	    	p = p.next;
	    	System.out.print(p.data + " ");
	    }
	}
	
	/**
	 * �������
	 * @param start ��ʼλ��
	 * @param end ����λ��
	 * @param x ����Ԫ��
	 * @return ��Ӧλ��
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
	
	//˳���
	Array<E> arr = new Array<>(arr_int,10);
	int result = arr.search_arr(0,9,x);
	
	System.out.print("�������У�");
    arr.print();
    System.out.println(",����Ԫ��"+x+"��0Ϊ��ʼ��");
	
	if(result == -1){
		System.out.print("δ�ҵ�");
	}else{
		System.out.print("�ҵ�λ�ã�"+result);
	}
	System.out.println();
	
	
	x=11; 
	//����
	LinkedList linkedListA =new LinkedList();
	linkedListA.add(arr_int);
	int result2 = linkedListA.search_list(0,9,x);
	
	linkedListA.print();
    System.out.println(",����Ԫ��"+x+"��1Ϊ��ʼ��");
	
	if(result2 == -1){
		System.out.print("δ�ҵ�");
	}else{
		System.out.print("�ҵ�λ�ã�"+result2);
	}
	System.out.println();
}

}
