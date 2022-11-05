@PostMapping("/PropertySearch")
public ArrayList property(HttpServletRequest req)throws ClassNotFoundException, SQLException
{
	String House_type =req.getParameter("Dekho_Ankh_khol_kr");
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	
	Statement stmt= con.createStatement();
	String query="Select * from bipul.property where House_type ='"+House_type+"'";
	ResultSet rs = stmt.executeQuery(query);
	HashMap map=new HashMap();
	int count=0;
	ArrayList list =new ArrayList();
	while(rs.next()) 
	{
			String q= ("Owner: " +" "+rs.getString("Owner")+ "Address : " +" " +rs.getString("location")+" Near_By_Places: "+ " "+rs.getString("near_by_places")+ "Carpet size :"+" "+rs.getString("Carpet_area")+" price : "+" "+rs.getString("price"));
		    list.add(q);
		    count++;
		   
		}
		if(count==0)
		{
			list.add("Oops! we could not find your interest related building right now ! try next time ");
			return list;
		}
	     return list;
}

