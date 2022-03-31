package Model;

public class Location {
    private int id;
    private String name;
    private String label;
    private String address;

    public Location() {
    }

    public Location(int id, String name, String label, String address) {
        this.id = id;
        this.name = name;
        this.label = label;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}