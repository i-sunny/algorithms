package com.algorithms.graph;

import java.util.*;

/**
 * 图的深度拷贝
 * 来源: https://leetcode.com/problems/clone-graph/
 * 递归
 * clonedMap用来维护已经clone的node,注意clonedMap需要指向新的node(即克隆后的node)
 * @author xiaoqi.sxq
 * @version $Id: CloneGraph.java, v 0.1 2016-08-04 15:30 xiaoqi.sxq Exp $
 */
public class CloneGraph {

    private static Map<Integer, UndirectedGraphNode> clonedMap = new HashMap<Integer, UndirectedGraphNode>();

    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }
        if (clonedMap.containsKey(node.value)) {
            return clonedMap.get(node.value);
        }

        UndirectedGraphNode cloneNode = new UndirectedGraphNode(node.value);
        clonedMap.put(node.value, cloneNode);
        for (UndirectedGraphNode e : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(e));
        }
        return cloneNode;
    }

    public static void main(String[] args) {
        UndirectedGraphNode node1 = new UndirectedGraphNode(1);
        UndirectedGraphNode node2 = new UndirectedGraphNode(2);
        UndirectedGraphNode node3 = new UndirectedGraphNode(3);
        UndirectedGraphNode node4 = new UndirectedGraphNode(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node3);
        node1.neighbors.add(node4);
        node3.neighbors.add(node1);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        UndirectedGraphNode cloned = cloneGraph(node1);
        return;
    }
}

class UndirectedGraphNode {
    int value;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int value) {
        this.value = value;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
