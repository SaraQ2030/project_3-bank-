package com.example.project_3.Service;

import com.example.project_3.API.ApiException;
import com.example.project_3.Model.User;
import com.example.project_3.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    public void registerCustomer(User user){
        user.setRole("CUSTOMER");
        String hashPassword=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);
    }

    public void registerEmployee(User user){
        user.setRole("EMPLOYEE");
        String hashPassword=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);
        authRepository.save(user);
    }

//    public void register(User user ){
//        if (user.getRole().equalsIgnoreCase("customer")){
//            user.setRole("CUSTOMER");
//        }
//        else if (user.getRole().equalsIgnoreCase("employee")){
//            user.setRole("EMPLOYEE");
//        }
//
//        String hashPassword=new BCryptPasswordEncoder().encode(user.getPassword());
//        user.setPassword(hashPassword);
//        authRepository.save(user);
//    }

    public void login(String username, String password) {
        User u=authRepository.findUserByUsername(username);
        if (u==null){
            throw new ApiException("you are not registered to our system yet") ;
        }
        if (u.getPassword().equals(password))
            throw new ApiException("you are logged in") ;
    }
    public void logout(){}


    public List<User> getAllUsers(){
        return authRepository.findAll();
    }
    public void updateUser(String username,User user){
        User u=authRepository.findUserByUsername(username);
        if (u==null){
            throw new ApiException("you are not register to the system yet") ;
        }
        u.setName(user.getName());
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        authRepository.save(u);
    }

    public void deleteUser(String usernameDel){
        User userDel=authRepository.findUserByUsername(usernameDel);
        if (userDel==null){
            throw new ApiException("this user name not in the system yet " +usernameDel) ;
        }
        authRepository.delete(userDel);
    }

}
