package StudentServlet;

import java.io.IOException;


import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/students")
public class StudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public StudentListServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	PrintWriter out = response.getWriter();
	
	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8") ;
	
	StudentDAO daoS = new StudentDAO() ;
	List <Student> studentList = daoS.getAllStudents() ;
	
	Gson gson = new Gson() ;
	String json = gson.toJson(studentList) ;
	
	out.print(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
