@PostMapping("/updateRestaurant")
public Map updateRestaurant(HttpServletRequest req)throws ClassNotFoundException, SQLException, IOException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String Restaurantid =req.getParameter("Restaurantid");
	String Restaurantname = req.getParameter("Restaurantname");
	String description =req.getParameter("description");
	String typeOfFood=req.getParameter("typeOfFood");
	Statement stmt =con.createStatement();
	String query = "select * from Restaurantdetails where Restaurantname = '"+Restaurantname+"' and Restaurantid='"+Restaurantid+"'" ;
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	if(rs.next())
	{
	
		String queryInsert="update Restaurantdetails set Restaurantid=?,description=?,typeOfFood=? where Restaurantname= ? "; 
		PreparedStatement pstmt= con.prepareStatement(queryInsert);
		pstmt.setString(1,Restaurantid);
		pstmt.setString(2,typeOfFood);
		pstmt.setString(3,description);
		pstmt.setString(4,Restaurantname);
		
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
	
