package test;

public class Test {
	public static void main(String[] args){//主函数测试用
		int n=3;
		float[][] a= {{1,2,3},{4,5,6},{7,8,9}};
		float[][] b= {{1,2,3},{1,2,3},{1,2,3}};
		
		float c[][] = pro(a,b,n);
		
		for (int i=0 ; i<n ; i++){
			for (int j=0 ; j<n ; j++){
				System.out.print(c[i][j]+" ");
			}
			System.out.println();
		}
	}
	//计算n阶矩阵a，b的a×b的值
	private static float[][] pro(float[][] a, float[][] b, int n) {
		float[][] c=new float[n][n];
		
		for (int i=0; i<n; i++){//矩阵a的i行
			
			for (int j=0; j<n ;j++){//矩阵b的j列
				float sum=0;
				
				for (int k=0; k<n; k++){//a(i)(k)*b(k)(j)
					sum+= a[i][k]*b[k][j];
				}
				c[i][j]= sum;
			}
		}
		return c;
	}
}
