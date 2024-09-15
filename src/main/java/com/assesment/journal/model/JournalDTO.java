package com.assesment.journal.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JournalDTO {
    private Long userId;
    private String journal;
    private LocalDateTime createdAt;
}
