package com.moqi.before20200530.bean;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.Validator;
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

    /**
     * 从版本开始1.4.0，可以将ValidationException实例打印为JSON格式的故障报告。
     * <p>
     * 该ValidationException#toJSON()方法返回JSONObject具有以下键的实例：
     * "message"：程序员友好的异常消息（验证失败的描述）
     * "keyword"：违反的JSON模式关键字
     * "pointerToViolation"：一个JSON指针，指示从输入文档根目录到其片段的路径，这导致验证失败
     * "schemaLocation"：一个JSON指针，指示从模式JSON根到违反关键字的路径
     * "causingExceptions"：子异常的数组（可能为空）。每个子异常均表示为JSON对象，其结构与此清单中所述相同。请参阅以上有关引起异常的更多信息。
     * <p>
     * 请注意，完整的故障报告是分层树结构：可以使用找出原因的子原因#getCausingExceptions()。
     */
    @Test()
    public void exceptionJSON() {
        String source = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"apple\",\n" +
                "  \"price\": -100 \n" +
                "}";

        ValidationException validationException =
                Assertions.assertThrows(ValidationException.class,
                        () -> SCHEMA.validate(new JSONObject(source)));

        JSONObject json = validationException.toJSON();
        System.out.println("json = " + json);
    }

    /**
     * 早期故障模式
     * 默认情况下，收集模式下的验证错误报告（请参见“调查失败”一章）。这对于获得详细的错误报告很方便，
     * 但是在某些情况下，如果发现故障而不检查JSON文档的其余部分，则停止验证是比较合适的。切换此快速失败的验证模式
     * <p>
     * 您必须Validator为架构显式构建实例，而不是调用Schema#validate(input)
     * 你要调用failEarly()的方法ValidatorBuilder
     * 例：
     * <p>
     * 注意：Validator该类是不可变的并且是线程安全的，因此您不必为每次验证都创建一个新类，只需要配置一次即可。
     */
    @Test()
    public void earlyFailureMode() {
        String source = "{\n" +
                "  \"id\": \"1\",\n" +
                "  \"name\": null,\n" +
                "  \"price\": -100 \n" +
                "}";

        Validator validator = Validator.builder().failEarly().build();

        Assertions.assertThrows(ValidationException.class,
                () -> validator.performValidation(SCHEMA, new JSONObject(source)));
    }


}
