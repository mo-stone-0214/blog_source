package test;

import java.util.Stack;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**����java.util.Stack
 * 
 * ջ��Vector��һ�����࣬��ʵ����һ����׼�ĺ���ȳ���ջ��
 * Stack()
 * 
 * boolean empty()  ���Զ�ջ�Ƿ�Ϊ�ա�
 * Object peek( )   �鿴��ջ�����Ķ��󣬵����Ӷ�ջ���Ƴ�����
 * Object pop( )    �Ƴ���ջ�����Ķ��󣬲���Ϊ�˺�����ֵ���ظö���
 * Object push(Object element)   ����ѹ���ջ������
 * int search(Object element)    ���ض����ڶ�ջ�е�λ�ã��� 1 Ϊ������
 * 
**/
public class ExpressionOperation {
	
	/**
	 * ����Ȩ��
	 * @param item ����
	 * @return Ȩ��int
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
			throw new IndexOutOfBoundsException ("����û������Ȩ�ص��ַ�");
		}
		return heavy;
	}
	
	/**
	 * ��׺���ʽת��Ϊ��׺���ʽ
	 * @param infix ��׺���ʽ�б�
	 * @return ��׺���ʽ�б�
	 */
	public static ArrayList<String> InfixToSuffix(ArrayList<String> infix){
		
		//������׺���ʽ��
		ArrayList<String> suffix = new ArrayList<String>();
		//��������ջ
		Stack<String> operator = new Stack<>();
		
		//��ԭ��׺���ʽ����ѭ��
		for (int i = 0 ; i < infix.size() ; i++){
			String item = infix.get(i);
			//��������֣�ֱ�����
			if (isNum(item)){
				suffix.add(item);
			
			//���������
			}else if ("(".equals(item) || ")".equals(item)){
				//���Ϊ����ֱ����ջ
				if ("(".equals(item)){
					operator.push(item);
				//����ǣ�����ջ���������³�ջ���ֱ��������
				}else{
					while (true){
						//��������ֻ��ջ��������ѭ��
						if("(".equals(operator.peek())){
							operator.pop();
							break;
						//�����������ţ���ջ���
						}else{
							suffix.add(operator.pop());
						}
					}
				}
			
			//�������������
			}else{
				//���ջΪ��ջ����ǰ����Ȩ�ش���ǰһ������ջ
			    if(operator.isEmpty() || heavy(item) > heavy(operator.peek())){
			    	operator.push(item);
			    
			    //����ǰ����Ȩ��С��ǰһ����ѭ��Ԫ�س�ջ���ֱ����ջ��������Ȩ�ظ�С�Ĳ�������
				}else{
					while (!operator.isEmpty() && !"(".equals(operator.peek())){
					    if(heavy(item) <= heavy(operator.peek())){
					         suffix.add(operator.pop());
	                    }
				    }
					//��ǰ������ѹջ
					operator.push(item);
				}
			}
		}
		
		//������һ�������ţ��ᵼ������������յ��ַ���
		for (int i = 0 ; i<suffix.size() ; i++){
			if ("".equals(suffix.get(i))){
				suffix.remove(i);
			}
		}
			
			
		//ѭ����ϣ���ջ��Ϊ�գ����γ�ջ���
		while (!operator.isEmpty()){
			suffix.add(operator.pop());
		}
		return suffix;
		
	}
	
	/**
	 * �ж��ַ����Ƿ�Ϊ����
	 * @param str ���жϵ��ַ���
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
	 * �ַ���ת��ΪArrayList
	 * @param str ��ת�����ַ���
	 * @return ArrayList
	 */
	public static ArrayList<String> StringToList(String str){
		//�����б�
		ArrayList<String> list = new ArrayList<>();
		String digit = "";
		//���ַ�������ѭ��
		
        for (int i = 0 ; i < str.length() ; i++){
        	//���浱ǰѭ�����ַ�
        	String item = String.valueOf(str.charAt(i));
        	//��������ֻ�С���㣬���浽digit��
        	if (isNum(item) || ".".equals(item)){
        		digit+=String.valueOf(item);
        		
        	//����Ƿ���
        	}else if("(".equals(item) || ")".equals(item) || "+".equals(item) || "-".equals(item) || "*".equals(item) || "/".equals(item)) {
        		
        		//����ǵ�һ����������ֱ�����
        		if (i==0){
        			list.add(String.valueOf(item));
        			
        		//����ǰһ������������������
        		}else{
        			if (!"".equals(digit)){
        				list.add(digit);
        				digit = "";
        			}
        			list.add(String.valueOf(item));
        		}
        	}else{
        		throw new IndexOutOfBoundsException ("�Ƿ��ַ�");
        	}
        }
        //ѭ����ϣ������д�������֣����
        list.add(String.valueOf(digit));
        
		return list;
	}
	
	/**
     * ���ݺ�׺���ʽsuffix������
     * @param suffix ��׺���ʽ�б�
     * @return ���
     */
    private static double calculate(ArrayList<String> suffix) {
    	//��������ջ
        Stack<String> number = new Stack<>();
        //���б���Ԫ�ؽ���ѭ��
        for(int i=0; i<suffix.size(); i++){
            String item = suffix.get(i);
            //��������֣���ջ
            if(isNum(item)){
                number.push(item);
            
            //����Ƿ��ţ�ȡջ������Ԫ�أ��������ջ
            }else {
            	//String �� double
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
                    throw new RuntimeException("�Ƿ�����");
                }
                //double �� String
                number.push(String.valueOf(result));
            }
        }
        //���ؼ�����
        return Double.parseDouble(number.pop());
    }

    /**
     * ��������֮�������
     * @param x1 ����1
     * @param x2 ����2
     * @param item �������
     * @return ���
     */
    private static double calculate(double x1,double x2,String item){
    	//������
    	double result ;
    	//������������������ֽ�������
    	if (item.equals("+")){
    		result = x1+x2;
    	}else if (item.equals("-")){
    		result = x1-x2;
    	}else if (item.equals("*")){
    		result = x1*x2;
    	}else if (item.equals("/")){
    		result = x1/x2;
    	}else{
    		throw new IndexOutOfBoundsException ("���ʽ�Ƿ�");
    	}
    	//����
		return result;
    }

    /**
     * ��׺���ʽ������
     * @param infix ��׺���ʽ���б�
     * @return ���
     */
    public static double InFixOperation(ArrayList<String> infix){
    	//�������ֺͲ�������ջ
    	Stack<String> number = new Stack<>();
    	Stack<String> operator = new Stack<>();
    	
    	//����׺���ʽ����ѭ��
    	for (int i = 0 ; i<infix.size() ; i++){
    		//��ǰѭ���ķ���
    		String item = infix.get(i);
    		
    		//��������֣�ֱ��������ջ
    		if (isNum(item)){
    			number.push(item);
    		
    		//������ǲ�����
    		}else{
    			//����ǣ�����ǰ������ֺͷ������δӺ���ǰ���㣬ֱ�������������������ջ
    			if (")".equals(item)){
    				while (!operator.peek().equals("(")){
    					double num2 = Double.parseDouble(number.pop());
        				double num1 = Double.parseDouble(number.pop());
        				String oper = operator.pop();
        				number.push(String.valueOf(calculate(num1,num2,oper)));
    				}
    				operator.pop();
    				
    			//����ǣ��������ջΪ�գ���ǰ�����Ȩ�ش���ǰ�ߣ�ֱ���������ջ
    			}else if ("(".equals(item) || operator.isEmpty() || heavy(item)>heavy(operator.peek()) ){
    				operator.push(item);
    				
    			//���򣬽�ǰ�������ֽ��з������㣬���������ջ�������������ջ
    			}else{
    				double num2 = Double.parseDouble(number.pop());
        			double num1 = Double.parseDouble(number.pop());
        			String oper = operator.pop();
        			number.push(String.valueOf(calculate(num1,num2,oper)));
        			operator.push(item);
    			}	
    		}
    	}
    	
    	//ѭ����ϣ�������ջ��Ϊ�գ���Ӻ���ǰ���㣬�������������ջ
    	if (!operator.isEmpty()){
    		double num2 = Double.parseDouble(number.pop());
			double num1 = Double.parseDouble(number.pop());
			String oper = operator.pop();
			number.push(String.valueOf(calculate(num1,num2,oper)));
    	}
    	
    	//���ؽ��
		return Double.parseDouble(number.pop());
    	
    }
    
	public static void main(String[] args){
		//����
		//Scanner sc = new Scanner(System.in); 
        //String orgexp = sc.nextLine(); 
		String orgexp="(0*1)+((2.5+3)*4.5)-50";
		        
        ArrayList<String> infix = StringToList(orgexp);
        ArrayList<String> suffix = InfixToSuffix(infix);
        
        System.out.print("��׺ת����׺Ϊ��");
        for (int i = 0 ; i < suffix.size() ; i++){
        	if (i == suffix.size()-1){
        		System.out.print(suffix.get(i));
        		continue;
        	}
    		System.out.print(suffix.get(i)+",");
        }
        System.out.println();
        
        System.out.print("���ս����"+orgexp+"="+calculate(suffix));
        
        System.out.print("���ս����"+orgexp+"="+InFixOperation(infix));
	}
}
