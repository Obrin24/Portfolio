import csv
import sys


sys.setrecursionlimit(10000)
#tests the users inputs
if len(sys.argv[1:]) < 2 or len(sys.argv[1:]) > 2:
    print("ERROR: Not enough or too many input arguments")
    exit()
else:
    INITIAL = str(sys.argv[1])
    NO_OF_PARKS = int(sys.argv[2])


#reads the driving csv to pull headers to help with confirming user inputs
rows = []
with open("driving2.csv", 'r') as file:
    csvreader = csv.reader(file)
    header = next(csvreader)
    for row in csvreader:
        rows.append(row)



class csp:
    # reads the driving csv
    def __init__(self,Initial,Parks):
        with open("driving2.csv", 'r') as f:
            driving = csv.DictReader(f)
            driving_list = list(driving)

        # reads the zone csv
        with open("zones.csv", 'r') as f:
            zone = csv.DictReader(f)
            zone_list = list(zone)

        # reads the parks csv
        with open("parks.csv", 'r') as f:
            parks = csv.DictReader(f)
            parks_list = list(parks)

        # Makes the driving csv read into a list
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

        # ALL ZONES
        self.zones = zone_list[0]
        # ALL PARKS
        self.park = parks_list[0]
        #Initial State
        self.Initial_state = Initial
        #Min number of parks
        self.Min_Parks = Parks
        #Driving Distances
        self.Driving = driving_states
        #Initial Zone
        self.Initial_Zone = self.zones[self.Initial_state]
        #Initial Park
        self.Initial_Park = self.park[self.Initial_state]

    def getParks(self):
        return self.park
    def getZones(self):
        return self.zones
    def getInitial(self):
        return self.Initial_state
    def getMinParks(self):
        return self.Min_Parks
    def getDriving(self):
        return self.Driving
    def getInitialZone(self):
        return self.Initial_Zone


def BACKTRACKING_SEARCH(csp):
    return BACKTRACKING(csp, {})


def BACKTRACKING(csp,assignment):
    var = SELECT_UNASSIGNED_VARIABLE(csp,assignment)
    for value in ORDER_DOMAIN_VALUES(csp,var,assignment):
        assignment[var] = value
        result = BACKTRACKING(csp,assignment)
        x = []
        for i in range(len(assignment.values())):
            vals = list(assignment.values())
            v = vals[i]
            x.append(v[2])
        su = int(csp.Initial_Park)
        for i in range(len(x)):
            su += int(x[i])
        if 12 in assignment.keys() and su >= csp.Min_Parks:
            return assignment
        del assignment[var]


#
def distance(csp,assignment):
    if len(assignment) == 0:
        expectedResult = [d for d in csp.Driving if d['STATE'] == csp.Initial_state]
        distance = []
        for i in range(0, len(expectedResult)):
            for key, value in expectedResult[i].items():
                if key != 'STATE':
                    distance.append(value)
        return distance
    else:
        a = list(assignment.values())[len(assignment) - 1]
        frontier_dict = [d for d in csp.Driving if d['STATE'] == a[0]]
        distance = []
        for i in range(0, len(frontier_dict)):
            for key, value in frontier_dict[i].items():
                if key != 'STATE':
                    distance.append(value)
        return distance

def parkss(csp,y):
        frontier_dict = [d for d in csp.park if d in y]
        distance = []
        for i in range(0, len((y))):
            for key, value in csp.park.items():
                if key == 'STATE':
                    pass
                elif key == y[i]:
                    distance.append(value)
        return distance


def SELECT_UNASSIGNED_VARIABLE(csp,assignment):
    if len(assignment) == 0:
        z = csp.getInitialZone()
        z = int(z)+1
        return z
    else:
        a = list(assignment.values())[len(assignment)-1]
        p = a[0]
        z = csp.zones[p]
        z = int(z)+1
        return z


def ORDER_DOMAIN_VALUES(csp,var,assignment):
    if len(assignment) == 0:
        x = [i for i in csp.zones if csp.zones[i]==str(var)]
        x.sort()
        expectedResult = [d for d in csp.Driving if d['STATE'] == csp.Initial_state]
        diction = expectedResult[0]
        y = (list(diction.keys()))
        z = distance(csp,assignment)
        p = parkss(csp,y)
        y.remove('STATE')
        m = []
        for i in range(len(x)):
            r = []
            if x[i] in y:
                index = y.index(x[i])
                r.append(x[i])
                r.append(z[index])
                r.append(p[index])
                m.append(r)
        return m
    else:
        a = list(assignment.values())[len(assignment)-1]
        s = a[0]
        x = [i for i in csp.zones if csp.zones[i] == str(var)]
        expectedResult = [d for d in csp.Driving if d['STATE'] == s]
        diction = expectedResult[0]
        y = (list(diction.keys()))
        z = distance(csp, assignment)
        p = parkss(csp, y)
        y.remove('STATE')
        m = []
        for i in range(len(x)):
            r = []
            if x[i] in y:
                index = y.index(x[i])
                r.append(x[i])
                r.append(z[index])
                r.append(p[index])
                m.append(r)
                # m[i].append()
        return m

csp = csp(INITIAL,NO_OF_PARKS)
x = BACKTRACKING_SEARCH(csp)
if x != None:
    path = [INITIAL]
    path_cst = []
    num_parks = [csp.Initial_Park]
    values = x.values()
    for i in range(len(x)):
        vals1 = list(x.values())
        v1 = vals1[i]
        path.append(v1[0])
    for i in range(len(x)):
        vals2 = list(x.values())
        v2 = vals2[i]
        path_cst.append(v2[1])
    for i in range(len(x)):
        vals3 = list(x.values())
        v3 = vals3[i]
        num_parks.append(v3[2])
    path_cost = 0
    for i in range(len(path_cst)):
        path_cost += int(path_cst[i])
    num_park = 0
    for i in range(len(num_parks)):
        num_park += int(num_parks[i])

# Print statments to find the outcome
if INITIAL not in header:
    print("Solution Path: FAILURE: NO PATH FOUND")
    print("Number of states on a path: 0")
    print("Path cost: 0")
    print("Number of Parks Visited: 0")
elif x == None:
    print("Solution Path: FAILURE: NO PATH FOUND")
    print("Number of states on a path: 0")
    print("Path cost: 0")
    print("Number of Parks Visited: 0")
else:
    print("Johnson, Gabriel, A20461382 solution:")
    print("Initial State: " + INITIAL)
    print("Mimimum Number of parks: " + str(NO_OF_PARKS))
    print("\nSolution Path: " + str(path))
    print("Number of states on a path: " + str(len(path)))
    print("Path cost: " + str(path_cost))
    print("Number of Parks Visited: " + str(num_park))