package brainacad.org.Models.OrderInfo;

import brainacad.org.Models.Order.Order;
import brainacad.org.Models.Product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetails
{
    private Long id;
    private Order order;
    private Product product;
    private int quantity;

    public void validate() {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
    }
}
