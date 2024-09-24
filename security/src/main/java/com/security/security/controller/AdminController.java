package com.security.security.controller;
import com.security.security.model.AppUser;
import com.security.security.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/secure")
public class AdminController {
    @Autowired
    private AppUserService userService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        List<AppUser> userList = userService.getAllUsers();
        if(userList != null || !userList.isEmpty()){
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
