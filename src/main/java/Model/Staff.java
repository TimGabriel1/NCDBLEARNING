package Model;

import Enums.StaffStatus;
import Enums.StaffType;

import java.util.List;

public class Staff {
    private int id;
    private String firstName;
    private String middleName;
    private String surnName;
    private String staffNo;
    private Department department;
    private Location location;
    private GradeLevel gradeLevel;
    private StaffType type;
    private StaffStatus status;
    private List<Role> roles;
    private boolean isActive;

    public Staff() {
    }

    public Staff(int id, String firstName, String middleName, String surnName, String staffNo, Department department, Location location, GradeLevel gradeLevel, StaffType type, StaffStatus status, List<Role> roles, boolean isActive) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surnName = surnName;
        this.staffNo = staffNo;
        this.department = department;
        this.location = location;
        this.gradeLevel = gradeLevel;
        this.type = type;
        this.status = status;
        this.roles = roles;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSurnName() {
        return surnName;
    }

    public void setSurnName(String surnName) {
        this.surnName = surnName;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public GradeLevel getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(GradeLevel gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public StaffType getType() {
        return type;
    }

    public void setType(StaffType type) {
        this.type = type;
    }

    public StaffStatus getStatus() {
        return status;
    }

    public void setStatus(StaffStatus status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return this.isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", surnName='" + surnName + '\'' +
                ", staffNo='" + staffNo + '\'' +
                ", department=" + department +
                ", location=" + location +
                ", gradeLevel=" + gradeLevel +
                ", type=" + type +
                ", status=" + status +
                ", roles=" + roles +
                ", isActive=" + isActive +
                '}';
    }
}