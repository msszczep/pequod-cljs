(ns pequod-cljs.gen
    (:require [pequod-cljs.ccs :as ccs]
              [pequod-cljs.wcs :as wcs]))

; lein run -m pequod-cljs.gen NS-TO-USE


(defn -main [& [ns-to-use]]
  (println (format "(ns pequod-cljs.%s)" ns-to-use))
  (println "")
  (println "(def ccs ")
  (clojure.pprint/pprint (ccs/create-ccs-bulk 30000 10 100 100))
  (println ")")
  (println "(def wcs ")
  (clojure.pprint/pprint (wcs/create-wcs-bulk 10000 10000 10000))
  (println ")")
)

