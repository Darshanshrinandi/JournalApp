package target.producBased.journal.App;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import target.producBased.journal.App.Entity.User;
import target.producBased.journal.App.Repository.UserRepo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepo userRepo;

//    @Test
//    public void testFindByUsername() {
//        User user = userRepo.findByUsername("Ammu");
//        assertNotNull(!user.getJournalEntries().isEmpty() );
//
//
//    }
//}
@ParameterizedTest
@CsvSource({
        "Darshu","Ammu"

})

public void testFindByUsername(String username) {

    User user = userRepo.findByUsername(username);
    assertNotNull(user);
}
}
