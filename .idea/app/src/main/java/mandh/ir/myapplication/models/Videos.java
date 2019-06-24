package mandh.ir.myapplication.models;

public class Videos {
    String uri;
    String name;
    String discription;
    int id;

    public Videos(String uri, String name, String discription, int id) {
        this.uri = uri;
        this.name = name;
        this.discription = discription;
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public String getDiscription() {
        return discription;
    }

    public int getId() {
        return id;
    }
}
