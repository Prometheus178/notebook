package notebook.service;

import notebook.entity.Note;
import notebook.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{
    private NoteRepository repository;

    @Autowired
    public void setNoteRepository(NoteRepository repository){
        this.repository = repository;
    }

    @Override
    public Note getById(int id) {
        return repository.getOne(id);
    }

    @Override
    public void save(Note note) {
        repository.save(note);
    }

    @Override
    public void update(int id, String message, boolean done) {
        Note updated = repository.getOne(id);
        updated.setMessage(message);
        updated.setDone(done);
        repository.save(updated);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<Note> findAllByOrderByDateAsc() {
        return repository.findAllByOrderByDateAsc();
    }

    @Override
    public List<Note> findAllByOrderByDateDesc() {
        return repository.findAllByOrderByDateDesc();
    }

}
