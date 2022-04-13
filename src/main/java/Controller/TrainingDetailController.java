package Controller;

import DAO.*;
import Enums.*;
import Model.TrainingDetail;
import Util.JSONUtil;
import Util.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet("/training-details")
public class TrainingDetailController  extends HttpServlet {
    private final TrainingDetailsDAO trainingsDetailDAO;
    private final JSONUtil jsonUtil;

    public TrainingDetailController() {
        trainingsDetailDAO = new TrainingDetailDAOImpl();
        jsonUtil = new JSONUtil();

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ArrayList<TrainingDetail> trainingDetails;
        TrainingDetail trainingDetail;
        //Check if Parameter as ID
        try{
            if(  req.getParameterMap().containsKey("id"))
            {
                String id =req.getParameter("id");
                trainingDetail = trainingsDetailDAO.getTrainingDetail(Integer.parseInt(id));
                jsonUtil.sendAsJson(resp, trainingDetail);
            }
            if(req.getParameterMap().isEmpty()){
                System.out.println("No parameter");
                trainingDetails = (ArrayList<TrainingDetail>) trainingsDetailDAO.getAllTrainingDetails();
                System.out.println("All TrainingDetails");
                jsonUtil.sendAsJson(resp, trainingDetails);

            }

        }catch( Exception e){
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TrainingDetail trainingDetail = new TrainingDetail();
        try {

            setTrainingDetailObject(req, trainingDetail);
            if(trainingsDetailDAO.saveTrainingDetail(trainingDetail)){
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

    private void setTrainingDetailObject(HttpServletRequest req, TrainingDetail trainingDetail) throws Exception {
        trainingDetail.setTraining(new TrainingDAOImpl().getTraining(Integer.parseInt(ServletUtil.getField(req, "training", true))));
        trainingDetail.setQualification(Qualification.valueOf(ServletUtil.getField(req, "qualification", true).toUpperCase()));
        trainingDetail.setCourse(new CourseDAOImpl().getCourse(Integer.parseInt(ServletUtil.getField(req, "course", true))));
        trainingDetail.setProvider(new TrainingProviderDAOImpl().getTrainingProvider(Integer.parseInt(ServletUtil.getField(req, "provider", true))));
        trainingDetail.setResidence(TrainingResidence.valueOf(ServletUtil.getField(req, "residence", true).toUpperCase()));
        trainingDetail.setLocation(new LocationDAOImpl().getLocation(Integer.parseInt(ServletUtil.getField(req, "location", true))));
        trainingDetail.setStartDate(Date.valueOf(ServletUtil.getField(req, "start-date", true)));
        trainingDetail.setEndDate(Date.valueOf(ServletUtil.getField(req, "end-date", true)));
        trainingDetail.setSponsor(TrainingSponsor.valueOf(ServletUtil.getField(req, "sponsor", true)));
        trainingDetail.setCertificate(ServletUtil.getField(req, "certificate", true));
        trainingDetail.setStatus(TrainingStatus.valueOf(ServletUtil.getField(req, "status", true).toUpperCase()));
        trainingDetail.setAction(TrainingAction.valueOf(ServletUtil.getField(req, "action", true).toUpperCase()));
        trainingDetail.setCompleted(Boolean.parseBoolean(ServletUtil.getField(req, "completed", true)));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TrainingDetail trainingDetail = new TrainingDetail();
        try {

            trainingDetail.setId(Integer.parseInt(ServletUtil.getField(req, "id", true)));

            setTrainingDetailObject(req, trainingDetail);

            if(trainingsDetailDAO.updateTrainingDetail(trainingDetail)){
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

            if(trainingsDetailDAO.deleteTrainingDetail(id)){
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
