@PostMapping("/AppointmentDetails")
public ArrayList Appointment(HttpServletRequest req) throws SQLException, ClassNotFoundException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String firstname=req.getParameter("firstname");
	String lastname=req.getParameter("lastname");
	Statement stmt =con.createStatement();
	String query = "select * from appointment where firstname='"+firstname+"'";
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	ArrayList list=new ArrayList();
	if(rs.next())
	{
		if(rs.getString("lastname").equals(lastname))
		{
			map.put("time", rs.getTime("time"));
			map.put("Room_no", rs.getInt("room"));
//			String i= ("yo time is"+" "+ rs.getTime("time")+" and "+" "+"your room number"+" "+  rs.getInt("room"));
		    list.add(map);
			
			return list;
		}
		else
		{
			map.put("message","Sorry sir yo dont have any appointment");
			list.add(map);
			return list;
		}
	}
	else 
	{
		map.put("message","sir please make an appointment first");
		list.add(map);
		return list;
	}
}


