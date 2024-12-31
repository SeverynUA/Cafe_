package brainacad.org.Models.Categories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubCategory
{
    private Long id;
    private String name;
    private Long categoryId;

    public SubCategory(Long categoryId, String name) {
        this.name = name;
        this.categoryId = categoryId;
    }

    public SubCategory(String name) {
        this.name = name;
    }
}
