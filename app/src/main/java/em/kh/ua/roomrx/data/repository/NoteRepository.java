package em.kh.ua.roomrx.data.repository;

import android.app.Application;

import java.util.List;

import em.kh.ua.roomrx.data.model.Note;
import em.kh.ua.roomrx.data.database.NoteDao;
import em.kh.ua.roomrx.data.database.NoteDatabase;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class NoteRepository {

    private Flowable<List<Note>> allNotes;
    private static NoteDao noteDao;

    public NoteRepository(Application application){
        NoteDatabase noteDatabase = NoteDatabase.getDatabase(application);
        noteDao = noteDatabase.noteDao();
    }

    public Flowable<List<Note>> fetchNotes() {
        allNotes = noteDao.fetchNotes();
        return allNotes;
    }

    public Flowable<List<Note>> fetchNotes(String search){
        allNotes = noteDao.fetchNotes(search);
        return allNotes;
    }

    public void addNote(Note note){
        noteDao.insertNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void updateNote(Note note) {
        noteDao.updateNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public void deleteNote(Note note) {
        noteDao.deleteNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
