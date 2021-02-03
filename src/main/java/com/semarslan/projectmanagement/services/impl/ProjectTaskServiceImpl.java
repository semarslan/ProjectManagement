package com.semarslan.projectmanagement.services.impl;

import com.semarslan.projectmanagement.entities.Backlog;
import com.semarslan.projectmanagement.entities.Project;
import com.semarslan.projectmanagement.entities.ProjectTask;
import com.semarslan.projectmanagement.exceptions.ProjectNotFoundException;
import com.semarslan.projectmanagement.repositories.BacklogRepository;
import com.semarslan.projectmanagement.repositories.ProjectRepository;
import com.semarslan.projectmanagement.repositories.ProjectTaskRepository;
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

    @Override
    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        try {

            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

            projectTask.setBacklog(backlog);

            Integer BacklogSequence = backlog.getPTSequence();
            BacklogSequence++;

            backlog.setPTSequence(BacklogSequence);

            projectTask.setProjectSequence(projectIdentifier+ "-"+ BacklogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            if (projectTask.getPriority() == null) projectTask.setPriority(3);

            if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }

            return projectTaskRepository.save(projectTask);
        }catch (Exception e) {
            throw new ProjectNotFoundException("Project not Found");
        }

    }

    @Override
    public Iterable<ProjectTask> findBacklogById(String backlogId) {

        Project project = projectRepository.findByProjectIdentifier(backlogId);

        if (project==null) {
            throw new ProjectNotFoundException("Project with ID " + backlogId + "does not exists");
        }
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(backlogId);
    }
}
