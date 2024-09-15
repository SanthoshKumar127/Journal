package com.assesment.journal.service;

import com.assesment.journal.model.JournalDTO;
import com.assesment.journal.model.UserJournalEvent;
import com.assesment.journal.persistence.entities.JournalEntity;
import com.assesment.journal.persistence.repository.JournalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    private ObjectMapper objectMapper;

    @KafkaListener(topics = "event-journal-test")
    public void consumeUserEvent(UserJournalEvent userEvent) {
        System.out.println("Received user event: " + userEvent);
        saveJournal(userEvent.getEventData(), userEvent.getUserId());
    }


    private void saveJournal(String journal, Long id){
        JournalEntity journalEntity = new JournalEntity();
        journalEntity.setJournal(journal);
        journalEntity.setUserId(id);
        journalEntity.setCreatedAt(LocalDateTime.now());

        journalRepository.save(journalEntity);
    }


    public List<JournalDTO> getJournalByUserId(Long id){
        List<JournalEntity> journalEntitiesList = journalRepository.findByUserId(id);
        List<JournalDTO> journalDTOList = new ArrayList<>();
        for(JournalEntity journalEntity : journalEntitiesList){
            journalDTOList.add(objectMapper.convertValue(journalEntity, JournalDTO.class));
        }
        return journalDTOList;
    }
}
