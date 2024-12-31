package brainacad.org.Models.OrderInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statuses
{
    private Long id;
    private String name;
    private String description;
}
