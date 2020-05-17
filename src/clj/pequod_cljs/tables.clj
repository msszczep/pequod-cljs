(ns pequod-cljs.tables
   (:require [clojure.data.csv :as csv]
             [clojure.java.io :as io]))

(defn parse-data [e]
  (let [d-to-use (clojure.string/split e #" ")
        color (->> d-to-use
                   last
                   rest
                   (apply str)
                   keyword)
        experiment (-> d-to-use
                       first
                       (clojure.string/split #"\.")
                       first
                       (clojure.string/split #"ex")
                       last
                       Integer.)
        iteration (-> d-to-use
                      first
                      (clojure.string/split #"\:")
                      last
                      Integer.)]
    {:color color
     :experiment experiment
     :iteration iteration}))

(def d
  (->> "resources/tables2.txt"
       slurp
       clojure.string/split-lines
       (mapv parse-data)))

(defn get-first-green-iteration [d n]
  (->> d
       (filter (comp (partial = n) :experiment))
       (filter (comp (partial = :green) :color))
       first
       :iteration))

(defn get-first-yellow-iteration [d n]
  (->> d
       (filter (comp (partial = n) :experiment))
       (filter (comp (partial = :yellow) :color))
       first
       :iteration))

(def table-one
   (mapv #(vector % (get-first-green-iteration d %))
         (range 1 51)))

(def max-table-one 
  (apply max (mapv last table-one))) ; => 13

(def table-one-avg
  (let [iterations (mapv last table-one)]
    (/ (apply + iterations) 50.0))) ; => 11.18

(defn get-first-blue-iteration [d n]
  (->> d
       (filter (comp (partial = n) :experiment))
       (filter (comp (partial = :blue) :color))
       first
       :iteration))

(def table-two
   (mapv #(vector % (get-first-blue-iteration d %))
         (range 1 51)))

(def max-table-two 
  (apply max (mapv last table-two))) ; => 23

(def table-two-avg
  (let [iterations (mapv last table-two)]
    (/ (apply + iterations) 50.0))) ; => 19.26

; cheap, but it works
(defn get-second-green-iteration [d n]
  (->> d
       (filter (comp (partial = n) :experiment))
       (filter (comp (partial = :green) :color))
       (mapv :iteration)
       sort
       first))

(def table-four
   (mapv #(vector % (get-second-green-iteration d %))
         (range 1 51)))

(def max-four-two 
  (apply max (mapv last table-four))) ; => 7

(def table-four-avg
  (let [iterations (mapv last table-four)]
    (/ (apply + iterations) 50.0))) ; => 5.2

(defn get-yellow-to-green [d n]
  (let [yellow (get-first-yellow-iteration d n)
        green  (get-first-green-iteration d n)]
    (- green yellow)))

(def table-five
   (mapv #(vector % (get-yellow-to-green d %))
         (range 1 51)))

(def max-five-two 
  (apply max (mapv last table-five))) ; => 6

(def table-five-avg
  (let [iterations (mapv last table-five)]
    (/ (apply + iterations) 50.0))) ; => 5.2

(def table-six
   (mapv #(vector % (get-second-green-iteration d %))
         (range 1 51)))

; adapted from data.csv README
(defn csv-data->maps [csv-data]
  (map zipmap
       (->> (first csv-data)
            (map (comp keyword (partial apply str) rest str))
            repeat)
	  (rest csv-data)))

(defn compute-gdp [r]
  (let [{private-good-prices-1 :private-good-prices-1
          private-good-prices-2 :private-good-prices-2
          private-good-prices-3 :private-good-prices-3
          private-good-prices-4 :private-good-prices-4
          private-good-prices-5 :private-good-prices-5
          private-good-prices-6 :private-good-prices-6
          private-good-prices-7 :private-good-prices-7
          private-good-prices-8 :private-good-prices-8
          private-good-prices-9 :private-good-prices-9
          private-good-prices-10 :private-good-prices-10
          supply-private-goods-1 :supply-private-goods-1
          supply-private-goods-2 :supply-private-goods-2
          supply-private-goods-3 :supply-private-goods-3
          supply-private-goods-4 :supply-private-goods-4
          supply-private-goods-5 :supply-private-goods-5
          supply-private-goods-6 :supply-private-goods-6
          supply-private-goods-7 :supply-private-goods-7
          supply-private-goods-8 :supply-private-goods-8
          supply-private-goods-9 :supply-private-goods-9
          supply-private-goods-10 :supply-private-goods-10
          public-good-prices-1 :public-good-prices-1
          public-good-prices-2 :public-good-prices-2
          public-good-prices-3 :public-good-prices-3
          public-good-prices-4 :public-good-prices-4
          public-good-prices-5 :public-good-prices-5
          public-good-prices-6 :public-good-prices-6
          public-good-prices-7 :public-good-prices-7
          public-good-prices-8 :public-good-prices-8
          public-good-prices-9 :public-good-prices-9
          public-good-prices-10 :public-good-prices-10
          supply-public-goods-1 :supply-public-goods-1
          supply-public-goods-2 :supply-public-goods-2
          supply-public-goods-3 :supply-public-goods-3
          supply-public-goods-4 :supply-public-goods-4
          supply-public-goods-5 :supply-public-goods-5
          supply-public-goods-6 :supply-public-goods-6
          supply-public-goods-7 :supply-public-goods-7
          supply-public-goods-8 :supply-public-goods-8
          supply-public-goods-9 :supply-public-goods-9
          supply-public-goods-10 :supply-public-goods-10} r]
    (+ (* (read-string private-good-prices-1)
          (read-string supply-private-goods-1))
       (* (read-string private-good-prices-2)
          (read-string supply-private-goods-2))
       (* (read-string private-good-prices-3)
          (read-string supply-private-goods-3))
       (* (read-string private-good-prices-4)
          (read-string supply-private-goods-4))
       (* (read-string private-good-prices-5)
          (read-string supply-private-goods-5))
       (* (read-string private-good-prices-6)
          (read-string supply-private-goods-6))
       (* (read-string private-good-prices-7)
          (read-string supply-private-goods-7))
       (* (read-string private-good-prices-8)
          (read-string supply-private-goods-8))
       (* (read-string private-good-prices-9)
          (read-string supply-private-goods-9))
       (* (read-string private-good-prices-10)
          (read-string supply-private-goods-10))
       (* (read-string public-good-prices-1)
          (read-string supply-public-goods-1))
       (* (read-string public-good-prices-2)
          (read-string supply-public-goods-2))
       (* (read-string public-good-prices-3)
          (read-string supply-public-goods-3))
       (* (read-string public-good-prices-4)
          (read-string supply-public-goods-4))
       (* (read-string public-good-prices-5)
          (read-string supply-public-goods-5))
       (* (read-string public-good-prices-6)
          (read-string supply-public-goods-6))
       (* (read-string public-good-prices-7)
          (read-string supply-public-goods-7))
       (* (read-string public-good-prices-8)
          (read-string supply-public-goods-8))
       (* (read-string public-good-prices-9)
          (read-string supply-public-goods-9))
       (* (read-string public-good-prices-10)
          (read-string supply-public-goods-10)))))

(defn get-last-year-green [r]
  (->> r
       (filter (comp (partial = ":green") :color))
       first))

(defn get-this-year-green [r]
  (->> r
       (filter (comp #(contains? #{"" ":green"} %) :color))
       (drop-while (comp (partial = ":green") :color))
       (filter (comp (partial = ":green") :color))
       first))

(defn get-prices-and-supplies [m]
  (select-keys m [
:color
:iteration
:private-good-prices-1 
:private-good-prices-2 
:private-good-prices-3 
:private-good-prices-4 
:private-good-prices-5 
:private-good-prices-6 
:private-good-prices-7 
:private-good-prices-8 
:private-good-prices-9 
:private-good-prices-10 
:supply-private-goods-1 
:supply-private-goods-2 
:supply-private-goods-3 
:supply-private-goods-4 
:supply-private-goods-5 
:supply-private-goods-6 
:supply-private-goods-7 
:supply-private-goods-8 
:supply-private-goods-9 
:supply-private-goods-10 
:public-good-prices-1 
:public-good-prices-2 
:public-good-prices-3 
:public-good-prices-4 
:public-good-prices-5 
:public-good-prices-6 
:public-good-prices-7 
:public-good-prices-8 
:public-good-prices-9 
:public-good-prices-10 
:supply-public-goods-1 
:supply-public-goods-2 
:supply-public-goods-3 
:supply-public-goods-4 
:supply-public-goods-5 
:supply-public-goods-6 
:supply-public-goods-7 
:supply-public-goods-8 
:supply-public-goods-9 
:supply-public-goods-10 
]))

(defn get-supplies [m]
  (select-keys m [:supply-private-goods-1 
:supply-private-goods-2 
:supply-private-goods-3 
:supply-private-goods-4 
:supply-private-goods-5 
:supply-private-goods-6 
:supply-private-goods-7 
:supply-private-goods-8 
:supply-private-goods-9
:supply-private-goods-10
:supply-public-goods-1 
:supply-public-goods-2 
:supply-public-goods-3 
:supply-public-goods-4 
:supply-public-goods-5 
:supply-public-goods-6 
:supply-public-goods-7 
:supply-public-goods-8 
:supply-public-goods-9 
:supply-public-goods-10 
]))

(defn mean [L]
  (/ (reduce + L) (count L)))

(defn get-gdp-avg-pi [data]
  (let [last-year (get-prices-and-supplies (get-last-year-green data))
        this-year (get-prices-and-supplies (get-this-year-green data))
        lys-typ (merge this-year (get-supplies last-year))
        lyp-tys (merge last-year (get-supplies this-year))
        gdp-typ-2 (compute-gdp this-year)
        gdp-typ-1 (compute-gdp lys-typ)
        gdp-lyp-2 (compute-gdp lyp-tys)
        gdp-lyp-1 (compute-gdp last-year)
        gdp-typ-pi (* 100 (/ (- gdp-typ-2 gdp-typ-1) gdp-typ-1))
        gdp-lyp-pi (* 100 (/ (- gdp-lyp-2 gdp-lyp-1) gdp-lyp-1))
        gdp-avg-pi (mean [gdp-typ-pi gdp-lyp-pi])]
    gdp-avg-pi))

(defn finish-gdp [& [file-to-use]]
  (let [data-to-use (->> file-to-use
                         io/reader
                         csv/read-csv
                         csv-data->maps)]
    (get-gdp-avg-pi data-to-use)))

(defn zero-pad [n]
  (if (< n 10) (str "0" n) (str n)))

(defn get-resource [n]
  (format "resources/dep1ex%s.csv" (zero-pad n)))

(def all-gdps
  (let [nums (range 1 51)]
    (mapv (juxt identity #(finish-gdp (get-resource %)))
          nums)))
