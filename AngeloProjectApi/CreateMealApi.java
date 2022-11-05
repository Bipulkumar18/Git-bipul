@PostMapping("/CreateMeal/SignUpUser")
public Map CreateMeal1(HttpServletRequest req)throws ClassNotFoundException, SQLException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String mealid = req.getParameter("mealid");
	String mealname =req.getParameter("mealname");
	String description =req.getParameter("description");
	String mealprice=req.getParameter("mealprice");
	String RestaurantId=req.getParameter("RestaurantId");
	Statement stmt =con.createStatement();
	String query = "select * from Mealdetails where mealname='"+mealname+"' and RestaurantId='"+RestaurantId+"'";
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	if(rs.next())
	{
		map.put("messgae", "this meal already exist in this restaurant");
		return map;
	}
	
	else
	{
		
	      String queryInsert = "insert into Mealdetails(mealid ,mealname,description,mealprice,RestaurantId)values(?,?,?,?,?)";
		  PreparedStatement pstmt= con.prepareStatement(queryInsert);
		  pstmt.setString(1,mealid);
		  pstmt.setString(2,mealname);
		  pstmt.setString(3,description);
		  pstmt.setString(4,mealprice);
		  pstmt.setString(5,RestaurantId);
		  int i =pstmt.executeUpdate();
		  if(i>=1)
		  {
			 map.put("message","your Meal is added successfully ");
			 return map;
		  }
		  else 
		  {
			 map.put("messgae","Something went wrong ");
			 return map;
		  }
	}
		
}
