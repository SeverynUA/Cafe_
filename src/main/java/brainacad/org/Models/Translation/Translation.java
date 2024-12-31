package brainacad.org.Models.Translation;

import brainacad.org.Models.Language.Language;
import brainacad.org.Models.Product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Translation
{
    private long id;
    private Product product;
    private Language language;
    private String name;
}
