package vttp.csf.server.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.csf.server.models.Favourite;
import vttp.csf.server.models.FinalProduct;
import vttp.csf.server.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepo;

    public String addFav(Favourite fav) {

        if (!productRepo.addFav(fav)) {
            return "Error! Product cannot be added.";
        } else {
            return "Success! Product has been added!";
        }
    }

    public List<Favourite> getFav(String userId) {

        return productRepo.getFav(userId);
    }

    public String deleteFav(String id) {

        if (!productRepo.deleteFav(id)) {
            return "Error! Product cannot be removed from your favourites.";
        } else {
            return "Success! Product has been removed from your favourites!";
        }
    }


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
