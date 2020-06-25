package test;

public class Test2 {


/** 线性表：定义顺序表类 SequenceList**/

/** 同上 **/
	
public static class SequenceList<E>{
	private Object[] seqList;
    private int size =0;
	public SequenceList(){
	    seqList= new Object[1000];
	}
	

    /** 定义删除指定位置的元素函数 remove(int index)
      *  @param index 删除位置 */

    public void  remove(int index) {
        // 1. 判别指定的位置是否合法
        rangeCheckForRemove(index);
        // 2. 将位置index+1 至 size-1 位置上的元素前移一个位置
        for (int i = index+1; i <= size - 1; ++i) {
        	seqList[i-1] = seqList[i];
        }
        // 3. 表的长度-1
        --size;
    }

	/** 定义删除位置是否合法函数 rangeCheckForRemove(int index)
      * @param index 删除位置 */

    private void  rangeCheckForRemove (int index) {
    	if (index >= size || index < 0)
    		throw new IndexOutOfBoundsException ("删除位置不合法！");
    }

	/** 定义打印顺序表函数 print ( )
	 ** 同上 */
    
	public void  print( ) {
	    System.out.print("顺序表长度为:"+size+",内部元素为:");
	    for (int i = 0; i < size; ++i)
	    	System.out.print(seqList[i] + " ");
	    System.out.println();
	}
	/** 定义：在顺序表中末尾添加元素函数 add(E element)
	  * @param element 插入元素
	  * @return 如果插入成功返回true，否则返回false */

	public boolean add(E element) {
	    seqList[size] = element;
	    ++size;
	    return true;
	}
	/** 定义在顺序表某位置插入函数add(int index, E element)
	  * @param index 插入位置
	  * @param element 插入元素
	  * @return 如果插入成功返回true，否则返回false */

	public boolean add(int index, E element) {
	    // 1. 判断插入是否合法
	    rangeCheckForAdd(index);
	    // 2. 将存储在size-1位置至index位置的元素依次后移一个位置
	    for (int i = size; i > index; --i) {
	    	seqList[i] = seqList[i - 1];
	    }
	    // 3. 将x插入到index位置
	    seqList[index] = element;
	    // 4. 表的长度+1
	    ++size;
	    return true; 
	}
	/** 定义插入位置是否合法函数 rangeCheckForAdd(int index)
	  * @param index 插入位置*/

	private void  rangeCheckForAdd (int index) {
	    if (index > size || index < 0)
	    	throw new IndexOutOfBoundsException("插入位置不合法！");
	}
}

/** 定义主函数 main()*/
public static void main(String[] args) {
	SequenceList<Integer> seqList = new SequenceList<>();
	// 构造一个顺序表
	seqList.add(1);
	seqList.add(5);
	seqList.add(2);
	seqList.add(12);
	seqList.print();
	// 在1位置插入元素7
	seqList.add(1, 7);
	seqList.print();
	// 删除1位置元素
	seqList.remove(1);
	seqList.print();
}}