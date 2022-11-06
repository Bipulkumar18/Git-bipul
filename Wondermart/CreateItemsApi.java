@PostMapping("/CreateItems")
public Map CreateItems(HttpServletRequest req)throws ClassNotFoundException, SQLException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String itemid = req.getParameter("itemid");
	String itemname =req.getParameter("itemname");
	String description =req.getParameter("description");
	String itemprice=req.getParameter("itemprice");
	String storeid=req.getParameter("storeid");
	Statement stmt =con.createStatement();
	String query = "select * from Itemdetails where itemname='"+itemname+"' and storeid='"+storeid+"'";
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	if(rs.next())
	{
		map.put("messgae", "this meal already exist in this restaurant");
		return map;
	}
	
	else
	{
		
	      String queryInsert = "insert into Itemdetails(itemid,itemname,description,itemprice,storeid)values(?,?,?,?,?)";
		  PreparedStatement pstmt= con.prepareStatement(queryInsert);
		  pstmt.setString(1,itemid);
		  pstmt.setString(2,itemname);
		  pstmt.setString(3,description);
		  pstmt.setString(4,itemprice);
		  pstmt.setString(5,storeid);
		  int i =pstmt.executeUpdate();
		  if(i>=1)
		  {
			 map.put("message","your Item is added successfully ");
			 return map;
		  }
		  else 
		  {
			 map.put("messgae","Something went wrong ");
			 return map;
		  }
	}
		
}

