package com.lijiangde.spring_boot_1.model;

public class Test {
	
	private final long id;
    private final String content;

    public Test(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    

}
