package cn.fung.servlet;

import cn.fung.domain.Student;
import cn.fung.service.StudentService;
import cn.fung.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 用于处理学生的添加请求
 * @author Fung
 */

public class AddServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        request.setCharacterEncoding("UTF-8");


        try {
            //1.获取客户端提交上来的数据
            String sname = request.getParameter("sname");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            String birthday = request.getParameter("birthday");
            String info = request.getParameter("info");
//        String hobby = request.getParameter("hobby");
            String [] h = request.getParameterValues("hobby");

            String hobby = Arrays.toString(h);
            hobby = hobby.substring(1, hobby.length()-1);

            //2.添加到数据库
            //string -- date
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birthday);

            Student student = new Student(sname, gender, phone, hobby, info, date);
            StudentService service = new StudentServiceImpl();
            service.insert(student);

            //3.跳转到列表页
            //再查一次数据库，然后再装到作用域，然后再跳转
            //这里是直接跳转到页面上，那么这个页面会重新翻译一次，上面的那个request的请求存放的数据是没有了
            //request.getRequestDispatcher("list.jsp").forward(request, response);

            //servlet除了能跳jsp之外，还能跳servlet
            request.getRequestDispatcher("StudentListServlet").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
