package com.qf.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@TableName("t_goods")
@Data
public class Goods implements Serializable {

    @TableId(type = IdType.AUTO)
    public Integer id;

    private String gname;

    private String gdesc;

    private BigDecimal gprice;

    private Integer gstock;

    private Integer gtype;

    private String gpic;
}
