package Model;

public class Role {
    private int id;
    private String name;
    private String label;
    private boolean isActive;

    public Role(int id, String name, String label, boolean isActive) {
        this.id = id;
        this.name = name;
        this.label = label;
        this.isActive = isActive;
    }

    public Role() {
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
