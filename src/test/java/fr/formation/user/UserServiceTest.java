package fr.formation.user;

<<<<<<< HEAD
import fr.formation.artist.Artist;
import fr.formation.artist.ArtistRepository;
import fr.formation.artist.ArtistService;
import fr.formation.event.Event;
import fr.formation.event.EventDaoRepository;
=======
import fr.formation.event.Event;
import fr.formation.event.EventRepository;
>>>>>>> b82844de0e9a26703cfa09ccfdd90409484e1192
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
<<<<<<< HEAD
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
=======
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
>>>>>>> b82844de0e9a26703cfa09ccfdd90409484e1192

public class UserServiceTest {


    @Mock
<<<<<<< HEAD
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
=======
    private UserRepository userRepository;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @InjectMocks
    private EventService eventService;
>>>>>>> b82844de0e9a26703cfa09ccfdd90409484e1192

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addEventUsers() throws NullPointerException {

        userService.findAll().size();

<<<<<<< HEAD
=======

>>>>>>> b82844de0e9a26703cfa09ccfdd90409484e1192
        User user = new User();
        user.setUsername("jeanne");
        user.setPassword("trombonNe45");
        user.setId(111L);
        userRepository.save(user);

<<<<<<< HEAD
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
=======
        Event event = new Event();
        event.setId(10L);
        event.setOwner(user);
        event.setHour(14);

        LocalDate date = LocalDate.of(2019,12,4);
        event.setDate(date);
        event.setAdress("place Daurade");
        event.setNbPlace(15);

        eventRepository.save(event);
        userRepository.save(user);
>>>>>>> b82844de0e9a26703cfa09ccfdd90409484e1192

        Mockito.when(userRepository.findById(111L)).thenReturn(Optional.of(user));
        Assertions.assertThat(userService.getUserById(111L)).isEqualTo(user);

<<<<<<< HEAD
        Mockito.when(EventDaoRepository.findById(10L)).thenReturn(Optional.of(event));
=======
        Mockito.when(eventRepository.findById(10L)).thenReturn(Optional.of(event));
>>>>>>> b82844de0e9a26703cfa09ccfdd90409484e1192
        Assertions.assertThat(eventService.getEventById(10L)).isEqualTo(event);

        Mockito.when(userRepository.findById(111L)).thenReturn(Optional.of(user));

<<<<<<< HEAD

        Assertions.assertThat(userService.getUserById(111L).getUsername() == "jeanne");
        Assertions.assertThat(eventService.listEvents().size() == 1);
        Assertions.assertThat(userService.getUserById(111L).getEvents().size() > 0);

        }
    }




=======
        userService.addEvent(
                user.getId(),event
        );

        Assertions.assertThat(userService.getUserById(111L).getUsername()== "jeanne");
        Assertions.assertThat(eventService.listEvents().size()==1);
        Assertions.assertThat(userService.getUserById(111L).getEvents().size()>0);

        /**
         //eventRepository.save(event);
         //eventRepository.save(event);
         userService.addEvent(user.getId(), event);
         Assertions.assertThat(eventService.listEvents().size()==1);
         Assertions.assertThat(userService.getUserById(111L).getEvents().size()>0);
         */



    }



}
>>>>>>> b82844de0e9a26703cfa09ccfdd90409484e1192
