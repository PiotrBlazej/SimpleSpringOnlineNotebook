package OnlineNotebook.Controller;

import java.util.List;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import OnlineNotebook.Model.Note;
import OnlineNotebook.Service.NoteService;

@Controller
public class NoteController {
	
	@Autowired
	 NoteService noteService;
	
	@RequestMapping("/getlist")
	@ResponseBody
	
	public List<Note> getNotes(){
		return noteService.findAllNote();
	}
	@RequestMapping(value="/g")
	@ResponseBody
	public String g(){
		return "dziala";
	}
	@RequestMapping(value="/a")
	public String getService(){
		return "index";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public String save(){
		try {
			noteService.saveNote("Piotr", "Nowicki");
			
		} catch (InvalidNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "zapisalem";
	}
	@RequestMapping("/d")
	@ResponseBody
	public String delete(){
		noteService.deleteNote(1);
		return "skasowalem";
	}

}
