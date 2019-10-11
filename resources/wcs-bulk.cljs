(defn create-wcs [worker-councils goods industry]
  "# of wcs created is (/ (count wcs) 2 (count goods)"
  (let [n (/ worker-councils 2 (count goods))]
    (->> goods
        (map #(vec (repeat n {:industry industry :product %})))
        flatten)))


(defn continue-setup-wcs [intermediate-inputs nature-types labor-types pollutant-types wc]
  "Assumes wc is a map; change 0.2 below to some other value?"
  (letfn [(get-random-subset [input-seq]
            (->> input-seq
                 shuffle
                 (take (rand-nth input-seq))
                 sort
                 vec))]
    (let [production-inputs (vector (get-random-subset intermediate-inputs)
                                    (get-random-subset nature-types)
                                    (get-random-subset labor-types)
                                    (get-random-subset pollutant-types))
          input-exponents (when (pos? (count (first production-inputs)))
                            (let [xz (/ 0.30 (count (first production-inputs)))]
                              (vec (take (count (first production-inputs))
                                         (repeatedly #(rand xz))))))
          nature-exponents (let [rz (/ 0.30 (count (second production-inputs)))]
                             (vec (take (count (second production-inputs))
                                        (repeatedly #(rand rz)))))
          labor-exponents (let [lz (/ 0.30 (count (nth production-inputs 2)))]
                            (vec (take (count (nth production-inputs 2))
                                       (repeatedly #(rand lz)))))
          pollutant-exponents (let [pz (/ 0.30 (count (last production-inputs)))]
                                (vec (take (count (last production-inputs))
                                           (repeatedly #(rand pz)))))]
      (merge wc {:production-inputs production-inputs
                 :xe 0.05
                 :c 0.05
                 :input-exponents input-exponents
                 :nature-exponents nature-exponents
                 :labor-exponents labor-exponents
                 :pollutant-exponents pollutant-exponents
                 :cq 0.25
                 :ce 1
                 :du 7
                 :s 1
                 :a 0.25
                 :effort 0.5
                 :output 0
                 :labor-quantities [0]}))))


(defn create-wcs-bulk [num-ind-0 num-ind-1]
  (->> (merge (create-wcs num-ind-0 [1 2 3 4] 0)
              (create-wcs num-ind-1 [1 2 3 4] 1))
       flatten
       (map (partial continue-setup-wcs
                     [1 2 3 4] ; intermediate-inputs
                     [1] ; nature-types
                     [1] ; labor-types
                     [1] ; pollutant-types
             ))))

