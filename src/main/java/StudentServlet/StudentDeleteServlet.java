package StudentServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/deleteStudent")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		int studentId = Integer.parseInt(request.getParameter("id"));
		StudentDAO st= new StudentDAO();
		
		int errorCode = st.deleteStudent(studentId); 
		
		Gson gson = new Gson();
		String json = gson.toJson(new Status(errorCode)); 
		out.print(json);
	}

}
