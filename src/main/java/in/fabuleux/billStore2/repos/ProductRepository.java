package in.fabuleux.billStore2.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.fabuleux.billStore2.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>
{

}
