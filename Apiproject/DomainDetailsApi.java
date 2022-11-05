@PostMapping("/DomainSearch")
public ArrayList Domain(HttpServletRequest req) throws SQLException, ClassNotFoundException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String Domain =req.getParameter("kya_chaiye");
	Statement stmt =con.createStatement();
	String query = " Select * from bipul.DomainSearch where Syntax ='"+Domain+"'";
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	ArrayList list=new ArrayList();
	if(rs.next())
	{
		map.put("Domian_name", rs.getString("Domain_name"));
		map.put("Status", rs.getString("Status"));
		map.put("created_on", rs.getString("created_on"));
		map.put("Registrant", rs.getString("Registrant"));
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

