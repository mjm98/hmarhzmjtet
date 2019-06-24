package mandh.ir.myapplication.models;

public class Pics {

    String name;
    String des;
    String imgUri;

    public Pics(String name, String des, String imgUri) {
        this.name = name;
        this.des = des;
        this.imgUri = imgUri;
    }

    public String getName() {
        return name;
    }

    public String getDes() {
        return des;
    }

    public String getImgUri() {
        return imgUri;
    }
}
