package com.moqi.in20210530;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class PersonPO extends BasePO {

    private String name;

}
