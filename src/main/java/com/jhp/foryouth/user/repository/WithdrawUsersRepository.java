package com.jhp.foryouth.user.repository;


import com.jhp.foryouth.user.domain.User;
import com.jhp.foryouth.user.domain.WithdrawUsers;
import org.springframework.data.jpa.repository.JpaRepository;



public interface WithdrawUsersRepository extends JpaRepository<WithdrawUsers, Long> {
}
