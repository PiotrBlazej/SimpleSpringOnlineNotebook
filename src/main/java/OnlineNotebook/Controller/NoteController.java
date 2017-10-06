package OnlineNotebook.Controller;

import java.util.List;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import OnlineNotebook.Model.Note;
import OnlineNotebook.Service.NoteService;

@Controller
public class NoteController {
	
	@Autowired
	 NoteService noteService;
	
	
	
	@GetMapping("/newNote")
	public String getNewNote(Model model){
		model.addAttribute("newNote", new Note());
		return "newNote";
	}
	@PostMapping("/newNote")
	public String saveNewNote(@ModelAttribute(value="newNote") Note note){
		try {
			noteService.saveNote(note.getTitle(), note.getNote());
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/";
	}
	

	@GetMapping(value="/")
	public String getService(Model model){
		model.addAttribute("notes", noteService.findAllNote());
		return "index";
	}
	
	@GetMapping(value="/remove")
public String removeNote(@RequestParam("id") int id){
		noteService.deleteNote(id);
		return "redirect:/";
	}

}
