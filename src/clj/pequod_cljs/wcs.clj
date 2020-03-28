(ns pequod-cljs.wcs)


(defn create-wcs [worker-councils goods industry]
  (->> goods
       (map #(vec (repeat (/ worker-councils (count goods))
                          {:industry industry :product %})))
       flatten))

; for however many inputs we have
;  apportion 95 points among those inputs
;  do some randomization such that it's within a suitable range
;  the sum must be greater than 0.5, less than 1

; range for 0.5 / number of inputs (length of flattened production-inputs)
;  up to 1 / number of inputs


(defn continue-setup-wcs [intermediate-inputs nature-types labor-types wc]
  "Assumes wc is a map"
  (letfn [(get-random-subset [input-seq input-type]
            (let [take-num (if (= input-type :intermediate-inputs) 4 2)]
              (->> input-seq
                   shuffle
                   (take (inc (rand-int take-num)))
                   sort
                   vec)))
          (rand-range [start end]
            (+ start (clojure.core/rand (- end start))))
          (generate-exponents [n f inputs]
            (->> #(rand-range (/ 0.5 n) (/ 0.80 n))
                 repeatedly
                 (take (count (f inputs)))
                 vec))
          (generate-exponents-2 [input-count inputs]
            (let [n (* 0.1 input-count)]
             (->> #(rand-range 0 n)
                  repeatedly
                  (take (dec input-count))
                  (cons 0)
                  (cons n)
                  sort
                  (partition 2 1)
                  (map (fn [[x y]] (- y x))))))
          (generate-exponents-3 [input-count inputs]
            (let [n (* 0.1 input-count)
                  r (generate-exponents-2 input-count inputs)]
              [(into [] (take (count (first inputs)) r))
               (into [] (take (count (second inputs)) (drop (count (first inputs)) r)))
               (into [] (take (count (last inputs)) (drop (count (+ (first inputs) (second inputs))) r)))]))]
    (let [production-inputs (vector (get-random-subset intermediate-inputs :intermediate-inputs)
                                    (get-random-subset nature-types :nature-types)
                                    (get-random-subset labor-types :labor-types))
          production-inputs-count (->> production-inputs
                                       flatten
                                       count)
          [input-exponents nature-exponents labor-exponents] (generate-exponents-3 production-inputs-count production-inputs)          
]
      (merge wc {:production-inputs production-inputs
                 :xe 0.05
                 :c 0.05
                 :input-exponents input-exponents
                 :nature-exponents nature-exponents
                 :labor-exponents labor-exponents
                 :cq 0.25
                 :ce 1
                 :du 7
                 :s 1
                 :a 0.25
                 :effort 0.5
                 :output 0
                 :labor-quantities [0]}))))


(defn create-wcs-bulk [num-ind-0 num-ind-1 num-ind-2]
  (->> (merge (create-wcs num-ind-0 [1 2 3 4 5] 0)
              (create-wcs num-ind-1 [1 2 3 4 5] 1)
              (create-wcs num-ind-2 [1 2 3 4 5] 2))
       flatten
       (mapv (partial continue-setup-wcs
                      [1 2 3 4 5] ; intermediate-inputs
                      [1 2 3 4 5] ; nature-types
                      [1 2 3 4 5] ; labor-types
  ))))
