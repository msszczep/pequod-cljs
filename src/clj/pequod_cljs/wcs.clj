(ns pequod-cljs.wcs)

(defn create-wcs [worker-councils goods industry]
  (->> goods
       (map #(vec (repeat (/ worker-councils 2 (count goods))
                          {:industry industry :product %})))
       flatten))


(defn continue-setup-wcs [intermediate-inputs nature-types labor-types wc]
  "Assumes wc is a map"
  (letfn [(get-random-subset [input-seq]
            (->> input-seq
                 shuffle
                 (take (rand-nth input-seq))
                 sort
                 vec))]
    (let [production-inputs (vector (get-random-subset intermediate-inputs)
                                    (get-random-subset nature-types)
                                    (get-random-subset labor-types))
          input-exponents (when (pos? (count (first production-inputs)))
                            (let [xz (/ 0.3 (count (first production-inputs)))]
                              (vec (take (count (first production-inputs))
                                         (repeatedly #(+ xz (rand xz)))))))
          nature-exponents (let [rz (/ 0.3 (count (second production-inputs)))]
                             (vec (take (count (second production-inputs))
                                        (repeatedly #(+ 0.05 rz (rand rz))))))
          labor-exponents (let [lz (/ 0.3 (count (last production-inputs)))]
                            (vec (take (count (last production-inputs))
                                       (repeatedly #(+ 0.05 lz (rand lz))))))]
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
              (create-wcs num-ind-2 [1] 2))
       flatten
       (map (partial continue-setup-wcs
                     [1 2 3 4] ; intermediate-inputs
                     [1 2] ; nature-types
                     [1 2] ; labor-types
))))
