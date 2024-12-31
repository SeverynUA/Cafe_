package brainacad.org.Models.Categories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category
{
    private Long id;
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
