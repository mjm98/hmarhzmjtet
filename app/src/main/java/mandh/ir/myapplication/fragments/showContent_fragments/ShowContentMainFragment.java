package mandh.ir.myapplication.fragments.showContent_fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import mandh.ir.myapplication.forHelp.G;
import mandh.ir.myapplication.models.Model;
import mandh.ir.myapplication.R;
import mandh.ir.myapplication.adapters.gradeViewAdapters.ShowContentGradeView_Adapter;
import mandh.ir.myapplication.models.ModelShowContent;
import mandh.ir.myapplication.models.StaticsData;

import static mandh.ir.myapplication.forHelp.G.context;

/**
 * Created by Dell  5559 on 25/05/2019.
 */

public class ShowContentMainFragment extends Fragment     {


    ArrayList<ModelShowContent> models=new ArrayList<>();
    GridView gridview;
    TextView title  ;
    TextView number ;
    EditText numberofPage;
    ShowContentGradeView_Adapter gradeView;
    //..............................................................................................
    ArrayList<ModelShowContent> arrayList;
    int bookid;
    int pageid=-1;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = null;
        bookid=getArguments().getInt("id", 0);
        view = inflater.inflate(R.layout.fragment_main_show_content, container, false);

        gridview = (GridView) view.findViewById(R.id.gridView);
        numberofPage=(EditText) view.findViewById(R.id.number);
        models=getBooksModels();


        numberofPage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()>0) {
                    if((Integer.valueOf(charSequence.toString()))<1){
                        Toast.makeText(G.context,"صفحه مورد نظر وجود ندارد",Toast.LENGTH_SHORT).show();
                    } else if(StaticsData.makeData().get(bookid).getPages().size()>(Integer.valueOf(charSequence.toString())-1)){
                    pageid=Integer.valueOf(charSequence.toString())-1;
                    // gradeView.setPageid(Integer.valueOf(charSequence.toString()));
                    models=getPageModels();
                    gradeView.setModel(getPageModels());
                    gradeView.notifyDataSetChanged();}
                    else {
                        Toast.makeText(G.context,"صفحه مورد نظر وجود ندارد",Toast.LENGTH_SHORT).show();
                    }
                }else if(charSequence.length()==0){
                    models=getBooksModels();
                    gradeView.setModel(getBooksModels());
                    gradeView.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        arrayList = new ArrayList<>();

        cast(view);

        setTypeFaces();

        createGradeViewData();

        setGradeView();




        return  view;

    }




    private void setTypeFaces() {

        Typeface myTypeface = Typeface.createFromAsset(context.getAssets(), "font/IRANYekanMobileMedium.ttf");

        title.setTypeface(myTypeface);
        number.setTypeface(myTypeface);
    }




    private void createGradeViewData() {

       /* Model model1 = new Model(1,);
        Model model2 = new Model(2,);
        Model model3 = new Model(3,);
        Model model4 = new Model(4,);
        Model model5 = new Model(5,);
        Model model6 = new Model(6,);

        arrayList.add(model1);
        arrayList.add(model2);
        arrayList.add(model3);
        arrayList.add(model4);
        arrayList.add(model5);
        arrayList.add(model6);*/

    }



    private void cast(View view) {

        title =(TextView) view.findViewById(R.id.title);
        number = (TextView)  view.findViewById(R.id.number);
    }



    private void setGradeView() {


         gradeView= new ShowContentGradeView_Adapter(models,bookid,pageid);
        gridview.setAdapter(gradeView);

    }
    public ArrayList<ModelShowContent> getBooksModels(){
        ArrayList<ModelShowContent> models=new ArrayList<>();
        models.add(new ModelShowContent(String.valueOf(StaticsData.makeData().get(bookid).getNumberOfVoices()),"محتوای صوتی"));
        models.add(new ModelShowContent(String.valueOf(StaticsData.makeData().get(bookid).getNumberOfPages()),"محتوای تصویری"));
        models.add(new ModelShowContent(String.valueOf(StaticsData.makeData().get(bookid).getNumberOfVideos()),"محتوای ویدیویی"));
        models.add(new ModelShowContent(String.valueOf(StaticsData.makeData().get(bookid).getNumberOfFiles()),"محتوای فایلی"));
        models.add(new ModelShowContent(String.valueOf(StaticsData.makeData().get(bookid).getNumberOf3d()),"محتوای 3بعدی"));
        models.add(new ModelShowContent(String.valueOf(StaticsData.makeData().get(bookid).getNumberOfdocs()),"محتوای متنی"));


        return models;
    }
    public ArrayList<ModelShowContent> getPageModels(){
        ArrayList<ModelShowContent> models=new ArrayList<>();
        models.add(new ModelShowContent(String.valueOf(StaticsData.makeData().get(bookid).getPages().get(pageid) .getNumberOfVoices()),"محتوای صوتی"));
        models.add(new ModelShowContent(String.valueOf(StaticsData.makeData().get(bookid).getPages().get(pageid).getNumberOfPics()),"محتوای تصویری"));
        models.add(new ModelShowContent(String.valueOf(StaticsData.makeData().get(bookid).getPages().get(pageid) .getNumberOfVideos()),"محتوای ویدیویی"));
        models.add(new ModelShowContent(String.valueOf(StaticsData.makeData().get(bookid).getPages().get(pageid) .getNumberOfFiles()),"محتوای فایلی"));
        models.add(new ModelShowContent(String.valueOf(StaticsData.makeData().get(bookid).getPages().get(pageid).getNumberOf3d()),"محتوای 3بعدی"));
        models.add(new ModelShowContent(String.valueOf(StaticsData.makeData().get(bookid).getPages().get(pageid) .getNumberOfdocs()),"محتوای متنی"));


        return models;
    }



}


