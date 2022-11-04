package black;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class suanfa{
	
	
	static Calc calc = new Calc();
	public static void main(String[] args) {
		calc.init();
        calc.quanzhong();
		calc.countfirst();                  //�ж����̴�С�Լ��Ⱥ���
        calc.countposition();               //��������λ��
		calc.xierutxt();                    //д��txt�ļ���������
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
		this.qipan = duqutxt();             //��ȡtxt�ļ��е����� 
		//System.out.print(qipan[3][3]);
        this.row = this.qipan.length;
        this.col = this.qipan[0].length;
        this.maxfive = (this.col-4)*this.row+(this.row-4)*this.col+(this.row-4)*(this.col-4)*2;//����������ж�����Ӯ��
        this.ptable = new boolean[this.row][this.col][this.maxfive];
        this.ctable = new boolean[this.row][this.col][this.maxfive];
        this.pgrades = new int[this.row][this.col];
        this.cgrades = new int[this.row][this.col];
        this.win = new int[2][this.maxfive];
        //��  
        for(i=0;i<this.col;i++)  
            for(j=0;j<this.row-4;j++){  
                for(k=0;k<5;k++){  
                    this.ptable[j+k][i][icount] = true;  
                    this.ctable[j+k][i][icount] = true;  
                }  
                icount++;  
            }  
        //��  
        for(i=0;i<this.row;i++)  
            for(j=0;j<this.col-4;j++){  
                for(k=0;k<5;k++){  
                    this.ptable[i][j+k][icount] = true;  
                    this.ctable[i][j+k][icount] = true;  
                }  
                icount++;  
            }  
        //��б  
        for(i=0;i<this.col-4;i++)  
            for(j=0;j<this.row-4;j++){  
                for(k=0;k<5;k++){  
                    this.ptable[j+k][i+k][icount] = true;  
                    this.ctable[j+k][i+k][icount] = true;  
                }  
                icount++;  
            }  
        //��б  
        for(i=0;i<this.col-4;i++)  
            for(j=this.row-1;j>=4;j--){  
                for(k=0;k<5;k++){  
                    this.ptable[j-k][i+k][icount] = true;  
                    this.ctable[j-k][i+k][icount] = true;  
                }  
                icount++;  
            }  
	}
	
	
    public void quanzhong(){//���ݵ�ǰ���̵����������ʼ�����������ӵ�Ȩ��
        for(int i=0;i<this.row;i++)
            for(int j=0;j<this.col;j++){
                if(this.qipan[i][j] == -1){
                    for(int k=0;k<this.maxfive;k++){  
                        if(this.ctable[i][j][k] && this.win[1][k] != 7)  
                            this.win[1][k]++;     //�����е����������ӿ��ܵļ��ص�ǰ������  
                        if(this.ptable[i][j][k]){  
                            this.ptable[i][j][k] = false;  
                            this.win[0][k]=7;  
                        }  
                    }
                }else if(this.qipan[i][j] == 1){
                    for(int k=0;k<this.maxfive;k++){  
                        if(this.ptable[i][j][k] && this.win[0][k] != 7)  
                            this.win[0][k]++;     //�����е����������ӿ��ܵļ��ص�ǰ������  
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
                if(this.qipan[i][j] == 1){//���з�
                    xianxing[i] = true;
                }else if(this.qipan[i][j] == -1){//���з�
                    houxing[i] = true;
                }
            }
        int xxnum = xianxing.length;
        int hxnum = houxing.length;
        if(xxnum == 0){
            //˵������û�����ӣ���������Ϊ���з���������Ϊ1
            this.xianXing = true;
            this.houXing = false;
        }else if(xxnum==hxnum){
            //���к���һ���࣬��������Ӧ��Ϊ���з�������Ϊ1
            this.xianXing = true;
            this.houXing = false;
        }else if(xxnum>hxnum){
            //���ж��ں��У���������Ӧ��Ϊ���з�������Ϊ-1
            this.houXing = true;
            this.xianXing = false;
        }
    }
    
    
    public void countposition(){
        for(i=0;i<this.row;i++)     //���������ϵ���������  
            for(j=0;j<this.col;j++){     
                this.pgrades[i][j]=0;  //������ĺ��ӽ�����������  
                if(this.qipan[i][j] == 0)  //�ڻ�û�����ӵĵط�����  
                    for(k=0;k<this.maxfive;k++)    //���������̿����ӵ��ϵĺ�������Ȩֵ��������������������ӵ������Ӧ������  
                        if(this.ptable[i][j][k]){  
                            switch(this.win[0][k]){     
                                case 1: //һ����  
                                    this.pgrades[i][j]+=5;  
                                    break;  
                                case 2: //������  
                                    this.pgrades[i][j]+=50;  
                                    break;  
                                case 3: //������  
                                    this.pgrades[i][j]+=180;  
                                    break;  
                                case 4: //������  
                                    this.pgrades[i][j]+=400;  
                                    break;  
                            }  
                        }  
                this.cgrades[i][j]=0;//������İ��ӵĽ�����������  
                if(this.qipan[i][j] == 0)  //�ڻ�û�����ӵĵط�����  
                    for(k=0;k<this.maxfive;k++)     //���������̿����ӵ��ϵİ�������Ȩֵ��������������������ӵ������Ӧ������  
                        if(this.ctable[i][j][k]){  
                            switch(this.win[1][k]){    
                                case 1:  //һ����  
                                    this.cgrades[i][j]+=5;  
                                    break;  
                                case 2:  //������  
                                    this.cgrades[i][j]+=52;  
                                    break;  
                                case 3: //������  
                                    this.cgrades[i][j]+=100;  
                                    break;  
                                case 4:  //������  
                                    this.cgrades[i][j]+=400;  
                                    break;  
                            }  
                        }  
            } 
        for(i=0;i<this.row;i++)  
            for(j=0;j<this.col;j++){ 
                if(this.qipan[i][j] == 0){  //�ҳ������Ͽ����ӵ�ĺ��Ӱ��ӵĸ������Ȩֵ���ҳ����Ե�������ӵ�  
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
        if(this.cgrade>=this.pgrade){   //������ӵ�������ӵ��Ȩֵ�Ⱥ��ӵ�������ӵ�Ȩֵ������Ե�������ӵ�Ϊ���ӵ�������ӵ㣬�����෴  
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
	
    
    
    //��ȡTXT�ļ��е����ݣ����ض�ά����
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
