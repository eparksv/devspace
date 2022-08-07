package our.portfolio.devspace.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import our.portfolio.devspace.domain.user.entity.User;
import our.portfolio.devspace.domain.user.repository.UserRepository;
import our.portfolio.devspace.exception.CustomException;
import our.portfolio.devspace.exception.ErrorDetail;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomException("User Id " + id + "에 해당하는 유저가 없습니다.", ErrorDetail.USER_NOT_FOUND));
    }
}
