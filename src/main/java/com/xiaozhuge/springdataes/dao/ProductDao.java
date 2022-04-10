package com.xiaozhuge.springdataes.dao;

import com.xiaozhuge.springdataes.bean.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author liyinlong
 * @description
 * @since 2022/4/10 9:55
 */
@Repository
public interface ProductDao extends ElasticsearchRepository<Product,String> {

}
