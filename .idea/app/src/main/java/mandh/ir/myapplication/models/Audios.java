package mandh.ir.myapplication.models;

public class Audios {
    String uri;
    String name;
    int id;
    String duration;


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Audios(String uri, String name, int id,String duration) {
        this.uri = uri;
        this.name = name;
        this.id = id;
        this.duration=duration;
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
