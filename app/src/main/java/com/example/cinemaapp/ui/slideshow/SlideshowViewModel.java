package com.example.cinemaapp.ui.slideshow;

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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class SlideshowViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<Movie>> mMovies;
    private final MutableLiveData<Movie> mCurrentMovie;
    private final DatabaseReference moviesReference;
    private final DatabaseReference ticketReference;

    public SlideshowViewModel() {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://thuncinemaapp-default-rtdb.asia-southeast1.firebasedatabase.app/");
        moviesReference = database.getReference("movies");
        ticketReference = database.getReference("tickets");

        mMovies = new MutableLiveData<>();
        mCurrentMovie = new MutableLiveData<>();
        moviesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Movie> movies = new ArrayList<>();
                snapshot.getChildren()
                        .forEach(dataSnapshot -> movies.add(dataSnapshot.getValue(Movie.class)));
                mMovies.setValue(movies);
                mCurrentMovie.setValue(movies.get(0));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Movie>> getMovies() {
        return mMovies;
    }

    public LiveData<Movie> getCurrentMovie() {
        return mCurrentMovie;
    }

    public void setCurrentMovie(Movie movie) {
        mCurrentMovie.setValue(movie);
    }

    public boolean bookCurrentMovie() {
        Movie currentMovie = mCurrentMovie.getValue();
        if (Objects.isNull(currentMovie)) return false;
        if (currentMovie.getCurrentSlot() >= currentMovie.getMaxSlot()) return false;
        currentMovie.setCurrentSlot(currentMovie.getCurrentSlot() + 1);
        moviesReference.child(String.valueOf(currentMovie.getId())).setValue(currentMovie);
        Ticket ticket = new Ticket(
                UUID.randomUUID().toString(),
                currentMovie.getName(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        ticketReference.child(ticket.getId()).setValue(ticket);
        return true;
    }

}