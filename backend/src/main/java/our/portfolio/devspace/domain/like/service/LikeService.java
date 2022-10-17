package our.portfolio.devspace.domain.like.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import our.portfolio.devspace.domain.like.dto.GetLikeResponse;
import our.portfolio.devspace.domain.like.repository.LikeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public List<GetLikeResponse> listLikes(Long id) {

        return likeRepository.findLikeUserByPostId(id);
    }
}
