package com.semarslan.projectmanagement.services.impl;

import com.semarslan.projectmanagement.entities.Project;
import com.semarslan.projectmanagement.exceptions.ProjectIdException;
import com.semarslan.projectmanagement.repositories.ProjectRepository;
import com.semarslan.projectmanagement.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public Project saveOrUpdateProject(Project project) {
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId);

        if (project == null) {
            throw new ProjectIdException("Project ID '" + projectId + "' doesn't exists");
        }
        return project;
    }

    public List<Project> getAll(){
        return projectRepository.findAll();
    }

    public void delete(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Project ID '" + projectId + "' doesn't exists");
        }
        projectRepository.delete(project);
    }
}
