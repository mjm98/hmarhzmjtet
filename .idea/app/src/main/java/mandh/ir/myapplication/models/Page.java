package mandh.ir.myapplication.models;

import java.util.ArrayList;

public class Page implements InformationofBook,VideosAndAudios{
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


    @Override
    public int getNumberOfPics() {
        return 3;
    }

    @Override
    public int getNumberOfVoices() {
        return voices.size();
    }

    @Override
    public int getNumberOfVideos() {
        return videos.size();
    }

    @Override
    public int getNumberOfFiles() {
        return 7;
    }

    @Override
    public int getNumberOf3d() {
        return 8;
    }

    @Override
    public int getNumberOfdocs() {
        return 6;
    }
}
