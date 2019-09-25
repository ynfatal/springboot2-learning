package com.fatal.repository;

import com.fatal.entity.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Fatal
 * @date 2019/9/25 0025 20:38
 */
public interface CityRepository extends ElasticsearchRepository<City, String> {

    City findByTheDetail(String theDetail);

}
