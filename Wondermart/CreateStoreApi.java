@PostMapping("/CreateStore")
public Map Restaurant(HttpServletRequest req)throws ClassNotFoundException, SQLException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String storeid =req.getParameter("storeid");
	String storename = req.getParameter("storename");
	String description =req.getParameter("description");
	String type_of_items=req.getParameter("type_of_items");
	Statement stmt =con.createStatement();
	String query = "select * from Storedetails where storename='"+storename+"'";
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	if(rs.next())
	{
		map.put("message"," Restuarant name already exist!");
		return map;
	}
	else
	{
		  String queryInsert = "insert into storedetails(storeid,storename,description,type_of_items)values(?,?,?,?)";
		  PreparedStatement pstmt= con.prepareStatement(queryInsert);
		  pstmt.setString(1,storeid);
		  pstmt.setString(2,storename);
		  pstmt.setString(3,description);
		  pstmt.setString(4,type_of_items);
		  int i =pstmt.executeUpdate();
		  if(i>=1)
		  {
			 map.put("message","Restaurant Successfully registered ");
			 return map;
		  }
		  else 
		  {
			 map.put("messgae","Something went wrong please try to login");
			 return map;
		  }
		}
		
}
