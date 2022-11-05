@PostMapping("/forgotpassword1")
public Map updateuser2(HttpServletRequest req)throws ClassNotFoundException, SQLException, IOException
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bipul","root","Bipultk@1");
	String email =req.getParameter("email");
	String password=req.getParameter("password");
	String Newpassword=req.getParameter("Newpassword");
	String Confirmpassword=req.getParameter("Confirmpassword");
	Statement stmt =con.createStatement();
	String query = "select * from userdetails where email ='"+email+"' or password ='"+password+"'";
	ResultSet rs =stmt.executeQuery(query);
	HashMap map= new HashMap();
	if(rs.next())
	{
		if(rs.getString("email").equals(email))
		{
			if(rs.getString("password").equals(password))
			{
				String query1="update userdetails set password = ?  where email ='"+email+"'";
				PreparedStatement pstmt=con.prepareStatement(query1);
				pstmt.setString(1,Newpassword);
				if(Newpassword.equals(Confirmpassword))
				{
					int i= pstmt.executeUpdate();
					if(i>=1)
						map.put("message","password change sucessfully");
					return map;
				}
				else
					map.put("message","New password and confirm password not Match");
				 return map;
			}
			else 
				map.put("message","your password is wrong");
				return map;
		}
		else 
			map.put("message","your email id is wrong");
		return map;
	}
//	else
//	{
//		map.put("messgae","this email is not registered");
//		return map;
//	}
	
	return map;
}
