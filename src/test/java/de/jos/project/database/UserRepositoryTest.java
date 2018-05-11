package de.jos.project.database;

import de.jos.project.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception{
        User user = new User("token");
        user.setProjectID("aa");
        user.setServiceID("ab");

        userRepository.insertNewUser(user);


        String sql = "SELECT * FROM User";
        User user2 = userRepository.findUserByID(sql);

        System.out.println("user2: " + user2.toSQLString());
    }

}
