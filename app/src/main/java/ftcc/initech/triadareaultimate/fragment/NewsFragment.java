package ftcc.initech.triadareaultimate.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ftcc.initech.triadareaultimate.R;
import ftcc.initech.triadareaultimate.model.NewsItem;

/**
 * Created by grturner on 3/23/2017.
 */

public class NewsFragment extends Fragment {
    private JSONArray mFeedArray;
    private JSONObject mFeedResponse;
    private NewsItem[] mNewsItems;

    private static NewsItem[] createArray(JSONArray feedArray) {
        NewsItem[] newsArray = new NewsItem[feedArray.length()];
        for (int i = 0; i < feedArray.length(); i++) {
            newsArray[i] = new NewsItem();
            try {
                JSONObject o = feedArray.getJSONObject(i);
                newsArray[i].setAuthor(o.getString("id"));
                newsArray[i].setDate(o.getString("created_time"));
                newsArray[i].setPost(o.getString("message"));
                // TODO: 3/27/2017 handle situation where facebook returns no full_picture
                newsArray[i].setPicture(o.getString("full_picture"));
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
        return newsArray;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        final ListView listView = (ListView) view.findViewById(R.id.feed_list);
        Bundle params = new Bundle();
        params.putString("fields", "message,created_time,id,full_picture");
        params.putString("limit", "20");
        params.putString("access_token", "1578803498826781|qNW39As051UT6x3tUVGivbUTfR4");
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/86086823308/feed",
                params,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        try {
                            mFeedResponse = response.getJSONObject();
                            mFeedArray = mFeedResponse.getJSONArray("data");
                            mNewsItems = createArray(mFeedArray);
                            listView.setAdapter(new FeedListAdapter(NewsFragment.this.getContext(),
                                    mNewsItems));
                        }
                        catch (JSONException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        ).executeAsync();
        return view;
    }

    private class FeedListAdapter extends ArrayAdapter<NewsItem> {
        // TODO: 3/27/2017 more formatting, convert icon to ImageView
        public FeedListAdapter(Context context, NewsItem[] newsItems){
            super(context, 0, newsItems);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            final NewsItem item = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.feed_list_item,
                        parent, false);
            }
            TextView author = (TextView) convertView.findViewById(R.id.feed_item_author);
            ImageView icon = (ImageView) convertView.findViewById(R.id.feed_item_avatar);
            TextView time = (TextView) convertView.findViewById(R.id.feed_item_timestamp);
            TextView message = (TextView) convertView.findViewById(R.id.feed_item_message);
            author.setText(item.getAuthor());
            Picasso.with(convertView.getContext()).load(item.getPicture()).into(icon);
            time.setText(item.getDate().substring(0, 10));
            message.setText(item.getPost());
            return convertView;
        }

    }
}
