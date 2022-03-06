package com.nnk.springboot.ControllerTest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.CheckInputService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private UserRepository userRepository;
	@MockBean
	CheckInputService checkInputService;
	@Autowired
	private WebApplicationContext context;

	@Autowired
	private ObjectMapper mapper;

	@Before()
	public void setup() {
		// Init MockMvc Object and build
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Ignore

	@WithMockUser(value = "test")

	@Test
	public void testShowWatchUserList() throws Exception {
		mvc.perform(get("/user/list")).andExpect(status().is2xxSuccessful());
	}

	@WithMockUser(value = "test")

	@Test
	public void testAddUser() throws Exception {
		mvc.perform(get("/user/add")).andExpect(status().is2xxSuccessful());
	}

	@WithMockUser(value = "test")

	@Test
	public void testSaveUser() throws Exception {

		User user = new User("username", " password", "fullname", "USER");

		String jsonRequest = mapper.writeValueAsString(user);
		mvc.perform(post("/user/validate").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@WithMockUser(value = "test")

	@Test
	public void testFailSaveUserEmptyData() throws Exception {

		User user1 = new User(null,null,null,null);
		String jsonRequest = mapper.writeValueAsString(user1);
		mvc.perform(post("/user/validate").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is(200));
	}

	@WithMockUser(value = "test")

	@Test
	public void testDeleteUser() throws Exception {
		User user = new User("username", " password", "fullname", "USER");

		when(userRepository.findById(922)).thenReturn(Optional.of(user));
		doNothing().when(userRepository).delete(user);
		mvc.perform(get("/user/delete/922")).andExpect(redirectedUrl("/user/list"));
	}

	@WithMockUser(value = "test")

	@Test
	public void testUpdateUser() throws Exception {

		User user = new User("username", " password", "fullname", "USER");

		when(userRepository.findById(69)).thenReturn(Optional.of(user));
		mvc.perform(get("/user/update/69")).andExpect(status().isOk());
	}

	@WithMockUser(value = "test")

	@Test
	public void testWrongDataUpdateUser() throws Exception {

		User User = new User();
		when(userRepository.findById(69)).thenReturn(Optional.of(User));
		mvc.perform(get("/user/update/69")).andExpect(status().is(200));
	}

	@WithMockUser(value = "test")

	@Test
	public void testSaveUpdateUser() throws Exception {
		User user = new User("username", " password", "fullname", "USER");

		when(userRepository.findById(69)).thenReturn(Optional.of(user));
		user.setFullname("accountUpdated");
		mvc.perform(post("/user/update/69")).andExpect(status().isOk());

	}

}
