@GetMapping("api/userdetails/username/{userid}")
public ArrayList userdetails(@PathVariable String userid) throws ClassNotFoundException, SQLException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	Statement stmt= con.createStatement();
	String query="Select*from userdetails where userid='"+userid+"'";
	ResultSet rs = stmt.executeQuery(query);
	HashMap map=new HashMap();
	ArrayList list=new ArrayList();
	if(rs.next())
	{
		map.put("User_Id", rs.getString("userid"));
		map.put("Username", rs.getString("username"));
		map.put("Permission",rs.getString("permission"));
	
	    list.add(map);
	}
	else
	{
		map.put("message","wrong credentials");
		list.add(map);
		return list;
	}
	return  list;
}
