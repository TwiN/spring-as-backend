package org.twinnation.springasbackend.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles({"test", "test-security"})
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
@WebAppConfiguration
public class UserControllerTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders
			.webAppContextSetup(webApplicationContext)
			.apply(springSecurity())
			.build();
	}
	
	
	@Test
	public void getCurrentUser_asUnauthenticatedUser() throws Exception {
		mockMvc
			.perform(get("/api/v1/users/me"))
			.andExpect(status().is4xxClientError());
	}
	
	
	@Test
	@WithUserDetails(value = "root", userDetailsServiceBeanName = "userService")
	public void getCurrentUser_asUser() throws Exception {
		mockMvc
			.perform(get("/api/v1/users/me"))
			.andExpect(status().is2xxSuccessful());
	}
	
	
	@Test
	public void createUser_asUnauthenticatedUser() throws Exception {
		mockMvc
			.perform(post("/api/v1/users?username=twin&password=dontbotheritsnotmyrealpassword"))
			.andExpect(status().is2xxSuccessful());
	}
	
	
	@Test
	@WithMockUser(username = "johndoe")
	public void createUser_asUser() throws Exception {
		mockMvc
			.perform(post("/api/v1/users?username=twin&password=dontbotheritsnotmyrealpassword"))
			.andExpect(status().is4xxClientError());
	}
	
}