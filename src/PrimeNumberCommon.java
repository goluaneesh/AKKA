public class PrimeNumberCommon {

	public static void main(String[] args){
		int i;
		for(i=1;i<=100000;i++){
			if(prime(i)==true){
			System.out.println(i);
			}
		}
	}
 
  public static boolean prime(int n){
	  int i;
	  if(n==1||n==2){
		  return true;
	  }
	  for(i=2;i*i<=n;i++){
		  if(n%i==0){
			  return false;
		  }
	  }
	  
	  return true;
  }
}
