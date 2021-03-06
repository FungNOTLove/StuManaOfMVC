package cn.fung.service.impl;

import cn.fung.dao.StudentDao;
import cn.fung.dao.impl.StudentDaoImpl;
import cn.fung.domain.PageBean;
import cn.fung.domain.Student;
import cn.fung.service.StudentService;

import java.sql.SQLException;
import java.util.List;

/**
 * 这是学生业务实现
 * @author Fung
 */

public class StudentServiceImpl implements StudentService {
    @Override
    public List<Student> findAll() throws SQLException {
        StudentDao dao = new StudentDaoImpl();
        return dao.findAll();
    }

    @Override
    public Student findStudentById(int sid) throws SQLException {
        StudentDao dao = new StudentDaoImpl();
        return dao.findStudentById(sid);
    }

    @Override
    public List<Student> searchStudent(String sname, String gender) throws SQLException {
        return new StudentDaoImpl().searchStudent(sname, gender);
    }

    @Override
    public void insert(Student student) throws SQLException {
        StudentDao dao = new StudentDaoImpl();
        dao.insert(student);
    }

    @Override
    public void delete(int sid) throws SQLException {
        StudentDao dao = new StudentDaoImpl();
        dao.delete(sid);
    }

    @Override
    public void update(Student student) throws SQLException {
        StudentDao dao = new StudentDaoImpl();
        dao.update(student);
    }

    @Override
    public PageBean findStudentByPage(int currentPage) throws SQLException {

        // 封装分页的该页数据
        PageBean<Student> pageBean = new PageBean<Student>();

        int pageSize = StudentDao.PAGE_SIZE;
        pageBean.setCurrentPage(currentPage);   //设置当前页
        pageBean.setPageSize(pageSize);     //设置每页显示多少数据

        StudentDao dao = new StudentDaoImpl();
        List<Student> list = dao.findStudentByPage(currentPage);
        pageBean.setList(list);     //设置这一页的学生数据

        // 总的记录数，总的页数。
        int count = dao.findCount();
        pageBean.setTotalSize(count);   //设置总的记录数
        // 200 ， 10 ==20   201 ， 10 = 21   201 % 10 == 0 ?201 / 10 :201 % 10 + 1
        //总页数
        pageBean.setTotalPage(count % pageSize == 0 ? count / pageSize :(count/pageSize)+1);
        return pageBean;
    }
}
