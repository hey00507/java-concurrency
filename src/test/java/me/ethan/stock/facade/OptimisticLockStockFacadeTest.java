package me.ethan.stock.facade;

import me.ethan.stock.domain.Stock;
import me.ethan.stock.repository.StockRepository;
import me.ethan.stock.service.OptimisticLockStockService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OptimisticLockStockFacadeTest {

    @Autowired
    private  OptimisticLockStockFacade optimisticLockStockFacade;

    @Autowired
    private StockRepository stockRepository;


    @AfterEach
    public void delete(){stockRepository.deleteAll();}

    @Test
    public void apiCall100TimesAtOnce() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);
        for ( int i= 0; i<threadCount; i++){
            executorService.submit(()->{
                try {
                    optimisticLockStockFacade.decrease(1L, 1L);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Stock stock = stockRepository.findById(1L).orElseThrow();

        assertEquals(0L, stock.getQuantity());
    }

}