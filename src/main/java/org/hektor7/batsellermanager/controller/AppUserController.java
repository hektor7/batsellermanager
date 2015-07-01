package org.hektor7.batsellermanager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.hektor7.batsellermanager.domain.AppUser;
import org.hektor7.batsellermanager.exception.AppUserNotFoundException;
import org.hektor7.batsellermanager.service.AppUserService;
import org.hektor7.batsellermanager.validator.AppUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * AppUser controller
 * 
 * @author hector
 *
 */
@Controller
@RequestMapping("/appUsers")
public class AppUserController {

	/**
	 * AppUser service.
	 */
	@Autowired
	private AppUserService appUserService;

	/**
	 * AppUser validator.
	 */
	@Autowired
	private AppUserValidator appUserValidator;

	/**
	 * Binding initializer for the binding of fields.
	 * It improves the security and defines which fields
	 * are allowed.
	 * 
	 * @param binder binder
	 */
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {

		binder.setAllowedFields("id", "name", "firstSurname", "secondSurname",
				"userName", "password");

		binder.setValidator(this.appUserValidator);
	}

	/**
	 * Handler method that obtains a list of all appUsers.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String list(Model model) {
		model.addAttribute("appUsers", this.appUserService.getAllAppUsers());
		return "appUsers/list";
	}

	
	/**
	 * Handler method for obtaining details for a given appUser's id.
	 * 
	 * @param appUserId appUser id
	 * @param model from the view
	 * @return navigation string
	 */
	@RequestMapping("/appUser")
	public String getAppUserById(@RequestParam("id") String appUserId,
			Model model) {

		Long id = 0L;
		if (!StringUtils.isEmpty(appUserId)) {
			id = Long.valueOf(appUserId);
		}

		// The service's method throws a non-checked exception that will be
		// handled by the handleError method.
		model.addAttribute("appUser", this.appUserService.getAppUserById(id));

		return "appUsers/details";
	}

	/**
	 * Handler method for deletion of appUser.
	 * 
	 * @param appUserId to be deleted
	 * @param model of the view
	 * @return navigation string
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteAppUser(@RequestParam("id") String appUserId,
			Model model) {

		Long id = 0L;
		if (!StringUtils.isEmpty(appUserId)) {
			id = Long.valueOf(appUserId);
		}

		if (id > 0L) {
			this.appUserService.removeAppUser(this.appUserService
					.getAppUserById(id));
		}

		return "redirect:/appUsers";
	}

	/**
	 * Handler method that obstains the form for new appUser
	 * 
	 * @param newAppUser new appUser
	 * @return navigation string
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewAppUserForm(
			@ModelAttribute("appUser") AppUser newAppUser) {
		return "appUsers/form";
	}

	/**
	 * Handler Method that process a new appUser in order to insert it.
	 * 
	 * @param appUser to be inserted
	 * @param result binding result
	 * @param request http request
	 * @return navigation string
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewAppUserForm(
			@ModelAttribute("appUser") @Valid AppUser appUser,
			BindingResult result, // For customize the WebDataBinder
			HttpServletRequest request) {

		return processAndSaveAppUser(appUser, result);
	}

	/**
	 * Handler method that obtains the modify form.
	 * 
	 * @param appUserId appUser id
	 * @param model view model
	 * @return navigation string
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String getModifyForm(@RequestParam("id") String appUserId,
			Model model) {

		Long id = 0L;
		if (!StringUtils.isEmpty(appUserId)) {
			id = Long.valueOf(appUserId);
		}

		model.addAttribute("appUser", this.appUserService.getAppUserById(id));

		return "appUsers/form";
	}

	/**
	 * Handler method for modify appUsers.
	 * 
	 * @param appUser to be modified
	 * @param result binding result
	 * @param request http request
	 * @return string navigation
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String processModifyAppUserForm(
			@ModelAttribute("appUser") @Valid AppUser appUser,
			BindingResult result, // For customize the WebDataBinder
			HttpServletRequest request) {

		return this.processAndSaveAppUser(appUser, result);
	}

	/**
	 * Handler method that for insert and update appUsers
	 * 
	 * @param appUser to be saved (updated or inserted)
	 * @param result binding result
	 * @return navigation string
	 */
	private String processAndSaveAppUser(AppUser appUser, BindingResult result) {
		if (result.hasErrors()) {
			return "appUsers/form";
		}

		this.checkForNonAllowedFieldsOnInsert(result);

		this.appUserService.saveAppUser(appUser);

		return "redirect:/appUsers";
	}

	/**
	 * Check for non allowed fields on insert.
	 * 
	 * @param result
	 *            Binding result
	 */
	private void checkForNonAllowedFieldsOnInsert(BindingResult result) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
	}

	/**
	 * Method for handling of AppUserNotFoundException exception.
	 * 
	 * @param req http request
	 * @param exception exception
	 * @return Model and view
	 */
	@ExceptionHandler(AppUserNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest req,
			AppUserNotFoundException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidAppUserId", exception.getAppUserId());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
		mav.setViewName("appUserNotFound");
		return mav;
	}

}