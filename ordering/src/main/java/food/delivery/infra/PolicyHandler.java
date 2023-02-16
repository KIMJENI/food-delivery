package food.delivery.infra;

import javax.naming.NameParser;

import javax.naming.NameParser;
import javax.transaction.Transactional;

import food.delivery.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import food.delivery.domain.*;

@Service
@Transactional
public class PolicyHandler{
    @Autowired OrderListRepository orderListRepository;
    @Autowired PaymentRepository paymentRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='OrderPlaced'")
    public void wheneverOrderPlaced_Pay(@Payload OrderPlaced orderPlaced){

        OrderPlaced event = orderPlaced;
        System.out.println("\n\n##### listener Pay : " + orderPlaced + "\n\n");


        

        // Sample Logic //
        Payment.pay(event);
        

        

    }

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='OrderRejected'")
    public void wheneverOrderRejected_RejectPayment(@Payload OrderRejected orderRejected){

        OrderRejected event = orderRejected;
        System.out.println("\n\n##### listener RejectPayment : " + orderRejected + "\n\n");


        

        // Sample Logic //
        Payment.rejectPayment(event);
        

        

    }

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='SyncOdered'")
    public void wheneverSyncOdered_UpdateStatus(@Payload SyncOdered syncOdered){

        SyncOdered event = syncOdered;
        System.out.println("\n\n##### listener UpdateStatus : " + syncOdered + "\n\n");


        

        // Sample Logic //
        OrderList.updateStatus(event);
        

        

    }

}


