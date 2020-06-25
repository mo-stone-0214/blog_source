package test;

import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**关于java.util.Stack
 * 
 * 栈是Vector的一个子类，它实现了一个标准的后进先出的栈。
 * Stack()
 * 
 * boolean empty()  测试堆栈是否为空。
 * Object peek( )   查看堆栈顶部的对象，但不从堆栈中移除它。
 * Object pop( )    移除堆栈顶部的对象，并作为此函数的值返回该对象。
 * Object push(Object element)   把项压入堆栈顶部。
 * int search(Object element)    返回对象在堆栈中的位置，以 1 为基数。
 * 
**/
public class ExpressionOperation {
	
	/**
	 * 符号权重
	 * @param item 符号
	 * @return 权重int
	 */
	private static int heavy(String item) {
		int heavy;
		if ("(".equals(item) || ")".equals(item) ){
			heavy = 0 ;
		}else if("+".equals(item) || "-".equals(item)){
			heavy = 1 ;  
		}else if("*".equals(item) || "/".equals(item)){
			heavy = 2;
		}else{
			throw new IndexOutOfBoundsException ("存在没有设置权重的字符");
		}
		return heavy;
	}
	
	/**
	 * 中缀表达式转化为后缀表达式
	 * @param infix 中缀表达式列表
	 * @return 后缀表达式列表
	 */
	public static ArrayList<String> InfixToSuffix(ArrayList<String> infix){
		
		//创建后缀表达式表
		ArrayList<String> suffix = new ArrayList<String>();
		//创建符号栈
		Stack<String> operator = new Stack<>();
		
		//对原中缀表达式进行循环
		for (int i = 0 ; i < infix.size() ; i++){
			String item = infix.get(i);
			//如果是数字，直接入表
			if (isNum(item)){
				suffix.add(item);
			
			//如果是括号
			}else if ("(".equals(item) || ")".equals(item)){
				//如果为（，直接入栈
				if ("(".equals(item)){
					operator.push(item);
				//如果是），从栈顶依次向下出栈入表，直到遇到（
				}else{
					while (true){
						//遇到（，只出栈，并跳出循环
						if("(".equals(operator.peek())){
							operator.pop();
							break;
						//遇到其他符号，出栈入表
						}else{
							suffix.add(operator.pop());
						}
					}
				}
			
			//如果遇到操作符
			}else{
				//如果栈为空栈，或当前符号权重大于前一个，入栈
			    if(operator.isEmpty() || heavy(item) > heavy(operator.peek())){
			    	operator.push(item);
			    
			    //若当前符号权重小于前一个，循环元素出栈入表，直至空栈，或遇到权重更小的操作符或（
				}else{
					while (!operator.isEmpty() && !"(".equals(operator.peek())){
					    if(heavy(item) <= heavy(operator.peek())){
					         suffix.add(operator.pop());
	                    }
				    }
					//当前操作符压栈
					operator.push(item);
				}
			}
		}
		
		//如果最后一个是括号，会导致输出结果多出空的字符串
		for (int i = 0 ; i<suffix.size() ; i++){
			if ("".equals(suffix.get(i))){
				suffix.remove(i);
			}
		}
			
			
		//循环完毕，若栈不为空，依次出栈入表。
		while (!operator.isEmpty()){
			suffix.add(operator.pop());
		}
		return suffix;
		
	}
	
	/**
	 * 判断字符串是否为数字
	 * @param str 需判断的字符串
	 * @return true/false
	 */
	public static boolean isNum(String str){
		for (int i = 0 ; i<str.length() ; i++){
			if (!Character.isDigit(str.charAt(0))){
				return false;
			}
		}
		return true;
	}

	/**
	 * 字符串转化为ArrayList
	 * @param str 需转换的字符串
	 * @return ArrayList
	 */
	public static ArrayList<String> StringToList(String str){
		//创建列表
		ArrayList<String> list = new ArrayList<>();
		String digit = "";
		//对字符串进行循环
		
        for (int i = 0 ; i < str.length() ; i++){
        	//储存当前循环的字符
        	String item = String.valueOf(str.charAt(i));
        	//如果是数字或小数点，储存到digit中
        	if (isNum(item) || ".".equals(item)){
        		digit+=String.valueOf(item);
        		
        	//如果是符号
        	}else if("(".equals(item) || ")".equals(item) || "+".equals(item) || "-".equals(item) || "*".equals(item) || "/".equals(item)) {
        		
        		//如果是第一个，即（，直接入表
        		if (i==0){
        			list.add(String.valueOf(item));
        			
        		//否则将前一个数字入表，随后符号入表
        		}else{
        			if (!"".equals(digit)){
        				list.add(digit);
        				digit = "";
        			}
        			list.add(String.valueOf(item));
        		}
        	}else{
        		throw new IndexOutOfBoundsException ("非法字符");
        	}
        }
        //循环完毕，若还有储存的数字，入表。
        list.add(String.valueOf(digit));
        
		return list;
	}
	
	/**
     * 根据后缀表达式suffix计算结果
     * @param suffix 后缀表达式列表
     * @return 结果
     */
    private static double calculate(ArrayList<String> suffix) {
    	//创建数字栈
        Stack<String> number = new Stack<>();
        //对列表中元素进行循环
        for(int i=0; i<suffix.size(); i++){
            String item = suffix.get(i);
            //如果是数字，入栈
            if(isNum(item)){
                number.push(item);
            
            //如果是符号，取栈顶两个元素，运算后入栈
            }else {
            	//String → double
            	double num2 = Double.parseDouble(number.pop());
            	double num1 = Double.parseDouble(number.pop());
            	double result = 0;
                if(item.equals("+")){
                	result = num1 + num2;
                }else if(item.equals("-")){
                	result = num1 - num2;
                }else if(item.equals("*")){
                	result = num1 * num2;
                }else if(item.equals("/")){
                	result = num1 / num2;
                }else {
                    throw new RuntimeException("非法符号");
                }
                //double → String
                number.push(String.valueOf(result));
            }
        }
        //返回计算结果
        return Double.parseDouble(number.pop());
    }

    /**
     * 两个数字之间的运算
     * @param x1 数字1
     * @param x2 数字2
     * @param item 运算符号
     * @return 结果
     */
    private static double calculate(double x1,double x2,String item){
    	//定义结果
    	double result ;
    	//根据运算符对两个数字进行运算
    	if (item.equals("+")){
    		result = x1+x2;
    	}else if (item.equals("-")){
    		result = x1-x2;
    	}else if (item.equals("*")){
    		result = x1*x2;
    	}else if (item.equals("/")){
    		result = x1/x2;
    	}else{
    		throw new IndexOutOfBoundsException ("表达式非法");
    	}
    	//返回
		return result;
    }

    /**
     * 中缀表达式的运算
     * @param infix 中缀表达式的列表
     * @return 结果
     */
    public static double InFixOperation(ArrayList<String> infix){
    	//定义数字和操作符的栈
    	Stack<String> number = new Stack<>();
    	Stack<String> operator = new Stack<>();
    	
    	//对中缀表达式进行循环
    	for (int i = 0 ; i<infix.size() ; i++){
    		//当前循环的符号
    		String item = infix.get(i);
    		
    		//如果是数字，直接入数字栈
    		if (isNum(item)){
    			number.push(item);
    		
    		//否则就是操作符
    		}else{
    			//如果是），将前面的数字和符号依次从后往前运算，直至（，并将结果入数字栈
    			if (")".equals(item)){
    				while (!operator.peek().equals("(")){
    					double num2 = Double.parseDouble(number.pop());
        				double num1 = Double.parseDouble(number.pop());
        				String oper = operator.pop();
        				number.push(String.valueOf(calculate(num1,num2,oper)));
    				}
    				operator.pop();
    				
    			//如果是（，或操作栈为空，或当前运算符权重大于前者，直接入操作符栈
    			}else if ("(".equals(item) || operator.isEmpty() || heavy(item)>heavy(operator.peek()) ){
    				operator.push(item);
    				
    			//否则，将前两个数字进行符号运算，结果入数字栈，操作符入操作栈
    			}else{
    				double num2 = Double.parseDouble(number.pop());
        			double num1 = Double.parseDouble(number.pop());
        			String oper = operator.pop();
        			number.push(String.valueOf(calculate(num1,num2,oper)));
        			operator.push(item);
    			}	
    		}
    	}
    	
    	//循环完毕，若符号栈不为空，则从后往前运算，并将结果入数字栈
    	if (!operator.isEmpty()){
    		double num2 = Double.parseDouble(number.pop());
			double num1 = Double.parseDouble(number.pop());
			String oper = operator.pop();
			number.push(String.valueOf(calculate(num1,num2,oper)));
    	}
    	
    	//返回结果
		return Double.parseDouble(number.pop());
    	
    }
    
	public static void main(String[] args){
		//输入
		//Scanner sc = new Scanner(System.in); 
        //String orgexp = sc.nextLine(); 
		String orgexp="(0*1)+((2.5+3)*4.5)-50";
		        
        ArrayList<String> infix = StringToList(orgexp);
        ArrayList<String> suffix = InfixToSuffix(infix);
        
        System.out.print("中缀转化后缀为：");
        for (int i = 0 ; i < suffix.size() ; i++){
        	if (i == suffix.size()-1){
        		System.out.print(suffix.get(i));
        		continue;
        	}
    		System.out.print(suffix.get(i)+",");
        }
        System.out.println();
        
        System.out.print("最终结果："+orgexp+"="+calculate(suffix));
        
        System.out.print("最终结果："+orgexp+"="+InFixOperation(infix));
	}
}
