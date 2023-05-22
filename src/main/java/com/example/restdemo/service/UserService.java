package com.example.restdemo.service;


import com.example.restdemo.dto.*;
import com.example.restdemo.entity.User;

import java.util.List;

public interface UserService {

    public CreateUserDTO createUser(CreateUserDTO userDTO);

    public User getUserById(Long id);

    public UserDetailsDTO getUserDetailsDtoById(Long id);

    public List<UserByOrgDTO> getUserListByOrgDtoByOrgId(Long id);

    public List<UserCountResponseDTO> getUserRoleCountByOrgId(Long id);

    public List<UserIdAndNameDTO> getUserIdAndNameByOrgAndRole(Long organisationId, int roleId);

    public void saveUser(User instructor);

    public void deleteUser(Long id);

}
