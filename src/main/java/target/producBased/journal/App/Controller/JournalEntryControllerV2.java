package target.producBased.journal.App.Controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import target.producBased.journal.App.Entity.JournalEntry;
import target.producBased.journal.App.Entity.User;
import target.producBased.journal.App.Service.JournalEntryService;
import target.producBased.journal.App.Service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

     @Autowired
     private JournalEntryService journalEntryService;

     @Autowired
     private UserService userService;

     @GetMapping
     public ResponseEntity<?> getAllJournalEntriesOfUser() {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String username = authentication.getName();
         User user = userService.findByuserName(username);

         List<JournalEntry> all = user.getJournalEntries();

         if(all != null && !all.isEmpty()){
             return new ResponseEntity<>(all,HttpStatus.OK);
         }
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);

     }

     @PostMapping
     public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
         try{
             Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
             String username = authentication.getName();
             myEntry.setDate(LocalDateTime.now());
             journalEntryService.saveEntry( myEntry,username);
             return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
         }catch(Exception e){
              return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
         }



     }
    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntry(@PathVariable ObjectId myId) {
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String username = authentication.getName();
         User user = userService.findByuserName(username);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
             if(!collect.isEmpty()){
                 Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
                 if(journalEntry.isPresent()){
                     return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);

                 }

             }

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


     @DeleteMapping ("id/{myId}")
    public ResponseEntity<?> deleteJournalEntryBbyId(@PathVariable ObjectId myId){
         Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
         String username = authentication.getName();

         boolean removed = journalEntryService.deleteById(myId, username);
         if(removed){
             journalEntryService.deleteById(myId, username);
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
         else
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);



     }

     @PutMapping("id/{id}")
     public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId id,@RequestBody JournalEntry newEntry){

         Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
         String name = authentication.getName();
         User user = userService.findByuserName(name);
         List<JournalEntry> collect = user.getJournalEntries().stream().filter(x -> x.getId().equals(id)).collect(Collectors.toList());
             if(!collect.isEmpty()){
                 Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
                 if(journalEntry.isPresent()){
                     JournalEntry old = journalEntry.get();

                       old.setName(newEntry.getName() !=null && !newEntry.getName().equals("") ? newEntry.getName(): old.getName());
                     old.setContent(newEntry.getContent() !=null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                      journalEntryService.saveEntry(old);
                      return new ResponseEntity<>(old,HttpStatus.OK);
                      }

                 }
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
             }

     }





