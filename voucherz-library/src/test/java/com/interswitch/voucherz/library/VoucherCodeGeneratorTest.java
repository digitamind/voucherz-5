package com.interswitch.voucherz.library;

import com.interswitch.voucherz.library.model.CodeConfig;
import com.interswitch.voucherz.library.utils.CharsetType;
import com.interswitch.voucherz.library.utils.VoucherCodeGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VoucherCodeGeneratorTest {

    @Autowired
    private VoucherCodeGenerator voucherCodeGenerator;

    @Test
    public void generateCodeOfGivenLength() {
        // given
        CodeConfig config = CodeConfig.builder().quantity(1).charsetType(CharsetType.ALPHABETIC).length(10).build();

        // when
        String code = voucherCodeGenerator.generate(config);

        // then
        assertThat(code.length()).isEqualTo(10);
    }

    @Test
    public void generateNumericCode() {
        // given
        CodeConfig config = CodeConfig.builder().quantity(1).length(4).charsetType(CharsetType.NUMBERS).build();

        // when
        String code = voucherCodeGenerator.generate(config);

        // then
        System.out.println("The generated code is: "+code);
        assertThat(code).matches("^([0-9]){4}$");
    }

    @Test
    public void generateCodeWithPrefix() {
        // given
        CodeConfig config = CodeConfig.builder().quantity(1).length(8).prefix("TEST-").build();

        // when
        String code = voucherCodeGenerator.generate(config);

        // then
        assertThat(code).startsWith("TEST-");
        assertThat(code.length()).isEqualTo(5 /*TEST-*/ + 8 /*random*/);
    }

    @Test
    public void generateCodeWithPostfix() {
        // given
        CodeConfig config = CodeConfig.builder().quantity(1).length(8).postfix("-TEST").build();

        // when
        String code = voucherCodeGenerator.generate(config);

        // then
        assertThat(code).endsWith("-TEST");
        assertThat(code.length()).isEqualTo(8 /*random*/ + 5 /*-TEST*/);
    }

    @Test
    public void generateCodeWithPrefixAndPostfix() {
        // given
        CodeConfig config = CodeConfig.builder().quantity(1).length(8).prefix("TE-").postfix("-ST").build();

        // when
        String code = voucherCodeGenerator.generate(config);

        // then
        assertThat(code).startsWith("TE-");
        assertThat(code).endsWith("-ST");
        assertThat(code.length()).isEqualTo(3 /*TE-*/ + 8 /*random*/ + 3 /*-ST*/);
    }

    @Test
    public void generateCodeFromGivenPattern() {
        // given
        CodeConfig config = CodeConfig.builder().quantity(1).pattern("##-###-##").build();

        // when
        String code = voucherCodeGenerator.generate(config);

        // then
        System.out.println("The generated code is: "+code);
        assertThat(code).matches("^([0-9a-zA-Z]){2}-([0-9a-zA-Z]){3}-([0-9a-zA-Z]){2}$");
    }
}
