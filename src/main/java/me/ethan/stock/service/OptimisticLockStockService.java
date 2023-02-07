package me.ethan.stock.service;

import me.ethan.stock.domain.Stock;
import me.ethan.stock.repository.StockRepository;
import org.springframework.stereotype.Service;

@Service
public class OptimisticLockStockService {
    private final StockRepository stockRepository;

    public OptimisticLockStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }
    
    public void decrease(Long id, Long quantity){
        Stock stock = stockRepository.findByIdWithOptimisticLock(id);
        stock.decreaseStock(quantity);
        stockRepository.saveAndFlush(stock);
    }
}
