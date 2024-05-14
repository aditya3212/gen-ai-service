import java.util.*;

public class DepthFirst {
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> graph[]= new ArrayList[5];
		
		for(int i=0;i<5;i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		graph[0].add(1);
		graph[0].add(2);	
		
		graph[1].add(0);
		graph[1].add(3);
		graph[2].add(0);
		graph[2].add(4);
		
		graph[3].add(1);
		graph[4].add(2);
		
		int visited[]= new int[5];
		
		System.out.print("Depth first search is ");
		
		depthFirst(0,graph,visited);
		System.out.println();
//		0-1, 2, 3
//		1- 0, 3
//		2-0,4
//		3- 0,1
//		4, 2
	}

	private static void depthFirst(int idx,List<Integer>[] graph, int[] visited) {
		// TODO Auto-generated method stub
		if(visited[idx]==0) {
			System.out.print(idx+" ");
			visited[idx] = 1;
		}
		
		for(int node: graph[idx]) {
			if(visited[node]==0) {
				depthFirst(node, graph, visited);
			}
		}
		
	}

}
