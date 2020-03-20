package com.chengfeng.springcloud.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

//网络通讯的时候实体类第一步需要序列化，所以Serializable，orm（对象关系映射）；实现序列化的目的是为了保证流传输不出问题
//@Data —————————————lombok
//@NoArgsConstructor———————无参？
//@Accessors(chain = true)————链式写法
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Dept implements Serializable {
    private Long deptno;//主键
    private String dname;//部门名称
    private String db_source;//标记此数据存在哪个数据库字段;（微服务层面理解）

    //    构造器只需要选一个dname
    public Dept(String dname) {
        this.dname = dname;
    }
}
