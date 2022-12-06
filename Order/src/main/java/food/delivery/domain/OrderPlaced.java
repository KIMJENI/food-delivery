package food.delivery.domain;

import food.delivery.domain.*;
import food.delivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String foodId;
    private List<String> options;
    private String address;
    private String customerId;
    private Integer qty;
    private Integer amount;

    public OrderPlaced(OrderList aggregate){
        super(aggregate);
    }
    public OrderPlaced(){
        super();
    }
}
