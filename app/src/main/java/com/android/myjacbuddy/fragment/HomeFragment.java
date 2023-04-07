package com.android.myjacbuddy.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.myjacbuddy.R;
import com.android.myjacbuddy.activity.ComingSoonActivity;
import com.android.myjacbuddy.activity.PdfListActivity;
import com.android.myjacbuddy.activity.SettingsActivity;
import com.google.android.material.card.MaterialCardView;


public class HomeFragment extends Fragment {

    String input_class;
    static final int FIRST_ID = R.id.first;
    static final int SECOND_ID = R.id.second;
    static final int THIRD_ID = R.id.third;
    ImageButton hamburger_icon;
    TextView class_name;

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SharedPreferences sp = requireActivity().getSharedPreferences("Credential", Context.MODE_PRIVATE);
        input_class = sp.getString("userClass","");

        hamburger_icon = view.findViewById(R.id.hamburger_icon);
        ImageButton share_icon = view.findViewById(R.id.share_icon);
        class_name = view.findViewById(R.id.class_name);
        class_name.setText(input_class);

        MaterialCardView card_5 = view.findViewById(R.id.card_5);
        card_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), PdfListActivity.class);
                i.putExtra("QuestionType","Term 2 Model Papers");
                i.putExtra("class_name",input_class);
                requireActivity().startActivity(i);
            }
        });

        MaterialCardView card_1 = view.findViewById(R.id.card_1);
        card_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), PdfListActivity.class);
                i.putExtra("QuestionType","Detailed Notes");
                i.putExtra("class_name",input_class);
                requireActivity().startActivity(i);
            }
        });

        MaterialCardView card_2 = view.findViewById(R.id.card_2);
        card_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), PdfListActivity.class);
                i.putExtra("QuestionType","new Syllabus");
                i.putExtra("class_name",input_class);
                requireActivity().startActivity(i);
            }
        });

        MaterialCardView card_3 = view.findViewById(R.id.card_3);
        card_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), PdfListActivity.class);
                i.putExtra("QuestionType","Previous Year's Papers");
                i.putExtra("class_name",input_class);
                requireActivity().startActivity(i);
            }
        });

        MaterialCardView card_4 = view.findViewById(R.id.card_4);
        card_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ComingSoonActivity.class);
//                i.putExtra("QuestionType","Previous Year's Papers");
//                i.putExtra("class_name",input_class);
                requireActivity().startActivity(i);
            }
        });

        MaterialCardView card_6 = view.findViewById(R.id.card_6);
        card_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), PdfListActivity.class);
                i.putExtra("QuestionType","Topper's Answer Sheet");
                i.putExtra("class_name",input_class);
                requireActivity().startActivity(i);
            }
        });


        hamburger_icon.setOnClickListener(view1 -> MenuAction());
        share_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    public void MenuAction() {
        PopupMenu pm = new PopupMenu(requireContext(),hamburger_icon);
        pm.getMenuInflater().inflate(R.menu.overflow_options_menu, pm.getMenu());
        pm.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()){
                case  FIRST_ID:
                    Intent i = new Intent(getContext(), SettingsActivity.class);
                    requireActivity().startActivity(i);
                    return true;

                case SECOND_ID:
                    return true;

                case THIRD_ID:
                    return true;
            }

            return true;
        });

        pm.show();

    }
}