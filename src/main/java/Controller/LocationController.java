package Controller;

import DAO.LocationDAO;
import DAO.LocationDAOImpl;
import Model.Location;
import Util.JSONUtil;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/location")
public class LocationController extends HttpServlet {
    private LocationDAO locationDAO;
    private JSONUtil jsonUtil;
    public LocationController(){

        locationDAO = new LocationDAOImpl();
        jsonUtil = new JSONUtil();

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("Locations");
        ArrayList<Location> locations = null;
        Location location = null;
        String pathInfo = req.getPathInfo();
        String id =req.getParameter("id");


        try {
            if (id != null) {
                location = locationDAO.getLocation(Integer.parseInt(id));
                if (location != null) {
                    System.out.println("id = " + id);
                    jsonUtil.sendAsJson(resp, location);
                } else{
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {

                if (pathInfo == null || pathInfo.equals("/")) {

                    locations = (ArrayList<Location>) locationDAO.getAllLocation();
                    System.out.println("All Locations");
                    jsonUtil.sendAsJson(resp, locations);

                }
            }
        }

        catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
