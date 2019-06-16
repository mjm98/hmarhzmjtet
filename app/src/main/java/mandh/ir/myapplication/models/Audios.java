package mandh.ir.myapplication.models;

public class Audios {
    String uri;
    String name;
    int id;

    public Audios(String uri, String name, int id) {
        this.uri = uri;
        this.name = name;
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
