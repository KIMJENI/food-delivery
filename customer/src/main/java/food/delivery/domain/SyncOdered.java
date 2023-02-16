package food.delivery.domain;

import food.delivery.domain.*;
import food.delivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class SyncOdered extends AbstractEvent {

    private Long id;
    private String status;

    public SyncOdered(){
        super();
    }
}
