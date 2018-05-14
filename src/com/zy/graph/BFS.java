package com.zy.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {

	// 存储节点信息
	private Object[] vertices;
	// 存储边的信息数组
	private int[][] arcs;
	// 边的条数
	private int vexnum;
	// 记录第i个节点是否被访问过
	private boolean[] visited;
	// 构建一个临时链表存已经遍历过的节点
	private List<Object> temp = new ArrayList<Object>();

	/**
	 * @param args
	 *
	 * @author TD_LSW
	 */
	public static void main(String[] args) {
		

		BFS g = new BFS(6);
		Character[] vertices = { '1', '2', '3', '4', '5', '6'};
		g.addVertex(vertices);
		g.addEdge(0, 1);  
        g.addEdge(0, 2);  
        g.addEdge(1, 3);  
        g.addEdge(1, 4);  
        g.addEdge(2, 1);  
        g.addEdge(2, 4);  
        g.addEdge(3, 5);  
        g.addEdge(2, 4);  
        g.addEdge(4, 5);  

		System.out.println("图的广度优先遍历：");
		g.bfs();
	}

	// 广度优先遍历实现
	private void bfs() {
		
		for (int i = 0; i < vexnum; i++) {
			visited[i] = false;
		}
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < vexnum; i++) {
			if (!visited[i]) {
				visited[i] = true;
				visit(i);
				q.add(i);
				while (!q.isEmpty()) {
					int j = (Integer) q.remove().intValue();
					// 判断如果全部遍历完了就不需要循环了
					if (temp.size() == vexnum) {
						q.removeAll(q);
						return;
					}
					for (int k = this.firstAdjVex(j); k >= 0; k = this.nextAdjVex(j, k)) {
						if (!visited[k]) {
							q.add(k);
							visited[k] = true;
							visit(k);
						}
					}
				}
			}
		}

	}

	// 查找下一个节点
	public int firstAdjVex(int i) {
		for (int j = 0; j < vexnum; j++) {
			if (arcs[i][j] > 0)
				return j;
		}
		return -1;
	}

	public int nextAdjVex(int i, int k) {
		for (int j = k + 1; j < vexnum; j++) {
			if (arcs[i][j] > 0)
				return j;
		}
		return -1;
	}

	// 初始化图的边
	private void addEdge(int i, int j) {
		
		if (i == j)
			return;
		arcs[i][j] = 1;
		arcs[j][i] = 1;

	}

	// 初始化图的节点
	private void addVertex(Object[] object) {
		
		this.vertices = object;
	}

	// 图的初始化
	public BFS(int n) {
		
		vexnum = n;
		vertices = new Object[n];
		arcs = new int[n][n];
		visited = new boolean[n];
		for (int i = 0; i < vexnum; i++) {
			for (int j = 0; j < vexnum; j++) {
				arcs[i][j] = 0;
			}
		}
	}

	private void visit(int i) {
		
		temp.add(vertices[i]);
		System.out.print(vertices[i] + " ");
	}

}
