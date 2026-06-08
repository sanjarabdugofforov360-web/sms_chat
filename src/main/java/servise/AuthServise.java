package servise;

import dtos.LoginDto;
import dtos.UserDto;
import entity.Profile;
import enums.UserRole;
import enums.UserStatus;
import repository.UserRepository;
import utils.Utill;

import java.util.Optional;
import java.util.UUID;

public class AuthServise {
    private final UserRepository userRepository = UserRepository.geInstanse();

    private static AuthServise authServise;

    private AuthServise() {

    }

    public static AuthServise geInstanse() {
        if (authServise == null) {
            authServise = new AuthServise();
        }
        return authServise;
    }


    public boolean registraton(UserDto userDto) {

        Optional<Profile> userByEmail = userRepository.getUserByEmail(userDto.email());

        if (userByEmail.isPresent()) return false;

        Profile user = new Profile(UUID.randomUUID().toString(), userDto.fullname(), userDto.email(), userDto.pasword(), UserStatus.ACTIVE, UserRole.USER);

        userRepository.savaUser(user);


        return true;
    }

    public Optional<UserRole> login(LoginDto logindto) {

        Optional<Profile> optional = userRepository.getUserByEmail(logindto.email());
        if (optional.isEmpty()) return Optional.empty();

        Profile user = optional.get();

        if (!user.getPassword().equals(logindto.password())) return Optional.empty();

        Utill.currentUserId = user.getId();


        return Optional.of(user.getRole());
    }
}
