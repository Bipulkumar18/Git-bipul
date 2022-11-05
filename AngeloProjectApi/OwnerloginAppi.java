@PostMapping("/RestaurantOwnerlogin")
public Map RestaurantOwnerlogin(HttpServletRequest req)throws ClassNotFoundException, SQLException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String username=req.getParameter("username");
	String password=req.getParameter("password");
	Statement stmt =con.createStatement();
	String query = "select * from ownerdetails where username='"+username+"'" ;
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	if(rs.next())
	{
		if(rs.getString("password").equals(password))
		{
			map.put("message","you are successfully login");
			return map;
		}
		else
		{
			map.put("message","Wrong password");
			return map;
		}
	}
	else 
	{
		map.put("message","you dont don't have login credentials  first signup  ");
		return map;
	}
}


