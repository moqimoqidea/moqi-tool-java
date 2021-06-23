package com.moqi.in20210530;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "BasePoBuilder")
public class BasePO {

    private int pageNum = 1;

}
