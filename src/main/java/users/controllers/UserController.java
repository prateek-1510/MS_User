package users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import users.controllers.request.AuthenticationRequest;
import users.controllers.response.AuthenticationResponse;
import users.model.User;
import users.services.UserDetailsService;
import users.utils.GenerateTokenUtils;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private org.springframework.security.core.userdetails.UserDetailsService userDetails;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private GenerateTokenUtils jwtUtil;

    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers()
    {

        return userDetailsService.fetchUsers();
    }

    @PostMapping(value = "/authenticate")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
        UserDetails userDetail=userDetails.loadUserByUsername("");
        String jwtToken=jwtUtil.generateToken(userDetail);
        return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(jwtToken), HttpStatus.OK);
    }




}
