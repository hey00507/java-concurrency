package me.ethan.stock.service;

import me.ethan.stock.domain.Stock;
import me.ethan.stock.repository.StockRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }


//    @Transactional
    public synchronized void decreaseStock(Long id, Long quantity){
        //get stock
        //재고 감소
        //저장
        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decreaseStock(quantity);
        stockRepository.saveAndFlush(stock);
    }
}
