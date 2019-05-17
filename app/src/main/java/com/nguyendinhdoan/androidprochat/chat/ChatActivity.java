package com.nguyendinhdoan.androidprochat.chat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.nguyendinhdoan.androidprochat.R;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener, ChatView {

    private static final String TAG = ChatActivity.class.getSimpleName();

    private RecyclerView chatListRecyclerView;
    private FloatingActionButton addUserButton;
    private ProgressBar chatLoading;

    public static Intent start(Context context) {
        return new Intent(context, ChatActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initViews();
        addEvents();
    }

    private void addEvents() {
        addUserButton.setOnClickListener(this);
    }

    private void initViews() {
        chatListRecyclerView = findViewById(R.id.chat_list_recycler_view);
        addUserButton = findViewById(R.id.add_user_button);
        chatLoading = findViewById(R.id.chat_loading);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_user_button) {

        }
    }

    @Override
    public void showLoading() {
        chatLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        chatLoading.setVisibility(View.GONE);
    }
}
