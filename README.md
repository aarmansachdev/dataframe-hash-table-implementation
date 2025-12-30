Custom DataFrame Implementation with Hash Tables
A fully functional DataFrame data structure built from scratch in Java, mimicking the core functionality of Python's Pandas library. This project demonstrates advanced data structures knowledge through custom implementations of hash tables, binary search trees, and linked lists.

Project Overview
This project implements a complete data management system using fundamental computer science principles. The DataFrame uses a custom hash table as its underlying storage mechanism, providing efficient O(1) average-case data retrieval while supporting dynamic resizing and collision resolution through linear probing.

Architecture
Core Components
1. HashTable.java - Custom Hash Table Implementation

Generic type support for flexible data storage
Linear probing collision resolution strategy
Dynamic resizing with automatic rehashing
Load factor optimization (0.55 threshold for upscaling, 0.3 for downscaling)
O(1) average-case lookup, insertion, and deletion

2. DataFrame.java - Pandas-like Data Structure

Column-based data storage using hash table of SeriesV2 objects
Support for heterogeneous data types through Java generics
Row and column manipulation operations
Efficient data retrieval by column name

3. LL.java - Custom Linked List

Generic linked list implementation with sentinel nodes
Index-based and data-based operations
Used as underlying structure for Series data storage

4. BST.java - Binary Search Tree

Self-balancing tree structure for ordered data storage
Efficient search, insertion, and deletion operations
Alternative data structure option for Series implementation

5. Series.java & SeriesV2.java - Data Series Interface

One-dimensional labeled array (similar to Pandas Series)
Integration with both Linked List and BST backends
Support for indexing and data manipulation

Technical Implementation
Hash Table Features
Custom Hash Function
javaint hashValue = 0;
for (int i = 0; i < k.length(); i++) {
    int letter = k.charAt(i) - 96;
    hashValue += (hashValue * 27 + letter);
}
return hashValue % capacity;

Polynomial rolling hash for string keys
Modulo operation for index mapping
Optimized for lowercase English letters

Collision Resolution

Linear Probing: When collision occurs, linearly search for next available slot
Bridge Elements: Special markers for deleted entries to maintain probe sequence
Prevents clustering through dynamic resizing

Dynamic Resizing

Upscaling: Doubles capacity when load factor ≥ 0.55
Downscaling: Halves capacity when load factor ≤ 0.3
Minimum capacity maintained at 4
Complete rehashing on resize to maintain optimal distribution

DataFrame Design
The DataFrame acts as a dictionary of column names mapped to Series objects:
DataFrame
    └── HashTable<String, SeriesV2<Object>>
            ├── Column1: SeriesV2<Integer>
            ├── Column2: SeriesV2<String>
            └── Column3: SeriesV2<Double>

Key Features
Hash Table

✅ Generic type support (<V>)
✅ Automatic load factor management
✅ Linear probing collision resolution
✅ Dynamic capacity adjustment
✅ Bridge elements for deletion handling
✅ Complete rehashing on resize

DataFrame

✅ Column-based data storage
✅ Support for heterogeneous data types
✅ Add/remove columns dynamically
✅ Row count tracking
✅ Column name management
✅ Data validation on column addition

Supporting Data Structures

✅ Linked list with sentinel nodes
✅ Binary search tree implementation
✅ Generic interface for Series abstraction
✅ Index-based and value-based operations

 Skills Demonstrated
Data Structures

Hash table design and implementation
Collision resolution strategies
Dynamic array resizing
Linked list operations
Binary search tree fundamentals

Algorithm Design

Hash function optimization
Load factor balancing
Time complexity optimization
Space-time tradeoffs
Amortized analysis

Software Engineering

Object-oriented design principles
Generic programming
Exception handling
Code modularity and reusability
Interface-based abstraction

Problem Solving

Efficient data organization
Memory management
Edge case handling
Performance optimization

Business Value
This project demonstrates the ability to:

Build production-ready data structures from first principles
Optimize for both time and space complexity
Design scalable systems for data management
Understand tradeoffs between different algorithmic approaches
Implement industry-standard data manipulation tools

 Potential Enhancements

 Add support for row-based operations (iloc, loc)
 Implement data filtering and querying
 Add CSV import/export functionality
 Create data visualization methods
 Implement aggregation operations (groupby, sum, mean)
 Add support for missing data handling (NaN values)
 Implement join operations between DataFrames
 Add index alignment and reindexing

 Learning Outcomes
Through this project, I gained deep understanding of:

How hash tables work under the hood
Collision resolution strategies and their tradeoffs
Dynamic memory management and resizing strategies
The design principles behind popular libraries like Pandas
Performance optimization through algorithmic improvements
Practical application of data structures in real-world scenarios

👤 Author
Aarman Sachdev

Data Science Major, Business Analytics Minor | UC San Diego

📝 Course Context
This project was completed as part of DSC 30 (Data Structures and Algorithms) at UC San Diego, demonstrating practical application of fundamental computer science concepts in data analysis contexts.
