(defn create-ccs-bulk-3 [consumer-councils workers-per-council finals public-goods num-pollutants]

  (let [effort 1

        cz (/ 0.5 (+ public-goods finals num-pollutants))

        utility-exponents (->> #(+ cz (rand cz))

                               repeatedly

                               (take (* finals (+ public-goods consumer-councils)))

                               (partition finals))

        public-good-exponents (->> #(+ cz (rand cz))

                                   repeatedly

                                   (take (* public-goods consumer-councils))

                                   (partition public-goods))

        pollution-supply-coefficients (->> #(* 2 (+ cz (rand cz)))

                                         repeatedly

                                         (take (* num-pollutants consumer-councils))



                                         (partition num-pollutants))]

    (map #(hash-map :num-workers workers-per-council

                    :effort effort

                    :income (* 500 effort workers-per-council)

                    :cy (+ 6 (rand 9))

                    :utility-exponents (vec (first %))

                    :final-demands (vec (repeat 5 0))

                    :public-good-demands (vec (repeat 1 0))

                    :public-good-exponents (vec (second %))

                    :pollution-supply-coefficients (vec (last %)))

         (partition 3 (interleave utility-exponents public-good-exponents pollution-supply-coefficients)))))

