//package com.interswitch.voucherz.gift.util;
//
//import com.interswitch.voucherz.library.model.CodeConfig;
//import com.interswitch.voucherz.library.utils.VoucherCodeGenerator;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.sql.Timestamp;
//import java.util.Date;
//import java.util.UUID;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class VoucherGeneratorTest {
//
//    @Test
//    public void generate(){
//        VoucherCodeGenerator generator = new VoucherCodeGenerator();
//        CodeConfig config = CodeConfig.builder().length(9).build();
//        System.out.println(generator.generate(config));
//        Timestamp ts = new Timestamp(new Date().getTime());
//        System.out.println(ts);
//    }
//}
