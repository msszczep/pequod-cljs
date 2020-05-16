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
