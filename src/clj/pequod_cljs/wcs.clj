(ns pequod-cljs.wcs)


#_(defn create-wcs [worker-councils goods industry]
  (->> goods
       (mapv #(vec (concat (repeat (dec (/ worker-councils (count goods)))
                                   {:toothache false 
                                    :industry industry 
                                    :product %}) 
                           [{:toothache true
                              :industry industry
                              :product %}])))
       flatten))

(defn create-wcs [worker-councils goods industry]
  (->> goods
       (mapv #(vec (concat (repeat (/ worker-councils (count goods))
                                   {:toothache false 
                                    :industry industry 
                                    :product %}) 
                           )))
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
            (->> #(rand-range (/ 0.75 n) (/ 0.85 n))
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
               (into [] (drop (+ (count (first inputs)) (count (second inputs))) r))]))]
    (let [production-inputs (vector (get-random-subset intermediate-inputs :intermediate-inputs)
                                    (get-random-subset nature-types :nature-types)
                                    (get-random-subset labor-types :labor-types))
          production-inputs-count (->> production-inputs
                                       flatten
                                       count)
          input-exponents (generate-exponents production-inputs-count first production-inputs)
          nature-exponents (generate-exponents production-inputs-count second production-inputs)
          labor-exponents (generate-exponents production-inputs-count last production-inputs)]
      (merge wc {:production-inputs production-inputs
                 :c (rand-range 0.05 0.1)
                 :input-exponents input-exponents
                 :nature-exponents nature-exponents
                 :labor-exponents labor-exponents
                 :du (rand-range 3 4)
                 :s 1
                 :a (rand-range 4 6)
                 :effort 0.5
                 :output 0
                 :labor-quantities [0]}))))

(defn create-toothache [wc]
  (if (:toothache wc)
    (assoc wc :input-exponents (mapv (partial + 0.1) (:input-exponents wc))
              :nature-exponents (mapv (partial + 0.1) (:nature-exponents wc))
              :labor-exponents (mapv (partial + 0.1) (:labor-exponents wc)))
    wc))

(defn create-wcs-bulk [num-ind-0 num-ind-1 num-ind-2]
  (->> (merge (create-wcs num-ind-0 [1 2 3 4 5 6 7 8 9 10] 0)
              (create-wcs num-ind-1 [1 2 3 4 5 6 7 8 9 10] 1)
              (create-wcs num-ind-2 [1 2 3 4 5 6 7 8 9 10] 2))
       flatten
       (mapv (partial continue-setup-wcs
                      [1 2 3 4 5 6 7 8 9 10] ; intermediate-inputs
                      [1 2 3 4 5 6 7 8 9 10] ; nature-types
                      [1 2 3 4 5 6 7 8 9 10] ; labor-types
       ))
       (mapv create-toothache)))
