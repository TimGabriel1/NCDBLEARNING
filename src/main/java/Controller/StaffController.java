package Controller;

import DAO.StaffDAO;
import DAO.StaffDAOImpl;
import Model.Staff;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/staff")
public class StaffController extends HttpServlet {
    private StaffDAO staffDAO;
    Gson gson;

    public StaffController(){
        staffDAO = new StaffDAOImpl();
        gson = new Gson();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Staff> staffList = staffDAO.getAllStaff();

            System.out.println(gson.toJson(staffList));
           Staff back = new Gson().fromJson(new Gson().toJson(staffList), Staff.class);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
