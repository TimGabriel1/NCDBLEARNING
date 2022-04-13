package Controller;

import DAO.TrainingDAO;
import DAO.TrainingDAOImpl;
import DAO.TrainingMajorDAOImpl;
import Model.Training;
import Util.JSONUtil;
import Util.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/training")
public class TrainingController extends HttpServlet {
    private final TrainingDAO trainingDAO;
    private final JSONUtil jsonUtil;
    public TrainingController(){

        trainingDAO = new TrainingDAOImpl();
        jsonUtil = new JSONUtil();

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ArrayList<Training> trainings;
        Training training;
        //Check if Parameter as ID
        try{
            if(  req.getParameterMap().containsKey("id"))
            {
                String id =req.getParameter("id");
                training = trainingDAO.getTraining(Integer.parseInt(id));
                jsonUtil.sendAsJson(resp, training);
            }
            if(req.getParameterMap().isEmpty()){
                System.out.println("No parameter");
                trainings = (ArrayList<Training>) trainingDAO.getAllTrainings();
                System.out.println("All Trainings");
                jsonUtil.sendAsJson(resp, trainings);

            }

        }catch( Exception e){
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Training training = new Training();
        try {
            training.setLabel(ServletUtil.getField(req, "label",true));
            training.setTitle(ServletUtil.getField(req, "title", true));
            training.setMajor(new TrainingMajorDAOImpl().getTrainingMajor(Integer.parseInt(ServletUtil.getField(req, "major", true))));
            if(trainingDAO.saveTraining(training)){
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
        Training training = new Training();
        try {

            training.setId(Integer.parseInt(ServletUtil.getField(req, "id", true)));
            training.setLabel(ServletUtil.getField(req, "label",true));
            training.setMajor(new TrainingMajorDAOImpl().getTrainingMajor(Integer.parseInt(ServletUtil.getField(req, "major", true))));
            training.setTitle(ServletUtil.getField(req, "title", true));
            if(trainingDAO.updateTraining(training)){
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

            if(trainingDAO.deleteTraining(id)){
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
