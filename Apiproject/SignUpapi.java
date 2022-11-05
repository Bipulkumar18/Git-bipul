@PostMapping("/signup")
public Map signup(HttpServletRequest req)throws ClassNotFoundException, SQLException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	String name =req.getParameter("name");
	Statement stmt =con.createStatement();
	String query = "select * from user where email='"+email+"'";
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	if(rs.next())
	{
		    map.put("message","you are already signed  up please try to login ");
		    return map;
		
	}
	else
	{
		String queryInsert="insert into user(email,password,name)values(?,?,?)";
		PreparedStatement pstmt= con.prepareStatement(queryInsert);
		pstmt.setString(1,email);
		pstmt.setString(2,password);
		pstmt.setString(3,name);
		int i =pstmt.executeUpdate();
		if(i>=1)
		{
			map.put("message","you are signed up successfully ");
			return map;
		}
		else 
		{
			map.put("messgae","Something went wrong please try to login");
			return map;
		}
	}
}
