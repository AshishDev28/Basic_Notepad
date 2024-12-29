package com.example.Notepad.Repository;
import com.example.Notepad.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface NoteRepository extends JpaRepository<Note, Long> {
    }


