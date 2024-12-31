package brainacad.org.Models.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import brainacad.org.Models.OrderInfo.Statuses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order
{
    private Long id;
    private LocalDateTime orderDate;
    private BigDecimal orderAmount;
    private Statuses status;
}
