package com.moqi.bean;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


/**
 * @author moqi
 * On 3/29/20 22:19
 */

public class ProductTest {

    private static final JSONObject JSON_SCHEMA_OBJECT = new JSONObject(new JSONTokener(ProductTest.class.getResourceAsStream("/json-schema/product-schema.json")));
    private static final Schema SCHEMA = SchemaLoader.load(JSON_SCHEMA_OBJECT);

    @Test
    public void checkRightProduct() {
        String source = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"apple\",\n" +
                "  \"price\": 22.22\n" +
                "}";

        SCHEMA.validate(new JSONObject(source));
    }

    /**
     * 这里因为 Schema 要求 name 为字符串而实际上是布尔值，所以失败。
     */
    @Test()
    public void nameWrongType() {
        String source = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": true,\n" +
                "  \"price\": 22.22\n" +
                "}";

        ValidationException validationException =
                Assertions.assertThrows(ValidationException.class,
                        () -> SCHEMA.validate(new JSONObject(source)));

        Assertions.assertEquals("#/name: expected type: String, found: Boolean",
                validationException.getMessage());

    }

    /**
     * 这里因为 Schema 要求 name 为必填字段实际上无此字段，所以失败。
     */
    @Test()
    public void lackName() {
        String source = "{\n" +
                "  \"id\": 1,\n" +
                "  \"price\": 22.22\n" +
                "}";

        ValidationException validationException =
                Assertions.assertThrows(ValidationException.class,
                        () -> SCHEMA.validate(new JSONObject(source)));

        Assertions.assertEquals("#: required key [name] not found",
                validationException.getMessage());

    }

    /**
     * 这里因为 Schema 要求 price 最低为 0 实际上是负数，所以失败。
     */
    @Test()
    public void negativePrice() {
        String source = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"apple\",\n" +
                "  \"price\": -100 \n" +
                "}";

        ValidationException validationException =
                Assertions.assertThrows(ValidationException.class,
                        () -> SCHEMA.validate(new JSONObject(source)));

        Assertions.assertEquals("#/price: -100 is not greater than 0",
                validationException.getMessage());
    }


}
