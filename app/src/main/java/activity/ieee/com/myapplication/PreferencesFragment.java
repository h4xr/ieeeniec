package activity.ieee.com.myapplication;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by saurabh on 7/2/16.
 */
public class PreferencesFragment extends Fragment {
    public PreferencesFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState){
        return inflater.inflate(R.layout.fragment_preferences,container,false);
    }
}
