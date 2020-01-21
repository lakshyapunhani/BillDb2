package in.fabuleux.billStore2.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import in.fabuleux.billStore2.entities.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
	User findByUsernameAndPassword(String username,String password);
	Optional<User> findByUsername(String username);
}
