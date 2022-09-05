package our.portfolio.devspace.domain.job.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JobResponse {

    private final int id;
    private final String title;
}
