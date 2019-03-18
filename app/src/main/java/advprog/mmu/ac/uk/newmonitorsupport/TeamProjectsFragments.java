package advprog.mmu.ac.uk.newmonitorsupport;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class TeamProjectsFragments extends Fragment {

    int loggedInID;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StudentFragmentHome activity = (StudentFragmentHome) getActivity();
        loggedInID = getArguments().getInt("loggedID");
        System.out.println(loggedInID);

        return inflater.inflate(R.layout.fragment_teamprojects,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //TextView textView = getView().findViewById(R.id.txtLoginID);
        //textView.setText(loggedInID);
        //or  (ImageView) view.findViewById(R.id.foo);
    }
}
