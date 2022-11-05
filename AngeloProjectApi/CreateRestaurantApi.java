@PostMapping("/CreateRestaurant")
public Map Restaurant(HttpServletRequest req)throws ClassNotFoundException, SQLException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String Restaurantid =req.getParameter("Restaurantid");
	String Restaurantname = req.getParameter("Restaurantname");
	String description =req.getParameter("description");
	String typeOfFood=req.getParameter("typeOfFood");
	Statement stmt =con.createStatement();
	String query = "select * from Restaurantdetails where Restaurantname='"+Restaurantname+"'";
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	if(rs.next())
	{
		map.put("message"," Restuarant name already exist!");
		return map;
	}
	else
	{
		  String queryInsert = "insert into Restaurantdetails(Restaurantid,Restaurantname,description,typeOfFood)values(?,?,?,?)";
		  PreparedStatement pstmt= con.prepareStatement(queryInsert);
		  pstmt.setString(1,Restaurantid);
		  pstmt.setString(2,Restaurantname);
		  pstmt.setString(3,description);
		  pstmt.setString(4,typeOfFood);
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

