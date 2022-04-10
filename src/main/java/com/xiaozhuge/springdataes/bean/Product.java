package com.xiaozhuge.springdataes.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author liyinlong
 * @description
 * @since 2022/4/9 20:02
 */
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "iphone")
public class Product {

    @Id
    private String id;
    @Field(name = "p_title", type = FieldType.Keyword)
    private String title;
    @Field(name = "p_category", type = FieldType.Text)
    private String category;
    @Field(name = "p_category", type = FieldType.Double)
    private Double price;
    @Field(name = "p_images", type = FieldType.Keyword, index = false)
    private String images;

}
