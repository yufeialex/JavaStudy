/**
 * Meituan.com Inc.
 * Copyright (c) 2010-2018 All Rights Reserved.
 */
package com.yufei.languagebasic.concurrent.collection;

/**
 * <p></p>
 *
 * @author xinyufei@meituan.com
 * @version $Id: Task.java, v 0.1 2018-06-09 下午3:22 @xinyufei $$
 */

public class Task implements Comparable<Task> {

    private int    id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Task task) {
        return this.id > task.id ? 1 : (this.id < task.id ? -1 : 0);
    }

    public String toString() {
        return this.id + "," + this.name;
    }

}
