import numpy
import string
import pandas
import math

ref = pandas.read_csv('products.csv',header=None,engine='python')
tr_1k = pandas.read_csv('tr-1k.csv',engine='python',names=range(9))
tr_5k = pandas.read_csv('tr-5k.csv',engine='python',names=range(9))
tr_20k = pandas.read_csv('tr-20k.csv',engine='python',names=range(9))
tr_75k = pandas.read_csv('tr-75k.csv',engine='python',names=range(9))


def convert(file):
    for i in range(1, (len(file.columns))):
        for j in range(0, len(file[i])):
            p = file[i][j]
            if type(p) is not int:
                if type(p) is str:
                    pass
                elif type(p) is numpy.int64 or type(p) is float:
                    if type(p) is float:
                        if (math.isnan(p)):
                            pass
                        else:
                            p = int(p)
                    else:
                        p = int(p)
                elif type(p) is numpy.float64:
                    if numpy.isnan(p):
                        pass
                    else:
                        p = int(p)
                elif p.isnan():
                    pass
            if isinstance(p, int):
                k = ref[1][p]
                file[i] = file[i].replace([p], k)
            elif type(p) is str:
                pass
            elif math.isnan(p):
                pass
            elif p.isnumeric():
                k = ref[1][p]
                file[i] = file[i].replace([p], k)
            else:
                pass
convert(tr_1k)
convert(tr_5k)
convert(tr_20k)
convert(tr_75k)
print(tr_1k)
print(tr_5k)
print(tr_20k)
print(tr_75k)

tr_1k.to_csv('tr-1k-canonical.csv',header=False,index=False,columns=[1,2,3,4,5,6,7,8])
tr_5k.to_csv('tr-5k-canonical.csv',header=False,index=False,columns=[1,2,3,4,5,6,7,8])
tr_20k.to_csv('tr-20k-canonical.csv',header=False,index=False,columns=[1,2,3,4,5,6,7,8])
tr_75k.to_csv('tr-75k-canonical.csv',header=False,index=False,columns=[1,2,3,4,5,6,7,8])