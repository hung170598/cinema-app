package com.example.cinemaapp.ui.slideshow;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.cinemaapp.R;
import com.example.cinemaapp.databinding.FragmentSlideshowBinding;
import com.example.cinemaapp.model.Movie;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private ViewPager2 viewPager2;
    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        initView();
        initBookingListener();

//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initView() {
        viewPager2 = binding.bannerViewPager;
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);

        CompositePageTransformer pageTransformer = new CompositePageTransformer();
        pageTransformer.addTransformer(new MarginPageTransformer(60));
        pageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.8f + r * 0.2f);
            int currentItem = viewPager2.getCurrentItem();
            Movie movie = slideshowViewModel.getMovies().getValue().get(currentItem);
            slideshowViewModel.setCurrentMovie(movie);
        });
        viewPager2.setPageTransformer(pageTransformer);


        slideshowViewModel.getMovies().observe(getViewLifecycleOwner(), movies -> {
            int currentItem = viewPager2.getCurrentItem();
            MovieAdapter movieAdapter = new MovieAdapter(movies, slideshowViewModel);
            viewPager2.setAdapter(movieAdapter);
            viewPager2.setCurrentItem(currentItem);
        });

        slideshowViewModel.getCurrentMovie().observe(getViewLifecycleOwner(), movie -> {
            String movieName = getString(R.string.movie_name, movie.getName());
            binding.movieName.setText(movieName);
            long time = movie.getTime();
            String movieTime = getString(R.string.movie_time, time / 60, time % 60);
            binding.movieTime.setText(movieTime);
            String slot = getString(R.string.slot, movie.getCurrentSlot(), movie.getMaxSlot());
            binding.slot.setText(slot);
        });
    }

    void initBookingListener(){
        Button btnBookNow = binding.btnBookNow;
        btnBookNow.setOnClickListener(v -> {
            Log.d("Click Btn", "Booking");
            slideshowViewModel.bookCurrentMovie();
        });
    }
}