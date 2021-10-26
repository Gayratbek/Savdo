package uz.futbolteam.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.futbolteam.domain.User;

public interface UserRepo extends JpaRepository<User, Long > {
    User findByUsername(String username);

    User findByActivationCode(String code);
}
