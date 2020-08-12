package de.appsfactory.userportal.adapter.web;

import de.appsfactory.userportal.adapter.web.request.CreateUserRequest;
import de.appsfactory.userportal.adapter.web.request.UpdateUserRequest;
import de.appsfactory.userportal.adapter.web.response.GetUserResponse;
import de.appsfactory.userportal.user.*;
import de.appsfactory.userportal.user.port.in.CreateOrUpdateUserUseCase;
import de.appsfactory.userportal.user.port.in.DeleteUserUseCase;
import de.appsfactory.userportal.user.port.in.GetUserQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static de.appsfactory.userportal.adapter.web.pages.Mappings.*;
import static de.appsfactory.userportal.util.DateUtil.LOCAL_DATE_FORMAT;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = DOMAIN_USER)
class UserController {

    private final GetUserQuery getUserQuery;
    private final CreateOrUpdateUserUseCase createOrUpdateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UserControllerMapper userControllerMapper;
    private final PasswordEncoder passwordEncoder;

    @RequestMapping(value = DOMAIN_USER_GET_ALL, method = RequestMethod.GET)
    public String getUsers(Model model) {
        log.debug("Getting all users");
        List<User> getUserQueryUsers = getUserQuery.getUsers();
        List<GetUserResponse> userResponseList = getUserQueryUsers.stream()
                .map(userControllerMapper::mapToResponseUser)
                .collect(Collectors.toList());
        model.addAttribute("userResponseList", userResponseList);
        return PAGE_USER_ALL;
    }

    @RequestMapping(value = DOMAIN_USER_ADD, method = RequestMethod.GET)
    public String addNewUser(ModelMap view) {
        log.debug("Add a new user");
        CreateUserRequest createUserRequest = new CreateUserRequest();
        view.addAttribute("createUserRequest", createUserRequest);
        return PAGE_USER_ADD;
    }

    @RequestMapping(value = DOMAIN_USER_ADD, method = RequestMethod.POST)
    public String createUser(@Valid CreateUserRequest createUserRequest, BindingResult result, ModelMap view) {
        if (result.hasErrors()) {
            log.debug("Error while creating a user");
            return PAGE_USER_ADD;
        }
        User user = User.withoutId(UserFullName.from(createUserRequest.getFirstName(), createUserRequest.getLastName()),
                UserDob.from(createUserRequest.getDob()),
                UserAddress.from(createUserRequest.getAddress1(), createUserRequest.getAddress2(), createUserRequest.getAddress3(),
                        UserPostalCode.from(createUserRequest.getPostcode()), createUserRequest.getCity(), createUserRequest.getCountryName(),
                        UserCountryCode.from(createUserRequest.getCountryISOCode())),
                UserEmail.from(createUserRequest.getEmail()),
                UserPassword.from(passwordEncoder.encode(createUserRequest.getPassword())),
                UserGender.from(createUserRequest.getGender()));
        log.debug("Creating a new user");
        createOrUpdateUserUseCase.create(user);
        return redirectToAllUsers();
    }

    private String redirectToAllUsers() {
        return "redirect:" + DOMAIN_USER + DOMAIN_USER_GET_ALL;
    }

    @RequestMapping(value = {DOMAIN_USER_EDIT + "/{id}",}, method = RequestMethod.GET)
    public String editUser(ModelMap view, @PathVariable long id) {
        log.debug("Editing user");
        User queryUser = getUserQuery.getUser(new User.UserId(id));
        view.addAttribute("updateUserRequest", userControllerMapper.mapToResponseUser(queryUser));
        return PAGE_USER_EDIT;
    }

    @RequestMapping(value = {DOMAIN_USER_EDIT + "/{id}"}, method = RequestMethod.POST)
    public String updateUser(@Valid UpdateUserRequest updateUserRequest, BindingResult result, ModelMap view, @PathVariable String id) {
        log.debug("Updating user");
        if (result.hasErrors()) {
            return PAGE_USER_EDIT;
        }
        User userToUpdate = User.withId(new User.UserId(Long.valueOf(id)),
                UserFullName.from(updateUserRequest.getFirstName(), updateUserRequest.getLastName()),
                UserDob.from(updateUserRequest.getDob()),
                UserAddress.from(updateUserRequest.getAddress1(), updateUserRequest.getAddress2(), updateUserRequest.getAddress3(),
                        UserPostalCode.from(updateUserRequest.getPostcode()), updateUserRequest.getCity(), updateUserRequest.getCountryName(),
                        UserCountryCode.from(updateUserRequest.getCountryISOCode())),
                UserEmail.from(updateUserRequest.getEmail()),
                null,
                UserGender.from(updateUserRequest.getGender()));
        createOrUpdateUserUseCase.update(userToUpdate);
        return redirectToAllUsers();
    }

    @RequestMapping(value = {DOMAIN_USER_DELETE + "/{id}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable long id) {
        deleteUserUseCase.deleteUser(new User.UserId(id));
        return redirectToAllUsers();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat(LOCAL_DATE_FORMAT), true));
    }
}
