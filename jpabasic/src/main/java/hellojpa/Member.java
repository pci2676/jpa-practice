package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Member {
    @Id
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipCode;

    
}
