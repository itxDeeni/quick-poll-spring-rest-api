package com.appress.repository;

import org.springframework.data.repository.CrudRepository;
import com.appress.domain.Option;

public interface OptionRepository extends CrudRepository<Option, Long> {
}
