package mate.academy.spring.controller;

import javax.validation.Valid;
import mate.academy.spring.dto.request.UserRequestDto;
import mate.academy.spring.dto.response.UserResponseDto;
import mate.academy.spring.exception.AuthenticationException;
import mate.academy.spring.model.User;
import mate.academy.spring.service.AuthenticationService;
import mate.academy.spring.service.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import validation.EmailValidator;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;
    private final UserMapper userMapper;
    private final EmailValidator emailValidator;

    public AuthenticationController(AuthenticationService authService,
                                    UserMapper userMapper, EmailValidator emailValidator) {
        this.authService = authService;
        this.userMapper = userMapper;
        this.emailValidator = emailValidator;
    }

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRequestDto requestDto)
            throws AuthenticationException {
        User user = authService.register(requestDto.getEmail(), requestDto.getPassword());
        return userMapper.mapToDto(user);
    }
}
