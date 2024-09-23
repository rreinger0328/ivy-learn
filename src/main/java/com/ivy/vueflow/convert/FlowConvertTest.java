package com.ivy.vueflow.convert;//package com.ming.core.el.convert;
//
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class FlowConvertTest implements ApplicationRunner {
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        FlowConvert convert = new VueFlowConvert();
//        String json = convert.el2Json("WHEN(a, THEN(b, c));");
//        String el = convert.json2EL(json);
//        System.out.println(json);
//        System.out.println(el);
//    }
//
//}
