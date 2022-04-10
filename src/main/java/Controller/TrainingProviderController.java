package Controller;

import DAO.TrainingProviderDAO;
import DAO.TrainingProviderDAOImpl;
import Model.TrainingProvider;
import Util.JSONUtil;
import Util.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/trainingController")
public class TrainingProviderController extends HttpServlet
    
{
    private final TrainingProviderDAO trainingProviderDAO;
    private final JSONUtil jsonUtil;
    public TrainingProviderController(){

        trainingProviderDAO = new TrainingProviderDAOImpl();
        jsonUtil = new JSONUtil();

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ArrayList<TrainingProvider> trainingProviders;
        TrainingProvider trainingProvider;
        //Check if Parameter as ID
        try{
            if(  req.getParameterMap().containsKey("id"))
            {
                String id =req.getParameter("id");
                trainingProvider = trainingProviderDAO.getTrainingProvider(Integer.parseInt(id));
                jsonUtil.sendAsJson(resp, trainingProvider);
            }
            if(req.getParameterMap().isEmpty()){
                System.out.println("No parameter");
                trainingProviders = (ArrayList<TrainingProvider>) trainingProviderDAO.getAllTrainingProviders();
                System.out.println("All TrainingProviders");
                jsonUtil.sendAsJson(resp, trainingProviders);

            }

        }catch( Exception e){
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TrainingProvider trainingProvider = new TrainingProvider();
        try {
            trainingProvider.setName(ServletUtil.getField(req, "name",true));
            trainingProvider.setAddress(ServletUtil.getField(req, "address", true));
            trainingProvider.setEmail(req.getParameter("email"));
            trainingProvider.setPhoneNumber(req.getParameter("phoneNumber"));
            trainingProvider.setWebsite(req.getParameter("website"));
            if(trainingProviderDAO.saveTrainingProvider(trainingProvider)){
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
        TrainingProvider trainingProvider = new TrainingProvider();
        try {

            trainingProvider.setId(Integer.parseInt(ServletUtil.getField(req, "id", true)));
            trainingProvider.setName(ServletUtil.getField(req, "name",true));
            trainingProvider.setAddress(ServletUtil.getField(req, "address", true));
            trainingProvider.setEmail(req.getParameter("email"));
            trainingProvider.setPhoneNumber(req.getParameter("phoneNumber"));
            trainingProvider.setWebsite(req.getParameter("website"));
            if(trainingProviderDAO.updateTrainingProvider(trainingProvider)){
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

            if(trainingProviderDAO.deleteTrainingProvider(id)){
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
