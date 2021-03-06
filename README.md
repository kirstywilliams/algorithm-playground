# Algorithm Playground

A collection of algorithms.

## Sorting Algorithms

### Insertion Sort

Insertion sort iterates, consuming one input element each repetition, 
and growing a sorted output list. Each iteration, insertion sort removes 
one element from the input data, finds the location it belongs within the 
sorted list, and inserts it there. It repeats until no input elements remain.

Read More: [Wikipedia](https://en.wikipedia.org/wiki/Insertion_sort)

#### Usage

```
String[] array = new String[] { "Bob", "Dog", "Alice", "Cat" };
Sort.insertionSort(array);
```

## Graph Algorithms

### Breadth First Search

Breadth-first search (BFS) is an algorithm for traversing or searching tree 
or graph data structures. It starts at the tree root (or some arbitrary source 
node of a graph) and explores the neighbour nodes first, before moving to the 
next level neighbours.

Read More: [Wikipedia](https://en.wikipedia.org/wiki/Breadth-first_search)

#### Usage

```
int initialCacheSize = 2;
String nodeLabel = "A";
DefaultNode node = new DefaultNode(initialCacheSize, nodeLabel);

BreadthFirstSearch bfs = new BreadthFirstSearch();
bfs.findAll(node);
int maxDepth = bfs.getMaxDepth();
```

### Nearest Neighbour

The nearest neighbour algorithm was one of the first algorithms used to determine 
a solution to the travelling salesman problem. In it, the salesman starts at a 
random city and repeatedly visits the nearest city until all have been visited. 
It quickly yields a short tour, but usually not the optimal one.

Read More: [Wikipedia: Nearest Neighbour](https://en.wikipedia.org/wiki/Nearest_neighbour_algorithm)

Read More: [Wikipedia: Travelling Salesman](https://en.wikipedia.org/wiki/Travelling_salesman_problem)

#### Usage

```
BiMap<Integer, INode> hubMap = HashBiMap.create();
// CODE MISSING: create and add nodes to the hubMap.

PathMatrix distances = new PathMatrix();
// CODE MISSING: initialise the distances array with an adjacency matrix.

NearestNeighbourTSP nn = new NearestNeighbourTSP(distances, hubMap, 0);
List<Integer> route = nn.execute();
```