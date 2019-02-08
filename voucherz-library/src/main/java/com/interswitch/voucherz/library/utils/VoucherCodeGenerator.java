package com.interswitch.voucherz.library.utils;

import com.interswitch.voucherz.library.model.CodeConfig;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public final class VoucherCodeGenerator {

    private static final Random RND = new Random(System.currentTimeMillis());

    public static String generate(CodeConfig config) {
        StringBuilder sb = new StringBuilder();
        CharsetType charsetType = config.getCharsetType();
        char[] charset = null;
        if(charsetType == CharsetType.CUSTOM)
            charset = config.getCustomCharset().toCharArray();
        else
            charset = Charset.getCharSet(charsetType).toCharArray();
        char[] pattern = config.getPattern().toCharArray();

        String prefix = config.getPrefix();
        String postfix = config.getPostfix();

        if (prefix != null) {
            sb.append(prefix);
        }

        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] == CodeConfig.PATTERN_PLACEHOLDER) {
                sb.append(charset[RND.nextInt(charset.length)]);
            } else {
                sb.append(pattern[i]);
            }
        }

        if (postfix != null) {
            sb.append(postfix);
        }

        return sb.toString();
    }
}