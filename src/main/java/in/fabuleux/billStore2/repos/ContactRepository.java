package in.fabuleux.billStore2.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.fabuleux.billStore2.entities.Contact;
import in.fabuleux.billStore2.entities.User;

@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

	@Query("Select contact from Contact contact where contact.user = :user")
	List<Contact> findAllByUser(@Param("user") User user, Pageable pageable);

}
