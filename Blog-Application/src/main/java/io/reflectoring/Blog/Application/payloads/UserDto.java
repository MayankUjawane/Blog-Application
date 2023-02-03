package io.reflectoring.Blog.Application.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;

    @NotEmpty
    private String name;

    @Email
    private String email;

    @Size(min=3, message = "Password must be minimum of 3 size")
    private String password;

    @NotEmpty
    private String about;
}
