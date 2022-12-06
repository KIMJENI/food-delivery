package food.delivery.domain;

import food.delivery.domain.*;
import food.delivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class OrderReviewed extends AbstractEvent {

    private Long id;
    private String foodId;
    private String address;
    private String status;
    private String customerId;

    public OrderReviewed(OrderList aggregate){
        super(aggregate);
    }
    public OrderReviewed(){
        super();
    }
}
