import java.util.Scanner;


public class Solution {
	static long count=0;
	static long number;
	static long numberOfPaths (int [][]a ,int M, int N){
			
		  
		return new Solution().numberOfPaths1(a, 0, 0, 0);
	}
	
	 long numberOfPaths1(int[][] a, int i, int j, long count) {
		 
	        int iMax = a.length - 1;
	        int jMax = a[0].length - 1;
	 
	        if (a[0][0] == 0 || a[iMax][jMax] == 0) {
	            return 0;
	        }
	 
	        if (i == iMax && j == jMax) {
	            return ++count;
	        }
	 
	        if (i + 1 <= iMax && a[i + 1][j] == 1) {
	            count = numberOfPaths1(a, i + 1, j, count);
	        }
	 
	        if (j + 1 <= jMax && a[i][j + 1] == 1) {
	            count = numberOfPaths1(a, i, j + 1, count);
	        }
	        return count;
	    }
	 
	 public static void main(String[] args) {

			Scanner sc = new Scanner(System.in);
					
			int _a_cnt = 0,_b_cnt = 0;
			int [][] _a = new int[1001][1001];
			try {
				_a_cnt = sc.nextInt();
				_b_cnt = sc.nextInt();
			}catch (Exception e) {
				 System.out.println("Here: " + e.getMessage()); 
			} 

			for(int i=0; i < _a_cnt; i++) {
				for( int j = 0;j < _b_cnt;j++ ){
					int _a_tmp = 0;
					try {
						_a_tmp = sc.nextInt();
					}catch (Exception e) { }
					_a[i][j] = _a_tmp;
				}			
			}
			System.out.println(numberOfPaths (_a ,_a_cnt,_b_cnt));

		}
	}

