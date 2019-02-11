package com.interswitch.voucherz.library.model;

import com.interswitch.voucherz.library.utils.CharsetType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
@Getter
@Builder
@ToString
public class CodeConfig {

    public final static char PATTERN_PLACEHOLDER = '#';
    public final static int DEFAULT_LENGTH = 8;

    private CodeConfig(Integer length, CharsetType charsetType, String prefix, String postfix, String pattern, String customCharset, Integer quantity) {
        if(length == null){
            length = DEFAULT_LENGTH;
        }

        if(prefix == null){
            prefix = prefix;
        }
        if (pattern == null) {
            char[] chars = new char[length];
            Arrays.fill(chars, PATTERN_PLACEHOLDER);
            pattern = new String(chars);
        }

        if(charsetType == null){
            charsetType = CharsetType.ALPHANUMERIC;
        }

        this.length = length;
        this.prefix = prefix;
        this.postfix = postfix;
        this.pattern = pattern;
        this.charsetType = charsetType;
        this.customCharset = customCharset;
        this.quantity = quantity;

    }

    private Integer length;

    private CharsetType charsetType;

    private String prefix;

    private String postfix;

    private String pattern;

    private String customCharset;

    private Integer quantity;
}
