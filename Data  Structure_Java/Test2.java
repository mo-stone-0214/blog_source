package test;

public class Test2 {


/** ���Ա�����˳����� SequenceList**/

/** ͬ�� **/
	
public static class SequenceList<E>{
	private Object[] seqList;
    private int size =0;
	public SequenceList(){
	    seqList= new Object[1000];
	}
	

    /** ����ɾ��ָ��λ�õ�Ԫ�غ��� remove(int index)
      *  @param index ɾ��λ�� */

    public void  remove(int index) {
        // 1. �б�ָ����λ���Ƿ�Ϸ�
        rangeCheckForRemove(index);
        // 2. ��λ��index+1 �� size-1 λ���ϵ�Ԫ��ǰ��һ��λ��
        for (int i = index+1; i <= size - 1; ++i) {
        	seqList[i-1] = seqList[i];
        }
        // 3. ��ĳ���-1
        --size;
    }

	/** ����ɾ��λ���Ƿ�Ϸ����� rangeCheckForRemove(int index)
      * @param index ɾ��λ�� */

    private void  rangeCheckForRemove (int index) {
    	if (index >= size || index < 0)
    		throw new IndexOutOfBoundsException ("ɾ��λ�ò��Ϸ���");
    }

	/** �����ӡ˳����� print ( )
	 ** ͬ�� */
    
	public void  print( ) {
	    System.out.print("˳�����Ϊ:"+size+",�ڲ�Ԫ��Ϊ:");
	    for (int i = 0; i < size; ++i)
	    	System.out.print(seqList[i] + " ");
	    System.out.println();
	}
	/** ���壺��˳�����ĩβ���Ԫ�غ��� add(E element)
	  * @param element ����Ԫ��
	  * @return �������ɹ�����true�����򷵻�false */

	public boolean add(E element) {
	    seqList[size] = element;
	    ++size;
	    return true;
	}
	/** ������˳���ĳλ�ò��뺯��add(int index, E element)
	  * @param index ����λ��
	  * @param element ����Ԫ��
	  * @return �������ɹ�����true�����򷵻�false */

	public boolean add(int index, E element) {
	    // 1. �жϲ����Ƿ�Ϸ�
	    rangeCheckForAdd(index);
	    // 2. ���洢��size-1λ����indexλ�õ�Ԫ�����κ���һ��λ��
	    for (int i = size; i > index; --i) {
	    	seqList[i] = seqList[i - 1];
	    }
	    // 3. ��x���뵽indexλ��
	    seqList[index] = element;
	    // 4. ��ĳ���+1
	    ++size;
	    return true; 
	}
	/** �������λ���Ƿ�Ϸ����� rangeCheckForAdd(int index)
	  * @param index ����λ��*/

	private void  rangeCheckForAdd (int index) {
	    if (index > size || index < 0)
	    	throw new IndexOutOfBoundsException("����λ�ò��Ϸ���");
	}
}

/** ���������� main()*/
public static void main(String[] args) {
	SequenceList<Integer> seqList = new SequenceList<>();
	// ����һ��˳���
	seqList.add(1);
	seqList.add(5);
	seqList.add(2);
	seqList.add(12);
	seqList.print();
	// ��1λ�ò���Ԫ��7
	seqList.add(1, 7);
	seqList.print();
	// ɾ��1λ��Ԫ��
	seqList.remove(1);
	seqList.print();
}}