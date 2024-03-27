package com.heima.model.wemedia.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class WmMaterialDto extends PageRequestDto {

    /**
     * 1 收藏
     * 0 为收藏
     */
    private Short isCollection;
}
