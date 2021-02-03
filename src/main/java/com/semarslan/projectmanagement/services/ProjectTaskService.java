package com.semarslan.projectmanagement.services;

import com.semarslan.projectmanagement.entities.ProjectTask;


public interface ProjectTaskService {

    ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask);

    Iterable<ProjectTask> findBacklogById(String backlogId);

}
