package white;


import java.time.LocalDateTime;
import java.util.Random;

public class Chess {
	 public String luozi(int[][] chessbox) {
		 LocalDateTime time = LocalDateTime.now();
		 int row=chessbox.length;
		 System.out.print(row);
		   int timestr= time.getSecond();
		    Random r = new Random(timestr);
	    	int ran1 = r.nextInt(row)+1;
	    	int ran2 = r.nextInt(row)+1;
	    	int count0=0;
	    	boolean count0num=true;
	    	for(int i=0;i<chessbox.length;i++)
	    		for(int j=0;j<chessbox.length;j++) {
	    			if(chessbox[i][j]==0)
	    				count0++;
	    		}
	    	if(count0==0)
	    		count0num=false;
	    	if(count0num=true) {
		   while(chessbox[ran1-1][ran2-1]!=0) {
			   ran1 = r.nextInt(row)+1;
			   ran2 = r.nextInt(row)+1;
		   }}
		 String zouqi=ran1+","+ran2;
		 return zouqi;
	 }
}
