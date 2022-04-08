package Controller;

import DAO.DepartmentDAO;
import DAO.DepartmentDAOImpl;
import Enums.DepartmentType;
import Model.Department;
import Util.JSONUtil;
import Util.ServletUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/department")
public class DepartmentController extends HttpServlet {


    private DepartmentDAO departmentDAO;
    private final JSONUtil jsonUtil;

    public DepartmentController(){
        departmentDAO = new DepartmentDAOImpl();
        jsonUtil = new JSONUtil();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Department> departments;
        Department department;
        //Check if Parameter as ID
        try{
            if(  req.getParameterMap().containsKey("id"))
            {
                String id =req.getParameter("id");
                department = departmentDAO.getDepartment(Integer.parseInt(id));
                jsonUtil.sendAsJson(resp, department);
            }
            if(req.getParameterMap().isEmpty()){
                System.out.println("No parameter");
                departments = (ArrayList<Department>) departmentDAO.getAllDepartment();
                System.out.println("All Departments");
                jsonUtil.sendAsJson(resp, departments);

            }

        }catch( Exception e){
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }    
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     Department department = new Department();
        try {
            createDepartmentObject(req, department);

            if(departmentDAO.saveDepartment(department)){
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_CREATED);
                System.out.println("Saved");
            }else{
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(422);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = new Department();
        try {
            department.setId(Integer.parseInt(ServletUtil.getField(req, "id", true)));
            createDepartmentObject(req, department);

            if(departmentDAO.updateDepartment(department)){
                resp.setContentType("application/json");
                resp.setStatus(HttpServletResponse.SC_OK);

            }else{
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(422);
        }
    }

    private void createDepartmentObject(HttpServletRequest req, Department department) throws Exception {
        department.setName(ServletUtil.getField(req, "name", true));
        department.setLabel(ServletUtil.getField(req, "label", true));
        department.setCode(ServletUtil.getField(req, "code", true ));
        department.setParent(Integer.parseInt(ServletUtil.getField(req, "parent", true)));
        department.setType(DepartmentType.valueOf(ServletUtil.getField(req, "type", true).toUpperCase()));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(ServletUtil.getField(req, "id", true));

            if(departmentDAO.deleteDepartment(id)){
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
