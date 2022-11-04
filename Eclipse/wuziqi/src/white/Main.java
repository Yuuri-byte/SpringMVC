package white;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Main m=new Main();
		int[][] qipan=m.qipan();
		while(true){
			int x,y;
			x=(int) Math.ceil(Math.random()*(qipan.length-1));
			y=(int) Math.ceil(Math.random()*(qipan.length-1));
			if (qipan[x][y]==0) {
				m.answer(x, y);
			}
		}
	}
	
	public void answer(int x,int y) throws FileNotFoundException {
		PrintStream out=new PrintStream(new File("wanquan.txt"));
		out.println(x);
		out.println(y);
		out.close();
		System.exit(0);
	}
	
	public int[][] qipan() throws FileNotFoundException {
		Scanner scanner=new Scanner(new File("qipan.txt"));
		String[] strs=scanner.nextLine().split(",");
		int n=strs.length;
		int[][] qipan=new int[n+1][n+1];
		for (int i = 0; i < n; i++) {
			qipan[1][i+1]=Integer.parseInt(strs[i]);
		}
		for (int i = 2; i <= n; i++) {
			strs=scanner.nextLine().split(",");
			for (int j = 1; j <= n; j++) {
				qipan[i][j]=Integer.parseInt(strs[j-1]);
			}
		}
		scanner.close();
		return qipan;
	}
}
