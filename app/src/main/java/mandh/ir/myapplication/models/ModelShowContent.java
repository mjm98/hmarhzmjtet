package mandh.ir.myapplication.models;

public class ModelShowContent {

    String number;
    String name;
    String iconUri;

    public ModelShowContent(String number,String name) {
        this.number = number;
        this.name=name;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
    public String getImageUrl(){
        return "";
    }
}
