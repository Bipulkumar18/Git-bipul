@GetMapping("api/Mealdetails/Restaurantname/{Restaurantid}")
public ArrayList Mealdetails(@PathVariable String Restaurantid) throws ClassNotFoundException, SQLException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	Statement stmt= con.createStatement();
	String query="Select*from Mealdetails where Restaurantid='"+Restaurantid+"'";
	ResultSet rs = stmt.executeQuery(query);
	HashMap map=new HashMap();
	int count=0;
	ArrayList list =new ArrayList();
	while(rs.next()) 
	{
		String q= (" meal_id: "+" "+rs.getString("mealid")+" "+"mealname : "+" "+rs.getString("mealname")+" "+ "description :"+" "+ rs.getString("description")+" " +"mealprice :"+rs.getString("mealprice")+" "+" RestaurantId :"+rs.getString("RestaurantId"));
	     list.add(q);
	     count++;
	   
	}
	if(count==0)
	{
		list.add("Oops! we could not understand what you mean, try rephrasing the query");
		return list;
	}
     return list;
}
