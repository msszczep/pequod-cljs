(ns pequod-cljs.gen
    (:require [pequod-cljs.ccs :as ccs]
              [pequod-cljs.wcs :as wcs]))

; lein run -m pequod-cljs.gen NS-TO-USE


(defn -main [& [ns-to-use]]
  (println (format "(ns pequod-cljs.%s)" ns-to-use))
  (println "")
  (println "(def ccs ")
  (clojure.pprint/pprint (ccs/create-ccs-bulk 80 10 4 2))
  (println ")")
  (println "(def wcs ")
  (clojure.pprint/pprint (wcs/create-wcs-bulk 40 40 40))
  (println ")")
)

