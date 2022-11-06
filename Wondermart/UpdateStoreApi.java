@PostMapping("/updateStore1")
public Map updateStore(HttpServletRequest req)throws ClassNotFoundException, SQLException, IOException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String storeid =req.getParameter("storeid");
	String storename = req.getParameter("storename");
	String description =req.getParameter("description");
	String type_of_items=req.getParameter("type_of_items");
	Statement stmt =con.createStatement();
	String query = "select * from Storedetails where storename = '"+storename+"' and storeid='"+storeid+"'" ;
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	if(rs.next())
	{
	
		String queryInsert="update Storedetails set storeid=?,description=?,type_of_items=? where storename= ? "; 
		PreparedStatement pstmt= con.prepareStatement(queryInsert);
		pstmt.setString(1,storeid);
		pstmt.setString(2,description);
		pstmt.setString(3,type_of_items);
		pstmt.setString(4,storename);
		
		int i =pstmt.executeUpdate();
		if(i>=1)
		{
			map.put("message","your items changed successfully");
			return map;
		}
		else 
		{
			map.put("messgae","Something went wrong please try to login");
			return map;
		}
	}
	return map;
}
	
