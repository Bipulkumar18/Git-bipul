@PostMapping("/CoachingDetails")
public ArrayList Coaching(HttpServletRequest req) throws SQLException, ClassNotFoundException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String Studentname=req.getParameter("Studentname");
	Statement stmt =con.createStatement();
	String query = "select * from Coaching where Studentname='"+Studentname+"'";
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	ArrayList list =new ArrayList();
	if(rs.next())
	{
		if(rs.getString("Studentname").equals(Studentname))
		{
			map.put("Roll_no", rs.getInt("Rollno"));
			map.put("Course ",rs.getString("course"));
			map.put("Date_Of_joining",rs.getDate("dateOfJoining"));
			map.put("Mobile_no ",rs.getString("Mobileno"));
			
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
		map.put("message","Sorry sir no data is there; first get  admission ");
		list.add(map);
		return list;
	}
}

