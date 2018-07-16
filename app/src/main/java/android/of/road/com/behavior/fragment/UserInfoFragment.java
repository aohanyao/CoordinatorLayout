package android.of.road.com.behavior.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.of.road.com.coordinatorlayout.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends Fragment {


    public UserInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_info, container, false);
    }

    public static UserInfoFragment newInstance() {
        
        Bundle args = new Bundle();
        
        UserInfoFragment fragment = new UserInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
