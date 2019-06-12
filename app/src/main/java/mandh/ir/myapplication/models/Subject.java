package mandh.ir.myapplication.models;

public class Subject {
    String title;
    String text;
    String date;
    int imageUrl;

    public int getImageUrl() {
        return imageUrl;
    }

    public Subject(String title, String text, int imageUrl) {
        this.imageUrl=imageUrl;
        this.title = title;
        this.text = text;

    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
