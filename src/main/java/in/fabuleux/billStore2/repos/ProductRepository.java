package in.fabuleux.billStore2.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.fabuleux.billStore2.entities.Contact;
import in.fabuleux.billStore2.entities.Product;
import in.fabuleux.billStore2.entities.User;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long>
{
	@Query("Select product from Product product where product.user = :user")
	List<Product> findAllByUser(@Param("user") User user, Pageable pageable);
}
