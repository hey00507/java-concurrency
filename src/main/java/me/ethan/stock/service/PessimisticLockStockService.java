package me.ethan.stock.service;

import me.ethan.stock.domain.Stock;
import me.ethan.stock.repository.StockRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PessimisticLockStockService {
    private final StockRepository stockRepository;

    public PessimisticLockStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease(Long id, Long quantity){
        Stock stock = stockRepository.findByIdWithPessimisticLock(id);
        stock.decreaseStock(quantity);
        stockRepository.saveAndFlush(stock);
    }
}
