package target.producBased.journal.App.Service;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import target.producBased.journal.App.Entity.JournalEntry;
import target.producBased.journal.App.Entity.User;
import target.producBased.journal.App.Repository.JournalEntryRepo;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {

          @Autowired
       private JournalEntryRepo journalEntryRepo;

          @Autowired
          private UserService userService;

          @Transactional
       public void saveEntry(JournalEntry journalEntry, String username){

        try {
            User user = userService.findByuserName(username);
            JournalEntry saved = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);

            userService.saveUser(user);

        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error ocuured During saving JournalEntry");
        }
       }
    public void saveEntry(JournalEntry journalEntry){

        journalEntryRepo.save(journalEntry);

    }

       public List<JournalEntry> getAll(){
          return journalEntryRepo.findAll();
       }

       public Optional<JournalEntry> findById(ObjectId id){
             return  journalEntryRepo.findById(id);
       }
        @Transactional
       public boolean deleteById(ObjectId id,String username){
                boolean reslut = false;
          try{
              User user = userService.findByuserName(username);
              boolean check = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
              if(check){
                  userService.saveUser(user);
                  journalEntryRepo.deleteById(id);
              }
          }
          catch (Exception e){

              log.error("Error",e);
              throw new RuntimeException("An error ocuured During deleting the entry");
          }
           return reslut;
        }

//       public List<JournalEntry> findByUserName(String username){
//
//       }

}
