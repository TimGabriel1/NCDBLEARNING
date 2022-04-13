package Controller;

import DAO.LocationDAO;
import DAO.LocationDAOImpl;
import Model.Location;
import Util.JSONUtil;
import Util.ServletUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/locations")
public class LocationController extends HttpServlet {
    private final LocationDAO locationDAO;
    private final JSONUtil jsonUtil;
    public LocationController(){

        locationDAO = new LocationDAOImpl();
        jsonUtil = new JSONUtil();

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ArrayList<Location> locations;
        Location location;
    //Check if Parameter as ID
        try{
        if(  req.getParameterMap().containsKey("id"))
        {
            String id =req.getParameter("id");
            location = locationDAO.getLocation(Integer.parseInt(id));
                jsonUtil.sendAsJson(resp, location);
            }
        if(req.getParameterMap().isEmpty()){
            System.out.println("No parameter");
            locations = (ArrayList<Location>) locationDAO.getAllLocation();
            System.out.println("All Locations");
            jsonUtil.sendAsJson(resp, locations);

        }

        }catch( Exception e){
        e.printStackTrace();
        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Location location = new Location();
        try {
            location.setLabel(ServletUtil.getField(req, "label",true));
            location.setAddress(ServletUtil.getField(req, "address", true));
            location.setName(ServletUtil.getField(req, "name", true));
            if(locationDAO.saveLocation(location)){
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
        Location location = new Location();
        try {

            location.setId(Integer.parseInt(ServletUtil.getField(req, "id", true)));
            location.setLabel(ServletUtil.getField(req, "label",true));
            location.setAddress(ServletUtil.getField(req, "address", true));
            location.setName(ServletUtil.getField(req, "name", true));
            if(locationDAO.updateLocation(location)){
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

            if(locationDAO.deleteLocation(id)){
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
