package com.fatal.groups;

/**
 * 自定义验证组（作用有点像标记）
 * 更新的时候，需要校验全部属性，所以不需要给它分组，新增的时候只需校验部分属性，所以可以给它分组
 * @author: Fatal
 * @date: 2018/11/28 0028 15:23
 */
public class Groups {

    public interface Insert {}

    public interface Update {}

    /**
     * Update组中，可以为null且不能为空的属性，在Other中不能为空
     */
    public interface Other {}


}
