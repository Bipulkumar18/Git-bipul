@PostMapping("/e-commerce/CreateUser/Createowner/SignupofUser")
public Map user(HttpServletRequest req)throws ClassNotFoundException, SQLException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String userid = req.getParameter("userid");
	String username =req.getParameter("username");
	String emailid =req.getParameter("emailid");
	String password=req.getParameter("password");
	String permission =req.getParameter("permission");
	
	Statement stmt =con.createStatement();
	String query = "select * from E_commerceUserdetails where emailid='"+emailid+"' or username ='"+username+"'";
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	if(rs.next())
	{
		map.put("message","this email already exist in our user list !! try use another email");
		return map;
	}
	else
	{
		String user="user";
		if(permission.equals(user))
		{
		  String queryInsert = "insert into E_commerceUserdetails(userid,username,emailid,password,permission)values(?,?,?,?,?)";
		  PreparedStatement pstmt= con.prepareStatement(queryInsert);
		  pstmt.setString(1,userid);
		  pstmt.setString(2,username);
		  pstmt.setString(3,emailid);
		  pstmt.setString(4,password);
		  pstmt.setString(5,permission);
		
		  int i =pstmt.executeUpdate();
		  if(i>=1)
		  {
			 map.put("message","you are signed up successfully as a user");
			 return map;
		  }
		  else 
		  {
			 map.put("messgae","User: Something went wrong please try to login");
			 return map;
		  }
		}
		else
		{
			 String queryInsert1 = "insert into E_commerceOwnerdetails(userid,username,emailid,password,permission)values(?,?,?,?,?)";
			  PreparedStatement pstmt= con.prepareStatement(queryInsert1);
			  pstmt.setString(1,userid);
			  pstmt.setString(2,username);
			  pstmt.setString(3,emailid);
			  pstmt.setString(4,password);
			  pstmt.setString(5,permission);
			  int i =pstmt.executeUpdate();
			  if(i>=1)
			  {
				 map.put("message","you are signed up successfully as a Owner ");
				 return map;
			  }
			  else 
			  {
				 map.put("messgae","Owner : Something went wrong please try to login");
			 return map;
			  }
		}
	}
}
	
