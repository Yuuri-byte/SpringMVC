<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="final_chess.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.swing.JOptionPane"%>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.util.regex.*" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="kongqipan.jsp"%>
	<%
	boolean avail=true;
	int player=0;
	int another=0;
	String playerstr="";
	
String stepsql="select * from bisailunci where number="+application.getAttribute("alltheNum");//获取最后一次落子结果
ResultSet rs2=createQipan.getResult(stepsql);
rs2.next();
String luozi = rs2.getString("steps");//将step字段内容赋给luozi变量
//System.out.print("luozi="+luozi);
//luozi=luozi.substring(luozi.length()-5, luozi.length()-2);

String[] str=new String[therow*therow];
int cou=0,cou2=0;
Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");  
Matcher matcher = pattern.matcher(luozi);
while(matcher.find()){
	   str[cou++]=matcher.group();
	}
while(str[cou2++]!=null)
{luozi=str[cou2-1];}
//System.out.print("正则表达式获取的落子是"+luozi);

String[] addluozi=luozi.split(",");
for(int i=0;i<addluozi.length;i++){
	//System.out.print("addluozi="+addluozi[i]);
}

int[] add=new int[2];
add[0]=Integer.parseInt(addluozi[0]);
add[1]=Integer.parseInt(addluozi[1]);//将最后一次落子结果横纵坐标存进add[]
int hengzuobiao=add[0];
int zongzuobiao=add[1];

		
String laststep=add[0]+","+add[1];
createQipan.changeContent("update bisailunci set laststep='"+laststep+"'where number="+application.getAttribute("alltheNum"));

String chessbox=rs2.getString("chessbox");
//System.out.print("qipanqingkuangshi"+chessbox);
int qipanArray[][]=createQipan.qipanStringtoInt(chessbox);//将从数据库获取的chessbox转化为二维数组

if(qipanArray[add[0]-1][add[1]-1]!=0||add[0]>therow||add[1]>therow){
	avail=false;
}

int total_chess=0;
int jiashu;
int count0=0;
boolean count0num=true;
for(int i=0;i<therow;i++){
	for(int j=0;j<therow;j++){
		total_chess+=qipanArray[i][j];
		if(qipanArray[i][j]==0)
			count0++;
	}
}//计算棋盘之和
if(count0==0)
	count0num=false;
if(total_chess==0){jiashu=-1;
playerstr=rs2.getString("firstplayerNo");
player=Integer.parseInt(playerstr);
playerstr=rs2.getString("lastplayerNo");
another=Integer.parseInt(playerstr);
}
else {jiashu=1;
playerstr=rs2.getString("lastplayerNo");
player=Integer.parseInt(playerstr);
playerstr=rs2.getString("firstplayerNo");
another=Integer.parseInt(playerstr);
}//为0走白，为-1走黑
//System.out.print("黑棋白棋："+jiashu);
for(int i=0;i<therow;i++) {
	for(int j=0;j<therow;j++) {
		if(i==add[0]-1&&j==add[1]-1){
			qipanArray[i][j]=jiashu;
		}//修改棋盘数组
	}
}
chessbox=createQipan.qipanInttoString(qipanArray);//再将二维数组转化为字符串

//System.out.print("改完后的chessbox"+chessbox);

int gengxin=createQipan.changeContent("update bisailunci set chessbox='"+chessbox+"'where number="+application.getAttribute("alltheNum"));//将更新后的字符串存入数据库
//System.out.print("更新的行数："+gengxin);

int yiweiqipan[]=new int[therow*therow];
for(int i=0;i<therow;i++) {
	for(int j=0;j<therow;j++) {
		yiweiqipan[i*therow+j]=qipanArray[i][j];
	}
}
for(int i=0;i<therow*therow;i++){
	//System.out.print("一维"+yiweiqipan[i]);
}

%>

<% 
boolean res=false;
int winnernum=0;
res=createQipan.Win((int)application.getAttribute("alltheNum"));
if(res==true&&jiashu==-1){
	//JOptionPane.showMessageDialog(null,"白子获胜！");
	String winnerNo = rs2.getString("lastplayerNo");
	winnernum=Integer.parseInt(winnerNo);
	createQipan.changeContent("update bisailunci set winner="+winnernum+"where number="+application.getAttribute("alltheNum"));
	}
else if(res==true&&jiashu==1){
	//JOptionPane.showMessageDialog(null,"黑子获胜！");
	String winnerNo = rs2.getString("firstplayerNo");
	winnernum=Integer.parseInt(winnerNo);
	createQipan.changeContent("update bisailunci set winner="+winnernum+"where number="+application.getAttribute("alltheNum"));
	}
else
	{//response.sendRedirect("loading.jsp");
	//System.out.print("比赛继续");
	}

%>

<script type="text/javascript">
var avail=<%=avail%>;
var res=<%=res%>;
var winnernum=<%=winnernum%>;
var hengzuobiao=<%=hengzuobiao%>;
var zongzuobiao=<%=zongzuobiao%>;
var count0=<%=count0num%>;

function oneStep(i,j,k,r){
	context.font="20px Georgia";
	 context.beginPath();
	 context.arc(15+i*30,15+j*30,11,0,2*Math.PI);//绘制棋子
	 context.fillStyle=k;
	 context.fill();
	 
	 context.closePath();
	 
	 
	}
function stepClear(i,j,r){
	//context.fillStyle = '#ffffff';
	//context.fillRect(5+r*30, 0, 200, 200);
	
	i=i+1;
	 j=j+1;
	 context.fillText("本次落子位置为("+i+","+j+")",10+r*30,30);
	 //context.fillText("",10+r*30,30);
}
	var row=<%=therow%>;
	var list = new Array(row*row);	
	<% for(int i=0;i<therow*therow;i++){
		%>
		list[<%= i%>]="<%=yiweiqipan[i]%>";
		<%
		}
		%>
	var n;
	for(n=0;n<row*row;n++){
		if(list[n]==-1)
			//oneStep(zong,heng,shai)
			{
			//context.fillStyle = '#cdcdcd';
			//context.fillRect(30+row*30, 0, 200, 200);
			oneStep(n%row,Math.ceil(n/row)-1,"red",row);//根据落子结果进行落子
			if(n==(hengzuobiao-1)*row+zongzuobiao-1)
			stepClear(n%row,Math.ceil(n/row)-1,row);
			}
		else if(list[n]==1){
			//context.fillStyle = '#cdcdcd';
			 //context.fillRect(5+row*30, 0, 200, 200);
			oneStep(n%row,Math.ceil(n/row)-1,"black",row);//根据落子结果进行落子
			if(n==(hengzuobiao-1)*row+zongzuobiao-1)
				stepClear(n%row,Math.ceil(n/row)-1,row);
		}
			
		}
	if(count0==false){
		alert("棋盘落满，本局平局！");
	}
	else if(avail==false){
		alert(<%=player%>+"号棋手非法落子！"+<%=another%>+"号棋手获胜！");
	}	
		
	else if(res!=true)
	setTimeout(()=>document.querySelector("body > a").click(),1200);
		else if(res==true){
			alert("胜者是"+winnernum+"号棋手！");
		}
		
</script>

<a href="loading.jsp">下一步</a>
</body>
</html>