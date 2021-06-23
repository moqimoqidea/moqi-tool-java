package com.moqi.in20210530;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class Person2PO extends Base2PO {

    private String name;

}
