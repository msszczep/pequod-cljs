(ns pequod-cljs.ccs)

(defn create-ccs-bulk [consumer-councils workers-per-council finals public-goods]
  (let [effort 1
;        cz (/ 0.5 (+ public-goods finals))
        cz 0.025
        utility-exponents (->> #(+ cz (rand 0.025))
                               repeatedly
                               (take (* finals (+ public-goods consumer-councils)))
                               (partition finals))
        public-good-exponents (->> #(+ cz (rand 0.025))
                                   repeatedly
                                   (take (* public-goods consumer-councils))
                                   (partition public-goods))]
    (mapv #(hash-map :num-workers workers-per-council
                     :effort effort
                     :income (* 500 effort workers-per-council)
                     :cy 1
                     :utility-exponents (vec (first %))
                     :final-demands (vec (repeat 5 0))
                     :public-good-demands (vec (repeat 1 0))
                     :public-good-exponents (vec (second %)))
          (partition 2 (interleave utility-exponents public-good-exponents)))))
