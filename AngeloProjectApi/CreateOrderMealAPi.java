@PostMapping("/CreateOrderMeal")
public Map OrderMeal(HttpServletRequest req)throws ClassNotFoundException, SQLException, IOException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String RestaurantId=req.getParameter("RestaurantId");
	String mealid = req.getParameter("mealid");
	String mealprice = req.getParameter("mealprice");
	String mealname =req.getParameter("mealname");
	String Quantity=req.getParameter("Quantity");
	String userid = req.getParameter("userid");
	String Itemsid= req.getParameter("Itemsid");
	
	Statement stmt =con.createStatement();
	String query = "select * from Mealdetails where mealname ='"+mealname+"' and  RestaurantId='"+RestaurantId+"' and mealprice = '"+mealprice+"'" ;
	ResultSet rs =stmt.executeQuery(query);
	int OrderId=0;

	HashMap map= new HashMap();
	ArrayList list =new ArrayList();
	if(rs.next())
	{
	
		String queryInsert="insert into Orderdetails(Itemsid,userid,mealid,mealname,Quantity,mealprice,RestaurantId )values(?,?,?,?,?,?,?)" ; 
		PreparedStatement pstmt= con.prepareStatement(queryInsert);
		pstmt.setString(1,Itemsid);
		pstmt.setString(2,userid);
		pstmt.setString(3,mealid);
		pstmt.setString(4,mealname);
		pstmt.setString(5,Quantity);
		pstmt.setString(6,mealprice);
		pstmt.setString(7,RestaurantId);
		OrderId++;
		int i =pstmt.executeUpdate();
		if(i>=1)
		{
			
			map.put("message","your item is Added sucessfully ");
			map.put("messgae"," It is going to processing and you will get your order within 30 min");
			map.put("OrderId",rs.getString(OrderId));
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


