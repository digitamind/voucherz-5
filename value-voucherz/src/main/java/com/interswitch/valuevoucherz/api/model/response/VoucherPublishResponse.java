package com.interswitch.valuevoucherz.api.model.response;


import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Getter
@Builder
public class VoucherPublishResponse {
    private Integer count;
    private List<PublishEntryResponse> entries;
}

