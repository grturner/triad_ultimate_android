package ftcc.initech.triadareaultimate.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewGroupCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;

import ftcc.initech.triadareaultimate.R;
import ftcc.initech.triadareaultimate.controller.NewsFeed;

/**
 * Created by grturner on 3/23/2017.
 */

public class NewsFragment extends Fragment {
    private NewsFeed newsFeed;
    private TextView textView;
    private JSONArray postArray;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        newsFeed = new NewsFeed();
        postArray = newsFeed.getNewsFeed();
        textView = (TextView) view.getRootView().findViewById(R.id.news_text_view);

        //Testing
        textView.setText(postArray.toString());

        return view;
    }
}
