package com.fastcampuspay.remittance.adapter.in.web;

import com.fastcampuspay.remittance.adapter.in.web.Test.InnerClass;
import com.fastcampuspay.remittance.adapter.in.web.Test.StaticInnerClass;

public class Test2 {

    public static void main(String[] args) {

        StaticInnerClass staticInnerClass = new StaticInnerClass();
        StaticInnerClass staticInnerClass2 = new StaticInnerClass();

        Test test = new Test();
        InnerClass innerClass = new Test().new InnerClass();
        InnerClass innerClass2 = new Test().new InnerClass();
        System.out.println(innerClass);
        System.out.println(innerClass2);

        System.out.println(staticInnerClass);
        System.out.println(staticInnerClass2);
    }
}
