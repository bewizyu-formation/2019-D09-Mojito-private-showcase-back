package fr.formation.util;

import fr.formation.artist.Artist;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChecksTest {

    @Test
    public void checkEmail() {
        Assertions.assertThat(Checks.checkEmail("test@java.com")).isTrue();
    }

    @Test
    public void checkEmailNull() {
        Assertions.assertThat(Checks.checkEmail(null)).isFalse();
    }


    @Test
    public void checkEmailMissingAt() {
        Assertions.assertThat(Checks.checkEmail("test.java.com")).isFalse();
    }

    @Test
    public void checkEmailOnlyAt() {
        Assertions.assertThat(Checks.checkEmail("@")).isFalse();
    }

    @Test
    public void checkEmailFalse1() {
        Assertions.assertThat(Checks.checkEmail("@tezrz")).isFalse();
    }

    @Test
    public void checkEmailFalse2() {
        Assertions.assertThat(Checks.checkEmail("aze@")).isFalse();
    }

    @Test
    public void checkEmailMissingDot1() {
        Assertions.assertThat(Checks.checkEmail("fdsfsdfds@tezrz")).isFalse();
    }

    @Test
    public void checkEmailMissingDot2() {
        Assertions.assertThat(Checks.checkEmail("fdsfsd.fds@tezrz")).isFalse();
    }

    @Test
    public void checkEmailStartingDotAfterAt() {
        Assertions.assertThat(Checks.checkEmail("fdsfsdfds@.tez.rz")).isFalse();
    }

    @Test
    public void checkEmailEndingDotAfterAt() {
        Assertions.assertThat(Checks.checkEmail("fdsfsdfds@tez.rz.")).isFalse();
    }

    @Test
    public void checkEmailEndingDotBeforeAt() {
        Assertions.assertThat(Checks.checkEmail("fdsfsdfds.@tezrz.com")).isFalse();
    }

    @Test
    public void checkEmailStartDotBeforeAt() {
        Assertions.assertThat(Checks.checkEmail(".fdsfsdfds@tezrz.com")).isFalse();
    }

    @Test
    public void checkPassword() {
        Assertions.assertThat(Checks.checkPassword("LoveK1ttens")).isTrue();
    }

    @Test
    public void checkPasswordMissingNumbers() {
        Assertions.assertThat(Checks.checkPassword("LoveKittens")).isFalse();
    }

    @Test
    public void checkPasswordMissingUpperCase() {
        Assertions.assertThat(Checks.checkPassword("lovek1ttens")).isFalse();
    }

    @Test
    public void checkPasswordMissingLowerCase() {
        Assertions.assertThat(Checks.checkPassword("LOVEK1TTENS")).isFalse();
    }
    @Test
    public void checkPasswordTooShort() {
        Assertions.assertThat(Checks.checkPassword("aZ1")).isFalse();
    }

    @Test
    public void checkPasswordNull() {
        Assertions.assertThat(Checks.checkPassword(null)).isFalse();
    }

    @Test
    public void checkArtist() {
        Artist newArtist = new Artist();
        newArtist.setUsername("usrrname");
        newArtist.setName("name");
        newArtist.setDescription("lorem ipsum");
        newArtist.setAdress("1 rue");
        newArtist.setEmail("aze@rezr.com");
        newArtist.setImage("image");
        newArtist.setImageType("imType");
        newArtist.setCity("city");
        Assertions.assertThat(Checks.check(newArtist)).isTrue();
    }

    @Test
    public void checkArtist2() {
        Artist newArtist = new Artist();
        newArtist.setUsername("usrrname");
        newArtist.setName("name");
        newArtist.setDescription("lorem ipsum");
        newArtist.setAdress("1 rue");
        newArtist.setEmail("aze@rezr.com");
        newArtist.setCity("city");
        Assertions.assertThat(Checks.check(newArtist)).isTrue();
    }

    @Test
    public void checkArtistPartialImage1() {
        Artist newArtist = new Artist();
        newArtist.setUsername("usrrname");
        newArtist.setName("name");
        newArtist.setDescription("lorem ipsum");
        newArtist.setAdress("1 rue");
        newArtist.setEmail("aze@rezr.com");
        newArtist.setImage("image");
        newArtist.setCity("city");
        Assertions.assertThat(Checks.check(newArtist)).isFalse();
    }

    @Test
    public void checkArtistPartialImage2() {
        Artist newArtist = new Artist();
        newArtist.setUsername("usrrname");
        newArtist.setName("name");
        newArtist.setDescription("lorem ipsum");
        newArtist.setAdress("1 rue");
        newArtist.setEmail("aze@rezr.com");
        newArtist.setImageType("imType");
        newArtist.setCity("city");
        Assertions.assertThat(Checks.check(newArtist)).isFalse();
    }

    @Test
    public void checkPartialArtist1() {
        Artist newArtist = new Artist();
        newArtist.setUsername("usrrname");
        newArtist.setName("name");
        newArtist.setDescription("lorem ipsum");
        newArtist.setAdress("1 rue");
        newArtist.setEmail("aze@rezr.com");
        Assertions.assertThat(Checks.check(newArtist)).isFalse();
    }

    @Test
    public void checkPartialArtist2() {
        Artist newArtist = new Artist();
        newArtist.setUsername("usrrname");
        newArtist.setName("name");
        newArtist.setDescription("lorem ipsum");
        newArtist.setAdress("1 rue");
        newArtist.setCity("city");
        Assertions.assertThat(Checks.check(newArtist)).isFalse();
    }

    @Test
    public void checkPartialArtist3() {
        Artist newArtist = new Artist();
        newArtist.setUsername("usrrname");
        newArtist.setName("name");
        newArtist.setDescription("lorem ipsum");
        newArtist.setEmail("aze@rezr.com");
        newArtist.setCity("city");
        Assertions.assertThat(Checks.check(newArtist)).isFalse();
    }

    @Test
    public void checkPartialArtist4() {
        Artist newArtist = new Artist();
        newArtist.setUsername("usrrname");
        newArtist.setName("name");
        newArtist.setAdress("1 rue");
        newArtist.setEmail("aze@rezr.com");
        newArtist.setCity("city");
        Assertions.assertThat(Checks.check(newArtist)).isFalse();
    }

    @Test
    public void checkPartialArtist5() {
        Artist newArtist = new Artist();
        newArtist.setUsername("usrrname");
        newArtist.setDescription("lorem ipsum");
        newArtist.setAdress("1 rue");
        newArtist.setEmail("aze@rezr.com");
        newArtist.setCity("city");
        Assertions.assertThat(Checks.check(newArtist)).isFalse();
    }

    @Test
    public void checkPartialArtist6() {
        Artist newArtist = new Artist();
        newArtist.setName("name");
        newArtist.setDescription("lorem ipsum");
        newArtist.setAdress("1 rue");
        newArtist.setEmail("aze@rezr.com");
        newArtist.setCity("city");
        Assertions.assertThat(Checks.check(newArtist)).isFalse();
    }



    @Test
    public void checkArtistNullShouldFail() {
        Assertions.assertThat(Checks.check((Artist)null)).isFalse();
    }

    @Test
    public void checkArtistEmptyShouldFail() {
        Assertions.assertThat(Checks.check(new Artist())).isFalse();
    }
}