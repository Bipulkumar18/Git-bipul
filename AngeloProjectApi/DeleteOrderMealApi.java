@PostMapping("/DeleteOrderMeal")
public Map DeleteOrderMeal(HttpServletRequest req)throws ClassNotFoundException, SQLException, IOException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String RestaurantId=req.getParameter("RestaurantId");
	String mealid = req.getParameter("mealid");
	String mealprice = req.getParameter("mealprice");
	String mealname =req.getParameter("mealname");
	String Quantity=req.getParameter("Quantity");
	
	Statement stmt =con.createStatement();
	String query = "select * from Mealdetails where mealname ='"+mealname+"' and  RestaurantId='"+RestaurantId+"' and mealprice = '"+mealprice+"'" ;
	ResultSet rs =stmt.executeQuery(query);

	HashMap map= new HashMap();
	ArrayList list =new ArrayList();
	if(rs.next())
	{
	
		String queryInsert="Delete from Orderdetails where mealname =? and  RestaurantId=? and mealprice = Quantity*?"; 
		PreparedStatement pstmt= con.prepareStatement(queryInsert);
		pstmt.setString(1,mealname);
		pstmt.setString(2,RestaurantId);
		pstmt.setString(3,mealprice);
		
		
		int i =pstmt.executeUpdate();
		if(i>=1)
		{
			
			map.put("messgae","ORDER DELETED");
			map.put("message","thank you for using it  ");
			list.add(map);
			return map;
		}
		else 
		{
			map.put("message","Something went wrong please try to login");
			return map;
		}
	}
	return map;
}

