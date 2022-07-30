package our.portfolio.devspace.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import our.portfolio.devspace.domain.user.entity.User;
import our.portfolio.devspace.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 User ID 입니다."));
    }
}
