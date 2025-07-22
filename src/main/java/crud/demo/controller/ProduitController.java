package crud.demo.controller;

import crud.demo.model.Produit;
import crud.demo.service.ProduitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    private final ProduitService produitService;

    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/tous") // GET /api/produits/tous
    public Flux<Produit> getAll() {
        return produitService.getAllProduits();
    }

    @GetMapping("/voir/{id}") // GET /api/produits/voir/1
    public Mono<ResponseEntity<Produit>> getById(@PathVariable Integer id) {
        return produitService.getProduitById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/ajouter") // POST /api/produits/ajouter
    public Mono<ResponseEntity<Produit>> create(@RequestBody Produit produit) {
        return produitService.createProduit(produit)
                .map(p -> ResponseEntity.status(201).body(p));
    }

    @PutMapping("/modifier/{id}") // PUT /api/produits/modifier/1
    public Mono<ResponseEntity<Produit>> update(@PathVariable Integer id, @RequestBody Produit produit) {
        return produitService.updateProduit(id, produit)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/supprimer/{id}") // DELETE /api/produits/supprimer/1
    public Mono<ResponseEntity<Void>> delete(@PathVariable Integer id) {
        return produitService.deleteProduit(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
