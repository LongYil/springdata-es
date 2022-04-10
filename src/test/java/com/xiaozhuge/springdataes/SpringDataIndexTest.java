package com.xiaozhuge.springdataes;

import com.xiaozhuge.springdataes.bean.Product;
import com.xiaozhuge.springdataes.dao.ProductDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author liyinlong
 * @description
 * @since 2022/4/10 10:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataIndexTest {

    @Autowired
    ElasticsearchRestTemplate template;

    @Autowired
    ProductDao productDao;

    @Test
    public void createIndex(){
        System.out.println("索引创建成功");
    }

    @Test
    public void deleteIndex(){

    }

    @Test
    public void save(){
        Product product = new Product();
        product.setId("12");
        product.setCategory("充电器");
        product.setPrice(24.02);
        product.setTitle("小米充电器-mi7");
        product.setImages("http:/kjaskdf.test.cn/dstes.png");
        productDao.save(product);
    }

    @Test
    public void update(){
        Product product = new Product();
        product.setId("12");
        product.setCategory("手机");
        product.setPrice(2999.99);
        product.setTitle("小米充电器-mi2");
        product.setImages("http:/kjaskdf.test.cn/dstes.png");
        productDao.save(product);
    }

    @Test
    public void findById(){
        Product product1 = productDao.findById("12").get();
        System.out.println(product1);
    }

    @Test
    public void deleteById(){
        productDao.deleteById("12");
    }

    @Test
    public void queryAll(){
        Iterable<Product> all = productDao.findAll();
        for (Product product : all) {
            System.out.println(product);
        }
    }

    @Test
    public void benchSave(){
        List<Product> list = new ArrayList<>();
        for (int i =0; i < 10; i++){
            Product product = new Product();
            product.setId(String.valueOf(new Random().nextInt(1000)));
            product.setCategory("手机");
            product.setPrice(2999.99);
            product.setTitle("小米充电器-mi" + new Random().nextInt());
            product.setImages("http:/kjaskdf.test.cn/dstes.png");
            list.add(product);
        }
        productDao.saveAll(list);
    }

    @Test
    public void findByPageable(){
        Sort sort = Sort.by(Sort.Direction.DESC, "price");
        int from = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(from, size, sort);
        Page<Product> all = productDao.findAll(pageRequest);
        for (Product product : all) {
            System.out.println(product);
        }
    }

}
