package com.example.cinemaapp.ui.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Ticket;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder>{

    private List<Ticket> tickets;

    public TicketAdapter(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_item, parent, false);
        return new TicketAdapter.TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        if (Objects.isNull(ticket)) {
            return;
        }
        holder.txtMovieName.setText(ticket.getMovieName());
        holder.txtTicketTime.setText(ticket.getTime());
    }

    @Override
    public int getItemCount() {
        return Objects.isNull(tickets) ? 0 : tickets.size();
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtMovieName;
        private final TextView txtTicketTime;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMovieName = itemView.findViewById(R.id.ticket_movie_name);
            txtTicketTime = itemView.findViewById(R.id.ticket_time);
        }
    }
}
