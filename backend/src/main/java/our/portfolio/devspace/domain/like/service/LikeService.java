package our.portfolio.devspace.domain.like.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import our.portfolio.devspace.common.mapper.LikeMapper;
import our.portfolio.devspace.domain.like.dto.CreateLikeRequest;
import our.portfolio.devspace.domain.like.dto.CreateLikeResponse;
import our.portfolio.devspace.domain.like.dto.GetLikeResponse;
import our.portfolio.devspace.domain.like.entity.Like;
import our.portfolio.devspace.domain.like.repository.LikeRepository;
import our.portfolio.devspace.exception.CustomException;

import java.util.List;

import static our.portfolio.devspace.exception.ErrorDetail.INVALID_PARAMETER_VALUE;
import static our.portfolio.devspace.exception.ErrorDetail.POSTS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final LikeMapper likeMapper;

    public List<GetLikeResponse> listLikes(Long id, Pageable pageable) {

        if (pageable.getPageSize() > 8) {
            throw new CustomException(INVALID_PARAMETER_VALUE);
        }
        return likeRepository.findLikeUserByPostId(id, pageable);
    }

    public CreateLikeResponse createLike(CreateLikeRequest likeRequest, Long userId) {
        Like like = likeMapper.toEntity(likeRequest, userId);

        try {
            likeRepository.save(like);
        } catch (IllegalArgumentException e) {
            throw new CustomException(POSTS_NOT_FOUND);
        }

        return likeMapper.toCreateLikeResponse(like);
    }
}
