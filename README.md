# README #

The task was to construct a deep clone of a given graph.

What is meant by a deep clone is that in a deep clone we create a clone which is independent of the original object and making changes in the cloned object should not affect the original object. Also it is important that the order of the data is the same as in the original object. The object in our case is a graph and the data is vertices and , all members that belong to them and arcs.
So here is an example of a graph in our case :
Graph_1
v42 -->
v1 --> av1_v6 (v1->v6) av1_v5 (v1->v5)
v2 --> av2_v6 (v2->v6) av2_v3 (v2->v3)
v3 --> av3_v2 (v3->v2) av3_v4 (v3->v4)
v4 --> av4_v5 (v4->v5) av4_v3 (v4->v3) av4_v6 (v4->v6)
v5 --> av5_v4 (v5->v4) av5_v1 (v5->v1) av5_v6 (v5->v6)
v6 --> av6_v2 (v6->v2) av6_v1 (v6->v1) av6_v4 (v6->v4) av6_v5 (v6->v5)
Graph_1 - is the name of a graph.
v42 - is a vertex, here we can see that it doesn’t have an arc. v1 - is also a vertex, but with arcs
av1_v6 - is an arc
(v1->v6) - is the direction of an arc.

The goal was to create a clone of a given graph, with the same vertices, arcs, directions and the same order. After creating a deep clone of a given graph we need to compare the original and a clone graph to see if they are equal or not. To prove correctness of task we need to modify the original graph and then compare them again to see that the clone was not affected by the modification of the original and if before modification of the original they were identical, after modification they are not.
