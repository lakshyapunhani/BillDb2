package in.fabuleux.billStore2.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.fabuleux.billStore2.entities.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
