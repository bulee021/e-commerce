package com.xsis.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.ecommerce.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(nativeQuery = true, value = """
            select
                exists(
                    select
                        *
                    from
                        m_user
                    where
                        is_delete = false
                    and
                        cast(phone_number as varchar) = :phone_number
                )
            """)
    public Boolean isPhoneNumberExists(@Param("phone_number") String phone_number);

    @Query(nativeQuery = true, value = """
            select
                exists(
                    select
                        *
                    from
                        m_user
                    where
                        is_delete = false
                    and
                        username = :username
                )
            """)
    public Boolean isUsernameExists(@Param("username") String username);

    @Query(nativeQuery = true, value = """
            select
                *
            from
                m_user
            where
                is_delete = false
            and
                username = :username
            and
                password = :password
            """)
    public User login(@Param("username") String username, @Param("password") String password);

    @Query(nativeQuery = true, value = """
            select
                m_user.*
            from
                token
            join
                m_user
            on
                m_user.id_user = token.id_user
            where
                m_user.is_delete = false
            and
                token.token = :token
            """)
    public User getUserByToken(@Param("token") String token);
}
