package com.semarslan.projectmanagement.api;

import com.semarslan.projectmanagement.entities.Project;
import com.semarslan.projectmanagement.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<Project> save(@RequestBody Project project){

        projectService.saveOrUpdateProject(project);

        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }
}
