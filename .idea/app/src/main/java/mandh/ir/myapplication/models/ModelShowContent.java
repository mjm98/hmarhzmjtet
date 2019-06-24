package mandh.ir.myapplication.models;

public class ModelShowContent {

    String number;
    String name;
    int iconUri=0;

    public ModelShowContent(String number,String name,int iconUri) {
        this.number = number;
        this.name=name;
        this.iconUri=iconUri;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
    public int getImageUrl(){
        return iconUri ;
    }
}
