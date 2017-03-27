package ftcc.initech.triadareaultimate.controller;

import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by grturner on 3/23/2017.
 */

/********************************************************************************
*       Probably not needed any longer, keeping around for time being           *
*       GRT - 3/27/17                                                           *
********************************************************************************/

public class NewsFeed {
    private JSONObject mFeedResponse;

    // TODO: 3/23/2017 MAKE THIS SHIT WORK 
    public void NewsFeed() {
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
                        //System.out.println("Facebook response: " + String.valueOf(response.getJSONArray()));
                        try {
                            mFeedResponse = new JSONObject(String.valueOf(response.getJSONObject()));
                        }
                        catch (JSONException jEx) {
                            jEx.printStackTrace();
                        }
                    }
                }
        ).executeAsync();
    }

    public JSONObject getNewsFeed(){
        return mFeedResponse;
    }

    public Boolean isReady() {
        if (mFeedResponse == null) {
            return false;
        } else {
            return true;
        }
    }
}
