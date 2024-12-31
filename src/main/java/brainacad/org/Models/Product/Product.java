package brainacad.org.Models.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product
{
    private Long id;
    private String name;
    private Long categoryId;
    private Long subCategoryId;
    private BigDecimal price;
}