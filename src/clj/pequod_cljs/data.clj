(ns pequod-cljs.data
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(def pequod-turtles
  (with-open [reader (io/reader "resources/data/pequod-turtles.csv")]
    (doall
     (csv/read-csv reader))))


(defn csv-data->maps [csv-data]
  (map zipmap
       (->> (first csv-data) ;; First row is the header
            (map keyword) ;; Drop if you want string keys instead
            repeat)
	  (rest csv-data)))


(def data-as-map
  (csv-data->maps data-to-use))


