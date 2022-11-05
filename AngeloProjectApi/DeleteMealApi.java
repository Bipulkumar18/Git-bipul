@PostMapping("/DeleteMeal")
public Map DeleteMeal(HttpServletRequest req)throws ClassNotFoundException, SQLException, IOException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String mealid = req.getParameter("mealid");
	String mealname =req.getParameter("mealname");
	String description =req.getParameter("description");
	String mealprice=req.getParameter("mealprice");
	String RestaurantId=req.getParameter("RestaurantId");
	Statement stmt =con.createStatement();
	String query = "select * from Mealdetails where mealid = '"+mealid+"' and mealname='"+mealname+"' and RestaurantId='"+RestaurantId+"'" ;
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	if(rs.next())
	{
	
		String queryInsert="delete from Mealdetails where mealid = ? and mealname= ? and RestaurantId= ? "; 
		PreparedStatement pstmt= con.prepareStatement(queryInsert);
		pstmt.setString(1,mealid);
		pstmt.setString(2,mealname);
		pstmt.setString(3,RestaurantId);
		
		int i =pstmt.executeUpdate();
		if(i>=1)
		{
			map.put("message","your items Deleted sucessfully");
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

