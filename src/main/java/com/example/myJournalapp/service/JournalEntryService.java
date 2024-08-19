package com.example.myJournalapp.service;

import com.example.myJournalapp.entity.JournalEntry;
import com.example.myJournalapp.entity.User;
import com.example.myJournalapp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

//@Service
@Component
@Slf4j
public class JournalEntryService  {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private  UserService userService;


    @Transactional
    public void saveEntry(JournalEntry  journalEntry, String userName){
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved =   journalEntryRepository.save(journalEntry);
//            user.setUserName(null);
            userService.saveUser(user);
        }catch (Exception e){
            System.out.println(e);

        }
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }


    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById (ObjectId id, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }catch (Exception  e){
            System.out.println(e);
            log.error("Error  ......... ");
            throw new RuntimeException(" AN error occurred while saving the entry ... ", e);
        }
        return removed;
    }
}
