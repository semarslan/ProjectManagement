package com.semarslan.projectmanagement.services;

import com.semarslan.projectmanagement.entities.Project;

import java.util.List;

public interface ProjectService {

    Project saveOrUpdateProject(Project project);

    Project findProjectByIdentifier(String projectId);

    List<Project> getAll();

    void delete(String projectId);
}
