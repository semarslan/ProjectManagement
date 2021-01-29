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
import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    private final MapValidationErrorService mapValidationErrorService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Project project, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MApValidationService(result);
        if(errorMap != null) return errorMap;

        projectService.saveOrUpdateProject(project);

        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectById(@PathVariable String projectId){

        Project project = projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<?>> getAll(){

        List<Project> projects = projectService.getAll();

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> delete(@PathVariable String projectId) {
        projectService.delete(projectId);

        return new ResponseEntity<String>("Project with ID: '" + projectId + "' deleted", HttpStatus.OK);
    }
}
