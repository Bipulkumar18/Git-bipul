@PostMapping("/forgotpassword")
public Map updateuser1(HttpServletRequest req)throws ClassNotFoundException, SQLException, IOException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String email =req.getParameter("email");
//	String password=req.getParameter("password");
	Statement stmt =con.createStatement();
	String query = "select * from userdetails where email='"+email+"'";
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	if(rs.next())
	{
		
		map.put("message","A reset password mail has been sent to your email, if your email exist on our site !");
		return map;
	}
//	else
//	{
//		map.put("messgae","this email is not registered");
//		return map;
//	}
	
	return map;
}

