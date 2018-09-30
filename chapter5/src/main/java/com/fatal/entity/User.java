package com.fatal.entity;

        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import lombok.experimental.Accessors;

/**
 * User 实体
 * @author: Fatal
 * @date: 2018/9/30 0030 11:55
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;

}
