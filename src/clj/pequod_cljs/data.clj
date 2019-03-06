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

(defn get-cc-keys [m]
  (select-keys m [:cy :effort :final-demands :income :num-workers :utility-exponents]))

(defn get-wc-keys [m]
  (select-keys m [:a :s :c :ce :cq :du :effort :industry :input-exponents :labor-exponents :labor-quantities :nature-exponents :output :product :production-inputs :xe]))

(defn convert-str-to-clj [m]
  (map (fn [[k v]] (vector k (read-string v))) m))


(def ccs (->> pequod-as-map
              (filter #(= "{breed ccs}" (:breed %)))
              (mapv #(->> %
                          get-cc-keys
                          convert-str-to-clj
                          (into {})))))

(def wcs (->> pequod-as-map
              (filter #(= "{breed wcs}" (:breed %)))
              (mapv #(->> %
                          get-wc-keys
                          convert-str-to-clj
                          (into {})))))

;(spit "ccs.clj" ccs)
;(spit "wcs.clj" wcs)


