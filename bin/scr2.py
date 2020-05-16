print("#!/bin/bash")
print("")

for i in list(range(1, 51)):
    if i < 10:
       n = "0" + str(i)
    else:
       n = str(i)
    print("mv src/clj/pequod_cljs/dep1ex" + n + ".cljs src/clj/pequod_cljs/dep1ex" + n + ".clj")
