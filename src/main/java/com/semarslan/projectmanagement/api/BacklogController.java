package com.semarslan.projectmanagement.api;

import com.semarslan.projectmanagement.entities.Project;
import com.semarslan.projectmanagement.entities.ProjectTask;
import com.semarslan.projectmanagement.services.MapValidationErrorService;
import com.semarslan.projectmanagement.services.ProjectTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
@RequiredArgsConstructor
public class BacklogController {


    private final ProjectTaskService projectTaskService;

    private final MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{backlogId}")
    public ResponseEntity<?> addProjectTaskToBacklog(@Valid @RequestBody ProjectTask projectTask,
                                                     BindingResult result,
                                                     @PathVariable String backlogId, Principal principal) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MApValidationService(result);
        if (errorMap != null) return errorMap;

        ProjectTask projectTask1 = projectTaskService.addProjectTask(backlogId, projectTask, principal.getName());

        return new ResponseEntity<ProjectTask>(projectTask1, HttpStatus.CREATED);
    }

    @GetMapping("/{backlogId}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlogId, Principal principal) {
        return projectTaskService.findBacklogById(backlogId, principal.getName());
    }

    @GetMapping("/{backlogId}/{projectTaskId}")
    public ResponseEntity<?> getProjectTask(@PathVariable String backlogId, @PathVariable String projectTaskId, Principal principal) {
        ProjectTask projectTask = projectTaskService.findProjectTaskByProjectSequence(backlogId, projectTaskId, principal.getName());
        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
    }

    @PatchMapping("{backlogId}/{projectTaskId}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
                                               @PathVariable String backlogId, @PathVariable String projectTaskId, Principal principal) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MApValidationService(result);
        if (errorMap != null) return errorMap;

        ProjectTask inDb = projectTaskService.updateByProjectSequence(projectTask, backlogId, projectTaskId, principal.getName());

        return new ResponseEntity<ProjectTask>(inDb, HttpStatus.OK);
    }


    @DeleteMapping("{backlogId}/{projectTaskId}")
    public ResponseEntity<?> deleteProjectTask(@PathVariable String backlogId, @PathVariable String projectTaskId, Principal principal) {
        projectTaskService.deleteProjectTaskByProjectSequence(backlogId, projectTaskId, principal.getName());

        return new ResponseEntity<String>("Project Task " + projectTaskId + " was deleted successfully.", HttpStatus.OK);
    }
}
