package com.zfc.study.domain.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zfc
 * @since 2019-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StockAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String stockCode;

    private String stockName;

    private String stockAddress;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;


}
