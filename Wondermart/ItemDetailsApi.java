@GetMapping("api/Itemdetails/Itemname/{itemid}")
public ArrayList itemdetails(@PathVariable String itemid) throws ClassNotFoundException, SQLException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	Statement stmt= con.createStatement();
	String query="Select*from Itemdetails where itemid='"+itemid+"'";
	ResultSet rs = stmt.executeQuery(query);
	HashMap map=new HashMap();
	int count=0;
	ArrayList list =new ArrayList();
	while(rs.next()) 
	{
		map.put("itemid", rs.getString("itemid"));
		map.put("itemname",rs.getString("itemname"));
		map.put("description",rs.getString("description"));
		map.put("itemprice", rs.getString("itemprice"));
		map.put("storeid",rs.getString("storeid"));
		list.add(map);
	     count++;
	   
	}
	if(count==0)
	{
		list.add("Oops! we could not understand what you mean, try rephrasing the query");
		return list;
	}
     return list;
}

