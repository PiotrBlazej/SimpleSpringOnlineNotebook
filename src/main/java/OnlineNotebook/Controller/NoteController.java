package OnlineNotebook.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import OnlineNotebook.Model.Note;
import OnlineNotebook.Service.NoteService;

@Controller
public class NoteController {

	@Autowired
	NoteService noteService;

	@GetMapping("/newNote")
	public String getNewNote(Model model) {
		model.addAttribute("newNote", new Note());
		return "newNote";
	}

	@PostMapping("/newNote")
	public String saveNewNote( @ModelAttribute(value = "newNote") Note note) {
			noteService.saveNote(note);
		return "redirect:/";
	}

	@GetMapping(value = "/")
	public String getService(Model model) {
		model.addAttribute("notes", noteService.findAllNote());
		return "index";
	}

	@GetMapping(value = "/remove")
	public String removeNote(@RequestParam("id") int id) {
		noteService.deleteNote(id);
		return "redirect:/";
	}
	@GetMapping(value = "/open")
	public String openNote(@RequestParam("id") int id, Model model) {
		Note findNoteById = noteService.findNoteById(id);
		model.addAttribute("newNote", findNoteById);
		return "openNote";
	}
	@GetMapping(value = "/edit")
	public String editNote(@RequestParam("id") int id, Model model) {
		Note findNoteById = noteService.findNoteById(id);
		model.addAttribute("editNote", findNoteById);
		return "editNote";
	}
	@PostMapping(value = "/edit")
	public String saveEditNote(@ModelAttribute(value = "editNote") Note note) {
	noteService.updateNote(note);
		return "redirect:/";
	}

}
