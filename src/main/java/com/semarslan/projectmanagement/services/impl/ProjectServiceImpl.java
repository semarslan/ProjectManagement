package com.semarslan.projectmanagement.services.impl;

import com.semarslan.projectmanagement.entities.Project;
import com.semarslan.projectmanagement.repositories.ProjectRepository;
import com.semarslan.projectmanagement.services.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Override
    public Project saveOrUpdateProject(Project project) {
        return projectRepository.save(project);
    }
}
