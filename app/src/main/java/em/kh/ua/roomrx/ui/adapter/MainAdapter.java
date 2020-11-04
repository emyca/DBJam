package em.kh.ua.roomrx.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import em.kh.ua.roomrx.R;
import em.kh.ua.roomrx.data.model.Note;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<Note> noteList;
    private View.OnLongClickListener longClickListener;
    private View.OnClickListener clickListener;

    public MainAdapter(List<Note> noteList,
                       View.OnLongClickListener longClickListener,
                       View.OnClickListener clickListener) {
        this.noteList = noteList;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return new MainViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_main, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int position) {
        Note note = noteList.get(position);
        mainViewHolder.itemNote.setText(note.getNoteName());
        mainViewHolder.itemView.setTag(note);
        mainViewHolder.itemView.setOnLongClickListener(longClickListener);
        mainViewHolder.itemView.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return noteList == null ? 0 : noteList.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        TextView itemNote;
        MainViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNote = itemView.findViewById(R.id.item_note);
        }
    }

    public void addListNotes(List<Note> listNotes){
        this.noteList = listNotes;
        notifyDataSetChanged();
    }
}
