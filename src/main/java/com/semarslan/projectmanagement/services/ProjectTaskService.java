package com.semarslan.projectmanagement.services;

import com.semarslan.projectmanagement.entities.ProjectTask;


public interface ProjectTaskService {

    ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask);

    Iterable<ProjectTask> findBacklogById(String backlogId);

    ProjectTask findProjectTaskByProjectSequence(String backlogId, String projectTaskId);

    ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlogId, String projectTaskId);

    void deleteProjectTaskByProjectSequence(String backlogId, String projectTaskId);
}
