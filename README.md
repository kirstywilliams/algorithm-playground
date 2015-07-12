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

