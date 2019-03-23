package cn.fung.servlet;

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

public class StudentListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //1.查询出来所有的学生
            StudentService service = new StudentServiceImpl();
            List<Student> list = service.findAll();

            //2.先把数据存储到作用域中
            request.setAttribute("list", list);

            //3.跳转页面
            request.getRequestDispatcher("list.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
