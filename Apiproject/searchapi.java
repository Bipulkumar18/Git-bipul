@PostMapping("/search")
public Map search(HttpServletRequest req)throws ClassNotFoundException, SQLException
{
	String value =req.getParameter("q");
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	Statement stmt= con.createStatement();
	String query="Select * from states where state_name ='"+value+"'";
	ResultSet rs = stmt.executeQuery(query);
	HashMap map=new HashMap();
	while(rs.next())
	{
	  map.put(rs.getString("State_id"),rs.getString("State_name"));
	 System.out.println(rs.getString("State_name")+" "+rs.getInt("State_id"));
	}
	
	return  map;
}

