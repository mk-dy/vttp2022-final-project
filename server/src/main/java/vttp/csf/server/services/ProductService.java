package vttp.csf.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.csf.server.models.FinalProduct;
import vttp.csf.server.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepo;

    public String createFinalProduct(FinalProduct finalProduct) {

        if (!productRepo.createFinalProduct(finalProduct)) {
            return "Error! Product cannot be added.";
        } else {
            return "Success! Product has been added!";
        }
    }

    public List<FinalProduct> getFinalProductByUserId(String userId) {

        return productRepo.getFinalProduct(userId);
    }
}
