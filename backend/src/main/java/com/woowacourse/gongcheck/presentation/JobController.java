package com.woowacourse.gongcheck.presentation;

import com.woowacourse.gongcheck.application.JobService;
import com.woowacourse.gongcheck.application.response.JobsResponse;
import com.woowacourse.gongcheck.application.response.SlackUrlResponse;
import com.woowacourse.gongcheck.presentation.request.JobCreateRequest;
import com.woowacourse.gongcheck.presentation.request.SlackUrlChangeRequest;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JobController {

    private final JobService jobService;

    public JobController(final JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/spaces/{spaceId}/jobs")
    public ResponseEntity<JobsResponse> showJobs(@AuthenticationPrincipal final Long hostId,
                                                 @PathVariable final Long spaceId,
                                                 final Pageable pageable) {
        JobsResponse response = jobService.findPage(hostId, spaceId, pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/spaces/{spaceId}/jobs")
    public ResponseEntity<Void> createJob(@AuthenticationPrincipal final Long hostId,
                                          @PathVariable final Long spaceId,
                                          @Valid @RequestBody final JobCreateRequest request) {
        Long savedJobId = jobService.createJob(hostId, spaceId, request);
        return ResponseEntity.created(URI.create("/api/spaces/" + savedJobId + "/jobs")).build();
    }

    @DeleteMapping("/jobs/{jobId}")
    public ResponseEntity<Void> removeJob(@AuthenticationPrincipal final Long hostId, @PathVariable final Long jobId) {
        jobService.removeJob(hostId, jobId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/jobs/{jobId}/slack")
    public ResponseEntity<SlackUrlResponse> findSlackUrl(@AuthenticationPrincipal final Long hostId,
                                                         @PathVariable final Long jobId) {
        SlackUrlResponse response = jobService.findSlackUrl(hostId, jobId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/jobs/{jobId}/slack")
    public ResponseEntity<Void> changeSlackUrl(@AuthenticationPrincipal final Long hostId,
                                               @PathVariable final Long jobId,
                                               @Valid @RequestBody final SlackUrlChangeRequest request) {
        jobService.changeSlackUrl(hostId, jobId, request);
        return ResponseEntity.noContent().build();
    }
}
