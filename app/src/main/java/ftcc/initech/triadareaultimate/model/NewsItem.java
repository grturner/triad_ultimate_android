package ftcc.initech.triadareaultimate.model;

/**
 * Created by grturner on 3/23/2017.
 */

public class NewsItem {

    String mDate;
    String mPost;
    FacebookPerson mPerson;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getPost() {
        return mPost;
    }

    public void setPost(String post) {
        mPost = post;
    }

    public FacebookPerson getPerson() {
        return mPerson;
    }

    public void setPerson(FacebookPerson person) {
        mPerson = person;
    }
}
