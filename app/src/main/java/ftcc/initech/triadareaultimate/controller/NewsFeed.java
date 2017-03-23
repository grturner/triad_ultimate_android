package ftcc.initech.triadareaultimate.controller;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONArray;

/**
 * Created by grturner on 3/23/2017.
 */

public class NewsFeed {
    private JSONArray postArray;

    // TODO: 3/23/2017 MAKE THIS SHIT WORK 
    public void NewsFeed() {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/86086823308/feed",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        initPostArray(response);
                    }
                }
        ).executeAndWait();
    }

    private void initPostArray(GraphResponse response){
        postArray = response.getJSONArray();
    }

    public JSONArray getNewsFeed(){
        return postArray;
    }
}
