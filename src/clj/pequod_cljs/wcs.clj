(ns pequod-cljs.wcs)

; TODO remove 2 and count goods
(defn create-wcs [worker-councils goods industry]
  (->> goods
       (map #(vec (repeat (/ worker-councils 2 (count goods))
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
  (letfn [(get-random-subset [input-seq]
            (->> input-seq
                 shuffle
                 (take (rand-nth input-seq))
                 sort
                 vec))
          (rand-range [start end]
            (+ start (clojure.core/rand (- end start))))
          (generate-exponents [n f inputs]
            (->> #(rand-range (/ 0.5 n) (/ 1 n))
                 repeatedly
                 (take (count (f inputs)))
                 vec))]
    (let [production-inputs (vector (get-random-subset intermediate-inputs)
                                    (get-random-subset nature-types)
                                    (get-random-subset labor-types))
          production-inputs-count (->> production-inputs
                                       flatten
                                       count)
          input-exponents (generate-exponents production-inputs-count first production-inputs)
          nature-exponents (generate-exponents production-inputs-count second production-inputs)
          labor-exponents (generate-exponents production-inputs-count last production-inputs)]
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
  (->> (merge (create-wcs num-ind-0 [1 2 3 4] 0)
              (create-wcs num-ind-1 [1 2 3 4] 1)
              (create-wcs num-ind-2 [1 2] 2))
       flatten
       (map (partial continue-setup-wcs
                     [1 2 3 4] ; intermediate-inputs
                     [1 2] ; nature-types
                     [1 2] ; labor-types
))))
