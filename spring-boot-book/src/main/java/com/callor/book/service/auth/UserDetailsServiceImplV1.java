package com.callor.book.service.auth;

// Ctrl + Alt + O : import 최적화
import com.callor.book.model.UserRole;
import com.callor.book.model.UserVO;
import com.callor.book.pesistence.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserDetailsServiceImplV1 implements UserDetailsService {

    private final UserDao userDao;
    public UserDetailsServiceImplV1(UserDao userDao) {
        this.userDao = userDao;
    }

    /*
    현재 userDao.findById() method 를 실행하면
    tbl_users table 과 tbl_authorities table 을 같이 SELECT 한다
    그런데 @OnToMany 설정에 fetch = FetchType.LAZY 부분을 설정해놨다

    tbl_users 테이블을 먼저 SELECT 를 하고
    혹시 어딘가에서 tbl_authorities table 의 데이터 확인하는 코드가
    실행되면 그때 tbl_authorities table 을 SELECT 한다
    만약에 그 사이 시간동안 다른 프로세스(JOB)이 tbl_authorities table 을
    변경 하면 데이터 참조 무결성이 무너진다

    참조 무결성이 무너지는 것을 방지 하기 위하여 2개 이상의 table 에 대하여
    CRUD 를 수행할때는 반드시 Transaction 으로 묶어야 한다

    Failed to lazily initialize a collection of rol
    @Transactional 을 설정해 주어야 한다
     */
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("USER SERVICE : {}",username);

        // findById() 는 Optional type VO 를 return 한다
        // Optional type 의 vo 에서 실제 UserVO 데이터를 Get 하기 위하여
        // orElse() 를 사용하는데
        // orElse() 매개변수로 blank UserVO 생성하여 주었다
        // 만약 username 으로 select 를 했는데 데이터가 없으면
        // blank UserVO를 얻게 된다
        // null check 를 하지 않아도 안전한 코드가 된다
        UserVO userVO = userDao.findById(username).orElse(UserVO.builder().build());
        if( !userVO.getUsername().equals(username) ) {
            throw new UsernameNotFoundException(username + " 없음");
        }
        log.debug("로그인한 사용자 {}", userVO);

        List<GrantedAuthority> grantList = new ArrayList<>();
        Set<UserRole> roleList = userVO.getUserRoles();
        for(UserRole role : roleList) {
            log.debug("사용자 ROLE 정보 {}",role.getRolename());
            // 문자열로 되어있는 ROLE 정보를
            // GrantedAuthority 정보로 변환하여 grantList 에 추가
            grantList.add(new SimpleGrantedAuthority(role.getRolename()));
        }
        userVO.setAuthorities(grantList);
        return userVO;
    }
}
