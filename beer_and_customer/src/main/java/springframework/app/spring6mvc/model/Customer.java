package springframework.app.spring6mvc.model;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {
    
    private UUID id;
    private String name;
    private Integer age;

}
