package in.fabuleux.billStore2.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.fabuleux.billStore2.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
