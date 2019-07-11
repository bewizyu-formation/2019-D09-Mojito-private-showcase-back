package fr.formation.userCommun;

import fr.formation.user.User;
import fr.formation.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class UserCommunServiceTest {

    @Mock
    private UserCommunRepository userCommunRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserCommunService userCommunService;
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listUserCommun() {

        Assertions.assertThat(userCommunService.listUserCommun()).isEmpty();

        UserCommun newUser = new UserCommun();
        newUser.setEmail("fake@email.com");
        newUser.setCity("Amiens");
        newUser.setAdress("21 rue de la rue");
        newUser.setPassword("passwordA1");
        newUser.setUsername("user");

        List<UserCommun> userList = new ArrayList<>();
        userList.add(newUser);
        Mockito.when(userCommunRepository.findAll()).thenReturn(userList);
        Assertions.assertThat(userCommunService.listUserCommun()).hasSize(1);

    }

    @Test
    public void usernameExists() {
        Assertions.assertThat(userCommunService.usernameExists("user")).isFalse();
        Mockito.when(userRepository.findByUsername("user")).thenReturn(new User());

        Assertions.assertThat(userCommunService.usernameExists("user")).isTrue();

    }

    @Test
    public void userExistsById() {
        Mockito.when(userRepository.findById(0l)).thenReturn(Optional.empty());
        Assertions.assertThat(userCommunService.userExistsById(0l)).isFalse();

        Mockito.when(userRepository.findById(0l)).thenReturn(Optional.of(new User()));
        Assertions.assertThat(userCommunService.userExistsById(0l)).isTrue();
    }

    @Test
    public void userWithIdenticalNameExists() {
        UserCommun newUser = new UserCommun();
        newUser.setEmail("fake@email.com");
        newUser.setCity("Amiens");
        newUser.setAdress("21 rue de la rue");
        newUser.setPassword("passwordA1");
        newUser.setUsername("user");

        Assertions.assertThat(userCommunService.userWithIdenticalNameExists(newUser)).isFalse();
        Mockito.when(userRepository.findByUsername("user")).thenReturn(newUser);
        Assertions.assertThat(userCommunService.userWithIdenticalNameExists(newUser)).isTrue();


    }

    @Test
    public void userCommunById() {
        UserCommun newUser = new UserCommun();
        newUser.setEmail("fake@email.com");
        newUser.setCity("Amiens");
        newUser.setAdress("21 rue de la rue");
        newUser.setPassword("passwordA1");
        newUser.setUsername("user");
        Mockito.when(userCommunRepository.findById(0l)).thenReturn(Optional.of(newUser));

        Assertions.assertThat(userCommunService.userCommunById(0l)).isEqualTo(newUser);
    }

    @Test
    public void userCommunByIdWhenNotExist() {
        Mockito.when(userCommunRepository.findById(0l)).thenReturn(Optional.empty());

        Assertions.assertThat(userCommunService.userCommunById(0l)).isNull();
    }

    @Test
    public void userCommunByUsername() {
        UserCommun newUser = new UserCommun();
        newUser.setEmail("fake@email.com");
        newUser.setCity("Amiens");
        newUser.setAdress("21 rue de la rue");
        newUser.setPassword("passwordA1");
        newUser.setUsername("user");
        Mockito.when(userCommunRepository.findByUsername("user")).thenReturn(newUser);

        Assertions.assertThat(userCommunService.userCommunByUsername("user")).isEqualTo(newUser);
    }

    @Test
    public void userCommunByUsernameWhenNotExisting() {
        Assertions.assertThat(userCommunService.userCommunByUsername("user")).isNull();
    }

    @Test
    public void addUserCommun() {
        UserCommun newUser = new UserCommun();
        newUser.setEmail("fake@email.com");
        newUser.setCity("Amiens");
        newUser.setAdress("21 rue de la rue");
        newUser.setPassword("passwordA1");
        newUser.setUsername("user");
        Assertions.assertThat(userCommunService.addUserCommun(newUser)).isEqualTo(1);
    }

    @Test
    public void addPartialUserCommun() {
        UserCommun newUser = new UserCommun();
        newUser.setEmail("fake@email.com");
        newUser.setCity("Amiens");
        newUser.setAdress("21 rue de la rue");
        newUser.setPassword("passwordA1");
        Assertions.assertThat(userCommunService.addUserCommun(newUser)).isEqualTo(0);
    }

    @Test
    public void addExistingUserCommun() {

        Mockito.when(userRepository.findByUsername("user")).thenReturn(new User());

        UserCommun newUser = new UserCommun();
        newUser.setEmail("fake@email.com");
        newUser.setCity("Amiens");
        newUser.setAdress("21 rue de la rue");
        newUser.setPassword("passwordA1");
        newUser.setUsername("user");
        Assertions.assertThat(userCommunService.addUserCommun(newUser)).isEqualTo(3);
    }

    @Test
    public void addIncorrectPasswordUserCommun() {

        Mockito.when(userRepository.findByUsername("user")).thenReturn(new User());

        UserCommun newUser = new UserCommun();
        newUser.setEmail("fake@email.com");
        newUser.setCity("Amiens");
        newUser.setAdress("21 rue de la rue");
        newUser.setPassword("passworda1");
        newUser.setUsername("user2");
        Assertions.assertThat(userCommunService.addUserCommun(newUser)).isEqualTo(2);
    }

    @Test
    public void modifyUserCommun() {
        Optional<UserCommun> optUser = Optional.of(new UserCommun());
        Mockito.when(userCommunRepository.findById(0l)).thenReturn(optUser);

        UserCommun newUser = new UserCommun();
        newUser.setEmail("fake@email.com");
        newUser.setCity("Amiens");
        newUser.setAdress("21 rue de la rue");
        newUser.setPassword("passwordA1");
        newUser.setUsername("user");
        Assertions.assertThat(userCommunService.modifyUserCommun(0,newUser)).isTrue();
    }

    @Test
    public void modifyUserCommunWhenUserNotExists() {
        Mockito.when(userCommunRepository.findById(0l)).thenReturn(Optional.empty());

        UserCommun newUser = new UserCommun();
        newUser.setEmail("fake@email.com");
        newUser.setCity("Amiens");
        newUser.setAdress("21 rue de la rue");
        newUser.setPassword("passwordA1");
        newUser.setUsername("user");
        Assertions.assertThat(userCommunService.modifyUserCommun(0,newUser)).isFalse();
    }
}