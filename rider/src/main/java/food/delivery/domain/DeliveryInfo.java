package food.delivery.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import lombok.Data;


@Entity
@Table(name="DeliveryInfo_table")
@Data
public class DeliveryInfo {

        @Id
        //@GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private String orderId;
        private String status;
        private String address;


}
