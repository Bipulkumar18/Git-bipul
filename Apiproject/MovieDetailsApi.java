@PostMapping("/MovieDetails")
public ArrayList Movie(HttpServletRequest req) throws SQLException, ClassNotFoundException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String Movie=req.getParameter("lelojankari");
	Statement stmt =con.createStatement();
	String query = " Select * from bipul.movies where Movies_name='"+Movie+"'";
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	ArrayList list=new ArrayList();
	if(rs.next())
	{
		map.put("Movies_name",rs.getString("Movies_name"));
		map.put("Release_date", rs.getString("Release_date"));
		map.put("Director",rs.getString("Director"));
		map.put("cast",rs.getString("cast"));
	    list.add(map);
		return list;
		
	}
	else 
	{
		map.put("message","Sorry sir no data is there: ");
		list.add(map);
		return list;
	}
}

