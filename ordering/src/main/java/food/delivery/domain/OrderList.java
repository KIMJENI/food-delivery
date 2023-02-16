package food.delivery.domain;

import food.delivery.domain.OrderPlaced;
import food.delivery.domain.OrderCanceled;
import food.delivery.domain.OrderReviewed;
import food.delivery.OrderingApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="OrderList_table")
@Data

public class OrderList  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private String foodId;
    
    
    
    
    
    private String address;
    
    
    
    
    
    private String status;
    
    
    
    
    
    private String customerId;

    @PostPersist
    public void onPostPersist(){


        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publishAfterCommit();


        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        food.delivery.external.CancelPaymentCommand cancelPaymentCommand = new food.delivery.external.CancelPaymentCommand();
        // mappings goes here
        OrderingApplication.applicationContext.getBean(food.delivery.external.PaymentService.class)
            .cancelPayment(/* get???(), */ cancelPaymentCommand);



        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();



        OrderReviewed orderReviewed = new OrderReviewed(this);
        orderReviewed.publishAfterCommit();

    }
    @PreRemove
    public void onPreRemove(){
        // Get request from Payment
        //food.delivery.external.Payment payment =
        //    Application.applicationContext.getBean(food.delivery.external.PaymentService.class)
        //    .getPayment(/** mapping value needed */);

    }

    public static OrderListRepository repository(){
        OrderListRepository orderListRepository = OrderingApplication.applicationContext.getBean(OrderListRepository.class);
        return orderListRepository;
    }




    public static void updateStatus(SyncOdered syncOdered){

        /** Example 1:  new item 
        OrderList orderList = new OrderList();
        repository().save(orderList);

        */

        /** Example 2:  finding and process
        
        repository().findById(syncOdered.get???()).ifPresent(orderList->{
            
            orderList // do something
            repository().save(orderList);


         });
        */

        
    }


}
