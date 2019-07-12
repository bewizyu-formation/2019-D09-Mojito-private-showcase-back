package fr.formation.artist;

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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ArtistRepositoryTest {

    private Artist newArtist;
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
        newArtist = new Artist();
        newArtist.setEmail("fake@email.com");
        newArtist.setCity("Amiens");
        newArtist.setAdress("21 rue de la rue");
        newArtist.setPassword("pAssw0rd");
        newArtist.setUsername("user");
        newArtist.setDescription("lorem ipsum");
        newArtist.setName("FooBar");
    }

    @Test
    public void listArtist() {

        Assertions.assertThat(artistService.listArtists()).isEmpty();

        List<Artist> artistList = new ArrayList<>();
        artistList.add(newArtist);
        Mockito.when(artistRepository.findAll()).thenReturn(artistList);
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


        Assertions.assertThat(artistService.userWithIdenticalNameExists(newArtist)).isFalse();
        Mockito.when(userRepository.findByUsername("user")).thenReturn(newArtist);
        Assertions.assertThat(artistService.userWithIdenticalNameExists(newArtist)).isTrue();


    }

    @Test
    public void ArtistById() {
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
        Mockito.when(artistRepository.findByUsername("user")).thenReturn(newArtist);

        Assertions.assertThat(artistService.artistByUsername("user")).isEqualTo(newArtist);
    }

    @Test
    public void artistByUsernameWhenNotExisting() {
        Assertions.assertThat(artistService.artistByUsername("user")).isNull();
    }

    @Test
    public void artistToAdd() {

//        Assertions.assertThat(artistService.addArtist(newArtist)).isEqualTo(1);
    }

    @Test
    public void addPartialArtist() {
        newArtist.setUsername(null);
        Assertions.assertThat(artistService.addArtist(newArtist)).isEqualTo(0);
    }
    @Test
    public void addPartialEmailEmpty() {
        newArtist.setEmail(null);
        Assertions.assertThat(artistService.addArtist(newArtist)).isEqualTo(4);
    }

    @Test
    public void addExistingArtist() {

        Mockito.when(userRepository.findByUsername("user")).thenReturn(new User());

        Assertions.assertThat(artistService.addArtist(newArtist)).isEqualTo(3);
    }

    @Test
    public void modifyArtist() {
        Optional<Artist> optArtist = Optional.of(new Artist());
        Mockito.when(artistRepository.findById(0l)).thenReturn(optArtist);


        Assertions.assertThat(artistService.modifyArtist(0,newArtist)).isTrue();
    }

    @Test
    public void modifyArtistWhenUserNotExists() {
        Mockito.when(artistRepository.findById(0l)).thenReturn(Optional.empty());

        Assertions.assertThat(artistService.modifyArtist(0,newArtist)).isFalse();
    }
}