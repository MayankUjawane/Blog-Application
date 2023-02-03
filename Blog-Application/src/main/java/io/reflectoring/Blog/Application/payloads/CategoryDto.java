package io.reflectoring.Blog.Application.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer id;

    @NotBlank
    @Size(min = 4, message = "Title should be minimum of size 4.")
    private String categoryTitle;

    @NotBlank
    private String categoryDescription;
}
