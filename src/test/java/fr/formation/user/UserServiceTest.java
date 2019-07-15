package fr.formation.user;

import fr.formation.artist.Artist;
import fr.formation.artist.ArtistRepository;
import fr.formation.artist.ArtistService;
import fr.formation.event.Event;
import fr.formation.event.EventDaoRepository;
import fr.formation.event.EventService;
import fr.formation.user.User;
import fr.formation.user.UserRepository;
import fr.formation.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserServiceTest {


    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private UserRepository userRepository;
    @Mock
    private EventDaoRepository EventDaoRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Mock
    private EventService eventService;

    @InjectMocks
    private ArtistService artistService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addEventUsers() throws NullPointerException {

        userService.findAll().size();

        User user = new User();
        user.setUsername("jeanne");
        user.setPassword("trombonNe45");
        user.setId(111L);
        userRepository.save(user);

        Artist artist = new Artist();
        artist.setEmail("synapson@bandcamp.com");
        artist.setAdress("8 rue Jaures");
        artist.setCity("Nantes, Loire Atl.");
        artist.setDescription("Electro synth pop français");
        artist.setId(120L);

        artistRepository.save(artist);

        Event event = new Event();
        event.setId(10L);
        event.setArtist(artist);
        LocalDateTime date = LocalDateTime.of(LocalDate.of(2019, 10, 2), LocalTime.of(15, 15));
        event.setDate(date);
        event.setNbPlace(15);

        EventDaoRepository.save(event);

        Mockito.when(artistRepository.findById(120L)).thenReturn(Optional.of(artist));
        Assertions.assertThat(artistService.artistById(120L)).isEqualTo(artist);

        Mockito.when(userRepository.findById(111L)).thenReturn(Optional.of(user));
        Assertions.assertThat(userService.getUserById(111L)).isEqualTo(user);

        Mockito.when(EventDaoRepository.findById(10L)).thenReturn(Optional.of(event));
        Assertions.assertThat(eventService.getEventById(10L)).isEqualTo(event);

        Mockito.when(userRepository.findById(111L)).thenReturn(Optional.of(user));


        Assertions.assertThat(userService.getUserById(111L).getUsername() == "jeanne");
        Assertions.assertThat(eventService.listEvents().size() == 1);
        Assertions.assertThat(userService.getUserById(111L).getEvents().size() > 0);

        }
    }




