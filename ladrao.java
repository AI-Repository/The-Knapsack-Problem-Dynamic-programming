import java.util.*;
public class ladrao {
	static Scanner sc=new Scanner(System.in);
	static int cap=sc.nextInt();
	static int n=sc.nextInt();
	public static void main(String[] args) {
		int[][] test=new int[2][n];
		int[][]V=new int[n+1][cap+1];
		int[][]keep=new int[n+1][cap+1];
		String[] names=new String[n];
		for(int i=0;i<n;i++){
			names[i]=sc.next();
			test[0][i]=sc.nextInt();
			test[1][i]=sc.nextInt();
		}
		force_ladrao(test,n);
		ladrao(test,V,keep,names);
		print(V,keep);
	}

	public static void print(int[][]V,int[][]keep){
		for(int i=0;i<=n;i++){
			for(int j=0;j<=cap;j++){
				System.out.print(V[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("---------------");
		for(int i=0;i<=n;i++){
			for(int j=0;j<=cap;j++){
				System.out.print(keep[i][j]+" ");
			}
			System.out.println();
		}
	}

	public static void force_ladrao(int[][] a,int n){
		int max = 0,max_i=0,size=(int) (Math.pow(2, n)-1);
		for(int i=1;i<=size;i++){
			String bynary=Integer.toBinaryString(i);
			int count=0,result=0,result_i=0;
			for(int j=bynary.length()-1;j>=0;j--){
				if(bynary.charAt(j)=='1'){
					result+=a[0][n-1-count];
					result_i+=a[1][n-1-count];
				}
				count++;
			}
			if(result>max && result_i<=cap){
				max=result;
				max_i=result_i;
			}
		}
		System.out.println("FORCE------> "+max+" "+max_i);
	}

	public static void ladrao(int[][] names, int[][] V,int[][] keep,String[] abs){
		String[] final_result=new String[n];
		int max=0,result=0;
		for(int j=0;j<=cap;j++){
			V[0][j]=0;
			keep[0][j]=0;
			for(int i=1;i<=n;i++){
				if(names[1][i-1]>j){
					V[i][j]=V[i-1][j];
					keep[0][j]=0;
				} else {
					int up=V[i-1][j];
					int aux=names[0][i-1]+V[i-1][j-names[1][i-1]];
					V[i][j]=Math.max(up, aux);
					if(V[i][j]>V[i-1][j]){
						keep[i][j]=1;
					}
				}
				max=V[i][j];
			}
		}
		System.out.print(max+"\n");
		int i=n,j=cap;
		for(int z=0;z<n;z++){
			if(keep[i][j]==1){
				final_result[result++]=abs[i-1];
				j-=names[1][i-1];
				i--;
			}
			else{
				i--;
			}
		}
		for(int z=result-1;z>=0;z--){
			System.out.print(final_result[z]+" ");
		}
	}
}