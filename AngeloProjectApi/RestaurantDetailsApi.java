@GetMapping("api/Restaurantdetails/Restaurantname/{Restaurantid}")
public ArrayList Restaurantdetails(@PathVariable String Restaurantid) throws ClassNotFoundException, SQLException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	Statement stmt= con.createStatement();
	String query="Select*from Restaurantdetails where Restaurantid='"+Restaurantid+"'";
	ResultSet rs = stmt.executeQuery(query);
	HashMap map=new HashMap();
	ArrayList list =new ArrayList();
	if(rs.next())
	{
		map.put("Restaurantid", rs.getString("Restaurantid"));
		map.put("Restaurantname", rs.getString("Restaurantname"));
		map.put("description", rs.getString("description"));
		map.put("typrOfFood", rs.getString("typeOfFood"));
		
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
