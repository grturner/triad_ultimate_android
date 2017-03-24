package ftcc.initech.triadareaultimate.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewGroupCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ftcc.initech.triadareaultimate.R;
import ftcc.initech.triadareaultimate.controller.NewsFeed;

/**
 * Created by grturner on 3/23/2017.
 */

public class NewsFragment extends Fragment {
    private NewsFeed mNewsFeed;
    private TextView mTextView;
    private JSONArray mPostArray;
    private JSONObject mFeedResponse;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        Bundle params = new Bundle();
        params.putString("fields", "message,created_time,id,full_picture");
        params.putString("limit", "20");
        params.putString("access_token", "1578803498826781|qNW39As051UT6x3tUVGivbUTfR4");
        //mNewsFeed = new NewsFeed();
        //mPostArray = mNewsFeed.getNewsFeed();
        mTextView = (TextView) view.getRootView().findViewById(R.id.news_text_view);
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/86086823308/feed",
                params,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        //System.out.println("Facebook response: " + String.valueOf(response.getJSONArray()));
                        try {
                            mFeedResponse = new JSONObject(String.valueOf(response.getJSONObject()));
                            mTextView.setText(mFeedResponse.toString());
                        }
                        catch (JSONException jEx) {
                            jEx.printStackTrace();
                        }
                    }
                }
        ).executeAsync();

        //Testing

        return view;
    }
}
