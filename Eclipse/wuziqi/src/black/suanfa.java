package black;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class suanfa{
	
	
	static Calc calc = new Calc();
	public static void main(String[] args) {
		calc.init();
        calc.quanzhong();
		calc.countfirst();                  //判断棋盘大小以及先后手
        calc.countposition();               //计算落子位置
		calc.xierutxt();                    //写入txt文件两个坐标
	}
}

class Calc {
	private boolean xianXing = true;
    private boolean houXing = false;
    private int qipan[][] = null;
    private int col = 0;
    private int row = 0;
    private int maxfive = 0;
    private boolean[][][] ptable = null;
    private boolean[][][] ctable = null;
    private int[][] pgrades = null;
    private int[][] cgrades = null;
    private int[][] win = null;
    private int cgrade,pgrade;
    private int i,j,k,m,n,icount;
    private int mat,nat,mde,nde;
	public void init(){
		this.qipan = duqutxt();             //读取txt文件中的内容 
		//System.out.print(qipan[3][3]);
        this.row = this.qipan.length;
        this.col = this.qipan[0].length;
        this.maxfive = (this.col-4)*this.row+(this.row-4)*this.col+(this.row-4)*(this.col-4)*2;//棋盘上最多有多少种赢法
        this.ptable = new boolean[this.row][this.col][this.maxfive];
        this.ctable = new boolean[this.row][this.col][this.maxfive];
        this.pgrades = new int[this.row][this.col];
        this.cgrades = new int[this.row][this.col];
        this.win = new int[2][this.maxfive];
        //横  
        for(i=0;i<this.col;i++)  
            for(j=0;j<this.row-4;j++){  
                for(k=0;k<5;k++){  
                    this.ptable[j+k][i][icount] = true;  
                    this.ctable[j+k][i][icount] = true;  
                }  
                icount++;  
            }  
        //竖  
        for(i=0;i<this.row;i++)  
            for(j=0;j<this.col-4;j++){  
                for(k=0;k<5;k++){  
                    this.ptable[i][j+k][icount] = true;  
                    this.ctable[i][j+k][icount] = true;  
                }  
                icount++;  
            }  
        //右斜  
        for(i=0;i<this.col-4;i++)  
            for(j=0;j<this.row-4;j++){  
                for(k=0;k<5;k++){  
                    this.ptable[j+k][i+k][icount] = true;  
                    this.ctable[j+k][i+k][icount] = true;  
                }  
                icount++;  
            }  
        //左斜  
        for(i=0;i<this.col-4;i++)  
            for(j=this.row-1;j>=4;j--){  
                for(k=0;k<5;k++){  
                    this.ptable[j-k][i+k][icount] = true;  
                    this.ctable[j-k][i+k][icount] = true;  
                }  
                icount++;  
            }  
	}
	
	
    public void quanzhong(){//根据当前棋盘的落子情况初始化所有已落子的权重
        for(int i=0;i<this.row;i++)
            for(int j=0;j<this.col;j++){
                if(this.qipan[i][j] == -1){
                    for(int k=0;k<this.maxfive;k++){  
                        if(this.ctable[i][j][k] && this.win[1][k] != 7)  
                            this.win[1][k]++;     //给后行的所有五连子可能的加载当前连子数  
                        if(this.ptable[i][j][k]){  
                            this.ptable[i][j][k] = false;  
                            this.win[0][k]=7;  
                        }  
                    }
                }else if(this.qipan[i][j] == 1){
                    for(int k=0;k<this.maxfive;k++){  
                        if(this.ptable[i][j][k] && this.win[0][k] != 7)  
                            this.win[0][k]++;     //给先行的所有五连子可能的加载当前连子数  
                        if(this.ctable[i][j][k]){  
                            this.ctable[i][j][k] = false;  
                            this.win[1][k]=7;  
                        }  
                    }
                }
            }
    }
    
    
    public void countfirst(){
        boolean xianxing[] = new boolean[this.col];
        boolean houxing[] = new boolean[this.col];
        for(int i =0;i<this.row;i++)
            for(int j = 0;j<this.col;j++){
                if(this.qipan[i][j] == 1){//先行方
                    xianxing[i] = true;
                }else if(this.qipan[i][j] == -1){//后行方
                    houxing[i] = true;
                }
            }
        int xxnum = xianxing.length;
        int hxnum = houxing.length;
        if(xxnum == 0){
            //说明先行没有落子，表明现在为先行方，需落子为1
            this.xianXing = true;
            this.houXing = false;
        }else if(xxnum==hxnum){
            //先行后行一样多，表明现在应该为先行方，落子为1
            this.xianXing = true;
            this.houXing = false;
        }else if(xxnum>hxnum){
            //先行多于后行，表明现在应该为后行方，落子为-1
            this.houXing = true;
            this.xianXing = false;
        }
    }
    
    
    public void countposition(){
        for(i=0;i<this.row;i++)     //遍历棋盘上的所有坐标  
            for(j=0;j<this.col;j++){     
                this.pgrades[i][j]=0;  //该坐标的黑子奖励积分清零  
                if(this.qipan[i][j] == 0)  //在还没下棋子的地方遍历  
                    for(k=0;k<this.maxfive;k++)    //遍历该棋盘可落子点上的黑子所有权值的连子情况，并给该落子点加上相应奖励分  
                        if(this.ptable[i][j][k]){  
                            switch(this.win[0][k]){     
                                case 1: //一连子  
                                    this.pgrades[i][j]+=5;  
                                    break;  
                                case 2: //两连子  
                                    this.pgrades[i][j]+=50;  
                                    break;  
                                case 3: //三连子  
                                    this.pgrades[i][j]+=180;  
                                    break;  
                                case 4: //四连子  
                                    this.pgrades[i][j]+=400;  
                                    break;  
                            }  
                        }  
                this.cgrades[i][j]=0;//该坐标的白子的奖励积分清零  
                if(this.qipan[i][j] == 0)  //在还没下棋子的地方遍历  
                    for(k=0;k<this.maxfive;k++)     //遍历该棋盘可落子点上的白子所有权值的连子情况，并给该落子点加上相应奖励分  
                        if(this.ctable[i][j][k]){  
                            switch(this.win[1][k]){    
                                case 1:  //一连子  
                                    this.cgrades[i][j]+=5;  
                                    break;  
                                case 2:  //两连子  
                                    this.cgrades[i][j]+=52;  
                                    break;  
                                case 3: //三连子  
                                    this.cgrades[i][j]+=100;  
                                    break;  
                                case 4:  //四连子  
                                    this.cgrades[i][j]+=400;  
                                    break;  
                            }  
                        }  
            } 
        for(i=0;i<this.row;i++)  
            for(j=0;j<this.col;j++){ 
                if(this.qipan[i][j] == 0){  //找出棋盘上可落子点的黑子白子的各自最大权值，找出各自的最佳落子点  
                    if(this.cgrades[i][j]>=this.cgrade){  
                        this.cgrade = this.cgrades[i][j];     
                        this.mat = i;  
                        this.nat = j;  
                    }  
                    if(this.pgrades[i][j]>=this.pgrade){  
                        this.pgrade = this.pgrades[i][j];     
                        this.mde = i;  
                        this.nde = j;  
                    }  
                } 
            }
        if(this.cgrade>=this.pgrade){   //如果白子的最佳落子点的权值比黑子的最佳落子点权值大，则电脑的最佳落子点为白子的最佳落子点，否则相反  
            m = mat;  
            n = nat;  
        }else{  
            m = mde;  
            n = nde;  
        }
        if((m == 0 && n == 0)||(m == this.row-1 && n == this.col-1)){
        	m = Math.round(this.row/2);
        	n = Math.round(this.col/2);
        }
        if(this.xianXing){
            this.qipan[m][n] = 1;
        }else if(this.houXing){
            this.qipan[m][n] = -1;   
        }
    }
	
    
    
    //读取TXT文件中的数据，返回二维数组
	public int[][] duqutxt() {
		// TODO Auto-generated method stub
		try {
			List<String[]> doubleArray = new ArrayList();
			String[] strArray = null;
			String pathname = "qipan.txt";
			File filename = new File(pathname);
			InputStreamReader reader = new InputStreamReader(
					new FileInputStream(filename));
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			int i = 0;
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				strArray = line.split(",");
				doubleArray.add(strArray);
				//System.out.println(strArray[0]);
			}
			br.close();
			String[][] result = doubleArray.toArray(new String[][] {});
			int aa=result.length;
			int bb=result[0].length;
			int result1[][]  = new int[aa][bb];
			for (int ii = 0; ii < aa ; ++ii) {
				for (int jj = 0;jj < bb; ++jj ) {
					result1[ii][jj] = Integer.parseInt(result[ii][jj]);
					//System.out.print(result1[ii][jj] + " ");
				}
				//System.out.println();
			}
			return result1;
	    } catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public void xierutxt() {
		try {
			File writename = new File("wangfei.txt");
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			out.write((this.m+1)+"\r\n"+(this.n+1));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
