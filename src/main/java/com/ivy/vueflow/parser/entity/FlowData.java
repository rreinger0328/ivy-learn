package com.ivy.vueflow.parser.entity;

import com.ivy.vueflow.parser.entity.edge.Edge;
import com.ivy.vueflow.parser.entity.node.Node;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowData {

    private List<Node> nodes;
    private List<Edge> edges;
    private List<Double> position;
    private Double zoom;
    private Viewport viewport;
    private Boolean format = false;

    public FlowData(List<Node> nodes, List<Edge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }
}
