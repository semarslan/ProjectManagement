package com.semarslan.projectmanagement.api;

import com.semarslan.projectmanagement.entities.Project;
import com.semarslan.projectmanagement.services.MapValidationErrorService;
import com.semarslan.projectmanagement.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
@CrossOrigin
public class ProjectController {

    private final ProjectService projectService;

    private final MapValidationErrorService mapValidationErrorService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Project project, BindingResult result, Principal principal) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MApValidationService(result);
        if(errorMap != null) return errorMap;

        projectService.saveOrUpdateProject(project, principal.getName());

        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId, Principal principal){

        Project project = projectService.findProjectByIdentifier(projectId, principal.getName());
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> getAllProjects(Principal principal){return projectService.getAll(principal.getName());}


    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> delete(@PathVariable String projectId, Principal principal) {
        projectService.delete(projectId, principal.getName());

        return new ResponseEntity<String>("Project with ID: '" + projectId + "' deleted", HttpStatus.OK);
    }
}
