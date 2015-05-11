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

	@Autowired
	private AppUserService appUserService;

	@Autowired
	private AppUserValidator appUserValidator;

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {

		binder.setAllowedFields("id", "name", "firstSurname", "secondSurname",
				"userName", "password");

		binder.setValidator(this.appUserValidator);
	}

	@RequestMapping
	public String list(Model model) {
		model.addAttribute("appUsers", this.appUserService.getAllAppUsers());
		return "appUsers";
	}

	@RequestMapping("/all")
	public String allAppUsers(Model model) {
		model.addAttribute("appUsers", this.appUserService.getAllAppUsers());

		return "appUsers";
	}

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

		return "appUser";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewAppUserForm(
			@ModelAttribute("newAppUser") AppUser newAppUser) {
		return "addAppUser";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewAppUserForm(
			@ModelAttribute("newAppUSer") @Valid AppUser newAppUser,
			BindingResult result, // For customize the WebDataBinder
			HttpServletRequest request) {

		if (result.hasErrors()) {
			return "addAppUser";
		}

		this.checkForNonAllowedFieldsOnInsert(result);

		this.appUserService.addAppUser(newAppUser);

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