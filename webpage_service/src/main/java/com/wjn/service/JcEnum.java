package com.wjn.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 使用枚举的方式消灭if...else
 */
@Getter
@AllArgsConstructor
public enum JcEnum implements RoleOperation {
    ABC {
        @Override
        public String op() {
            return null;
        }
    },

    BCD {
        @Override
        public String op() {
            return null;
        }
    },
    EFG {
        @Override
        public String op() {
            return null;
        }
    };
}
class Judge{
public String judge(String roleName){
    return JcEnum.valueOf(roleName).op();
}
}
