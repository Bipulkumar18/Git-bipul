@PostMapping("/Createowner/SignupofUser")
   public Map user(HttpServletRequest req)throws ClassNotFoundException, SQLException
  {
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String userid = req.getParameter("userid");
	String username =req.getParameter("username");
	String password=req.getParameter("password");
	String permission =req.getParameter("permission");
	String email =req.getParameter("email");
	Statement stmt =con.createStatement();
	String query = "select * from userdetails where email='"+email+"' or username ='"+username+"'";
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
		  String queryInsert = "insert into userdetails(userid,username,password,permission,email)values(?,?,?,?,?)";
		  PreparedStatement pstmt= con.prepareStatement(queryInsert);
		  pstmt.setString(1,userid);
		  pstmt.setString(2,username);
		  pstmt.setString(3,password);
		  pstmt.setString(4,permission);
		  pstmt.setString(5,email);
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
			 String queryInsert1 = "insert into ownerdetails(userid ,username,password,permission)values(?,?,?,?)";
			  PreparedStatement pstmt= con.prepareStatement(queryInsert1);
			  pstmt.setString(1,userid);
			  pstmt.setString(2,username);
			  pstmt.setString(3,password);
			  pstmt.setString(4,permission);
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

