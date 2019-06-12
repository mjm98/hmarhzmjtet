package mandh.ir.myapplication.models;

import java.util.ArrayList;

public class Page {
    int pageNumber;
    ArrayList<Integer> imgs;
    ArrayList<Integer> voices;
    ArrayList<Integer> videos;

    public Page(int pageNumber, ArrayList<Integer> imgs, ArrayList<Integer> voices, ArrayList<Integer> videos) {

        this.pageNumber = pageNumber;
        this.imgs = imgs;
        this.voices = voices;
        this.videos = videos;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public ArrayList<Integer> getImgs() {
        return imgs;
    }

    public ArrayList<Integer> getVoices() {
        return voices;
    }

    public ArrayList<Integer> getVideos() {
        return videos;
    }


}
