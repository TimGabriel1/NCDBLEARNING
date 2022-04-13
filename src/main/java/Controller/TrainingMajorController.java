package Controller;

import DAO.TrainingMajorDAOImpl;
import Model.TrainingMajor;
import Util.JSONUtil;
import Util.ServletUtil;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/training-majors")
public class TrainingMajorController extends HttpServlet {

    private final TrainingMajorDAOImpl trainingMajorDAO;
    private final JSONUtil jsonUtil;

    public TrainingMajorController(){
        trainingMajorDAO = new TrainingMajorDAOImpl();
        jsonUtil = new JSONUtil();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ArrayList<TrainingMajor> trainingMajors;
        TrainingMajor trainingMajor;
        //Check if Parameter as ID
        try{
            if(  req.getParameterMap().containsKey("id"))
            {
                String id =req.getParameter("id");
                trainingMajor = trainingMajorDAO.getTrainingMajor(Integer.parseInt(id));
                jsonUtil.sendAsJson(resp, trainingMajor);
            }
            if(req.getParameterMap().isEmpty()){
                System.out.println("No parameter");
                trainingMajors = (ArrayList<TrainingMajor>) trainingMajorDAO.getAllTrainingMajors();
                System.out.println("All TrainingMajors");
                jsonUtil.sendAsJson(resp, trainingMajors);

            }

        }catch( Exception e){
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TrainingMajor trainingMajor = new TrainingMajor();
        try {
            setTrainingMajorObject(req, trainingMajor);
            if(trainingMajorDAO.saveTrainingMajor(trainingMajor)){
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
        TrainingMajor trainingMajor = new TrainingMajor();
        try {

            trainingMajor.setId(Integer.parseInt(ServletUtil.getField(req, "id", true)));
            setTrainingMajorObject(req, trainingMajor);
            if(trainingMajorDAO.updateTrainingMajor(trainingMajor)){
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

    private void setTrainingMajorObject(HttpServletRequest req, TrainingMajor trainingMajor) throws Exception {
        trainingMajor.setActive(Boolean.parseBoolean(ServletUtil.getField(req, "active", true)));
        trainingMajor.setName(ServletUtil.getField(req, "name", true));
        trainingMajor.setLabel(ServletUtil.getField(req, "label", true));

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(ServletUtil.getField(req, "id", true));

            if(trainingMajorDAO.deleteTrainingMajor(id)){
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
