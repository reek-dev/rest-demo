package com.example.restdemo.service.impl;

import com.example.restdemo.dto.CreateUserDTO;
import com.example.restdemo.dto.UserCountResponseDTO;
import com.example.restdemo.dto.UserDetailsDTO;
import com.example.restdemo.dto.UserByOrgDTO;
import com.example.restdemo.entity.*;
import com.example.restdemo.exception.*;
import com.example.restdemo.mapper.UserMapper;
import com.example.restdemo.repository.CityRepository;
import com.example.restdemo.repository.OrganisationRepository;
import com.example.restdemo.repository.StateRepository;
import com.example.restdemo.repository.UserRepository;
import com.example.restdemo.service.UserService;
import com.example.restdemo.util.CaseConverter;
import com.example.restdemo.util.EmailValidator;
import com.example.restdemo.util.NameValidator;
import com.example.restdemo.util.PhoneValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final OrganisationRepository organisationRepository;

    private final StateRepository stateRepository;

    private final CityRepository cityRepository;



//    @Override
//    public User createUser(User newUser) {
//        if (userRepository.existsByEmail(newUser.getEmail())) {
//            throw new EmailAlreadyExistsException(newUser.getEmail());
//        }
//
//        if (!EmailValidator.isEmailValid(newUser.getEmail())) {
//            throw new InvalidEmailException(newUser.getEmail());
//        }
//
//        if (userRepository.existsByPhone(newUser.getPhone())) {
//            throw new PhoneNumberAlreadyExistsException(newUser.getPhone());
//        }
//        return userRepository.save(newUser);
//    }

    @Override
    public CreateUserDTO createUser(CreateUserDTO userDTO) {

        if (!NameValidator.isNameValid(userDTO.getFirstName().trim()))
            throw new InvalidNameException(userDTO.getFirstName().trim());

        if (!NameValidator.isNameValid(userDTO.getLastName().trim()))
            throw new InvalidNameException(userDTO.getLastName().trim());

        if (!EmailValidator.isEmailValid(userDTO.getEmailAddress().trim().toLowerCase()))
            throw new InvalidEmailException(userDTO.getEmailAddress());

        if (!PhoneValidator.isPhoneValid(userDTO.getPhoneNumber().trim()))
            throw new InvalidPhoneException(userDTO.getPhoneNumber());

        Organisation organisation = organisationRepository.findById(userDTO.getOrganisationId())
                .orElseThrow(() -> new ResourceNotFoundException("Organisation", "id", String.valueOf(userDTO.getOrganisationId())));

        String roleString = userDTO.getRole().trim().toUpperCase();

        Role role = switch (roleString) {
            case "ADMIN" -> Role.ADMIN;
            case "TEACHER" -> Role.TEACHER;
            case "STUDENT" -> Role.STUDENT;
            default -> null;
        };

        State state = stateRepository.findById(userDTO.getStateId())
                .orElseThrow(() -> new ResourceNotFoundException("Organisation", "id", String.valueOf(userDTO.getStateId())));

        City city = cityRepository.findById(userDTO.getCityId())
                .orElseThrow(() -> new ResourceNotFoundException("Organisation", "id", String.valueOf(userDTO.getCityId())));


        if (userRepository.existsByEmail(userDTO.getEmailAddress().trim().toLowerCase()))
            throw new ResourceAlreadyExistsException("User", "email", userDTO.getEmailAddress());

        if (userRepository.existsByPhone(userDTO.getPhoneNumber().trim()))
            throw new ResourceAlreadyExistsException("User", "phone", userDTO.getPhoneNumber());

        String genderString = userDTO.getGender().trim().toUpperCase();
        Gender gender = switch (genderString) {
            case "MALE" -> Gender.MALE;
            case "FEMALE" -> Gender.FEMALE;
            default -> null;
        };

        User user = new User();
        user.setRole(role);
        user.setOrganisation(organisation);
        user.setState(state);
        user.setCity(city);
        user.setEmail(userDTO.getEmailAddress().trim().toLowerCase());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(CaseConverter.convertToTitleCase(userDTO.getFirstName().trim()));
        user.setLastName(CaseConverter.convertToTitleCase(userDTO.getLastName().trim()));
        user.setPhone(userDTO.getPhoneNumber().trim());
        user.setAddress(userDTO.getAddress().trim());
        user.setGender(gender);
        user.setDob(LocalDate.parse(userDTO.getDateOfBirth(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        user.setJoiningDate(LocalDate.parse(userDTO.getJoiningDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        userRepository.save(user);
        return userDTO;
    }

    //    @Override
//    public List<UserIdAndNameDTO> getAllUserIdAndName() {
//        List<User> possibleUsers = userRepository.getAllUsers();
//        return possibleUsers.stream()
//                .map(UserMapper::mapToUserIdAndNameDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<UsersListDTO> getAllUsersList() {
//        List<User> possibleUsers = userRepository.getAllUsers();
//        return possibleUsers.stream()
//                .map(UserMapper::mapToUsersListDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<UserDTO> getAllUsers() {
//        List<User> possibleExistingUsers = userRepository.findAll();
//        return possibleExistingUsers.stream()
//                .map(UserMapper::mapToUserDTO)
//                .collect(Collectors.toList());
//    }
//
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", String.valueOf(id)));
    }

    @Override
    public UserDetailsDTO getUserDetailsDtoById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", String.valueOf(id)));
        return UserMapper.mapToUserDetailsDTO(user);
    }

    @Override
    public List<UserByOrgDTO> getUserListByOrgDtoByOrgId(Long id) {
        List<User> users = userRepository.findUsersByOrganizationId(id);
        return users.stream()
                .map(UserMapper::mapToUserByOrgDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserCountResponseDTO> getUserRoleCountByOrgId(Long id) {

        return null;
    }


    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User userToRemove = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(id)));

        userToRemove.setOrganisation(null);
        userToRemove.setState(null);
        userToRemove.setCity(null);
        userToRemove.setAssignedCourses(null);

        userRepository.deleteById(userToRemove.getId());

    }
//

//    @Override
//    public UserDTO updateUser(UserDTO user) {
//        User possibleExistingUser = userRepository.findById(user.getId())
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(user.getId())));
//
//        if (user.getFirstName() != null) possibleExistingUser.setFirstName(user.getFirstName());
//        if (user.getLastName() != null) possibleExistingUser.setLastName(user.getLastName());
//        if (user.getEmail() != null) possibleExistingUser.setEmail(user.getEmail());
//        if (user.getAddress() != null) possibleExistingUser.setAddress(user.getAddress());
//        if (user.getPhone() != null) possibleExistingUser.setPhone(user.getPhone());
//
//        User updatedUser = userRepository.save(possibleExistingUser);
//        return UserMapper.mapToUserDTO(updatedUser);
//    }
//
//    @Override
//    public void deleteUser(Long id) {
//        userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(id)));
//        userRepository.deleteById(id);
//    }
}
