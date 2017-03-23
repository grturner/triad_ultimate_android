package ftcc.initech.triadareaultimate.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ftcc.initech.triadareaultimate.R;

/**
 * Created by grturner on 3/23/2017.
 */

public class HomeFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
