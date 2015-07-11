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