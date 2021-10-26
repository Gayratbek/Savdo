package uz.repo;

import org.springframework.data.repository.CrudRepository;
import uz.domain.Player;

import java.util.List;

public interface PlayerRepo extends CrudRepository<Player, Long> {
    List<Player> findByComment(String comment);
}
