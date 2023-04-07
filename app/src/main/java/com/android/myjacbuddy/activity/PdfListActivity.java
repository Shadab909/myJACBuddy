package com.android.myjacbuddy.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.myjacbuddy.R;
import com.android.myjacbuddy.adapter.QuestionAdapter;
import com.android.myjacbuddy.model.QuestionModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class PdfListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private QuestionAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MyJACBuddy);
        setContentView(R.layout.activity_pdf_list);

        recyclerView = findViewById(R.id.question_recyclerview);

        String question_type = getIntent().getStringExtra("QuestionType");
        String class_name = getIntent().getStringExtra("class_name");

        Objects.requireNonNull(getSupportActionBar()).setTitle(question_type);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setElevation(12);

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://myjacbuddy-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference reference = database.getReference(class_name).child(question_type);

        initRecyclerView(reference);
    }

    public void initRecyclerView(DatabaseReference reference){

        ShimmerFrameLayout shimmerFrameLayout = findViewById(R.id.shimmerLayout);
        shimmerFrameLayout.startShimmer();
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        ArrayList<QuestionModel> list = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot item : snapshot.getChildren()){
                    String name = Objects.requireNonNull(item.child("name").getValue()).toString();
                    String link = Objects.requireNonNull(item.child("link").getValue()).toString();
                    QuestionModel model = new QuestionModel(name,link);
                    list.add(model);
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(PdfListActivity.this));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                adapter = new QuestionAdapter(list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}