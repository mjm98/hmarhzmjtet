package mandh.ir.myapplication.models;

import java.util.ArrayList;

import mandh.ir.myapplication.R;

public class Book {
    int id;
    int imageurl;
    String title;
    String brief;
    ArrayList<Page> pages;
    int numberOfPages;
    int numberOfVoices;
    int numberOfVideos;
    int numberOfFiles;
    int numberOf3d;
    int numberOfdocs;

    public Book(int imageurl, String title,int id) {
        this.imageurl = imageurl;
        this.title = title;
        this.id=id;
    }

    public Book(int imageurl, String title, String brief, ArrayList<Page> pages,int id) {
        this.imageurl = imageurl;
        this.title = title;
        this.brief = brief;
        this.pages = pages;
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setNumberOfVoices(int numberOfVoices) {
        this.numberOfVoices = numberOfVoices;
    }

    public void setNumberOfVideos(int numberOfVideos) {
        this.numberOfVideos = numberOfVideos;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }

    public void setNumberOf3d(int numberOf3d) {
        this.numberOf3d = numberOf3d;
    }

    public void setNumberOfdocs(int numberOfdocs) {
        this.numberOfdocs = numberOfdocs;
    }

    public Book(int id, int imageurl, String title, String brief, int numberOfPages, int numberOfVoices, int numberOfVideos, int numberOfFiles, int numberOf3d, int numberOfdocs) {

        this.id = id;
        this.imageurl = imageurl;
        this.title = title;
        this.brief = brief;
        this.numberOfPages = numberOfPages;
        this.numberOfVoices = numberOfVoices;
        this.numberOfVideos = numberOfVideos;
        this.numberOfFiles = numberOfFiles;
        this.numberOf3d = numberOf3d;
        this.numberOfdocs = numberOfdocs;
    }

    public Book(int imageurl, String title, String brief, ArrayList<Page> pages, int numberOfPages, int numberOfVoices, int numberOfVideos, int numberOfFiles, int numberOf3d, int numberOfdocs, int id) {

        this.imageurl = imageurl;
        this.title = title;
        this.brief = brief;
        this.pages = pages;
        this.numberOfPages = numberOfPages;
        this.numberOfVoices = numberOfVoices;
        this.numberOfVideos = numberOfVideos;
        this.numberOfFiles = numberOfFiles;
        this.numberOf3d = numberOf3d;
        this.numberOfdocs = numberOfdocs;
        this.id=id;


    }


    public int getImageurl() {
        return imageurl;
    }

    public String getTitle() {
        return title;
    }

    public String getBrief() {
        return brief;
    }

    public ArrayList<Page> getPages() {
        return pages;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public int getNumberOfVoices() {
        return numberOfVoices;
    }

    public int getNumberOfVideos() {
        return numberOfVideos;
    }

    public int getNumberOfFiles() {
        return numberOfFiles;
    }

    public int getNumberOf3d() {
        return numberOf3d;
    }

    public int getNumberOfdocs() {
        return numberOfdocs;
    }





}
