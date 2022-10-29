@GetMapping("/api/v2/admin/location/states")
public Map home3() throws ClassNotFoundException, SQLException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	Statement stmt= con.createStatement();
	String query="Select*from states";
	ResultSet rs = stmt.executeQuery(query);
	HashMap map=new HashMap();
	while(rs.next())
	{
	  map.put(rs.getInt("State_id"),rs.getString("State_name"));
	System.out.println(rs.getString("State_name")+" "+rs.getInt("State_id"));
	}
	String query2="insert into states values(10,'BIPUL')";
	int i=stmt.executeUpdate(query2);
	System.out.println(i+" "+"Rows inserted");
	return  map;
}
