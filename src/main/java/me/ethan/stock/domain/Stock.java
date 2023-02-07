package me.ethan.stock.domain;

import javax.persistence.*;

@Entity
public class Stock {
    //id, productId, quantity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long quantity;

    @Version
    private Long version;

    public Stock() {
    }

    public Stock(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void  decreaseStock(Long quantity){
        if(this.quantity - quantity < 0){
            throw new RuntimeException("수량 없음");
        }
        this.quantity = this.quantity - quantity;
    }
}
