#!/usr/bin/python

import csv
from collections import defaultdict
from itertools import chain

def makeFileName(n):
    return str('dep1ex' + str(n) + '.csv')

def getData(f):
    reader = csv.reader(open(f))
    to_return = []
    for line in reader:
        to_return.append((line[0], str(line[1])))
    return to_return

# source: https://overlaid.net/2016/02/04/convert-a-csv-to-a-dictionary-in-python/
def getCsvDicts(f):
    reader = csv.DictReader(open(f))
    dict_list = []
    for line in reader:
        dict_list.append(line)
    return dict_list

def makePGPs():
    to_return = []
    for n in range(1, 101):
        to_return.append(':private-good-prices-' + str(n))
        to_return.append(':public-good-prices-' + str(n))
        to_return.append(':supply-public-goods-' + str(n))
        to_return.append(':supply-private-goods-' + str(n))
    return to_return 

def getOnlyNeededKeys(d):
    somekeys = makePGPs()
    return dict([(k, d.pop(k)) for k in somekeys])

def getYearTwoFirstGreen(d):
    s = filter(lambda x: x[1] == ':green' or x[0] == 'exponent-sum', d)
    return s[s.index(('exponent-sum', '')) + 1][0]

def getFirstColor(color, d):
    return filter(lambda x: x[1] == color, d)[0][0]

def computeGdp(d):
    gdp = 0.0
    for n in range(1, 101):
        gdp = gdp + (d[':private-good-prices-' + str(n)] * d[':supply-private-goods-' + str(n)])
        gdp = gdp + (d[':public-good-prices-' + str(n)] * d[':supply-public-goods-' + str(n)])
    return gdp

def getYearOneData(d):
    yearOneIndex = 0
    n = 0
    for e in d:
        if e[':private-good-prices-1'] == '':
            yearOneIndex = n
            return d[yearOneIndex - 1]
        n = n + 1

def getYearTwoData(d):
    return d[-2]

def getPricesOrSupplies(d, v):
    keys_to_use = []
    for n in range(1, 101):
        if v == 'p': # prices
            keys_to_use.append(':private-good-prices-' + str(n))
            keys_to_use.append(':public-good-prices-' + str(n))
        elif v == 's': # supply
            keys_to_use.append(':supply-public-goods-' + str(n))
            keys_to_use.append(':supply-private-goods-' + str(n))
    return dict([(k, d.pop(k)) for k in keys_to_use])

def mergeDicts(d1, d2):
    d3 = {} 
    for k, v in d1.items():
        d3[k] = float(v)
    for k, v in d2.items():
        d3[k] = float(v)
    return d3

def turnToPct(v1, v2):
    return 100 * ((v2 - v1) / v1)

#gdp-typ-2 (compute-gdp supply-list private-good-prices public-good-prices)
#gdp-typ-1 (compute-gdp (:last-years-supply t2) private-good-prices public-good-prices)
#gdp-lyp-2 (compute-gdp supply-list (:last-years-private-good-prices t2) (:last-years-public-good-prices t2))
#gdp-lyp-1 (compute-gdp (:last-years-supply t2) (:last-years-private-good-prices t2) (:last-years-public-good-prices t2))
#gdp-typ-pi (* 100 (/ (- gdp-typ-2 gdp-typ-1) gdp-typ-1))
#gdp-lyp-pi (* 100 (/ (- gdp-lyp-2 gdp-lyp-1) gdp-lyp-1))
#gdp-avg-pi (mean [gdp-typ-pi gdp-lyp-pi])

# tables 1 and 2
#for n in range(51, 101):
#    d = getFirstColor(':blue', getData(makeFileName(n)))
#    print n, d

# table 4
#for n in range(51, 101):
#    d = getYearTwoFirstGreen(getData(makeFileName(n)))
#    print n, d

# table 4, gdp
for n in range(51, 101):
    r = map(getOnlyNeededKeys, getCsvDicts(makeFileName(n)))
    y1 = getYearOneData(r)
    y2 = getYearTwoData(r)
    y1s = getPricesOrSupplies(y1, 's')
    y1p = getPricesOrSupplies(y1, 'p')
    y2s = getPricesOrSupplies(y2, 's')
    y2p = getPricesOrSupplies(y2, 'p')
    y1sy1p = mergeDicts(y1s, y1p)
    y1sy2p = mergeDicts(y1s, y2p)
    y2sy1p = mergeDicts(y2s, y1p)
    y2sy2p = mergeDicts(y2s, y2p)
    gdptyp2 = computeGdp(y2sy2p)
    gdptyp1 = computeGdp(y1sy2p)
    gdplyp2 = computeGdp(y2sy1p)
    gdplyp1 = computeGdp(y1sy1p)
    gdptyppi = turnToPct(gdptyp1, gdptyp2)
    gdplyppi = turnToPct(gdplyp1, gdplyp2)
    print n, (gdptyppi + gdplyppi) / 2

# table 5
#for n in range(51, 101):
#    d = getData(makeFileName(n))
#    g = getFirstColor(':green', d)
#    y = getFirstColor(':yellow', d)
#    print n, int(g) - int(y) 
