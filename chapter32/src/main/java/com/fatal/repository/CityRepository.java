package com.fatal.repository;

import com.fatal.entity.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Fatal
 * @date 2019/9/3 0003 18:46
 */
public interface CityRepository extends ElasticsearchRepository<City, String> {
}
