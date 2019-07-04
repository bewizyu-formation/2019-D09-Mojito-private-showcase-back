package fr.formation.userCommun;

import fr.formation.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCommunRepository extends JpaRepository<UserCommun, Long> {

    /**
     * Find all common users.
     **
     * @return the list of users
     */
    public List<UserCommun> findAll();
    public UserCommun findByUsername(String username);
    public Optional<UserCommun> findById(Long id);

}
