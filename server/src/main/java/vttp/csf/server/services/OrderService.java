package vttp.csf.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.csf.server.models.OrderResp;
import vttp.csf.server.repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepo;

    public Optional<List<OrderResp>> getOrderById(String userId) {
        return orderRepo.getByUserId(userId);
    }
}
