package com.fatal.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author Fatal
 * @date 2019/9/3 0003 18:31
 */
@Data
@Accessors(chain = true)
@Document(indexName = "city", type = "city_search", replicas = 0)
public class City {

    @Id
    private String id;

    private String name;

    private String culture;

}
