package fr.formation.userCommun;


import fr.formation.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class UserCommunRepositoryTest {

    private UserCommun newUser;
    @Mock
    private UserCommunRepository userCommunRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserCommunService userCommunService;

    @Before
    public void init() {
        UserCommun newUser = new UserCommun();
        newUser.setEmail("fake@email.com");
        newUser.setCity("Amiens");
        newUser.setAdress("21 rue de la rue");
        newUser.setPassword("password");
        newUser.setUsername("user");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAll() {
        Assertions.assertThat(userCommunRepository.findAll()).isEmpty();
    }

    @Test
    public void AddUser() {
        /*
        Assertions.assertThat(userCommunRepository.findAll()).isEmpty();
        userCommunService.addUserCommun(newUser);
        List listUser = new ArrayList<>();
        listUser.add(newUser);
        Mockito.when(userCommunRepository.findAll()).thenReturn(listUser);
        Assertions.assertThat(userCommunService.listUserCommun()).hasSize(1);
        */
    }

    @Test
    public void findByUsername() {
        userCommunRepository.save(newUser);

        Assertions.assertThat(userCommunRepository.findByUsername("user")).isEqualTo(newUser);
    }

    @Test
    public void findByUsernameWhenNotExisting() {
        userCommunRepository.save(newUser);

        Assertions.assertThat(userCommunRepository.findByUsername("user2")).isNull();
    }

    @Test
    public void findById() {
        /*
        userCommunService.addUserCommun(newUser);
        Mockito.when(userCommunRepository.findById(newUser.getId())).thenReturn(Optional.of(newUser));

        Assertions.assertThat(userCommunService.userCommunById(newUser.getId())).isEqualTo(newUser);
        */
    }

    @Test
    public void findByIdWhenNotExisting() {
        Assertions.assertThat(userCommunRepository.findById(0l)).isEmpty();

    }
}