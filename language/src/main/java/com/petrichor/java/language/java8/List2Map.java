package com.petrichor.java.language.java8;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class List2Map {
    class Account {
        private Long Id;
        private String username;

        Long getId() {
            return Id;
        }

        public void setId(Long id) {
            Id = id;
        }

        String getUsername() {
            return username;
        }

        public void setUsername(String userName) {
            this.username = userName;
        }
    }

    public Map<Long, String> getIdNameMap(List<Account> accounts) {
        return accounts.stream().collect(Collectors.toMap(Account::getId, Account::getUsername));
    }

    // 收集成实体本身map
    public Map<Long, Account> getIdAccountMap(List<Account> accounts) {
        return accounts.stream().collect(Collectors.toMap(Account::getId, account -> account));
    }

    //account -> account是一个返回本身的lambda表达式，其实还可以使用Function接口中的一个默认方法代替，使整个方法更简洁优雅：
    public Map<Long, Account> getIdAccountMap1(List<Account> accounts) {
        return accounts.stream().collect(Collectors.toMap(Account::getId, Function.identity()));
    }

    // 重复key的情况
    public Map<String, Account> getNameAccountMap(List<Account> accounts) {
        return accounts.stream().collect(Collectors.toMap(Account::getUsername, Function.identity()));
    }

    // 上面方法可能报错（java.lang.IllegalStateException: Duplicate key），因为name是有可能重复的。
    // toMap有个重载方法，可以传入一个合并的函数来解决key冲突问题：
    public Map<String, Account> getNameAccountMap1(List<Account> accounts) {
        return accounts.stream().collect(Collectors.toMap(Account::getUsername, Function.identity(),
                (key1, key2) -> key2));
    }

    // 指定具体收集的map
    // toMap还有另一个重载方法，可以指定一个Map的具体实现，来收集数据：
    public Map<String, Account> getNameAccountMap2(List<Account> accounts) {
        return accounts.stream().collect(Collectors.toMap(Account::getUsername, Function.identity(),
                (key1, key2) -> key2, LinkedHashMap::new));
    }
}
