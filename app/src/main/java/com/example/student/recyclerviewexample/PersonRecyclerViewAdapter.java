package com.example.student.recyclerviewexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PersonRecyclerViewAdapter extends RecyclerView.Adapter<PersonRecyclerViewAdapter.PersonViewHolder> {

    private List<Person> personList;

    public PersonRecyclerViewAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.titleTextView.setText(person.name);
        holder.subtitleTextView.setText(person.prezime);
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public void setUserList(List<Person> userList) {
        this.personList = userList;
        notifyDataSetChanged();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView subtitleTextView;

        public PersonViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title);
            subtitleTextView = itemView.findViewById(R.id.subtitle);
        }
    }
}
