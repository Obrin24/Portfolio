import functools
import sys
import csv
import heapq
import random
import heapq
import math
import sys
from collections import defaultdict, deque, Counter
from itertools import combinations
from timeit import default_timer as timer

import networkx as nx
import string

sys.setrecursionlimit(10000)
#tests the users inputs
if len(sys.argv[1:]) < 2 or len(sys.argv[1:]) > 2:
    print("ERROR: Not enough or too many input arguments")
    exit()
else:
    initial = str(sys.argv[1])
    goal = str(sys.argv[2])

#reads the driving csv to pull headers to help with confirming user inputs
rows = []
with open("driving.csv", 'r') as file:
    csvreader = csv.reader(file)
    header = next(csvreader)
    for row in csvreader:
        rows.append(row)


#reads the driving csv
with open("driving.csv", 'r') as f:
    driving = csv.DictReader(f)
    driving_list = list(driving)


#reads the straightline csv
with open("straightline.csv",'r') as f:
    straight = csv.DictReader(f)
    straightline_list = list(straight)


#Makes the driving csv read into a list
x = len(driving_list)
driving_states = []
for i in range(0, x):
    mydict = driving_list[i]
    for k in mydict.copy():
        if mydict[k] == '-1':
            del mydict[k]
        elif mydict[k] == '0':
            del mydict[k]
    driving_states.append(mydict)

#Makes the straight line csv read into a dictionary
j = len(straightline_list)
straight_states = []
for i in range(0, j):
    mydict2 = straightline_list[i]
    straight_states.append(mydict2)

path = [initial]
path_cost = 0
path2 = [initial]
path_cost2 = 0
drive_cost = 0
#Greedy Best first search algorithm code
def greedy_best_first_search(cost,drive,pathh):
    min = 99999999
    min2 = 0
    frontier = []
    frontier_dict = dict()
    visited = []
    expectedResult = [d for d in straight_states if d['STATE'] == pathh]
    xpectedResult = [d for d in driving_states if d['STATE'] == pathh]
    for p in range(0, len(expectedResult)):
        val = next((index for (index, d) in enumerate(expectedResult) if d["STATE"] == pathh),None)
        val2 = next((index for (index, d) in enumerate(xpectedResult) if d["STATE"] == pathh),None)
        diction = expectedResult[val]
        diction2 = xpectedResult[val2]
        frontier += (list(diction2.keys()))
        frontier = listfrontier(frontier)
        new_diction = {}
        for key, value in diction.items():
            if key in diction2.keys():
                new_diction[key] = value
        diction = new_diction
        for i in range(0, len(path)):
            if path[i] in diction:
                del diction[path[i]]
        for i in range(0, len(path)):
            if path[i] in diction2:
                del diction2[path[i]]
        frontier_dict = [d for d in straight_states if d['STATE'] in frontier]
        distance = []
        for i in range(0, len(frontier_dict)):
            for key,value in frontier_dict[i].items():
                if key == goal:
                    distance.append(value)
        frontier.remove('STATE')
        for i in range(0, len(frontier)):
            lb = list(diction2.values())
            if goal in frontier:
                min = 0
                min2 = lb[i]
            elif int(distance[i]) < int(min):
                min = distance[i]
                if lb[i].isnumeric():
                    min2 = lb[i]
                else:
                    min2 = lb[i+1]
    cost = cost + int(min)
    drive = drive + int(min2)
    if goal in frontier:
        path.append(goal)
    else:
        index = distance.index(min)
        path.append(frontier[index])
    if path[len(path) - 1] == goal:
        return(drive)
    else:
        m = path[len(path)-1]
        return greedy_best_first_search(cost,drive,m)

#My attempt at an A* algorithm
def astar_search(cost,drive,pathh):
    min = 99999999
    min2 = 0
    frontier = []
    frontier_dict = dict()
    visited = []
    expectedResult = [d for d in straight_states if d['STATE'] == pathh]
    xpectedResult = [d for d in driving_states if d['STATE'] == pathh]
    for p in range(0, len(expectedResult)):
        val = next((index for (index, d) in enumerate(expectedResult) if d["STATE"] == pathh),None)
        val2 = next((index for (index, d) in enumerate(xpectedResult) if d["STATE"] == pathh),None)
        diction = expectedResult[val]
        diction2 = xpectedResult[val2]
        frontier += (list(diction2.keys()))
        frontier = listfrontier(frontier)
        # print(frontier)
        new_diction = {}
        for key, value in diction.items():
            if key in diction2.keys():
                new_diction[key] = value
        diction = new_diction
        for i in range(0, len(path2)):
            if path2[i] in diction:
                del diction[path2[i]]
        for i in range(0, len(path2)):
            if path2[i] in diction2:
                del diction2[path2[i]]
        frontier.remove('STATE')
        frontier = [i for i in frontier if i not in path2]
        frontier_dict = [d for d in straight_states if d['STATE'] in frontier]
        distance = []
        for i in range(0, len(frontier_dict)):
            for key,value in frontier_dict[i].items():
                if key == goal:
                    distance.append(value)
        # print(len(frontier))
        # print(frontier)
        dist = []
        lb = list(diction2.values())
        if(len(distance) < len(lb)):
            for i in range(0,len(distance)):
                if lb[i+1].isnumeric():
                    p = int(distance[i]) + int(lb[i+1])
                    dist.append(p)
                else:
                    print()
        else:
            for i in range(0, len(distance)):
                if lb[i].isnumeric():
                    p = int(distance[i]) + int(lb[i])
                    dist.append(p)
                else:
                    print()
        for i in range(0, len(dist)):
                if goal in frontier:
                    min = 0
                    min2 = lb[i]
                elif int(dist[i]) < int(min):
                    min = dist[i]
                    if lb[i].isnumeric():
                        min2 = lb[i]
                    else:
                        min2 = lb[i + 1]
    cost = cost + int(min)
    drive += int(min2)
    if goal in frontier:
        path2.append(goal)
    else:
        index = dist.index(min)
        path2.append(frontier[index])
    if path2[len(path2) - 1] == goal:
        return(drive)
    else:
        m = path2[len(path2)-1]
        return astar_search(cost,drive,m)

#Converts the frontier list to a single not a nested list
def listfrontier(front):
    resultList = []
    # Traversing in till the length of the input list of lists
    for m in range(len(front)):

        # using nested for loop, traversing the inner lists
        for n in range(len(front[m])):
            # Add each element to the result list
            resultList.append(front[m][n])
    remove_dups(front)
    return front

def remove_dups(f):
    res = set(f) - set(path)
    return res

#Print statments to find the outcome
if initial not in header:
    print("Solution Path: FAILURE: NO PATH FOUND")
    print("Number of states on a path: 0")
    print("Path cost: 0")
    print("Execution Time:")
elif goal not in header:
    print("Solution Path: FAILURE: NO PATH FOUND")
    print("Number of states on a path: 0")
    print("Path cost: 0")
    print("Execution Time:")
elif initial == goal:
    print("Johnson, Gabriel, A40261382 solution:")
    print("Initial State: " + initial)
    print("Goal State: " + goal)
    print("\nGreedy Best First Search: ")
    print("Solution Path: " + initial)
    print("Number of states on a path: 1")
    print("Path Cost: 0")
    print("Execution Time: 0")
    print("\nA* Search: ")
    print("Solution Path: " + initial)
    print("Number of states on a path: 1")
    print("Path Cost: 0")
    print("Execution Time: 0")
elif initial in header:
    start = timer()
    path_cost = (greedy_best_first_search(path_cost,drive_cost, initial))
    end = timer() - start
    start2 = timer()
    path_cost2 = astar_search(path_cost2, drive_cost, initial)
    end2 = timer() - start2
    print("Johnson, Gabriel, A40261382 solution:")
    print("Initial State: " + initial)
    print("Goal State: " + goal)
    print("\nGreedy Best First Search: ")
    print("Solution Path: " + str (path))
    print("Number of states on a path: " + str(len(path)))
    print("Path Cost: " + str(path_cost))
    print("Execution Time: " + str((round(end, 5))) + " seconds")
    print("\nA* Search: ")
    print("Solution Path: " + str(path2))
    print("Number of states on a path: " + str(len(path2)))
    print("Path Cost: " + str(path_cost2))
    print("Execution Time: "+ str((round(end,5))) + " seconds")