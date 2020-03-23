package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.noteholder> {
    private List<Note> notes =  new ArrayList<>();
    private OnItemClicklistener listener;
    @NonNull
    @Override
    public noteholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.note_item,parent,false);
        return new noteholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull noteholder holder, int position) {
        Note currentNote = notes.get(position);
        holder.textviewtitle.setText(currentNote.getTitle());
        holder.textviewdescription.setText(currentNote.getDescription());
        holder.textviewpriority.setText(String.valueOf(currentNote.getPriority()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }
    public Note getatnote(int position)
    {
        return notes.get(position);
    }

    class noteholder extends RecyclerView.ViewHolder
    {
        private TextView textviewtitle;
        private TextView textviewdescription;
        private TextView textviewpriority;

        public noteholder(@NonNull View itemView) {
            super(itemView);
            textviewtitle = itemView.findViewById(R.id.text_view_title);
            textviewdescription = itemView.findViewById(R.id.text_view_description);
            textviewpriority = itemView.findViewById(R.id.text_view_priority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener!= null && position!= RecyclerView.NO_POSITION)
                    listener.OnItemClick(notes.get(position));
                }
            });

        }
    }
    public interface OnItemClicklistener
    {
        void OnItemClick(Note note);
    }
    public void SetOnItemClicklistener(OnItemClicklistener listener)
    {
        this.listener = listener;
    }
}
