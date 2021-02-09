package com.semarslan.projectmanagement.services;

import com.semarslan.projectmanagement.entities.Project;

import java.util.List;

public interface ProjectService {

    Project saveOrUpdateProject(Project project, String username);

    Project findProjectByIdentifier(String projectId, String username);

    Iterable<Project> getAll(String username);

    void delete(String projectId, String username);
}
