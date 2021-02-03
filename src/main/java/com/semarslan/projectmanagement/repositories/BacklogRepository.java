package com.semarslan.projectmanagement.repositories;

import com.semarslan.projectmanagement.entities.Backlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Long> {

    Backlog findByProjectIdentifier(String Identifier);
}
