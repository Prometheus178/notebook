package notebook.service;

import notebook.entity.Note;

import java.util.List;

public interface NoteService {
    Note getById(int id);
    void save(Note note);
    void update(int id, String message, boolean done);
    void delete(int id);
    List<Note> findAllByOrderByDateAsc();
    List<Note> findAllByOrderByDateDesc();
}
