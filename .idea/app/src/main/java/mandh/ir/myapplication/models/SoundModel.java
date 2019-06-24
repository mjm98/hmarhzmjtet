package mandh.ir.myapplication.models;


public class SoundModel {

    private String title;
    private String genre;
    private int year;
    // State of the item
    private boolean expanded;

    public SoundModel(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }
}