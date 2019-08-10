package com.fatal;

import com.fatal.dto.CustomerDetailDTO;
import com.fatal.entity.Address;
import com.fatal.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author Fatal
 * @date 2019/8/9 0009 12:19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MultiTableTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void saveAddressTest() {
        // 与手动添加id的Customer关联
        Address address1 = new Address()
                .setCustomerId("137063")
                .setAddress("shantou");
        Address address2 = new Address()
                .setCustomerId("137063")
                .setAddress("guangzhou");
        // 与自动生成id的Customer关联
        Address address3 = new Address()
                .setCustomerId("5d4e908fe429c66df040ae69")
                .setAddress("foshan");
        Address address4 = new Address()
                .setCustomerId("5d4e908fe429c66df040ae69")
                .setAddress("hangzhou");
        mongoTemplate.insertAll(Arrays.asList(address1, address2, address3, address4));
    }

    @Test
    public void findAllAddressTest() {
        List<Address> addressList = mongoTemplate.findAll(Address.class);
        addressList.forEach(System.out::println);
    }

    /**
     * @method public static LookupOperation lookup(String from, String localField, String foreignField, String as)
     * @parameter
     *  from: 从表
     *  localField: 主表被关联的键（如果关联的是主键，必须写 “_id”，下划线不能省略）
     *  foreignField: 从表外键
     *  as: 从表集合名称
     * @desc 从表关联主表时，如果主表的id是自动生成的，那么它的值为：ObjectId("...")，这时通过 $lookup 就不能完成关联了。
     */
    @Test
    public void findMultiply() {
        MatchOperation match = Aggregation.match(Criteria.where("age").is(18));
        // 关联操作
        LookupOperation lookup = Aggregation.lookup("address", "_id", "customer_id", "addressList");
        Aggregation aggregation = Aggregation.newAggregation(lookup, match);
        List<CustomerDetailDTO> customerDetail = mongoTemplate.aggregate(aggregation, "customer", CustomerDetailDTO.class).getMappedResults();
        System.out.println(JsonUtil.toJson(customerDetail));
    }

}
