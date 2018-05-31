package com.example.student.recyclerviewexample;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Person> personList;
    PersonRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);
        userService.getUsers().enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                Log.d("TAG", "response ok " + response.body().data.size());
                List<Person> userList = response.body().data;
                adapter.setUserList(userList);
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                Log.d("TAG", "Response error");
            }
        });

        generisiListPersona();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        adapter = new PersonRecyclerViewAdapter(personList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EnterPersonData.class);
                MainActivity.this.startActivityForResult(intent, 42);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 42) {
            if (resultCode == MainActivity.RESULT_OK) {
                String name = data.getStringExtra("name");
                String surname = data.getStringExtra("surname");
                Log.d("TAG", "name + surname " + name + " " + surname);
                Person person = new Person("", name, surname);
                personList.add(person);
                adapter.notifyItemInserted(personList.size() - 1);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void generisiListPersona() {
        personList = new ArrayList<>();
//        Person person = new Person("","Aleksandar", "Markovic");
//        personList.add(person);
//        personList.add(new Person("", "Marko", "Djurdjev"));
//        personList.add(new Person("", "Mitar", "Miric"));
    }
}
