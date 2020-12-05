package ua.kh.em.dbjam.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ua.kh.em.dbjam.data.model.Note;
import io.reactivex.Completable;
import io.reactivex.Flowable;


import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY name ASC")
    Flowable<List<Note>> fetchNotes();

    @Query("SELECT * FROM notes WHERE name LIKE :search")
    Flowable<List<Note>> fetchNotes(String search);

    @Insert(onConflict = REPLACE)
    Completable insertNote(Note note);

    @Update(onConflict = REPLACE)
    Completable updateNote(Note note);

    @Delete
    Completable deleteNote(Note note);
}
