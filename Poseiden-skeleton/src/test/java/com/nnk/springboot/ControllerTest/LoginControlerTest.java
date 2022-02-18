package com.nnk.springboot.ControllerTest;




import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nnk.springboot.controllers.LoginController;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.CheckInputService;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControlerTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private LoginController controller;
	@MockBean
	UserRepository userRepository;
	@MockBean
	CheckInputService checkInputService;
	@Autowired
    private WebApplicationContext context;
	@Before
    public void setup() {
        mvc = MockMvcBuilders
          .webAppContextSetup(context)
          .apply(springSecurity())
          .build();
    }
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
	mvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());
	}
	/*
	 * @Test void contextLoads()throws Exception {
	 * assertThat(controller).isNotNull(); }
	 */
	
	/*
	 * @Test public void shouldReturnDefaultHomePage() throws Exception {
	 * mvc.perform(formLogin("/login").user("test").password("1234")).
	 * andExpect(status().isOk());}
	 */
	 
	 
	  @Test public void userLoginFailed() throws Exception {
	  mvc.perform(formLogin("/login").user("test").password("wrongpassword")).
	  andExpect(unauthenticated()); }
	 

	
	/*
	 * @Test public void userLoginTest() throws Exception {
	 * mvc.perform(formLogin("/login").user("test").password("1234")).andExpect(
	 * authenticated()); }
	 */
}
