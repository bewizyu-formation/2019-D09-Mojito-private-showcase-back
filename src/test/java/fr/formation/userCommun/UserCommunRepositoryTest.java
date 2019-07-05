package fr.formation.userCommun;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class UserCommunRepositoryTest {

    @Autowired
    private UserCommunRepository userCommunRepository;

    @Test
    public void findAll() {
        Assertions.assertThat(userCommunRepository.findAll()).isEmpty();
    }

    @Test
    public void AddUser() {
        Assertions.assertThat(userCommunRepository.findAll()).isEmpty();
        userCommunRepository.save(new UserCommun());
        Assertions.assertThat(userCommunRepository.findAll()).hasSize(1);

    }

    @Test
    public void findByUsername() {
        UserCommun newUser = new UserCommun();
        newUser.setEmail("fake@email.com");
        newUser.setCity("Amiens");
        newUser.setAdress("21 rue de la rue");
        newUser.setPassword("password");
        newUser.setUsername("user");
        userCommunRepository.save(newUser);

        Assertions.assertThat(userCommunRepository.findByUsername("user")).isEqualTo(newUser);
    }

    @Test
    public void findByUsernameWhenNotExisting() {
        UserCommun newUser = new UserCommun();
        newUser.setEmail("fake@email.com");
        newUser.setCity("Amiens");
        newUser.setAdress("21 rue de la rue");
        newUser.setPassword("password");
        newUser.setUsername("user");
        userCommunRepository.save(newUser);

        Assertions.assertThat(userCommunRepository.findByUsername("user2")).isNull();
    }

    @Test
    public void findById() {
        UserCommun newUser = new UserCommun();
        newUser.setEmail("fake@email.com");
        newUser.setCity("Amiens");
        newUser.setAdress("21 rue de la rue");
        newUser.setPassword("password");
        newUser.setUsername("user");

        userCommunRepository.save(newUser);

        Assertions.assertThat(userCommunRepository.findById(newUser.getId())).isPresent();
    }

    @Test
    public void findByIdWhenNotExisting() {
        Assertions.assertThat(userCommunRepository.findById(0l)).isEmpty();

    }
}