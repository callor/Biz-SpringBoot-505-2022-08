package com.callor.book.service.impl;

import com.callor.book.model.UserRole;
import com.callor.book.model.UserVO;
import com.callor.book.pesistence.UserDao;
import com.callor.book.pesistence.UserRoleDao;
import com.callor.book.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplV1 implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserDao userDao;
    private final UserRoleDao userRoleDao;

    public UserServiceImplV1(PasswordEncoder passwordEncoder, UserDao userDao, UserRoleDao userRoleDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.userRoleDao = userRoleDao;
    }

    // 회원가입을 하기 위한 Service Method
    /*
    회원가입을 할때 최초의 회원이면 ROLE_ADMIN, ROLE_USER 를 설정하고
    enabled 등을 true 로 설정하여 준다

    두번째 이후 회원가입하는 경우 ROLE_USER 만 지정하고
    enabled 등은 기본값( false ) 로 설정한다
     */
    @Transactional
    @Override
    public UserVO join(UserVO userVO) {

        // tbl_users table 의 데이터 개수 확인
        Long userCount = userDao.count();
        List<UserRole> roleList = new ArrayList<>();

        /*
        테이블에 데이터가 없으면 count() 연산은 당연히 0 을 보여준다
        그러면 userCount == 0 의 논리식도 문제가 없어 보인다
        그런데 간혹 일부 DBMS 엔진중에 table 에 데이터가 없거나 어떤 문제가 있으면
        음수(-) 값을 보여주는 경우가 있다 이런 경우를 대비하여 안전한 코드로 작성하기 위해
        userCount < 1 과 같은 논리식을 사용한다
         */
        // if( userCount == 0 ) {
        if(userCount < 1) {
            // Legacy 에서는 선택적으로 사용을 했으나
            // boot 에서는 필수적으로 체크하는 항목이므로 설정을 해주어아 한다
            userVO.setEnabled(true);
            userVO.setAccountNonExpired(true);
            userVO.setAccountNonLocked(true);
            userVO.setCredentialsNonExpired(true);

            roleList.add(UserRole
                    .builder()
                    .username(userVO.getUsername())
                    .rolename("ROLE_ADMIN").build());
            roleList.add(UserRole
                    .builder()
                    .username(userVO.getUsername())
                    .rolename("ROLE_USER").build());
        } else {
            userVO.setEnabled(false);
            userVO.setAccountNonExpired(true);
            userVO.setAccountNonLocked(true);
            userVO.setCredentialsNonExpired(true);
            roleList.add(UserRole
                    .builder()
                    .username(userVO.getUsername())
                    .rolename("ROLE_USER").build());
        } // end if

        // 비밀번호 암호화 하여 저장하기
        String password = userVO.getPassword();
        String encPassword = passwordEncoder.encode(password);
        userVO.setPassword(encPassword);

        // 1개의 데이터를 insert or Update
        userDao.save(userVO);

        // List 에 담긴 데이터를 insert All or Update ALl
        userRoleDao.saveAll(roleList);

        return null;
    }
}
