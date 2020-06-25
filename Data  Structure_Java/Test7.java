package test;

//�������ջ����
import java.util.Stack;

public class Test7 {
	
	/**
	 * ��ջ�ж��Ƿ�Ϊ����
	 * @param str �жϵ��ַ���
	 * @return true/false
	 */
	public static boolean TurnString(String str){
		//���Ϊ�գ��ӳ�����
		if (str.length()==0){
			throw new IndexOutOfBoundsException ("�Ƿ��ַ���");
		}
		
		//���ֻ��һ����ĸ������true
		if (str.length()==1){
			return true;
		}
		
		//�½�ջ1��2
		Stack<String> stack1 = new Stack<>();
		Stack<String> stack2 = new Stack<>();
		
		//���ѭ������
		double n;
		if (str.length()%2==0){
			n = str.length()/2+0.5;
		}else{
			n = (str.length()+1)/2;
		}
		
		//���ַ�����ǰ���ַ����������ջ1
        for (int i = 1 ; i < n ; i++){
        	stack1.add(String.valueOf(str.charAt(i-1))); 
        }
        
        //���ַ����к����ַ����������ջ2
        for (int i = str.length() ; i > n ; i--){
        	stack2.add(String.valueOf(str.charAt(i-1))); 
        }
        
        //ѭ����ջ�Ƚ�ÿһ���ַ�
        for (int i = 1 ; i<=stack1.size() ; i++){
        	
        	//�������ͬ������false
        	if (!(stack1.pop()).equals(stack2.pop())){
        		return false;
        	}
        }
        
        //�����ͬ������true
		return true;
	}
	
	public static void main(String[] args){
		String str1 = "";
		String str2 = "a";
		String str3 = "asd";
		String str4 = "asdsa";
		String str5 = "as";
		String str6 = "assa";
		
		//System.out.println(TurnString(str1));
		System.out.println(TurnString(str2));
		System.out.println(TurnString(str3));
		System.out.println(TurnString(str4));
		System.out.println(TurnString(str5));
		System.out.println(TurnString(str6));
	}

}
