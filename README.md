# deserialization-with-final-fields
This is an example project to show that Spring Boot applications with Jackson for JSON
mapping and Lombok for convenience of coding data model are able to deserialize JSON 
HTTP POST input into Java class with **final** fields and Lombok `@Data` annotation that 
will generate constructor and getters, but **no** setters.

This POST body
```json
{
  "str": "test", 
  "i": "3"
}
```

Will be deserialized into 
> *Note* no setters will be generated on final fields, so `DomainClass` is following 
> the **Immutable Object** pattern.
```java
@Data
public class DomainClass {
    private final String str;
    private final int i;
}
```

Used as HTTP POST body input as well as output in the controller
```java
@RestController
public class Controller {
    @PostMapping("/echo")
    public DomainClass echo(@RequestBody DomainClass input) {
        return new DomainClass(input.getStr() + "-echo", input.getI() + 10);
    }
}
```
