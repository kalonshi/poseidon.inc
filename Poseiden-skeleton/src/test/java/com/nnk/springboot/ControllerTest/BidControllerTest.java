package com.nnk.springboot.ControllerTest;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;

@RunWith(SpringRunner.class)
@WebMvcTest(BidListController.class)

public class BidControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private BidListService bidListService;

	@MockBean
	BidListRepository bidListRepository;
	@MockBean
	BidList bidList;
	@Autowired
    private WebApplicationContext context;
	@Before
    public void setup() {
        mvc = MockMvcBuilders
          .webAppContextSetup(context)
          .apply(springSecurity())
          .build();
    }
	
	
	
	
	@WithMockUser(value = "test")

	@Test
	public void testShowWatchBidList() throws Exception {
 mvc.perform(get("/bidList/list")) .andExpect(status().is2xxSuccessful()) ;
	}
	@WithMockUser(value = "test")	 
	@Test
	public void testAddBid() throws Exception {
 mvc.perform(get("/bidList/add")) .andExpect(status().is2xxSuccessful()) ;
	}
	
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testSaveBid() throws Exception {
	 * mvc.perform(post("/bidList/add")) .andExpect(status().is2xxSuccessful()) ; }
	 */
	
	
	
	/*
	 * @WithMockUser(username = "test",roles="USER")
	 * 
	 * @Test public void testDeleteBid() throws Exception {
	 * mvc.perform(get("/bidList/delete/{id}",69))
	 * .andExpect(status().is2xxSuccessful()) ; }
	 * 
	 */
	
	
	
	
	
	
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testUpdateBid() throws Exception {
	 * mvc.perform(get("/bidList/update/{id}",69)) .andExpect(status().isOk()) ; }
	 * 
	 */
	 
	
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testSaveUpdateBid() throws Exception {
	 * mvc.perform(post("/bidList/update")) .andExpect(status().is2xxSuccessful()) ;
	 * }
	 */
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void testAddBidWithData() throws Exception { User user = new
	 * User(); mockMvc.perform(post("/signup").contentType(MediaType.
	 * APPLICATION_FORM_URLENCODED).with(csrf()).content(
	 * "{\"email\":\"demo@example.com\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"
	 * )) .andDo(print()).andExpect(status().isCreated()).andExpect(content().
	 * contentType("text/html;charset=UTF-8"))
	 * .andExpect(content().string(containsString("Signup for an account")));
	 * //.andExpect(content().source()); }
	 */
		  
		/*
		 * mvc.perform(get("bidList/list")) .andExpect(status().is2xxSuccessful())
		 * .andExpect(view().name("bidList/list")) .andExpect(model().size(1))
		 * .andExpect(model().attributeExists("bidList/list"));
		 */

	

	/*
	 * @Autowired private WebApplicationContext context;
	 *//*
		 * @Autowired private WebApplicationContext context;
		 * 
		 * @Autowired private BidListController bidListController;
		 */

	/*
	 * @Before public void setup() { mvc = MockMvcBuilders
	 * .webAppContextSetup(context) .apply(springSecurity()) .build();
	 * 
	 * }
	 */

	/*
	 * @Test void contextLoads()throws Exception {
	 * assertThat(bidListController).isNotNull(); }
	 */
	/*
	 * @WithMockUser(value = "test")
	 * 
	 * @Test public void givenAuthRequestOnBidService_shouldSucceedWith200() throws
	 * Exception {
	 * mvc.perform(get("/bidList/list").contentType(MediaType.APPLICATION_JSON))
	 * .andExpect(status().isOk()); }
	 */
}