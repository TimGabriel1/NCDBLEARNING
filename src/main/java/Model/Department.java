package Model;

import Enums.DepartmentType;

public class Department {
    private int id;
    private String name;
    private String label;
    private String code;
    private int parent;
    private DepartmentType type;

    public Department(int id, String name, String label, String code, int parent, DepartmentType type) {
        this.id = id;
        this.name = name;
        this.label = label;
        this.code = code;
        this.parent = parent;
        this.type = type;
    }

    public Department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public DepartmentType getType() {
        return type;
    }

    public void setType(DepartmentType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", code='" + code + '\'' +
                ", parent=" + parent +
                ", type=" + type +
                '}';
    }
}


