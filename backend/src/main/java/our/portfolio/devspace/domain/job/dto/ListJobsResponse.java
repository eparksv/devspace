package our.portfolio.devspace.domain.job.dto;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import our.portfolio.devspace.domain.job.entity.Job;
import our.portfolio.devspace.domain.job.entity.JobType;

@Getter
public class ListJobsResponse {

    private final List<JobResponse> developers;
    private final List<JobResponse> designers;
    private final List<JobResponse> planners;
    private final List<JobResponse> marketers;
    private final List<JobResponse> startups;

    @Builder
    public ListJobsResponse(List<JobResponse> developers, List<JobResponse> designers, List<JobResponse> planners, List<JobResponse> marketers, List<JobResponse> startups) {
        this.developers = developers;
        this.designers = designers;
        this.planners = planners;
        this.marketers = marketers;
        this.startups = startups;
    }

    public static ListJobsResponse from(List<Job> jobs) {
        Map<JobType, List<JobResponse>> groupedJobs = jobs.stream()
            .collect(Collectors.groupingBy(
                Job::getType,
                Collectors.mapping(job -> new JobResponse(job.getId(), job.getTitle()), toList())
            ));

        return ListJobsResponse.builder()
            .developers(groupedJobs.get(JobType.DEVELOPER))
            .designers(groupedJobs.get(JobType.DESIGNER))
            .planners(groupedJobs.get(JobType.PLANNER))
            .marketers(groupedJobs.get(JobType.MARKETER))
            .startups(groupedJobs.get(JobType.STARTUP))
            .build();
    }

    @Getter
    @RequiredArgsConstructor
    public static class JobResponse {

        private final int id;
        private final String name;
    }
}
