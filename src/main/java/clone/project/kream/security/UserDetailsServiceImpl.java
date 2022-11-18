package clone.project.kream.security;

import clone.project.kream.domain.entity.Member;
import clone.project.kream.domain.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Autowired
    public UserDetailsServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member =  memberRepository.findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("아이디가 존재하지 않습니다"));
        return new UserDetailsImpl(member);

    }
}
