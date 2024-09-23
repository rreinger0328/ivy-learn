package com.ivy.vueflow.parser.entity.node;

import lombok.Data;

@Data
public class Node {

    public String id;
    private String type;
    public String label;
    private Position position;
    private NodeData data;
    private String parentNode;
    private Boolean initialized;

    public Node() {
    }

    public Node(String id, String label) {
        this.id = id;
        this.label = label;
    }
}
