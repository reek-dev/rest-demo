package com.example.restdemo.service;


import com.example.restdemo.entity.User;


import java.util.List;

public interface UserService {

    public User createUser(User newUser);

//    public List<UserIdAndNameDTO> getAllUserIdAndName();
//
//    public List<UsersListDTO> getAllUsersList();
//
//    public List<UserDTO> getAllUsers();

    public User getUserById(Long id);

    public void saveUser(User instructor);


//    public UserDTO updateUser(UserDTO user);
//
//    public void deleteUser(Long id);
}
