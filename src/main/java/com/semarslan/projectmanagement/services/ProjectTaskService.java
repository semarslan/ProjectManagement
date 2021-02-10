package com.semarslan.projectmanagement.services;

import com.semarslan.projectmanagement.entities.ProjectTask;


public interface ProjectTaskService {

    ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username);

    Iterable<ProjectTask> findBacklogById(String backlogId, String username);

    ProjectTask findProjectTaskByProjectSequence(String backlogId, String projectTaskId, String username);

    ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlogId, String projectTaskId, String username);

    void deleteProjectTaskByProjectSequence(String backlogId, String projectTaskId, String username);
}
