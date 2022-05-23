package com.example.cinemaapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private RecyclerView ticketRcv;
    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        initView();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initView(){
        ticketRcv = binding.ticketRcv;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        ticketRcv.setLayoutManager(linearLayoutManager);
        galleryViewModel.getTickets().observe(getViewLifecycleOwner(), tickets -> {
            TicketAdapter ticketAdapter = new TicketAdapter(tickets);
            ticketRcv.setAdapter(ticketAdapter);
        });
    }
}