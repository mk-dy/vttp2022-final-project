package vttp.csf.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.csf.server.models.Fabric;
import vttp.csf.server.models.Product;
import vttp.csf.server.repository.SearchRepository;

@Service
public class SearchService {
    
    @Autowired
    private SearchRepository searchRepo;

    public List<Product> getProduct() {

        return searchRepo.getProduct();
    }

    public Product getChalkbag() {

        return searchRepo.getChalkbag();
    }

    public Product getChalkbucket() {

        return searchRepo.getChalkbucket();
    }

    public Optional<List<Product>> searchProduct(String query) {
        
        return searchRepo.searchProduct(query);
    }

    public List<Fabric> getFabric() {
        
        return searchRepo.getFabric();
    }



}
