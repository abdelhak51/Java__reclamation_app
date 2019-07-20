
import java.sql.*;



import java.sql.DriverManager;


public class connect {//la classe de connection à la BDD
  public static Connection getConnection(){
	  Connection con=null;
	  try{

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestreclam","root","");
		  
	  }catch (Exception e) {
			System.out.println(e);
	  }
	  return con;
  }

public static void main(String args[]){
	connect c=new connect();
	c.getConnection();
}}
