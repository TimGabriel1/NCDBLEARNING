package Controller;

import DAO.CourseDAOImpl;
import DAO.TrainingMajorDAOImpl;
import DAO.TrainingProviderDAOImpl;
import Enums.TrainingSponsor;
import Model.TrainingMajor;
import Model.TrainingMajor;
import Model.TrainingProvider;
import Util.JSONUtil;
import Util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Locale;

@WebServlet("/training-major")
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
            trainingMajor.setStartDate(Date.valueOf(ServletUtil.getField(req, "start-date",true)));
            trainingMajor.setEndDate(Date.valueOf(ServletUtil.getField(req, "end-date",true)));
            trainingMajor.setTrainingProvider(new TrainingProviderDAOImpl().getTrainingProvider(Integer.parseInt(ServletUtil.getField(req, "provider", true))));
            trainingMajor.setLocation(ServletUtil.getField(req, "location", true));
            trainingMajor.setCertificate(ServletUtil.getField(req, "certificate", true));
            trainingMajor.setActive(Boolean.parseBoolean(ServletUtil.getField(req,"active", true)));
            trainingMajor.setCourse(new CourseDAOImpl().getCourse(Integer.parseInt(ServletUtil.getField(req, "course", true))));
            trainingMajor.setTrainingSponsor(TrainingSponsor.valueOf(ServletUtil.getField(req, "sponsor", true).toUpperCase()));
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

//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        TrainingMajor trainingMajor = new TrainingMajor();
//        try {
//
//            trainingMajor.setId(Integer.parseInt(ServletUtil.getField(req, "id", true)));
//            trainingMajor.setLabel(ServletUtil.getField(req, "label",true));
//            trainingMajor.setAddress(ServletUtil.getField(req, "address", true));
//            trainingMajor.setName(ServletUtil.getField(req, "name", true));
//            if(trainingMajorDAO.updateTrainingMajor(trainingMajor)){
//                resp.setContentType("application/json");
//                resp.setStatus(HttpServletResponse.SC_OK);
//                System.out.println("Record Updated");
//            }else{
//                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            resp.sendError(422);
//        }
//    }

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
