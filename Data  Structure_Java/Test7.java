package test;

//导入关于栈的类
import java.util.Stack;

public class Test7 {
	
	/**
	 * 用栈判断是否为回文
	 * @param str 判断的字符串
	 * @return true/false
	 */
	public static boolean TurnString(String str){
		//如果为空，扔出错误
		if (str.length()==0){
			throw new IndexOutOfBoundsException ("非法字符串");
		}
		
		//如果只有一个字母，返回true
		if (str.length()==1){
			return true;
		}
		
		//新建栈1和2
		Stack<String> stack1 = new Stack<>();
		Stack<String> stack2 = new Stack<>();
		
		//获得循环次数
		double n;
		if (str.length()%2==0){
			n = str.length()/2+0.5;
		}else{
			n = (str.length()+1)/2;
		}
		
		//将字符串中前半字符按正序放入栈1
        for (int i = 1 ; i < n ; i++){
        	stack1.add(String.valueOf(str.charAt(i-1))); 
        }
        
        //将字符串中后半个字符按倒序放入栈2
        for (int i = str.length() ; i > n ; i--){
        	stack2.add(String.valueOf(str.charAt(i-1))); 
        }
        
        //循环出栈比较每一个字符
        for (int i = 1 ; i<=stack1.size() ; i++){
        	
        	//如果不相同，返回false
        	if (!(stack1.pop()).equals(stack2.pop())){
        		return false;
        	}
        }
        
        //如果相同，返回true
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
