package StudentServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.google.gson.Gson;

@WebServlet("/addStudent")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentAddServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			

PrintWriter out = response.getWriter();
		
response.setContentType("application/json");
response.setCharacterEncoding("UTF-8");

		int id = Integer.parseInt(request.getParameter("id")); 
		String firstname = request.getParameter("firstname"); 
		String lastname = request.getParameter("lastname"); 
		String streetaddress = request.getParameter("streetaddress"); 
		String postcode = request.getParameter("postcode"); 
		String postoffice = request.getParameter("postoffice"); 
				
		Student st = new Student() ;
		
		st.setId(id) ;
		st.setName(firstname) ;
		st.setLastname(lastname) ;
		st.setStreetAddress(streetaddress);
		st.setPostCode(postcode) ;
		st.setPostOffice(postoffice) ;
		
		StudentDAO studentD = new StudentDAO();
		
		int errorCode = studentD.insertStudent(st); 
		
		
		Gson gson = new Gson();
		
		String json = gson.toJson(new Status(errorCode)); 
		out.print(json);
	}
	
	

}
