package org.hektor7.batsellermanager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.apache.commons.lang3.RandomStringUtils;
import org.hektor7.batsellermanager.domain.AppUser;
import org.hektor7.batsellermanager.service.AppUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-DispatcherServlet-context.xml")
@WebAppConfiguration
public class AppUserControllerTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private AppUserService appUserService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testGetProducts() throws Exception {
		this.mockMvc.perform(get("/appUsers")).andExpect(
				model().attributeExists("appUsers"));
	}

	@Test
	public void testGetAppUsersById() throws Exception {
		// Arrange
		AppUser appUser = this.obtainInsertedAppUser();

		// Act & Assert
		this.mockMvc
				.perform(
						get("/appUsers/appUser").param("id",
								appUser.getId().toString()))
				.andExpect(model().attributeExists("appUser"))
				.andExpect(model().attribute("appUser", appUser));

	}

	@Test
	public void testInsertAppUserOk() throws Exception {
		// Arrange
		AppUser appUser = this.createValidAppUser();

		// Act & Assert
		mockMvc.perform(
				post("/appUsers/add").contentType(
						MediaType.APPLICATION_FORM_URLENCODED).param(
						"userName", appUser.getUserName())).andExpect(
				view().name("redirect:/appUsers"));
		/*
		 * .andExpect(status().isOk()) .andExpect(view().name("todo/add"))
		 * .andExpect(forwardedUrl("/WEB-INF/jsp/todo/add.jsp"))
		 * .andExpect(model().attributeHasFieldErrors("todo", "title"))
		 * .andExpect(model().attributeHasFieldErrors("todo", "description"))
		 * .andExpect(model().attribute("todo", hasProperty("id", nullValue())))
		 * .andExpect(model().attribute("todo", hasProperty("description",
		 * is(description)))) .andExpect(model().attribute("todo",
		 * hasProperty("title", is(title))));
		 */

	}

	@Test
	public void testInsertAppUserUsernameError() throws Exception {
		// Arrange
		

		// Act & Assert
		mockMvc.perform(
				post("/appUsers/add").contentType(
						MediaType.APPLICATION_FORM_URLENCODED)).andExpect(
				model().attributeHasFieldErrors("newAppUser", "userName"));

	}

	private AppUser obtainInsertedAppUser() {
		AppUser appUser = null;

		if (!this.appUserService.getAllAppUsers().isEmpty()) {
			appUser = this.appUserService.getAllAppUsers().get(0);
		} else {
			appUser = this.insertValidUser();
		}

		return appUser;
	}

	private AppUser insertValidUser() {
		return this.appUserService.addAppUser(this.createValidAppUser());

	}

	private AppUser createValidAppUser() {

		AppUser appUser = new AppUser();

		appUser.setFirstSurname(RandomStringUtils.randomAlphabetic(10));
		appUser.setSecondSurname(RandomStringUtils.randomAlphabetic(10));
		appUser.setName(RandomStringUtils.randomAlphabetic(5));
		appUser.setPassword(RandomStringUtils.randomAlphabetic(10));
		appUser.setUserName(RandomStringUtils.randomAlphabetic(5));
		// TODO: contactInfo

		return appUser;
	}

}