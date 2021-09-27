package com.example.bankAccountProject.services;

import antlr.debug.MessageEvent;
import com.example.bankAccountProject.DAORepository.CreditCardRepository;
import com.example.bankAccountProject.DAORepository.UserRepository;
import com.example.bankAccountProject.dto.UserDTO;
import com.example.bankAccountProject.exception.UserException;
import com.example.bankAccountProject.model.CardRequest;
import com.example.bankAccountProject.model.CreditCard;
import com.example.bankAccountProject.model.Role;
import com.example.bankAccountProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.bankAccountProject.messages.Messages.*;

import java.util.List;
import java.util.Optional;

import static com.example.bankAccountProject.messages.Messages.USER_NOT_FOUND_MSG;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private CreditCardRepository creditCardRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder,
                       CreditCardRepository creditCardRepository,
                       UserRepository userRepository) {
        this.creditCardRepository = creditCardRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Optional<User> show(Long id) {
        return userRepository.findById(id);
    }

    public void changeUserState(User user) {
        if (user.getState() == User.State.BLOCKED) {
            user.setState(User.State.ACTIVE);
        } else {
            user.setState(User.State.BLOCKED);
        }
        userRepository.save(user);
    }

    public List<User> showAllUsers(){
        return userRepository.findAll();
    }

    public void register(UserDTO userDTO) throws UserException {


        User newUser = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .state(User.State.ACTIVE)
                .role(Role.ROLE_USER)
                .build();

        userRepository.save(newUser);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }
}
