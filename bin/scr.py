print("#!/bin/bash")
print("")

for i in list(range(1, 51)):
    if i < 10:
       n = "0" + str(i)
    else:
       n = str(i)
    print("time lein run -m pequod-cljs.gen dep1ex" + n + " > src/clj/pequod_cljs/dep1ex" + n + ".cljs")
