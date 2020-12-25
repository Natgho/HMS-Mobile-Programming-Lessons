package yu.yufragmentdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ChatsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private int mParam2;

    public static ChatsFragment newInstance(String param1, int param2) {
        ChatsFragment fragment = new ChatsFragment();

        // Configure bundle to pass variable
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  myFragmentView = inflater.inflate(R.layout.fragment_chats, container, false);

        TextView textView = myFragmentView.findViewById(R.id.welcomeText);
        textView.setText(mParam1 + " " + mParam2);

        return myFragmentView;
    }

    @Override
    public void onPause(){
        Toast.makeText(getContext(), "ChatsFragment is paused!", Toast.LENGTH_LONG).show();
        super.onPause();
    }

    @Override
    public void onResume(){
        Toast.makeText(getContext(), "ChatsFragment is resumed!", Toast.LENGTH_LONG).show();
        super.onResume();
    }
}