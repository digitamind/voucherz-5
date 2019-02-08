package com.interswitch.discountvoucherz.api.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Page<T> {
    private Long count;
    private List<T> content;
}
