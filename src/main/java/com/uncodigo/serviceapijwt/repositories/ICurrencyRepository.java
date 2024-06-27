package com.uncodigo.serviceapijwt.repositories;

import com.uncodigo.serviceapijwt.models.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICurrencyRepository extends JpaRepository<Currency, Long> {

}
