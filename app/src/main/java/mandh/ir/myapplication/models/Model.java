package mandh.ir.myapplication.models;

/**
 * Created by Dell  5559 on 11/06/2019.
 */

public class Model {

    int id = 0;
    String name;
    String imageUrl = "";


    public Model(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Model(int id, String name ) {
        this.id = id;
        this.name = name;

    }

    public Model( String name, String imageUrl) {

        this.name = name;
        this.imageUrl = imageUrl;
    }

    public Model( String name ) {

        this.name = name;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageRecourse) {
        this.imageUrl = imageRecourse;
    }

    @Override
    public String toString() {
        return name;
    }
}
