package com.assesment.journal.persistence.repository;

import com.assesment.journal.model.JournalDTO;
import com.assesment.journal.persistence.entities.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JournalRepository extends JpaRepository<JournalEntity, Long> {
    List<JournalEntity> findByUserId(Long id);
}
