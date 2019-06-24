package mandh.ir.myapplication.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import mandh.ir.myapplication.R;

public class ImageFragment extends Fragment {
    private String title;

    private int imageMain;

   String des;

    public static ImageFragment newInstance(String title, int resMainImage, String des) {

        ImageFragment fragment = new ImageFragment();

        Bundle args = new Bundle();

        args.putInt("imageMain", resMainImage);

        args.putString("des", des);

        args.putString("title", title);

        fragment.setArguments(args);

        return fragment;

    }

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        imageMain = getArguments().getInt("imageMain", 0);

        des = getArguments().getString("des", "");

        title = getArguments().getString("title");

    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.image_fragment, container, false);

        TextView tvLabel = (TextView) view.findViewById(R.id.image_name);

        tvLabel.setText(title);

        TextView desText = (TextView) view.findViewById(R.id.image_des);

        tvLabel.setText(des);

        ImageView imageView = (ImageView) view.findViewById(R.id.image_image);

        imageView.setImageResource(imageMain);


        return view;

    }
}
