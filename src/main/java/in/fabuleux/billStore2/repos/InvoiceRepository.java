package in.fabuleux.billStore2.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import in.fabuleux.billStore2.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
