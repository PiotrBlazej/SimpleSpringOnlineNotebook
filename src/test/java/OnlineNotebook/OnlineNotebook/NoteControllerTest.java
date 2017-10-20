package OnlineNotebook.OnlineNotebook;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import OnlineNotebook.OnlineNotebookApplication;
import OnlineNotebook.Model.Note;
import OnlineNotebook.Service.NoteServiceImpl;



@RunWith(SpringRunner.class)
@SpringBootTest(classes=OnlineNotebookApplication.class)
@AutoConfigureMockMvc
public class NoteControllerTest {
@Autowired
	private MockMvc mockMvc;
@Autowired
private NoteServiceImpl service;
	@Test
	public void shouldReturnNewNotePage() throws Exception {
		 mockMvc.perform(get("/newNote"))
		  .andDo(print())
		  .andExpect(status().isOk())
		  .andExpect(view().name("newNote"))
		  .andExpect(model().attributeExists("newNote"));
	}
	@Test
	public void shouldSaveNewNote() throws Exception{
		 mockMvc.perform(post("/newNote")
	             .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	             .param("note", "new Note")
	             .param("title", "newTitle")
	     ).andDo(print())
	             .andExpect(redirectedUrl("/")).andExpect(status().isFound());
	}
	@Test
	public void shouldRejectValidationNewNote() throws Exception{
		 mockMvc.perform(post("/newNote")
	             .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	             .param("note", "new Note")
	             .param("title", "")
	     ).andDo(print())
	             .andExpect(view().name("newNote"))
	             .andExpect(content()
	            		 .string(Matchers.containsString("Title can not be empty")));
	}
	@Test
	public void shouldReturnIndexPage() throws Exception {
		 mockMvc.perform(get("/"))
		  .andDo(print())
		  .andExpect(status().isOk())
		  .andExpect(view().name("index"));

	}
	@Test
	public void shouldReturnEditNotePage() throws Exception {
		service.saveNote(new Note("title", "note"));
		 mockMvc.perform(get("/edit?id=1"))
		  .andDo(print())
		  .andExpect(status().isOk())
		  .andExpect(view().name("editNote"))
		  .andExpect(model().attributeExists("editNote"));
	}
	
	@Test
	public void shouldSaveEditNewNote() throws Exception{
		service.saveNote(new Note("title", "note"));
		 mockMvc.perform(post("/edit")
	             .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	             .param("note", "new Note")
	             .param("title", "newTitle")
	     ).andDo(print())
	             .andExpect(redirectedUrl("/")).andExpect(status().isFound());
	}
	
	@Test
	public void shouldReturnNotePage() throws Exception {
		service.saveNote(new Note("title", "note"));
		 mockMvc.perform(get("/open?id=1"))
		  .andDo(print())
		  .andExpect(status().isOk())
		  .andExpect(view().name("openNote"))
		  .andExpect(model().attributeExists("newNote"));
	}
	
	@Test
	public void shouldRemoveNote() throws Exception {
		service.saveNote(new Note("title", "note"));
		 mockMvc.perform(get("/remove?id=1"))
		  .andDo(print())
		  .andExpect(redirectedUrl("/")).andExpect(status().is3xxRedirection());
	}

}
