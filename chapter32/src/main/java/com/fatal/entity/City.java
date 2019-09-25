package com.fatal.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

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

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Text)
    private String theDetail;

}