package com.waleed.task17.Controller;

import com.waleed.task17.Api.ApiResponse;
import com.waleed.task17.Entity.User;
import com.waleed.task17.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<ApiResponse> getAllUsers() {
        return ResponseEntity.status(200).body(new ApiResponse("Success", userService.getAllUsers()));
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage(), null));
        }
        int result = userService.addUser(user);
        if (result == 1) {
            return ResponseEntity.status(400).body(new ApiResponse("Email already exists", null));
        }
        return ResponseEntity.status(201).body(new ApiResponse("User added successfully", user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage(), null));
        }
        int result = userService.updateUser(id, user);
        if (result == 1) {
            return ResponseEntity.status(404).body(new ApiResponse("User not found", null));
        }
        return ResponseEntity.status(200).body(new ApiResponse("User updated successfully", user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (!deleted) {
            return ResponseEntity.status(404).body(new ApiResponse("User not found", null));
        }
        return ResponseEntity.status(200).body(new ApiResponse("User deleted successfully", null));
    }
}