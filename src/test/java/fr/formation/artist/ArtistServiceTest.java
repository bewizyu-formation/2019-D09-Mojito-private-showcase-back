package fr.formation.artist;

import fr.formation.user.User;
import fr.formation.user.UserRepository;
import fr.formation.userCommun.UserCommun;
import fr.formation.userCommun.UserCommunRepository;
import fr.formation.userCommun.UserCommunService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ArtistServiceTest {


    @Mock
    private ArtistRepository artistRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private ArtistService artistService;
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listArtists() {

        Assertions.assertThat(artistService.listArtists()).isEmpty();

        Artist newArtist = new Artist();
        newArtist.setEmail("fake@email.com");
        newArtist.setCity("Amiens");
        newArtist.setAdress("21 rue de la rue");
        newArtist.setPassword("password");
        newArtist.setUsername("user");

        List<Artist> userList = new ArrayList<>();
        userList.add(newArtist);
        Mockito.when(artistRepository.findAll()).thenReturn(userList);
        Assertions.assertThat(artistService.listArtists()).hasSize(1);

    }

    @Test
    public void usernameExists() {
        Assertions.assertThat(artistService.usernameExists("user")).isFalse();
        Mockito.when(userRepository.findByUsername("user")).thenReturn(new User());

        Assertions.assertThat(artistService.usernameExists("user")).isTrue();

    }

    @Test
    public void userExistsById() {
        Mockito.when(userRepository.findById(0l)).thenReturn(Optional.empty());
        Assertions.assertThat(artistService.userExistsById(0l)).isFalse();

        Mockito.when(userRepository.findById(0l)).thenReturn(Optional.of(new User()));
        Assertions.assertThat(artistService.userExistsById(0l)).isTrue();
    }

    @Test
    public void userWithIdenticalNameExists() {
        Artist newArtist = new Artist();
        newArtist.setEmail("fake@email.com");
        newArtist.setCity("Amiens");
        newArtist.setAdress("21 rue de la rue");
        newArtist.setPassword("password");
        newArtist.setUsername("user");

        Assertions.assertThat(artistService.userWithIdenticalNameExists(newArtist)).isFalse();
        Mockito.when(userRepository.findByUsername("user")).thenReturn(newArtist);
        Assertions.assertThat(artistService.userWithIdenticalNameExists(newArtist)).isTrue();


    }

    @Test
    public void artistById() {
        Artist newArtist = new Artist();
        newArtist.setEmail("fake@email.com");
        newArtist.setCity("Amiens");
        newArtist.setAdress("21 rue de la rue");
        newArtist.setPassword("password");
        newArtist.setUsername("user");
        Mockito.when(artistRepository.findById(0l)).thenReturn(Optional.of(newArtist));

        Assertions.assertThat(artistService.artistById(0l)).isEqualTo(newArtist);
    }

    @Test
    public void artistByIdWhenNotExist() {
        Mockito.when(artistRepository.findById(0l)).thenReturn(Optional.empty());

        Assertions.assertThat(artistService.artistById(0l)).isNull();
    }

    @Test
    public void artistByUsername() {
        Artist newArtist = new Artist();
        newArtist.setEmail("fake@email.com");
        newArtist.setCity("Amiens");
        newArtist.setAdress("21 rue de la rue");
        newArtist.setPassword("password");
        newArtist.setUsername("user");
        Mockito.when(artistRepository.findByUsername("user")).thenReturn(newArtist);

        Assertions.assertThat(artistService.artistByUsername("user")).isEqualTo(newArtist);
    }

    @Test
    public void artistByUsernameWhenNotExisting() {
        Assertions.assertThat(artistService.artistByUsername("user")).isNull();
    }

    @Test
    public void addArtist() {
        Artist newArtist = new Artist();
        newArtist.setEmail("fake@email.com");
        newArtist.setCity("Amiens");
        newArtist.setAdress("21 rue de la rue");
        newArtist.setPassword("pAssw0rd");
        newArtist.setUsername("user");
        newArtist.setDescription("lorem ipsum");
        newArtist.setName("FooBar");
        Assertions.assertThat(artistService.addArtist(newArtist)).isTrue();
    }

    @Test
    public void addPartialArtist() {
        Artist newArtist = new Artist();
        newArtist.setEmail("fake@email.com");
        newArtist.setCity("Amiens");
        newArtist.setAdress("21 rue de la rue");
        newArtist.setPassword("password");
        Assertions.assertThat(artistService.addArtist(newArtist)).isFalse();
    }

    @Test
    public void addExistingArtist() {

        Mockito.when(userRepository.findByUsername("user")).thenReturn(new User());

        Artist newArtist = new Artist();
        newArtist.setEmail("fake@email.com");
        newArtist.setCity("Amiens");
        newArtist.setAdress("21 rue de la rue");
        newArtist.setPassword("pAssw0rd");
        newArtist.setUsername("user");
        newArtist.setDescription("lorem ipsum");
        newArtist.setName("FooBar");
        Assertions.assertThat(artistService.addArtist(newArtist)).isFalse();
    }

    @Test
    public void modifyArtist() {
        Artist artist = new Artist();
        Optional<Artist> optUser = Optional.of(artist);
        Mockito.when(artistRepository.findById(0l)).thenReturn(optUser);

        Artist newArtist = new Artist();
        newArtist.setEmail("fake@email.com");
        newArtist.setCity("Amiens");
        newArtist.setAdress("21 rue de la rue");
        newArtist.setPassword("pAssw0rd");
        newArtist.setUsername("user");
        newArtist.setDescription("lorem ipsum");
        newArtist.setName("FooBar");
        Assertions.assertThat(artistService.modifyArtist(0l,newArtist)).isEqualTo(artist);
    }

    @Test
    public void modifyPartialArtistShouldFail() {
        Optional<Artist> optUser = Optional.of(new Artist());
        Mockito.when(artistRepository.findById(0l)).thenReturn(optUser);

        Artist newArtist = new Artist();
        newArtist.setEmail("fake@email.com");
        newArtist.setCity("Amiens");
        newArtist.setAdress("21 rue de la rue");
        newArtist.setDescription("lorem ipsum");
        newArtist.setName("FooBar");
        Assertions.assertThat(artistService.modifyArtist(0l,newArtist)).isNull();
    }

    @Test
    public void modifyArtistWhenUserNotExists() {
        Mockito.when(artistRepository.findById(0l)).thenReturn(Optional.empty());

        Artist newArtist = new Artist();
        newArtist.setEmail("fake@email.com");
        newArtist.setCity("Amiens");
        newArtist.setAdress("21 rue de la rue");
        newArtist.setPassword("password");
        newArtist.setUsername("user");
        Assertions.assertThat(artistService.modifyArtist(0,newArtist)).isNull();
    }
}