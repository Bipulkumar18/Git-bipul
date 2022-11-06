@GetMapping("api/Storedetails/Storename/{storeid}")
public ArrayList Storedetails(@PathVariable String storeid) throws ClassNotFoundException, SQLException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	Statement stmt= con.createStatement();
	String query="Select*from Storedetails where storeid='"+storeid+"'";
	ResultSet rs = stmt.executeQuery(query);
	HashMap map=new HashMap();
	ArrayList list =new ArrayList();
	if(rs.next())
	{
		map.put("storeid", rs.getString("storeid"));
		map.put("storename", rs.getString("storename"));
		map.put("description", rs.getString("description"));
		map.put("typr_of_items", rs.getString("type_of_items"));
		
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


