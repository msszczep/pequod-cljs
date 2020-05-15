(ns pequod-cljs.tables
   )

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
  (->> "resources/tables.txt"
       slurp
       clojure.string/split-lines
       (mapv parse-data)))

(defn get-first-green-iteration [d n]
  (->> d
       (filter (comp (partial = n) :experiment))
       (filter (comp (partial = :green) :color))
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

(defn get-second-green-iteration [d n]
  (->> d
       (filter (comp (partial = n) :experiment))
       (filter (comp (partial = :green) :color))))

#_(defn find-ascending-split [s]
  ())

; table 4
