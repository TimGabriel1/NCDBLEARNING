package Controller;

import DAO.CourseDAO;
import DAO.CourseDAOImpl;
import Enums.Qualification;
import Model.Course;
import Util.JSONUtil;
import Util.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/courses")
public class CourseController extends HttpServlet {

    private final CourseDAO courseDAO;
    private final JSONUtil jsonUtil;

    public CourseController() {
        courseDAO = new CourseDAOImpl();
        jsonUtil = new JSONUtil();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ArrayList<Course> courses;
        Course course;
        //Check if Parameter as ID
        try{
            if(  req.getParameterMap().containsKey("id"))
            {
                String id =req.getParameter("id");
                course = courseDAO.getCourse(Integer.parseInt(id));
                jsonUtil.sendAsJson(resp, course);
            }
            if(req.getParameterMap().isEmpty()){
                System.out.println("No parameter");
                courses = (ArrayList<Course>) courseDAO.getAllCourses();
                System.out.println("All Courses");
                jsonUtil.sendAsJson(resp, courses);

            }

        }catch( Exception e){
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Course course = new Course();
        try {
            course.setName(ServletUtil.getField(req, "name",true));
            course.setDescription(ServletUtil.getField(req, "description", true));
            course.setQualification(Qualification.valueOf(ServletUtil.getField(req, "qualification", true).toUpperCase()));
            if(courseDAO.saveCourse(course)){
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_CREATED);
                System.out.println("saved");
            }else{
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(422);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Course course = new Course();
        try {

            course.setId(Integer.parseInt(ServletUtil.getField(req, "id", true)));
            course.setDescription(ServletUtil.getField(req, "name", true));
            course.setName(ServletUtil.getField(req, "description", true));
            course.setQualification(Qualification.valueOf(ServletUtil.getField(req, "qualification", true).toUpperCase()));

            if(courseDAO.updateCourse(course)){
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_OK);
                System.out.println("Record Updated");
            }else{
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(422);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(ServletUtil.getField(req, "id", true));

            if(courseDAO.deleteCourse(id)){
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_OK);
                System.out.println("deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}

