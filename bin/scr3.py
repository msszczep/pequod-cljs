print("#!/bin/bash")
print("")

# time lein run -m pequod-cljs.csvgen ex01 > src/clj/pequod_cljs/dep1ex01.csv

for i in list(range(2, 51)):
    if i < 10:
       n = "0" + str(i)
    else:
       n = str(i)
    print("time lein run -m pequod-cljs.csvgen ex" + n + " > src/clj/pequod_cljs/dep1ex" + n + ".csv")

