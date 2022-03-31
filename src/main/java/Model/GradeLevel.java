package Model;

public class GradeLevel {
    private int id;
    private String name;
    private String label;
    private String key;

    public GradeLevel() {
    }

    public GradeLevel(int id, String name, String label, String key) {
        this.id = id;
        this.name = name;
        this.label = label;
        this.key = key;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "GradeLevel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}