package ryhor.mudrahel.jsusermngt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ryhor.mudrahel.jsusermngt.entity.User;
import ryhor.mudrahel.jsusermngt.service.UserService;

@Controller // classic/common annotation to specify that this class will
@RequestMapping("/users") //indicate that controller will handle all http request which starts with /users
// like localhost:8080/users
public class UserClassicController {

    private UserService userService;

    //here we assume @Autowired annotation, but not required since Spring 4.3
    public UserClassicController(UserService userService) {
        this.userService = userService;
    }

    // Due to @RequestMapping annotation on class level, actual path for this method to react is "/users"
    @GetMapping("") //indicate that we expect http GET request here
    //@RequestMapping(value="", method = RequestMethod.GET) //alternative to use
    //@RequestMapping(value="") //if we don't specify http method, it will expect all of them, lik GET,POST,PUT,..
    public String listUsers(Model model) {
        System.out.println("mapped to listUsers");
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @PostMapping("") // good of example, we expect POST request on same "/users" path to save new User
    public String saveUser(@ModelAttribute("user") User user) {
        System.out.println("mapped to saveUser");
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String getUser(Model model, @PathVariable("id") Long id) {
        System.out.println("mapped to getUser");
        model.addAttribute("users", userService.getUserById(id));
        return "users";
    }

    @GetMapping("/new")
    public String createUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "create_user";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit_user";
    }

    @PutMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user, Model model) {
        System.out.println("Mapped to update user");
        User existingUser = userService.getUserById(id);

        existingUser.setId(user.getId());
        existingUser.setUserName(user.getUserName());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        System.out.println(existingUser);
        userService.updateUser(existingUser);
        return "redirect:/users";
    }

    @PatchMapping("/{id}")
    public String partialUpdateUser(@PathVariable Long id, @RequestBody User user, Model model) {
        System.out.println("Mapped to patch user");
        User existingUser = userService.getUserById(id);

        existingUser.setId(user.getId());
        existingUser.setUserName(user.getUserName());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        System.out.println(existingUser);
        userService.updateUser(existingUser);
        return "redirect:/users";
    }

    public UserService getUserService() {
        return userService;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        System.out.println("mapped to deleteUser");
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
