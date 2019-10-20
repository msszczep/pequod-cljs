(ns pequod-cljs.gen
    (:require [pequod-cljs.ccs :as ccs]
              [pequod-cljs.wcs :as wcs]))

; lein run -m pequod-cljs.gen


(defn -main [& args]
  #_(clojure.pprint/pprint 
                         (ccs/create-ccs-bulk 80 10 4 2) 
                         )
  (clojure.pprint/pprint (wcs/create-wcs-bulk 40 40 40))
)

