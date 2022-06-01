package com.example.espressotrialapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DroidFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DroidFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DroidFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DroidFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DroidFragment newInstance(String param1, String param2) {
        DroidFragment fragment = new DroidFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_droid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btn_display = view.findViewById(R.id.btn_display);
        Button btn_no_display = view.findViewById(R.id.btn_no_display);
        ImageView imageView = view.findViewById(R.id.imageView);

        //初期表示
        btn_display.setVisibility(View.INVISIBLE);
        btn_no_display.setVisibility(View.VISIBLE);

        //表示ボタンをタップ
        btn_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //表示・非表示の切り替え
                imageView.setVisibility(View.VISIBLE);
                btn_display.setVisibility(View.INVISIBLE);
                btn_no_display.setVisibility(View.VISIBLE);

            }
        });

        //非表示ボタンをタップ
        btn_no_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //表示・非表示の切り替え
                imageView.setVisibility(View.INVISIBLE);
                btn_display.setVisibility(View.VISIBLE);
                btn_no_display.setVisibility(View.INVISIBLE);
            }
        });


        //次へボタンをタップ
        view.findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //RecyclerViewFragmentに遷移
                Navigation.findNavController(v).navigate(R.id.action_droidFragment_to_recyclerViewFragment);
            }
        });
    }
}