package com.ivy.vueflow.parser.entity.edge;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Edge {

    public String id;
    private String type;
    public String source;
    public String target;
    private String sourceHandle;
    private String targetHandle;
    private EdgeData data;
    private String label;
    private Boolean animated;
    private Boolean updatable;
    private String markerEnd;
    private Double sourceX;
    private Double sourceY;
    private Double targetX;
    private Double targetY;

    private Map<String,Object> style;
    private Map<String,Object> labelStyle;
    private List<Integer> labelBgPadding;
    private Integer labelBgBorderRadius;
    private Map<String,Object> labelBgStyle;


    public Edge() {
    }

    public Edge(String source, String target) {
        this.source = source;
        this.target = target;
    }
}
