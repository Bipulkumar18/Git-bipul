@PostMapping("/UpdateOrderMeal")
public Map UpdateMeal(HttpServletRequest req)throws ClassNotFoundException, SQLException, IOException
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
	
		String queryInsert="update Orderdetails set mealid=?,mealname=?,Quantity=?,mealprice=Quantity*?,RestaurantId=? where RestaurantId=?" ; 
		PreparedStatement pstmt= con.prepareStatement(queryInsert);
		pstmt.setString(1,mealid);
		pstmt.setString(2,mealname);
		pstmt.setString(3,Quantity);
		pstmt.setString(4,mealprice);
		pstmt.setString(5,RestaurantId);
		pstmt.setString(6,RestaurantId);
		
		int i =pstmt.executeUpdate();
		if(i>=1)
		{
			
			map.put("messgae","and its going to processing and you will get your order within 30 min");
			map.put("message","your items Updated sucessfully ");
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

