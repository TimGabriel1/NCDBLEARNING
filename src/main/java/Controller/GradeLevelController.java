package Controller;

import DAO.GradeLevelDAO;
import DAO.GradeLevelDAOImpl;
import Model.GradeLevel;
import Util.JSONUtil;
import Util.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/gradelevels")
public class GradeLevelController extends HttpServlet {

    private final GradeLevelDAO gradeLevelDAO;
    private final JSONUtil jsonUtil;
    public GradeLevelController(){

        gradeLevelDAO = new GradeLevelDAOImpl();
        jsonUtil = new JSONUtil();

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ArrayList<GradeLevel> gradeLevels;
        GradeLevel gradeLevel;

        try{
            if(  req.getParameterMap().containsKey("id"))
            {
                String id =req.getParameter("id");
                gradeLevel = gradeLevelDAO.getGradeLevel(Integer.parseInt(id));
                jsonUtil.sendAsJson(resp, gradeLevel);
            }
            if(req.getParameterMap().isEmpty()){
                gradeLevels = (ArrayList<GradeLevel>) gradeLevelDAO.getAllGradeLevels();
                jsonUtil.sendAsJson(resp, gradeLevels);

            }

        }catch( Exception e){
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        GradeLevel gradeLevel = new GradeLevel();
        try {
           
            gradeLevel.setLabel(ServletUtil.getField(req, "label",true));
            gradeLevel.setKey(ServletUtil.getField(req, "key", true));
            gradeLevel.setName(ServletUtil.getField(req, "name", true));
            if(gradeLevelDAO.saveGradeLevel(gradeLevel)){
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
        GradeLevel gradeLevel = new GradeLevel();
        try {

            gradeLevel.setId(Integer.parseInt(ServletUtil.getField(req, "id", true)));
            gradeLevel.setLabel(ServletUtil.getField(req, "label",true));
            gradeLevel.setKey(ServletUtil.getField(req, "key", true));
            gradeLevel.setName(ServletUtil.getField(req, "name", true));
            if(gradeLevelDAO.updateGradeLevel(gradeLevel)){
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

            if(gradeLevelDAO.deleteGradeLevel(id)){
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
