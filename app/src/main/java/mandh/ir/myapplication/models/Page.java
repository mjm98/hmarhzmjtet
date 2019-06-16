package mandh.ir.myapplication.models;

import java.util.ArrayList;

public class Page {
    int pageNumber;
    ArrayList<Integer> imgs;
    ArrayList<Audios> voices;
    ArrayList<Videos> videos;

    public Page(int pageNumber, ArrayList<Integer> imgs, ArrayList<Audios> voices, ArrayList<Videos> videos) {

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

    public ArrayList<Audios> getVoices() {
        return voices;
    }

    public ArrayList<Videos> getVideos() {
        return videos;
    }


}
