package org.px.modules.demo.customerpayment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.px.modules.demo.customerpayment.entity.CustomerPayment;

/**
 * @Description: 顾客支付信息管理
 * @@author 品讯科技
 * @Date:   2022-12-21
 * @Version: V1.0
 */
public interface CustomerPaymentMapper extends BaseMapper<CustomerPayment> {

    @Select("select sort from customer_payment where id=#{id} ")
    Integer upfindsort(String id);

    @Select("select id from customer_payment where sort=#{i} ")
    String findproid(int i);

    @Update("update customer_payment set sort =#{i} where id = #{id} ")
    void innew(String id, int i);

    @Update("update customer_payment set sort =#{sort} where id = #{proid} ")
    void innewpro(String proid, Integer sort);

    @Select("select sort from customer_payment where id=#{id} ")
    int findSort(String id);
}
