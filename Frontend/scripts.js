const apiBaseUrl = "http://localhost:8080/api/notes";

const fetchNotes = async () => {
    const response = await fetch(apiBaseUrl);
    const notes = await response.json();

    const notesContainer = document.getElementById("notes-container");
    notesContainer.innerHTML = ""; 
    notes.forEach(note => {
        const noteCard = document.createElement("div");
        noteCard.className = "note-card";
        noteCard.innerHTML = `
            <div>
                <h3>${note.title}</h3>
                <p>${note.content}</p>
            </div>
            <button onclick="deleteNote(${note.id})">Delete</button>
        `;
        notesContainer.appendChild(noteCard);
    });
};

const addNote = async (event) => {
    event.preventDefault();

    const title = document.getElementById("note-title").value;
    const content = document.getElementById("note-content").value;

    const newNote = { title, content };

    await fetch(apiBaseUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newNote),
    });

    document.getElementById("note-form").reset();
    fetchNotes();
};

const deleteNote = async (id) => {
    await fetch(`${apiBaseUrl}/${id}`, { method: "DELETE" });

    fetchNotes();
};

document.getElementById("note-form").addEventListener("submit", addNote);

fetchNotes();
