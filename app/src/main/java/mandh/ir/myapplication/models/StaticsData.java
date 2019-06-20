package mandh.ir.myapplication.models;

import java.util.ArrayList;

import mandh.ir.myapplication.R;

public class StaticsData {

    public static ArrayList<Book> books=new ArrayList<>();

    public static void setBooks(ArrayList<Book> books) {
        StaticsData.books = books;
    }

    public static ArrayList<Book> getBooks() {
        return books;
    }

    public static int[] booksimages={R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9,R.drawable.pic10};
    public static String[] booksnames={"علوم ششم","حسابان","فیزیک دهم","عربی جامع","زمین شناسی","علوم پنجم","زبان یازدهم","فارسی جامع","زیست جامع","ریاضی و امار"};
    public static String[] subjects={
            " جموعه\u200Cی کارآموز علوم ششم دبستان با همکاری محمدرضا اعتباری، آزاده رحیمی و پریسا محبی در 128 صفحه نوشته شده و انتشارات مهروماه آن را به چاپ رسانده است. این کتاب مجموعه\u200Cای از بهترین پرسش\u200Cها و مطالب آموزشی منطبق بر متدهای به روز آموزشی دنیا را در اختیار شما قرار می\u200Cدهد",
            " جامعه\u200Cشناسی جامع کنکور کتابی است از مجموعه\u200Cی مرشد که کلیه\u200Cی مطالب کتاب درسی دهم، یازدهم و دوازدهم انسانی را در سطح پیشرفته ارائه می\u200Cدهد و دانش\u200Cآموزان را برای شرکت در امتحانات و کنکور آماده می\u200Cسازد. کتاب\u200Cهای مرشد به\u200Cگونه\u200Cای تألیف شده\u200Cاند که مطالعه آن\u200Cها دانش\u200Cآموز را برای شرکت در آزمون\u200Cهای ورودی دانشگاه\u200Cها آماده می\u200Cسازد. در این کتاب، دانش\u200Cآموز ابتدا با مباحث و نکته\u200Cهای مهم هر درس آشنا شده و با مثال\u200Cهای گوناگون بر آن\u200Cها تسلط می\u200Cیابد",
            " تاب \"عربی پایه کنکور\" با همکاری مشترک علی جعفری\u200Cندوشن، مجید همایی، محمدتقی بابائیان و ایرج کاظمی در 504 صفحه گردآوری شده است. چاپ و نشر این کتاب توسط انتشارات مبتکران صورت پذیرفته و در اختیار دانش\u200Cآموزان قرار گرفته است.",
            " انتشارات مشاوران آموزش با درنظرگرفتن نیاز دانش\u200Cآموزان رشته علوم انسانی در درس ریاضی، دست به تألیف کتاب ریاضی و آمار زده است. به همین جهت با ارائه\u200Cی تمرین\u200Cهای بسیار ذهن دانش\u200Cآموز را برای حل مسائل آن آشنا می\u200Cسازد. به دلیل حساسیت دانش\u200Cآموزان این رشته نسبت به درس ریاضی، در بخش پاسخ\u200C تشریحی روش\u200Cهای مختلف حل سؤالات قرار گرفته تا با مروری بر آن بتوان سؤالات را به آسانی پاسخ داد"
    };

    public static void makeDataing(){
        ArrayList<Videos> videos=new ArrayList<>();
        int v=R.raw.video1;
        Videos vp1=new Videos(String.valueOf(v),"مثلثات","اموزش مثلتات کنکور به شیوه مدرن",0);
        v=R.raw.video2;
        Videos vp2=new Videos(String.valueOf(R.raw.video2),"اموزش ایفون","اموزش مقدماتی کار با گوشی ایفون و فنون استفاده از ان با استفاده از برنامه های مختلف",1);
        Videos vp3=new Videos(String.valueOf(R.raw.video3),"اموزش توابع","اموزش توابع متناوب و و مسابل مربوط به انها ",2);
        Videos vp4=new Videos(String.valueOf(R.raw.video4),"ویژگی توابع","اموزش دامنه و برد توابع با استفاده از روش های نو",3);

        videos.add(vp1);
        videos.add(vp2);
        videos.add(vp3);
        videos.add(vp4);


        ArrayList<Audios> audios=new ArrayList<>();

        int a=R.raw.b1;
        Audios a1=new Audios(String.valueOf(a),"پادکست",1,"11:44");
        a=R.raw.b2;
        Audios a2=new Audios(String.valueOf(a),"قسمت دو",2,"3:23");
        a=R.raw.c1;
        Audios a3=new Audios(String.valueOf(a),"اموزش",3,"7:23");
        a=R.raw.c2;
        Audios a4=new Audios(String.valueOf(a),"بایت و بیت",4,"7:40");
        a=R.raw.c3;
        Audios a5=new Audios(String.valueOf(a),"موفقیت",5,"3:46");

        audios.add(a1);
        audios.add(a2);
        audios.add(a3);
        audios.add(a4);
        audios.add(a5);

        ArrayList<Integer> images=new ArrayList<>();
        for(int i=0;i<booksimages.length;i++)
            images.add(booksimages[i]);

        Page p1=new Page(1,images,audios,videos);
        Page p2=page2();
        Page p3=page2();
        ArrayList<Page> pages=new ArrayList<>();
        pages.add(p1);
        pages.add(p2);
        pages.add(p3);

        Book b1=new Book(booksimages[0],booksnames[0],booksnames[0],pages,3,10,11,5,8,9,0);
        Book b2=new Book(booksimages[1],booksnames[1],booksnames[1],pages,3,10,11,6,5,4,1);
        Book b3=new Book(booksimages[2],booksnames[2],booksnames[2],pages,3,10,11,4,7,6,2);
        Book b4=new Book(booksimages[3],booksnames[3],booksnames[3],pages,3,10,11,2,9,5,3);
        Book b5=new Book(booksimages[4],booksnames[4],booksnames[4],pages,3,10,11,3,3,4,4);
        Book b6=new Book(booksimages[5],booksnames[5],booksnames[5],pages,3,10,11,1,11,9,5);
        Book b7=new Book(booksimages[6],booksnames[6],booksnames[6],pages,3,10,11,5,4,1,6);
        Book b8=new Book(booksimages[7],booksnames[7],booksnames[7],pages,3,10,11,7,6,9,7);

        ArrayList<Book> books=new ArrayList<>();
        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.add(b4);
        books.add(b5);
        books.add(b6);
        books.add(b7);
        books.add(b8);



        setBooks(books);
    }

    public static ArrayList<Book> makeData(){
        return books;

    }
    public static Page page2(){
        ArrayList<Videos> videos=new ArrayList<>();
        Videos vp1=new Videos(String.valueOf(R.raw.video1),"مثلثات","اموزش مثلتات کنکور به شیوه مدرن",0);
        Videos vp2=new Videos(String.valueOf(R.raw.video2),"اموزش ایفون","اموزش مقدماتی کار با گوشی ایفون و فنون استفاده از ان با استفاده از برنامه های مختلف",1);
        Videos vp3=new Videos(String.valueOf(R.raw.video4),"اموزش توابع","اموزش توابع متناوب و و مسابل مربوط به انها ",2);

        videos.add(vp1);
        videos.add(vp2);
        videos.add(vp3);

        ArrayList<Audios> audios=new ArrayList<>();
        Integer a=R.raw.b1;
        Audios a1=new Audios(a.toString(),"پادکست",1,"11:44");
         a=R.raw.b2;
        Audios a2=new Audios(a.toString(),"قسمت دو",2,"3:23");
        a=R.raw.c1;
        Audios a3=new Audios(a.toString(),"اموزش",3,"7:23");
        audios.add(a1);
        audios.add(a2);
        audios.add(a3);
        ArrayList<Integer> images=new ArrayList<>();
        for(int i=0;i<booksimages.length;i++)
            images.add(booksimages[i]);

        Page p1=new Page(2,images,audios,videos);
        return p1;



    }

}
