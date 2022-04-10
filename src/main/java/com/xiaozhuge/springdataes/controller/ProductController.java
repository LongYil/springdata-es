package com.xiaozhuge.springdataes.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaozhuge.springdataes.bean.Product;
import com.xiaozhuge.springdataes.dao.ProductDao;
import netscape.javascript.JSObject;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


/**
 * @author liyinlong
 * @description
 * @since 2022/4/9 20:11
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Qualifier("esRestClient")
    RestHighLevelClient highLevelClient;

    @Autowired
    ProductDao productDao;

    @GetMapping("/save")
    public void save(){
        Product product = new Product();
        product.setCategory("充电器");
        product.setPrice(23.02);
        product.setTitle("小米充电器-Mi8");
        product.setImages("http:/kjaskdf.test.cn/dstes.png");
        productDao.save(product);
    }

    @GetMapping("/list")
    public void list(){
        Iterable<Product> all = productDao.findAll();
        for (Product product : all) {
            System.out.println(product);
        }
    }

    @GetMapping("/query")
    public void query() throws IOException {
        SearchRequest request = new SearchRequest();
        //指定要查询的索引名称
        request.indices("shopping");
        //构建查询条件对象
        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        request.source(query);
        SearchResponse response = highLevelClient.search(request, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getInternalResponse().hits().getHits();
        for (SearchHit hit : hits) {
            JSONObject o = (JSONObject)JSONObject.parse(hit.getSourceAsString());
            System.out.println(o);
        }
        System.out.println(response.getHits());
    }



}
