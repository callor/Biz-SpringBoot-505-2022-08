package com.callor.book.service.auth;

// Ctrl + Alt + O : import 최적화
import com.callor.book.model.UserVO;
import com.callor.book.pesistence.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImplV1 implements UserDetailsService {

    private final UserDao userDao;
    public UserDetailsServiceImplV1(UserDao userDao) {
        this.userDao = userDao;
    }

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
        return userVO;
    }
}
