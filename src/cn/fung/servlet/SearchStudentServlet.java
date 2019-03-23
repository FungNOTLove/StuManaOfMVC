package cn.fung.servlet;
import cn.fung.dao.impl.StudentDaoImpl;
import cn.fung.domain.Student;
import cn.fung.service.StudentService;
import cn.fung.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class SearchStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            //1.取到了要查询的关键数据  姓名 性别
            String sname = request.getParameter("sname");
            String gender = request.getParameter("gender");

            //2.找service去查询
            StudentService service = new StudentServiceImpl();
            List<Student> list = service.searchStudent(sname, gender);

            System.out.println("list的大小是： "+ list.size());
            for (Student student : list){
                System.out.println("stu = "+student);
            }

            request.setAttribute("list", list);

            //3.跳转界面，列表界面
            request.getRequestDispatcher("list.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

