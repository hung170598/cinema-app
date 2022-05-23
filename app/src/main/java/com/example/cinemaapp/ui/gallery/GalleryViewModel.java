package com.example.cinemaapp.ui.gallery;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.cinemaapp.model.Movie;
import com.example.cinemaapp.model.Ticket;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Ticket>> mTickets;
    private final DatabaseReference ticketReference;

    public GalleryViewModel() {
        mTickets = new MutableLiveData<>();
        ticketReference = FirebaseDatabase.getInstance("https://thuncinemaapp-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("tickets");
        ticketReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Ticket> tickets = new ArrayList<>();
                Log.d("Ticket Size", "" + snapshot.getChildrenCount());
                snapshot.getChildren()
                        .forEach(dataSnapshot -> tickets.add(dataSnapshot.getValue(Ticket.class)));
                mTickets.setValue(tickets);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Ticket>> getTickets() {
        return mTickets;
    }

    public void addTicket(Ticket ticket){
        ticketReference.child(ticket.getId()).setValue(ticket);
    }
}