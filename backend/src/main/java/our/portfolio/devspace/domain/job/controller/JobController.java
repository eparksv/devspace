package our.portfolio.devspace.domain.job.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import our.portfolio.devspace.common.dto.HttpResponseBody;
import our.portfolio.devspace.domain.job.dto.ListJobsResponse;
import our.portfolio.devspace.domain.job.service.JobService;

@RequiredArgsConstructor
@RestController
public class JobController {
    private final JobService jobService;

    @GetMapping("/api/jobs")
    public ResponseEntity<HttpResponseBody<ListJobsResponse>> listJobs() {
        HttpResponseBody<ListJobsResponse> body = new HttpResponseBody<>("직군 목록이 조회되었습니다.", jobService.listJobs());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
