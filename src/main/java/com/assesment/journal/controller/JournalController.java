package com.assesment.journal.controller;

import com.assesment.journal.model.JournalDTO;
import com.assesment.journal.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @GetMapping("/{userId}")
    public List<JournalDTO> getJournalByUserId(@PathVariable Long userId){
        return journalService.getJournalByUserId(userId);
    }


}
