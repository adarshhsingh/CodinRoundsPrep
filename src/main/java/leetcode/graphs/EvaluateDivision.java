package leetcode.graphs;

import java.util.*;

class pair {
    int edge;
    Double weight;

    public pair(int edge, Double weight) {
        this.edge = edge;
        this.weight = weight;
    }
}

public class EvaluateDivision {

        public static void main(String[] args) {

        }

        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

            // lets try to create simple representation
            Map<String, Integer> stringMap = new HashMap<>();
            int index = 0;
            for(List<String> edges : equations) {
                if(!stringMap.containsKey(edges.get(0))) stringMap.put(edges.get(0),index++);
                if(!stringMap.containsKey(edges.get(1))) stringMap.put(edges.get(1),index++);
            }

            Double[][] matrix = new Double[index][index];
            buildGraph(matrix,equations,values,stringMap);

            double[] result = new double[queries.size()];
            index = 0;
            for(List<String> edges : queries) {
                result[index++] = bfs(stringMap.getOrDefault(edges.get(0), -1), stringMap.getOrDefault(edges.get(1), -1), matrix);
            }

            return result;
        }

        public void buildGraph(Double[][] matrix, List<List<String>> equations, double[] values, Map<String, Integer> stringMap) {
            int index = 0;
            for(List<String> edges : equations) {
                int vertexA = stringMap.get(edges.get(0));
                int vertexB = stringMap.get(edges.get(1));
                matrix[vertexA][vertexB] = values[index++];
                matrix[vertexB][vertexA] = 1.0 / matrix[vertexA][vertexB];
            }
        }

        public double bfs(int start, int end, Double[][] matrix) {
            if (start == -1 || end == -1) return -1.0;
            if (start == end) return 1.0;
            if (matrix[start][end] != null) return matrix[start][end];

            int n = matrix.length;

            Queue<pair> queue = new LinkedList<>();
            queue.offer(new pair(start, 1.0));
            boolean visited[] = new boolean[n];
            visited[start] = true;

            while (!queue.isEmpty()) {
                pair current = queue.remove();
                int currentNode = current.edge;
                double currentWeight = current.weight;
                if (currentNode == end) {
                    break;
                }

                for (int i = 0; i < n; i++) {
                    if (!visited[i] && matrix[currentNode][i] != null) {
                        double newWeight = currentWeight * matrix[currentNode][i];
                        matrix[start][i] = newWeight;
                        queue.offer(new pair(i, matrix[start][i]));
                    }
                }
            }

            if (matrix[start][end] == null) matrix[start][end] = -1.0;
            return matrix[start][end];
        }
}
