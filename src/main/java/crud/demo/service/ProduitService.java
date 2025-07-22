package crud.demo.service;


import crud.demo.model.Produit;
import org.springframework.stereotype.Service;
import crud.demo.repo.ProductRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProduitService {

    private final ProductRepo produitRepository;

    public ProduitService(ProductRepo produitRepository) {
        this.produitRepository = produitRepository;
    }

    public Flux<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    public Mono<Produit> getProduitById(Integer id) {
        return produitRepository.findById(id);
    }

    public Mono<Produit> createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Mono<Produit> updateProduit(Integer id, Produit produit) {
        return produitRepository.findById(id)
                .flatMap(existing -> {
                    existing.setName(produit.getName());
                    existing.setPrice(produit.getPrice());
                    return produitRepository.save(existing);
                });
    }

    public Mono<Void> deleteProduit(Integer id) {
        return produitRepository.deleteById(id);
    }
}

