package africa.semicolon.loanAppSystem.controllers;

import africa.semicolon.loanAppSystem.data.models.UserRole;
import africa.semicolon.loanAppSystem.dtos.request.UserRegistrationRequest;
import africa.semicolon.loanAppSystem.dtos.response.UserRegistrationResponse;
import africa.semicolon.loanAppSystem.exceptions.UserAlreadyExistsException;
import africa.semicolon.loanAppSystem.exceptions.UserRegistrationFailedException;
import africa.semicolon.loanAppSystem.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3001")
public class UserController {
    private final UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest request) {
        try {
            UserRegistrationResponse response = userService.register(request);
            return ResponseEntity.ok(response);
        } catch (UserRegistrationFailedException | UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        try {
            var response = userService.getUserById(id);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        try {
            var response = userService.getAllUsers();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
    @GetMapping("/{role}")
    public ResponseEntity<?> getAllUsersByRole(@PathVariable UserRole role){
        try {
            var response = userService.getUsersByRole(role);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
//        try {
            var response = userService.deleteUser(id);
            return ResponseEntity.ok(response);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e);
//        }
    }
}
