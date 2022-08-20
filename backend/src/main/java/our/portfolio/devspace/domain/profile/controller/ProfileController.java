package our.portfolio.devspace.domain.profile.controller;

import java.security.Principal;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import our.portfolio.devspace.common.dto.HttpResponseBody;
import our.portfolio.devspace.domain.profile.dto.CreateProfileDto;
import our.portfolio.devspace.domain.profile.service.ProfileService;

@RequiredArgsConstructor
@RestController
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping(value = "/api/profiles", produces = "application/json; charset=utf-8")
    public ResponseEntity<HttpResponseBody<CreateProfileDto>> createProfile(@RequestBody @Valid CreateProfileDto requestDto, Principal userPrincipal) {

        CreateProfileDto newProfile = profileService.createProfile(Long.parseLong(userPrincipal.getName()), requestDto);

        // HTTP Status Code: 201 Created, Response Body: { message, data: ProfileCreationResponse}
        HttpResponseBody<CreateProfileDto> body = new HttpResponseBody<>("프로필이 저장되었습니다.", newProfile);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }
}
