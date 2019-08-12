package com.fatal.entity;

import com.fatal.groups.Groups;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Book 实体
 * @author: Fatal
 * @date: 2018/11/28 0028 15:23
 */
@Data
public class Book {

    @NotNull(message = "id 不能为空", groups = Groups.Update.class)
    private Long id;

    /**
     * 纸的质量
     * @desc 更新的时候可以填充，要么不填（可以为null），要填就不能为空（空串）
     *      更新的属性中，很多需要这么校验，可以为 null，但不能为空
     */
    @Length(min = 1, message = "paperQuality 不能为空", groups = Groups.Update.class)
    @NotBlank(message = "paperQuality 不能为空", groups = Groups.Other.class)
    private String paperQuality;

    /**
     * 页码
     */
    @Min(value = 200, message = "pageNumber 不能为小于{value}", groups = Groups.Update.class)
    private Integer pageNumber;

    /**
     * 书名
     */
    @NotBlank(message = "name 不能为空", groups = {Groups.Insert.class, Groups.Update.class})
    private String name;

    /**
     * 价格
     */
    @NotNull(message = "price 不能为空", groups = {Groups.Insert.class, Groups.Update.class})
    private Long price;

}
