package crud.demo.repo;


import crud.demo.model.Produit;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ProductRepo extends ReactiveCrudRepository<Produit,Integer> {
}