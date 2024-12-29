package com.example.Notepad.Controller;

import com.example.Notepad.Entity.Note;
import com.example.Notepad.Repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/notes")
    @CrossOrigin(origins = "*")
    public class NoteController {

        @Autowired
        private NoteRepository noteRepository;

        @GetMapping
        public List<Note> getAllNotes() {
            return noteRepository.findAll();
        }

        @PostMapping
        public Note createNote(@RequestBody Note note) {
            return noteRepository.save(note);
        }

        @PutMapping("/{id}")
        public Note updateNote(@PathVariable Long id, @RequestBody Note updatedNote) {
            return noteRepository.findById(id).map(note -> {
                note.setTitle(updatedNote.getTitle());
                note.setContent(updatedNote.getContent());
                return noteRepository.save(note);
            }).orElseThrow(() -> new RuntimeException("Note not found"));
        }

        @DeleteMapping("/{id}")
        public void deleteNote(@PathVariable Long id) {
            noteRepository.deleteById(id);
        }
    }