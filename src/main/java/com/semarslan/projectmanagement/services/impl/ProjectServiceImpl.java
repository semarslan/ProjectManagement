package com.semarslan.projectmanagement.services.impl;

import com.semarslan.projectmanagement.entities.Backlog;
import com.semarslan.projectmanagement.entities.Project;
import com.semarslan.projectmanagement.entities.User;
import com.semarslan.projectmanagement.exceptions.ProjectIdException;
import com.semarslan.projectmanagement.exceptions.ProjectNotFoundException;
import com.semarslan.projectmanagement.repositories.BacklogRepository;
import com.semarslan.projectmanagement.repositories.ProjectRepository;
import com.semarslan.projectmanagement.repositories.UserRepository;
import com.semarslan.projectmanagement.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final BacklogRepository backlogRepository;

    private final UserRepository userRepository;

    @Override
    public Project saveOrUpdateProject(Project project, String username) {

        /**
         * project.getId != null
         *
         * findByDbId -> null
         */

        if (project.getId() != null) {
            Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());

            if (existingProject != null && (!existingProject.getProjectLeader().equals(username))) {
                throw new ProjectNotFoundException("Project not found in your account");
            } else if (existingProject == null) {
                throw new ProjectNotFoundException("Project wih Id: '" + project.getProjectIdentifier() + "' cannot be updated. Because it doesn't exists");
            }
        }
        try {

            User user = userRepository.findByUsername(username);
            project.setUser(user);
            project.setProjectLeader(user.getUsername());

            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if (project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }

            if (project.getId() != null) {
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }

            return projectRepository.save(project);

        } catch (Exception e) {
            throw new ProjectIdException("Project ID '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectId, String username) {

        Project project = projectRepository.findByProjectIdentifier(projectId);

        if (project == null) {
            throw new ProjectIdException("Project ID '" + projectId + "' doesn't exists");
        }

        if (!project.getProjectLeader().equals(username)) {
            throw new ProjectNotFoundException("Project not found in your account");
        }

        return project;
    }

    public Iterable<Project> getAll(String username) {
        return projectRepository.findAllByProjectLeader(username);
    }

    public void delete(String projectId, String username) {

        projectRepository.delete(findProjectByIdentifier(projectId, username));
    }
}
