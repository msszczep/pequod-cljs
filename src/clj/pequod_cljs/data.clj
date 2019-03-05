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


(def pequod-as-map
  (csv-data->maps pequod-turtles))

(def ccs (filter #(= "{breed ccs}" (:breed %)) pequod-as-map))

(def wcs (filter #(= "{breed wcs}" (:breed %)) pequod-as-map))

(defn get-cc-keys [m]
  (select-keys m [:cy :effort :final-demands :income :num-workers :utility-exponents]))

(defn get-wc-keys [m]
  (select-keys m [:a :s :c :ce :cq :du :effort :industry :input-exponents :labor-exponents :labor-quantities :nature-exponents :output :product :production-inputs :xe]))

; TODO: Convert string into Clojure data and data structures
; TODO: Output as text
