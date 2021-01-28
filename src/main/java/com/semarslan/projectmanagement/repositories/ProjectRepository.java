package com.semarslan.projectmanagement.repositories;

import com.semarslan.projectmanagement.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllById(Iterable<Long> iterable);
}
