@PostMapping("/Updateuser")
public Map updateuser(HttpServletRequest req)throws ClassNotFoundException, SQLException, IOException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String username =req.getParameter("username");
	String password=req.getParameter("password");
	Statement stmt =con.createStatement();
	String query = "select username,password from userdetails where username='"+username+"' or password ='"+password+"'";
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	if(rs.next())
	{
		map.put("message","you are successfully login ");
		return map;
	}
	else 
	{
		
		String queryInsert="update userdetails set password= ? where username = ?"; 
		PreparedStatement pstmt= con.prepareStatement(queryInsert);
		pstmt.setString(1,password);
		pstmt.setString(2,username);
		
		int i =pstmt.executeUpdate();
		if(i>=1)
		{
			map.put("message","you'r password changed successfully");
			return map;
		}
		else 
		{
			map.put("messgae","Something went wrong please try to login");
			return map;
		}
		
	}
	
}
