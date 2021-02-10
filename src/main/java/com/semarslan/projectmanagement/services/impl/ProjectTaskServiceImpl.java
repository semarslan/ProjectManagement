package com.semarslan.projectmanagement.services.impl;

import com.semarslan.projectmanagement.entities.Backlog;
import com.semarslan.projectmanagement.entities.Project;
import com.semarslan.projectmanagement.entities.ProjectTask;
import com.semarslan.projectmanagement.exceptions.ProjectNotFoundException;
import com.semarslan.projectmanagement.repositories.BacklogRepository;
import com.semarslan.projectmanagement.repositories.ProjectRepository;
import com.semarslan.projectmanagement.repositories.ProjectTaskRepository;
import com.semarslan.projectmanagement.services.ProjectService;
import com.semarslan.projectmanagement.services.ProjectTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectTaskServiceImpl implements ProjectTaskService {

    private final BacklogRepository backlogRepository;

    private final ProjectTaskRepository projectTaskRepository;

    private final ProjectRepository projectRepository;

    private final ProjectService projectService;

    @Override
    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask, String username) {

        Backlog backlog = projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog();

        projectTask.setBacklog(backlog);

        Integer BacklogSequence = backlog.getPTSequence();
        BacklogSequence++;

        backlog.setPTSequence(BacklogSequence);

        projectTask.setProjectSequence(projectIdentifier + "-" + BacklogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        if(projectTask.getStatus()==""|| projectTask.getStatus()==null){
            projectTask.setStatus("TO_DO");
        }

        if(projectTask.getPriority()==null||projectTask.getPriority()==0){
            projectTask.setPriority(3);
        }

        return projectTaskRepository.save(projectTask);

    }

    @Override
    public Iterable<ProjectTask> findBacklogById(String backlogId, String username) {

        projectService.findProjectByIdentifier(backlogId, username);
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlogId);
    }

    @Override
    public ProjectTask findProjectTaskByProjectSequence(String backlogId, String projectTaskId, String username) {

        projectService.findProjectByIdentifier(backlogId, username);


        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectTaskId);

        if (projectTask == null) throw new ProjectNotFoundException("Project Task " + projectTaskId + " not found");


        if (!projectTask.getProjectIdentifier().equals(backlogId)) {
            throw new ProjectNotFoundException("Project Task '" + projectTaskId + "' does not exist in project: " + backlogId);
        }

        return projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlogId, String projectTaskId, String username) {

        ProjectTask projectTask = findProjectTaskByProjectSequence(backlogId, projectTaskId, username);

        projectTask = updatedTask;

        return projectTaskRepository.save(projectTask);
    }

    public void deleteProjectTaskByProjectSequence(String backlogId, String projectTaskId, String username) {
        ProjectTask projectTask = findProjectTaskByProjectSequence(backlogId, projectTaskId, username);

        projectTaskRepository.delete(projectTask);
    }
}
