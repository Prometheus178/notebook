package notebook.controller;

import notebook.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import notebook.entity.Note;
import java.util.List;
@Controller
public class NoteController {
    private NoteService service;
    private String sortDateMethod = "ASC";
    @Autowired
    public void setService(NoteService service) {
        this.service = service;
    }
    @GetMapping("/")
    public String list(Model model){
        List<Note> notebook = filterAndSort();
        model.addAttribute("notes", notebook);
        model.addAttribute("sort",sortDateMethod);
        return "index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Note note = service.getById(id);
        model.addAttribute("note", note);
        return "operations/edit";
    }

    @PostMapping("/update")
    public String saveNote(@RequestParam Integer id, @RequestParam String message,
                           @RequestParam(value = "done", required = false) boolean done) {
        service.update(id, message, done);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newNote() {
        return "operations/new";
    }

    @PostMapping("/save")
    public String update(@RequestParam String message) {
        service.save(new Note(message));
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/";
    }

    private List<Note> filterAndSort() {
        List<Note> notebook = null;
        switch (sortDateMethod) {
            case "ASC":
                notebook = service.findAllByOrderByDateAsc();
                break;
            case "DESC":
                notebook = service.findAllByOrderByDateDesc();
                break;
        }
        return notebook;
    }
}
