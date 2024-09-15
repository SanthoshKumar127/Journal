package com.assesment.journal.model;

import lombok.Data;

@Data
public class UserJournalEvent {
    private Long userId;
    private String eventData;
}
