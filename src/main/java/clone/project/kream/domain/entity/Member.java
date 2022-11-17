package clone.project.kream.domain.entity;

import clone.project.kream.service.dto.request.MemberRequestDto;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String email;

    private String password;

    private String profileImageUrl;

    private String nickname;

    private int shoeSize;

    public Member(MemberRequestDto memberRequestDto) {
        this.email = memberRequestDto.getEmail();
        this.password = memberRequestDto.getPassword();
        this.shoeSize = memberRequestDto.getShoeSize();
    }

    public boolean validatePassword(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.matches(password, this.password);
    }
}
