(ns pequod-cljs.core
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]
;              [pequod-cljs.ex001data :as ex001]
;              [pequod-cljs.ex001ppgdata :as ex001pg]
              [pequod-cljs.ex1dot3 :as ex1dot3]
              [goog.string :as gstring]
              [goog.string.format]))

(def globals
  (atom {:init-private-good-price 100
         :init-intermediate-price 100
         :init-labor-price        150
         :init-nature-price       150
         :init-public-good-price  1000
         :init-pollutant-price    100

         :private-goods             4
         :inputs                    4
         :resources                 1
         :labors                    1
         :public-goods              1
         :num-pollutants            1

         :private-good-prices      []
         :intermediate-good-prices []
         :labor-prices             []
         :nature-prices            []
         :public-goods-prices      []
         :pollutant-prices         []

         :final-surpluses          []
         :input-surpluses          []
         :nature-surpluses         []
         :labor-surpluses          []
         :public-good-surpluses    []
         :pollutant-surplus        []

         :threshold-met            false
         :pdlist                   []
         :delta-delay              0
         :price-deltas             []
         :price-delta              0
         :wcs                      []
         :ccs                      []
         :iteration                0
         :natural-resources-supply 0
         :labor-supply             0
         :pollutant-supply         0}))


(defn create-ccs-bulk [consumer-councils workers-per-council finals public-goods num-pollutants]
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
        pollution-supply-coefficients (->> #(+ cz (rand cz))
                                         repeatedly
                                         (take (* num-pollutants consumer-councils))
                                         (map -)
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
                            (let [xz (/ 0.2 (count (first production-inputs)))]
                              (vec (take (count (first production-inputs))
                                         (repeatedly #(rand xz))))))
          nature-exponents (let [rz (/ 0.2 (count (second production-inputs)))]
                             (vec (take (count (second production-inputs))
                                        (repeatedly #(rand rz)))))
          labor-exponents (let [lz (/ 0.2 (count (nth production-inputs 2)))]
                            (vec (take (count (nth production-inputs 2))
                                       (repeatedly #(rand lz)))))
          pollutant-exponents (let [pz (/ 0.2 (count (last production-inputs)))]
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


(defn create-wcs-bulk [num-ind-0 num-ind-1 num-ind-2]
  (->> (merge (create-wcs num-ind-0 [1 2 3 4] 0)
              (create-wcs num-ind-1 [1 2 3 4] 1)
              (create-wcs num-ind-2 [1] 2))
       flatten
       (map (partial continue-setup-wcs
                     [1 2 3 4] ; intermediate-inputs
                     [1] ; nature-types
                     [1] ; labor-types
                     [1] ; pollutant-types
             ))))


(defn initialize-prices [t]
  (let [private-goods (t :private-goods)
        inputs (t :inputs)
        resources (t :resources)
        labor (t :labors)
        public-goods (t :public-goods)
        num-pollutants (t :num-pollutants)]
    (assoc t
      :private-good-prices (vec (repeat private-goods (t :init-private-good-price)))
      :intermediate-good-prices (vec (repeat inputs (t :init-intermediate-price)))
      :nature-prices (vec (repeat resources (t :init-nature-price)))
      :labor-prices (vec (repeat labor (t :init-labor-price)))
      :public-goods-prices (vec (repeat public-goods (t :init-public-good-price)))
      :pollutant-prices (vec (repeat num-pollutants (t :init-pollutant-price)))
      :price-deltas (vec (repeat 6 0.05))
      :pdlist (vec (repeat (+ private-goods inputs resources labor public-goods num-pollutants) 0.05)))))


(defn setup [t _ button-type]
  (let [intermediate-inputs (vec (range 1 (inc (t :inputs))))
        nature-types (vec (range 1 (inc (t :resources))))
        labor-types (vec (range 1 (inc (t :labors))))
        private-goods (vec (range 1 (inc (t :private-goods))))
        public-goods-types (vec (range 1 (inc (t :public-goods))))
        pollutant-types (vec (range 1 (inc (t :num-pollutants))))]
    (-> t
        initialize-prices
        (assoc :price-delta 0.1
               :delta-delay 5
               :natural-resources-supply 1000
               :labor-supply 1000
               :pollutant-supply 100
               :final-goods private-goods
               :intermediate-inputs intermediate-inputs
               :nature-types nature-types
               :labor-types labor-types
               :public-goods-types public-goods-types
               :pollutant-types pollutant-types
               :surplus-threshold 0.02
               :ccs ex1dot3/ccs
                    #_(condp = button-type
                      "ex001" ex001/ccs
                      "ex001pg" ex001pg/ccs)
               :wcs ex1dot3/wcs
               #_(condp = button-type 
                 "ex001" ex001/wcs
                 "ex001pg" ex001pg/wcs)))))


(defn consume [final-goods private-good-prices public-goods public-goods-prices pollutants pollutant-prices num-of-ccs cc]
  (let [utility-exponents (cc :utility-exponents)
        public-good-exponents (cc :public-good-exponents)
        pollution-supply-coefficients (cc :pollution-supply-coefficients)
        income (cc :income)
        all-exponents (->> pollution-supply-coefficients
                           (concat utility-exponents public-good-exponents)
                           (map #(Math/abs %)))
        final-demands (mapv (fn [final-good]
                              (/ (* income (nth utility-exponents (dec final-good)))
                                 (* (apply + all-exponents)
                                    (nth private-good-prices (dec final-good)))))
                            final-goods)
        public-good-demands (mapv (fn [public-good]
                                    (/ (* income (nth public-good-exponents (dec public-good)))
                                       (* (apply + all-exponents)
                                          (/ (nth public-goods-prices (dec public-good))
                                             num-of-ccs))))
                                  public-goods)
        pollutant-supply (mapv (fn [pollutant]
                                 (/ (* income (nth pollution-supply-coefficients (dec pollutant)))
                                    (* (apply + all-exponents)
                                       (/ (nth pollutant-prices (dec pollutant))
                                          num-of-ccs))))
                               pollutants)]
    (assoc cc :final-demands final-demands
              :public-good-demands public-good-demands
              :pollutant-supply pollutant-supply
              :income (+ income
                         (* (/ (first pollutant-supply) num-of-ccs)
                            (first pollutant-prices))))))


(defn assign-new-proposal [production-inputs xs]
  (let [num-input-quantities (count (first production-inputs))
        num-nature-quantities (count (second production-inputs))
        num-labor-quantities (count (nth production-inputs 2))
        input-quantities (->> xs
                              (take num-input-quantities)
                              (into []))
        nature-quantities (->> xs
                               (drop num-input-quantities)
                               (take num-nature-quantities)
                               (into []))
        labor-quantities (->> xs
                              (drop (+ num-input-quantities num-nature-quantities))
                              (take num-labor-quantities)
                              (into []))
        pollutant-demand (->> xs
                              (drop (+ num-input-quantities num-nature-quantities num-labor-quantities))
                              (into []))]
    [input-quantities nature-quantities labor-quantities pollutant-demand]))


(defn solution-1 [a s c k ps b λ p-i]
  (let [b1 (first b)
        p1 (first (flatten ps))
        output (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ)))) (+ c (- k) (* k b1))))
        x1 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* c (Math/log b1)) (- (* k (Math/log b1))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* c (Math/log p1))) (* k (Math/log p1)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1))))
        effort (Math/pow Math/E (/ (+ (- (* (Math/log a))) (- (* b1 (Math/log b1))) (- (* (Math/log c))) (* b1 (Math/log c)) (* (Math/log k)) (- (* b1 (Math/log k))) (* b1 (Math/log p1)) (* (Math/log s)) (- (* b1 (Math/log s))) (- (* (Math/log λ)))) (+ c (- k) (* k b1))))
        [input-qs nature-qs labor-qs pollutant-d] (assign-new-proposal p-i [x1])]
    {:output output
     :x1 x1
     :effort effort
     :input-quantities input-qs
     :nature-quantities nature-qs
     :labor-quantities labor-qs
     :pollutant-demand pollutant-d}))


(defn solution-2 [a s c k ps b λ p-i]
  (let [[b1 b2] b
        [p1 p2] (flatten ps)
        output (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2))))
        x1 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* c (Math/log b1)) (- (* k (Math/log b1))) (* b2 k (Math/log b1)) (- (* b2 k (Math/log b2))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* c (Math/log p1))) (* k (Math/log p1)) (- (* b2 k (Math/log p1))) (* b2 k (Math/log p2)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2))))
        x2 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (* c (Math/log b2)) (- (* k (Math/log b2))) (* b1 k (Math/log b2)) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (- (* c (Math/log p2))) (* k (Math/log p2)) (- (* b1 k (Math/log p2))) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2))))
        effort (Math/pow Math/E (/ (+ (- (* (Math/log a))) (- (* b1 (Math/log b1))) (- (* b2 (Math/log b2))) (- (* (Math/log c))) (* b1 (Math/log c)) (* b2 (Math/log c)) (* (Math/log k)) (- (* b1 (Math/log k))) (- (* b2 (Math/log k))) (* b1 (Math/log p1)) (* b2 (Math/log p2)) (* (Math/log s)) (- (* b1 (Math/log s))) (- (* b2 (Math/log s))) (- (* (Math/log λ)))) (+ c (- k) (* k b1) (* k b2))))
        [input-qs nature-qs labor-qs pollutant-d] (assign-new-proposal p-i [x1 x2])]
    {:output output
     :x1 x1
     :x2 x2
     :effort effort
     :input-quantities input-qs
     :nature-quantities nature-qs
     :labor-quantities labor-qs
     :pollutant-demand pollutant-d}))


(defn solution-3 [a s c k ps b λ p-i]
  (let [[b1 b2 b3] b
        [p1 p2 p3] (flatten ps)
        output (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3))))
        x1 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* c (Math/log b1)) (- (* k (Math/log b1))) (* b2 k (Math/log b1)) (* b3 k (Math/log b1)) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* c (Math/log p1))) (* k (Math/log p1)) (- (* b2 k (Math/log p1))) (- (* b3 k (Math/log p1))) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3))))
        x2 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (* c (Math/log b2)) (- (* k (Math/log b2))) (* b1 k (Math/log b2)) (* b3 k (Math/log b2)) (- (* b3 k (Math/log b3))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (- (* c (Math/log p2))) (* k (Math/log p2)) (- (* b1 k (Math/log p2))) (- (* b3 k (Math/log p2))) (* b3 k (Math/log p3)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3))))
        x3 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (* c (Math/log b3)) (- (* k (Math/log b3))) (* b1 k (Math/log b3)) (* b2 k (Math/log b3)) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (- (* c (Math/log p3))) (* k (Math/log p3)) (- (* b1 k (Math/log p3))) (- (* b2 k (Math/log p3))) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3))))
        effort (Math/pow Math/E (/ (+ (- (* (Math/log a))) (- (* b1 (Math/log b1))) (- (* b2 (Math/log b2))) (- (* b3 (Math/log b3))) (* b1 (Math/log p1)) (* b2 (Math/log p2)) (* b3 (Math/log p3)) (- (* b1 (Math/log λ))) (- (* b2 (Math/log λ))) (- (* b3 (Math/log λ))) (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3))) (- (/ (* b1 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ)))))  (+ c (- k) (* k b1) (* k b2) (* k b3)))) (- (/ (* b2 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))))) (+ c (- k) (* k b1) (* k b2) (* k b3)))) (- (/ (* b3 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))))) (+ c (- k) (* k b1) (* k b2) (* k b3))))) c))
        [input-qs nature-qs labor-qs pollutant-d] (assign-new-proposal p-i [x1 x2 x3])]
    {:output output
     :x1 x1
     :x2 x2
     :x3 x3
     :effort effort
     :input-quantities input-qs
     :nature-quantities nature-qs
     :labor-quantities labor-qs
     :pollutant-demand pollutant-d}))


(defn solution-4 [a s c k ps b λ p-i]
  (let [[b1 b2 b3 b4] b
        [p1 p2 p3 p4] (flatten ps)
        output (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4))))
        x1 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* c (Math/log b1)) (- (* k (Math/log b1))) (* b2 k (Math/log b1)) (* b3 k (Math/log b1)) (* b4 k (Math/log b1)) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* c (Math/log p1))) (* k (Math/log p1)) (- (* b2 k (Math/log p1))) (- (* b3 k (Math/log p1))) (- (* b4 k (Math/log p1))) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4))))
        x2 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (* c (Math/log b2)) (- (* k (Math/log b2))) (* b1 k (Math/log b2)) (* b3 k (Math/log b2)) (* b4 k (Math/log b2)) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (- (* c (Math/log p2))) (* k (Math/log p2)) (- (* b1 k (Math/log p2))) (- (* b3 k (Math/log p2))) (- (* b4 k (Math/log p2))) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4))))
        x3 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (* c (Math/log b3)) (- (* k (Math/log b3))) (* b1 k (Math/log b3)) (* b2 k (Math/log b3)) (* b4 k (Math/log b3)) (- (* b4 k (Math/log b4))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (- (* c (Math/log p3))) (* k (Math/log p3)) (- (* b1 k (Math/log p3))) (- (* b2 k (Math/log p3))) (- (* b4 k (Math/log p3))) (* b4 k (Math/log p4)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4))))
        x4 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (* c (Math/log b4)) (- (* k (Math/log b4))) (* b1 k (Math/log b4)) (* b2 k (Math/log b4)) (* b3 k (Math/log b4)) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (- (* c (Math/log p4))) (* k (Math/log p4)) (- (* b1 k (Math/log p4))) (- (* b2 k (Math/log p4))) (- (* b3 k (Math/log p4))) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4))))
     effort (Math/pow Math/E (/ (+ (- (* (Math/log a))) (- (* b1 (Math/log b1))) (- (* b2 (Math/log b2))) (- (* b3 (Math/log b3))) (- (* b4 (Math/log b4))) (- (* (Math/log c))) (* b1 (Math/log c)) (* b2 (Math/log c)) (* b3 (Math/log c)) (* b4 (Math/log c)) (* (Math/log k)) (- (* b1 (Math/log k))) (- (* b2 (Math/log k))) (- (* b3 (Math/log k))) (- (* b4 (Math/log k))) (* b1 (Math/log p1)) (* b2 (Math/log p2)) (* b3 (Math/log p3)) (* b4 (Math/log p4)) (* (Math/log s)) (- (* b1 (Math/log s))) (- (* b2 (Math/log s))) (- (* b3 (Math/log s))) (- (* b4 (Math/log s))) (- (* (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4))))
        [input-qs nature-qs labor-qs pollutant-d] (assign-new-proposal p-i [x1 x2 x3 x4])]
    {:output output
     :x1 x1
     :x2 x2
     :x3 x3
     :x4 x4
     :effort effort
     :input-quantities input-qs
     :nature-quantities nature-qs
     :labor-quantities labor-qs
     :pollutant-demand pollutant-d}))


(defn solution-5 [a s c k ps b λ p-i]
  (let [[b1 b2 b3 b4 b5] b
        [p1 p2 p3 p4 p5] (flatten ps)
        output (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5))))
        x1 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* c (Math/log b1)) (- (* k (Math/log b1))) (* b2 k (Math/log b1)) (* b3 k (Math/log b1)) (* b4 k (Math/log b1)) (* b5 k (Math/log b1)) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* c (Math/log p1))) (* k (Math/log p1)) (- (* b2 k (Math/log p1))) (- (* b3 k (Math/log p1))) (- (* b4 k (Math/log p1))) (- (* b5 k (Math/log p1))) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5))))
        x2 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (* c (Math/log b2)) (- (* k (Math/log b2))) (* b1 k (Math/log b2)) (* b3 k (Math/log b2)) (* b4 k (Math/log b2)) (* b5 k (Math/log b2)) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (- (* c (Math/log p2))) (* k (Math/log p2)) (- (* b1 k (Math/log p2))) (- (* b3 k (Math/log p2))) (- (* b4 k (Math/log p2))) (- (* b5 k (Math/log p2))) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5))))
        x3 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (* c (Math/log b3)) (- (* k (Math/log b3))) (* b1 k (Math/log b3)) (* b2 k (Math/log b3)) (* b4 k (Math/log b3)) (* b5 k (Math/log b3)) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (- (* c (Math/log p3))) (* k (Math/log p3)) (- (* b1 k (Math/log p3))) (- (* b2 k (Math/log p3))) (- (* b4 k (Math/log p3))) (- (* b5 k (Math/log p3))) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5))))
        x4 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (* c (Math/log b4)) (- (* k (Math/log b4))) (* b1 k (Math/log b4)) (* b2 k (Math/log b4)) (* b3 k (Math/log b4)) (* b5 k (Math/log b4)) (- (* b5 k (Math/log b5))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (- (* c (Math/log p4))) (* k (Math/log p4)) (- (* b1 k (Math/log p4))) (- (* b2 k (Math/log p4))) (- (* b3 k (Math/log p4))) (- (* b5 k (Math/log p4))) (* b5 k (Math/log p5)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5))))
        x5 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (* c (Math/log b5)) (- (* k (Math/log b5))) (* b1 k (Math/log b5)) (* b2 k (Math/log b5)) (* b3 k (Math/log b5)) (* b4 k (Math/log b5)) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (- (* c (Math/log p5))) (* k (Math/log p5)) (- (* b1 k (Math/log p5))) (- (* b2 k (Math/log p5))) (- (* b3 k (Math/log p5))) (- (* b4 k (Math/log p5))) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5))))
        effort (Math/pow Math/E (/ (+ (- ( * (Math/log a))) (- (* b1 (Math/log b1))) (- (* b2 (Math/log b2))) (- (* b3 (Math/log b3))) (- (* b4 (Math/log b4))) (- (* b5 (Math/log b5))) (* b1 (Math/log p1)) (* b2 (Math/log p2)) (* b3 (Math/log p3)) (* b4 (Math/log p4)) (* b5 (Math/log p5)) (- (* b1 (Math/log λ))) (- (* b2 (Math/log λ))) (- (* b3 (Math/log λ))) (- (* b4 (Math/log λ))) (- (* b5 (Math/log λ))) (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5))) (- (/ (* b1 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log λ)))))  (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5)))) (- (/ (* b2 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log λ))))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5)))) (- (/ (* b3 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log λ)))))  (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5)))) (- (/ (* b4 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log λ))))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5)))) (- (/ (* b5 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log λ))))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5))))) c))
        [input-qs nature-qs labor-qs pollutant-d] (assign-new-proposal p-i [x1 x2 x3 x4 x5])]
     {:output output
      :x1 x1
      :x2 x2
      :x3 x3
      :x4 x4
      :x5 x5
      :effort effort
      :input-quantities input-qs
      :nature-quantities nature-qs
      :labor-quantities labor-qs
      :pollutant-demand pollutant-d}))


(defn solution-6 [a s c k ps b λ p-i]
  (let [[b1 b2 b3 b4 b5 b6] b
        [p1 p2 p3 p4 p5 p6] (flatten ps)
        output (Math/pow Math/E (- (/ (+ (* k (Math/log a)) (* b1 k (Math/log b1)) (* b2 k (Math/log b2)) (* b3 k (Math/log b3)) (* b4 k (Math/log b4)) (* b5 k (Math/log b5)) (* b6 k (Math/log b6)) (* c (Math/log c)) (- (* c (Math/log k))) (- (* b1 k (Math/log p1))) (- (* b2 k (Math/log p2))) (- (* b3 k (Math/log p3))) (- (* b4 k (Math/log p4))) (- (* b5 k (Math/log p5))) (- (* b6 k (Math/log p6)))  (- (* c (Math/log s))) (* c (Math/log λ)) (* b1 k (Math/log λ)) (* b2 k (Math/log λ)) (* b3 k (Math/log λ)) (* b4 k (Math/log λ)) (* b5 k (Math/log λ)) (* b6 k (Math/log λ))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6)))))
        x1 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* c (Math/log b1)) (- (* k (Math/log b1))) (* b2 k (Math/log b1)) (* b3 k (Math/log b1)) (* b4 k (Math/log b1)) (* b5 k (Math/log b1)) (* b6 k (Math/log b1)) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* c (Math/log p1))) (* k (Math/log p1)) (- (* b2 k (Math/log p1))) (- (* b3 k (Math/log p1))) (- (* b4 k (Math/log p1))) (- (* b5 k (Math/log p1))) (- (* b6 k (Math/log p1))) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6))))
        x2 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (* c (Math/log b2)) (- (* k (Math/log b2))) (* b1 k (Math/log b2)) (* b3 k (Math/log b2)) (* b4 k (Math/log b2)) (* b5 k (Math/log b2)) (* b6 k (Math/log b2)) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (- (* c (Math/log p2))) (* k (Math/log p2)) (- (* b1 k (Math/log p2))) (- (* b3 k (Math/log p2))) (- (* b4 k (Math/log p2))) (- (* b5 k (Math/log p2))) (- (* b6 k (Math/log p2))) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6))))
        x3 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (* c (Math/log b3)) (- (* k (Math/log b3))) (* b1 k (Math/log b3)) (* b2 k (Math/log b3)) (* b4 k (Math/log b3)) (* b5 k (Math/log b3)) (* b6 k (Math/log b3)) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (- (* c (Math/log p3))) (* k (Math/log p3)) (- (* b1 k (Math/log p3))) (- (* b2 k (Math/log p3))) (- (* b4 k (Math/log p3))) (- (* b5 k (Math/log p3))) (- (* b6 k (Math/log p3))) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6))))
        x4 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (* c (Math/log b4)) (- (* k (Math/log b4))) (* b1 k (Math/log b4)) (* b2 k (Math/log b4)) (* b3 k (Math/log b4)) (* b5 k (Math/log b4)) (* b6 k (Math/log b4)) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (- (* c (Math/log p4))) (* k (Math/log p4)) (- (* b1 k (Math/log p4))) (- (* b2 k (Math/log p4))) (- (* b3 k (Math/log p4))) (- (* b5 k (Math/log p4))) (- (* b6 k (Math/log p4))) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6))))
        x5 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (* c (Math/log b5)) (- (* k (Math/log b5))) (* b1 k (Math/log b5)) (* b2 k (Math/log b5)) (* b3 k (Math/log b5)) (* b4 k (Math/log b5)) (* b6 k (Math/log b5)) (- (* b6 k (Math/log b6))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (- (* c (Math/log p5))) (* k (Math/log p5)) (- (* b1 k (Math/log p5))) (- (* b2 k (Math/log p5))) (- (* b3 k (Math/log p5))) (- (* b4 k (Math/log p5))) (- (* b6 k (Math/log p5))) (* b6 k (Math/log p6)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6))))
        x6 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (* c (Math/log b6)) (- (* k (Math/log b6))) (* b1 k (Math/log b6)) (* b2 k (Math/log b6)) (* b3 k (Math/log b6)) (* b4 k (Math/log b6)) (* b5 k (Math/log b6)) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (- (* c (Math/log p6))) (* k (Math/log p6)) (- (* b1 k (Math/log p6))) (- (* b2 k (Math/log p6))) (- (* b3 k (Math/log p6))) (- (* b4 k (Math/log p6))) (- (* b5 k (Math/log p6))) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6))))
        effort (Math/pow Math/E (/ (+ (- (* (Math/log a))) (- (* b1 (Math/log b1))) (- (* b2 (Math/log b2))) (- (* b3 (Math/log b3))) (- (* b4 (Math/log b4))) (- (* b5 (Math/log b5))) (- (* b6 (Math/log b6))) (- (* (Math/log c))) (* b1 (Math/log c)) (* b2 (Math/log c)) (* b3 (Math/log c)) (* b4 (Math/log c)) (* b5 (Math/log c)) (* b6 (Math/log c)) (* (Math/log k)) (- (* b1 (Math/log k))) (- (* b2 (Math/log k))) (- (* b3 (Math/log k))) (- (* b4 (Math/log k))) (- (* b5 (Math/log k))) (- (* b6 (Math/log k))) (* b1 (Math/log p1)) (* b2 (Math/log p2)) (* b3 (Math/log p3)) (* b4 (Math/log p4)) (* b5 (Math/log p5)) (* b6 (Math/log p6)) (* (Math/log s)) (- (* b1 (Math/log s))) (- (* b2 (Math/log s))) (- (* b3 (Math/log s))) (- (* b4 (Math/log s))) (- (* b5 (Math/log s))) (- (* b6 (Math/log s))) (- (* (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6))))
        [input-qs nature-qs labor-qs pollutant-d] (assign-new-proposal p-i [x1 x2 x3 x4 x5 x6])]
     {:output output
      :x1 x1
      :x2 x2
      :x3 x3
      :x4 x4
      :x5 x5
      :x6 x6
      :effort effort
      :input-quantities input-qs
      :nature-quantities nature-qs
      :labor-quantities labor-qs
      :pollutant-demand pollutant-d}))


(defn solution-7 [a s c k ps b λ p-i]
  (let [[b1 b2 b3 b4 b5 b6 b7] b
        [p1 p2 p3 p4 p5 p6 p7] (flatten ps)
        output (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* c (Math/log k)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* c (Math/log c))) (- (* b1 k (Math/log b1))) (* b1 k (Math/log p1)) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log b2))) (* b2 k (Math/log p2)) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log b3))) (* b3 k (Math/log p3)) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log b4))) (* b4 k (Math/log p4)) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log b5))) (* b5 k (Math/log p5)) (- (* b5 k (Math/log λ))) (- (* b6 k (Math/log b6))) (* b6 k (Math/log p6)) (- (* b6 k (Math/log λ))) (- (* b7 k (Math/log b7))) (* b7 k (Math/log p7)) (- (* b7 k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7))))
        x1 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* b2 k (Math/log b1)) (* b3 k (Math/log b1)) (* b4 k (Math/log b1)) (* b5 k (Math/log b1)) (* b6 k (Math/log b1)) (* b7 k (Math/log b1)) (* c (Math/log b1)) (- (* k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* b2 k (Math/log p1))) (- (* b3 k (Math/log p1))) (- (* b4 k (Math/log p1))) (- (* b5 k (Math/log p1))) (- (* b6 k (Math/log p1))) (- (* b7 k (Math/log p1))) (* k (Math/log p1)) (- (* c (Math/log p1))) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7))))
        x2 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* b1 k (Math/log b2)) (* b3 k (Math/log b2)) (* b4 k (Math/log b2)) (* b5 k (Math/log b2)) (* b6 k (Math/log b2)) (* b7 k (Math/log b2)) (* c (Math/log b2)) (- (* k (Math/log b2))) (- (* b1 k (Math/log b1))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* b1 k (Math/log p2))) (- (* b3 k (Math/log p2))) (- (* b4 k (Math/log p2))) (- (* b5 k (Math/log p2))) (- (* b6 k (Math/log p2))) (- (* b7 k (Math/log p2))) (* k (Math/log p2)) (- (* c (Math/log p2))) (* b1 k (Math/log p1)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7))))
        x3 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* b1 k (Math/log b3)) (* b2 k (Math/log b3)) (* b4 k (Math/log b3)) (* b5 k (Math/log b3)) (* b6 k (Math/log b3)) (* b7 k (Math/log b3)) (* c (Math/log b3)) (- (* k (Math/log b3))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* b1 k (Math/log p3))) (- (* b2 k (Math/log p3))) (- (* b4 k (Math/log p3))) (- (* b5 k (Math/log p3))) (- (* b6 k (Math/log p3))) (- (* b7 k (Math/log p3))) (* k (Math/log p3)) (- (* c (Math/log p3))) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7))))
        x4 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* b1 k (Math/log b4)) (* b2 k (Math/log b4)) (* b3 k (Math/log b4)) (* b5 k (Math/log b4)) (* b6 k (Math/log b4)) (* b7 k (Math/log b4)) (* c (Math/log b4)) (- (* k (Math/log b4))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* b1 k (Math/log p4))) (- (* b2 k (Math/log p4))) (- (* b3 k (Math/log p4))) (- (* b5 k (Math/log p4))) (- (* b6 k (Math/log p4))) (- (* b7 k (Math/log p4))) (* k (Math/log p4)) (- (* c (Math/log p4))) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7))))
        x5 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* b1 k (Math/log b5)) (* b2 k (Math/log b5)) (* b3 k (Math/log b5)) (* b4 k (Math/log b5)) (* b6 k (Math/log b5)) (* b7 k (Math/log b5)) (* c (Math/log b5)) (- (* k (Math/log b5))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* b1 k (Math/log p5))) (- (* b2 k (Math/log p5))) (- (* b3 k (Math/log p5))) (- (* b4 k (Math/log p5))) (- (* b6 k (Math/log p5))) (- (* b7 k (Math/log p5))) (* k (Math/log p5)) (- (* c (Math/log p5))) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7))))
        x6 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* b1 k (Math/log b6)) (* b2 k (Math/log b6)) (* b3 k (Math/log b6)) (* b4 k (Math/log b6)) (* b5 k (Math/log b6)) (* b7 k (Math/log b6)) (* c (Math/log b6)) (- (* k (Math/log b6))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b7 k (Math/log b7))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* b1 k (Math/log p6))) (- (* b2 k (Math/log p6))) (- (* b3 k (Math/log p6))) (- (* b4 k (Math/log p6))) (- (* b5 k (Math/log p6))) (- (* b7 k (Math/log p6))) (* k (Math/log p6)) (- (* c (Math/log p6))) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b7 k (Math/log p7)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7))))
        x7 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* b1 k (Math/log b7)) (* b2 k (Math/log b7)) (* b3 k (Math/log b7)) (* b4 k (Math/log b7)) (* b5 k (Math/log b7)) (* b6 k (Math/log b7)) (* c (Math/log b7)) (- (* k (Math/log b7))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* b1 k (Math/log p7))) (- (* b2 k (Math/log p7))) (- (* b3 k (Math/log p7))) (- (* b4 k (Math/log p7))) (- (* b5 k (Math/log p7))) (- (* b6 k (Math/log p7))) (* k (Math/log p7)) (- (* c (Math/log p7))) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7))))
        effort (Math/pow Math/E (/ (+ (- (* (Math/log a))) (- (* b1 (Math/log b1))) (- (* b2 (Math/log b2))) (- (* b3 (Math/log b3))) (- (* b4 (Math/log b4))) (- (* b5 (Math/log b5))) (- (* b6 (Math/log b6))) (- (* b7 (Math/log b7))) (* b1 (Math/log p1)) (* b2 (Math/log p2)) (* b3 (Math/log p3)) (* b4 (Math/log p4)) (* b5 (Math/log p5)) (* b6 (Math/log p6)) (* b7 (Math/log p7)) (- (* b1 (Math/log λ))) (- (* b2 (Math/log λ))) (- (* b3 (Math/log λ))) (- (* b4 (Math/log λ))) (- (* b5 (Math/log λ))) (- (* b6 (Math/log λ))) (- (* b7 (Math/log λ))) (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log λ))) (- (* b6 k (Math/log λ))) (- (* b7 k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7))) (- (/ (* b1 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log λ))) (- (* b6 k (Math/log λ))) (- (* b7 k (Math/log λ))))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7)))) (- (/ (* b2 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log λ))) (- (* b6 k (Math/log λ))) (- (* b7 k (Math/log λ))))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7)))) (- (/ (* b3 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log λ))) (- (* b6 k (Math/log λ))) (- (* b7 k (Math/log λ))))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7)))) (- (/ (* b4 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log λ))) (- (* b6 k (Math/log λ))) (- (* b7 k (Math/log λ))))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7)))) (- (/ (* b5 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log λ))) (- (* b6 k (Math/log λ))) (- (* b7 k (Math/log λ))))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7)))) (- (/ (* b6 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log λ))) (- (* b6 k (Math/log λ))) (- (* b7 k (Math/log λ))))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7)))) (- (/ (* b7 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log λ))) (- (* b6 k (Math/log λ))) (- (* b7 k (Math/log λ))))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7))))) c))
        [input-qs nature-qs labor-qs pollutant-d] (assign-new-proposal p-i [x1 x2 x3 x4 x5 x6 x7])]
     {:output output
      :x1 x1
      :x2 x2
      :x3 x3
      :x4 x4
      :x5 x5
      :x6 x6
      :x7 x7
      :effort effort
      :input-quantities input-qs
      :nature-quantities nature-qs
      :labor-quantities labor-qs
      :pollutant-demand pollutant-d}))


(defn get-input-quantity [f ii [production-inputs input-quantities]]
  (->> ii
       first
       (.indexOf (f production-inputs))
       (nth input-quantities)))

; remove abs / replace with Math/abs ?
(defn get-deltas [J price-delta pdlist]
  (letfn [(abs [n] (max n (- n)))]
    (max 0.001 (min price-delta (abs (* price-delta (nth pdlist J)))))))


(defn update-surpluses-prices
  [type inputs prices wcs ccs natural-resources-supply labor-supply pollutant-supply price-delta pdlist]
  (loop [inputs inputs
         prices prices
         surpluses []
         J 0]
    (if (empty? inputs)
      {:prices prices :surpluses surpluses}
      (let [supply (condp = type
                     "final" (->> wcs
                                  (filter #(and (= 0 (% :industry))
                                                (= (first inputs)
                                                   (% :product))))
                                  (map :output)
                                  (reduce +))
                     "intermediate" (->> wcs
                                         (filter #(and (= 1 (% :industry))
                                                       (= (first inputs)
                                                          (% :product))))
                                         (map :output)
                                         (reduce +))
                     "nature" natural-resources-supply
                     "labor"  labor-supply
                     "public-goods" (->> wcs
                                         (filter #(= 2 (% :industry)))
                                         (map :output)
                                         (reduce +))
                     "pollutants" (/ (reduce + (mapv first (mapv :pollutant-supply ccs)))
                                       (count ccs)) )
            demand (condp = type
                     "final"  (->> ccs
                                   (mapv :final-demands)
                                   (mapv #(nth % (dec (first inputs))))
                                   (reduce +))                                 
                     "intermediate" (->> wcs
                                         (filter #(contains? #{0, 1} (:industry %)))
                                         (filter #(contains? (set (first (:production-inputs %)))
                                                             (first inputs)))
                                         (map (juxt :production-inputs :input-quantities))
                                         (map (partial get-input-quantity first inputs))
                                         (reduce +))
                     "nature" (->> wcs
                                   (filter #(contains? #{0, 1} (:industry %)))
                                   (filter #(contains? (set (second (:production-inputs %)))
                                                       (first inputs)))
                                   (map :nature-quantities)
                                   flatten
                                   (reduce +))
                     "labor" (->> wcs
                                  #_(filter #(contains? (set (last (:production-inputs %)))
                                                        (first inputs)))
                                  (filter #(contains? #{0, 1} (:industry %)))
                                  (map (juxt :production-inputs :labor-quantities))
                                  (map (partial get-input-quantity last inputs))
                                  (reduce +))
                     "public-goods" (/ (reduce + (mapv first (mapv :public-good-demands ccs)))
                                       (count ccs))
                     "pollutants" (->> wcs
                                       (map :pollutant-demand)
                                       flatten
                                       (apply +)))
            j-offset (condp = type
                       "final" 0
                       "intermediate" 4
                       "nature" 8
                       "labor" 9
                       "public-goods" 10
                       "pollutants" 11)
            surplus (- supply demand)
            delta (get-deltas (+ j-offset J) price-delta pdlist)
            new-delta (cond (<= delta 1)            delta
                            (= type "final")        delta
                            :else                   (last (take-while (partial < 1)
                                                                      (iterate #(/ % 2.0) delta))))
            new-price (cond (pos? surplus) (* (- 1 new-delta) (nth prices (dec (first inputs))))
                            (neg? surplus) (* (+ 1 new-delta) (nth prices (dec (first inputs))))
                            :else (nth prices (dec (first inputs))))]
        (recur (rest inputs)
               (assoc prices J new-price)
               (conj surpluses surplus)
               (inc J))))))


; Q: does lambda change?
(defn proposal [private-good-prices input-prices nature-prices labor-prices public-goods-prices pollutant-prices wc]
  (letfn [(count-inputs [w]
            ((comp count flatten :production-inputs) w))
          (get-input-prices [[indexes prices]]
            (map #(nth prices (dec %)) indexes))
          (get-lambda-o [w private-good-prices input-prices public-goods-prices]
            (let [industry (:industry w)
                  product (:product w)]
              (cond (= 0 industry) (nth private-good-prices (dec product))
                    (= 1 industry) (nth input-prices (dec product))
                    (= 2 industry) (nth public-goods-prices (dec product)))))]
    (let [prices-and-indexes (->> (vector input-prices nature-prices labor-prices pollutant-prices)
                                  (interleave (wc :production-inputs))
                                  (partition 2))
          input-count-r (count-inputs wc)
          a (wc :a)
          s (wc :s)
          c (wc :c)
          k (wc :du)
;          wcid (wc :id)
          ps (into [] (flatten (map get-input-prices prices-and-indexes)))
          b-input (wc :input-exponents)
          b-labor (wc :labor-exponents)
          b-nature (wc :nature-exponents)
          b-pollutant (wc :pollutant-exponents)
          b (concat b-input b-nature b-labor b-pollutant)
          λ (get-lambda-o wc private-good-prices input-prices public-goods-prices)
          p-i (wc :production-inputs)]
      (condp = input-count-r
        1 (merge wc (solution-1 a s c k ps b λ p-i))
        2 (merge wc (solution-2 a s c k ps b λ p-i))
        3 (merge wc (solution-3 a s c k ps b λ p-i))
        4 (merge wc (solution-4 a s c k ps b λ p-i))
        5 (merge wc (solution-5 a s c k ps b λ p-i))
        6 (merge wc (solution-6 a s c k ps b λ p-i))
        7 (merge wc (solution-7 a s c k ps b λ p-i))
        (str "unexpected input-count value: " input-count-r)))))


(defn mean [L]
  (/ (reduce + L) (count L)))


(defn update-price-deltas [supply-list demand-list surplus-list]
  (let [supply-list-means (map mean supply-list)
        demand-list-means (map mean demand-list)
        surplus-list-means (map mean surplus-list)
        averaged-s-and-d (->> (interleave supply-list-means
                                          demand-list-means)
                              (partition 2)
                              (map mean))]
    (->> (interleave surplus-list-means averaged-s-and-d)
         (partition 2)
         (mapv #(Math/abs (/ (first %) (last %)))))))


(defn update-pdlist [supply-list demand-list surplus-list]
  (let [averaged-s-and-d (->> (interleave (flatten supply-list)
                                          (flatten demand-list))
                              (partition 2)
                              (mapv mean))]
    (->> (interleave (flatten surplus-list) averaged-s-and-d)
         (partition 2)
         (mapv #(/ (first %) (last %))))))


(defn get-demand-list [t]
  (letfn [(merge-inputs-and-quantities [type ks vs]
            (map #(hash-map :type type :key (first %) :value (last %))
                 (partition 2 (interleave ks vs))))
          (get-inputs-and-quantities [m]
            [(merge-inputs-and-quantities :input-quantity
                                          (first (:production-inputs m))
                                          (:input-quantities m))
             (merge-inputs-and-quantities :nature-quantity
                                          (second (:production-inputs m))
                                          (:nature-quantities m))
             (merge-inputs-and-quantities :labor-quantity
                                          (nth (:production-inputs m) 2)
                                          (:labor-quantities m))
             (merge-inputs-and-quantities :pollutant-demand
                                          (last (:production-inputs m))
                                          (:pollutant-demand m))])
          (sum-input-quantities [qs pos type]
            (->> qs
                 (filter #(and (= pos (:key %)) (= type (:type %))))
                 (map :value)
                 (reduce +)))]
    (let [final-demands (->> t
                             :final-goods
                             (mapv (fn [i] (mapv #(nth (:final-demands %) (dec i)) (:ccs t))))
                             (mapv (partial reduce +)))
          all-quantities (->> t
                              :wcs
                              (filter #(not= 2 (% :industry)))
                              (map get-inputs-and-quantities)
                              flatten)
          input-quantity [(sum-input-quantities all-quantities 1 :input-quantity)
                          (sum-input-quantities all-quantities 2 :input-quantity)
                          (sum-input-quantities all-quantities 3 :input-quantity)
                          (sum-input-quantities all-quantities 4 :input-quantity)]
          public-good-demands
                     (mapv (fn [public-good]
                             (mean (map #(nth (:public-good-demands %) (dec public-good))
                                         (t :ccs))))
                           (t :public-goods-types))
          pollutant-demands (->> t
                                 :wcs
                                 (map :pollutant-demand)
                                 flatten
                                 (apply +)
                                 vector)]
      [final-demands
       input-quantity
       [(sum-input-quantities all-quantities 1 :nature-quantity)]
       [(sum-input-quantities all-quantities 1 :labor-quantity)]
       public-good-demands
       pollutant-demands])))


(defn get-supply-list [t]
  
  (letfn [(get-producers [t industry product]
            (->> t
                 :wcs
                 (filter #(and (= industry (% :industry))
                               (= product (% :product))))
                 (map :output)
                 flatten
                 (reduce +)))]
   (let [final-goods (:final-goods t)
         final-producers (mapv (partial get-producers t 0) final-goods)
         intermediate-inputs (:intermediate-inputs t)
         input-producers (mapv (partial get-producers t 1) intermediate-inputs)
         natural-resources-supply (vector (:natural-resources-supply t))
         labor-supply (vector (:labor-supply t))
         public-good-supply (->> t
                                :wcs
                                (filter #(= 2 (% :industry)))
                                (map :output)
                                (reduce +)
                                vector)
         pollutant-supply (vector (/ (reduce + (mapv first (mapv :pollutant-supply (:ccs t))))
                               (count (:ccs t)))) ]
     (vector final-producers input-producers natural-resources-supply labor-supply public-good-supply pollutant-supply))))


(defn iterate-plan [t]
  (let [t2 (assoc t :ccs (map (partial consume (t :final-goods) (t :private-good-prices) (t :public-goods-types) (t :public-goods-prices) (t :pollutant-types) (t :pollutant-prices) (count (t :ccs)))
                              (t :ccs))
                    :wcs (map (partial proposal (t :private-good-prices) (t :intermediate-good-prices) (t :nature-prices) (t :labor-prices) (t :public-goods-prices) (t :pollutant-prices))
                              (t :wcs)))
        {private-good-prices :prices, final-surpluses :surpluses} (update-surpluses-prices "final" (t2 :final-goods) (t2 :private-good-prices) (t2 :wcs) (t2 :ccs) (t2 :natural-resources-supply) (t2 :labor-supply) (t2 :pollutant-supply) (t2 :price-delta) (t2 :pdlist))
        {input-prices :prices, input-surpluses :surpluses} (update-surpluses-prices "intermediate" (t2 :intermediate-inputs) (t2 :intermediate-good-prices) (t2 :wcs) (t2 :ccs) (t2 :natural-resources-supply) (t2 :labor-supply) (t2 :pollutant-supply) (t2 :price-delta) (t2 :pdlist))
        {nature-prices :prices, nature-surpluses :surpluses} (update-surpluses-prices "nature" (t2 :nature-types) (t2 :nature-prices) (t2 :wcs) (t2 :ccs) (t2 :natural-resources-supply) (t2 :labor-supply) (t2 :pollutant-supply) (t2 :price-delta) (t2 :pdlist))
        {labor-prices :prices, labor-surpluses :surpluses} (update-surpluses-prices "labor" (t2 :labor-types) (t2 :labor-prices) (t2 :wcs) (t2 :ccs) (t2 :natural-resources-supply) (t2 :labor-supply) (t2 :pollutant-supply) (t2 :price-delta) (t2 :pdlist))
        {public-good-prices :prices, public-good-surpluses :surpluses} (update-surpluses-prices "public-goods" (t2 :public-goods-types) (t2 :public-goods-prices) (t2 :wcs) (t2 :ccs) (t2 :natural-resources-supply) (t2 :labor-supply) (t2 :pollutant-supply) (t2 :price-delta) (t2 :pdlist))
        {pollutant-prices :prices, pollutant-surpluses :surpluses} (update-surpluses-prices "pollutants" (t2 :pollutant-types) (t2 :pollutant-prices) (t2 :wcs) (t2 :ccs) (t2 :natural-resources-supply) (t2 :labor-supply) (t2 :pollutant-supply) (t2 :price-delta) (t2 :pdlist))
        surplus-list (vector final-surpluses input-surpluses nature-surpluses labor-surpluses public-good-surpluses pollutant-surpluses)
        supply-list (get-supply-list t2)
        demand-list (get-demand-list t2)
        new-price-deltas (update-price-deltas supply-list demand-list surplus-list)
        new-pdlist (update-pdlist supply-list demand-list surplus-list)
        iteration (inc (:iteration t2))]
    (assoc t2 :private-good-prices private-good-prices
              :final-surpluses final-surpluses
              :intermediate-good-prices input-prices
              :input-surpluses input-surpluses
              :nature-prices nature-prices
              :nature-surpluses nature-surpluses
              :labor-prices labor-prices
              :labor-surpluses labor-surpluses
              :public-good-prices public-good-prices
              :public-good-surpluses public-good-surpluses 
              :pollutant-prices pollutant-prices
              :pollutant-surpluses pollutant-surpluses
              :demand-list demand-list
              :surplus-list surplus-list
              :supply-list supply-list
              :price-deltas new-price-deltas
              :pdlist new-pdlist
              :iteration iteration)))


(defn check-surpluses [t]
  (letfn [(check-producers [surpluses producers inputs]
            (some #(> (Math/abs (nth surpluses (dec %)))
                      (* (:surplus-threshold t)
                         (reduce + (map :output producers))))
                  inputs))
          (check-supplies [surpluses supply inputs surplus-threshold]
            (some #(> (Math/abs (nth surpluses (dec %)))
                      (* surplus-threshold supply))
                  inputs))
          (get-producers [wcs industry]
            (filter #(= industry (% :industry))) wcs)]
    (let [surplus-threshold (:surplus-threshold t)
          final-producers (get-producers (:wcs t) 0)
          input-producers (get-producers (:wcs t) 1)
          public-good-producers (get-producers (:wcs t) 2)
          final-goods-check (check-producers (:final-surpluses t) final-producers (:final-goods t))
          im-goods-check (check-producers (:input-surpluses t) input-producers (:intermediate-inputs t))
          nature-check (check-supplies (:nature-surpluses t) (:natural-resources-supply t) (:nature-types t) surplus-threshold)
          labor-check (check-supplies (:labor-surplus t) (:labor-supply t) (:labor-types t) surplus-threshold)
          public-goods-check (check-producers (:public-good-surpluses t) public-good-producers (:public-goods-types t))
          pollutant-check (check-supplies (:pollutant-surpluses t) (:pollutant-supply t) (:pollutant-types t) surplus-threshold)]
;      [final-goods-check im-goods-check nature-check labor-check public-goods-check pollutant-check]
      (every? nil? [final-goods-check im-goods-check nature-check labor-check public-goods-check pollutant-check])
      )))


(defn total-surplus [surplus-list]
  (->> surplus-list
       flatten
       (map Math/abs)
       (reduce +)))


(defn adjust-delta [price-delta raise-or-lower]
  (let [price-delta-adjustment-fn
         (if (= raise-or-lower "raise") + -)
        min-or-max-fn
         (if (= raise-or-lower "raise") min max)
        delta-delay
         (if (= raise-or-lower "raise") 10 5)
        pd (->> [0.1 (price-delta-adjustment-fn price-delta 0.01)]
                (apply min-or-max-fn)
                (gstring/format "%.2f"))]
    {:price-delta pd
     :delta-delay delta-delay}))


(defn rest-of-to-do [t]
  (let [surplus-total (total-surplus (:surplus-list t))
        threshold (check-surpluses t)
        delta-delay (:delta-delay t)
        price-delta (:price-delta t)
        {price-delta :price-delta
         delta-delay :delta-delay} (if (and (< surplus-total 100)
                                              (<= delta-delay 0))
                                     (adjust-delta price-delta "lower")
                                     {:price-delta price-delta
                                      :delta-delay delta-delay})
        {price-delta :price-delta
         delta-delay :delta-delay} (if (and (> surplus-total 100000)
                                              (<= delta-delay 0))
                                     (adjust-delta price-delta "raise")
                                     {:price-delta price-delta
                                      :delta-delay delta-delay})
        delta-delay (if (pos? delta-delay)
                      (dec delta-delay) delta-delay)
        t-updated (assoc t :threshold-met threshold
                  :price-delta price-delta
                  :delta-delay delta-delay)]
    t-updated))

(defn proceed [t]
  (let [t-plus (iterate-plan t)]
    (rest-of-to-do t-plus)))

;; -------------------------
;; Views-

#_(defn setup-ex001-button-pg []
  [:input {:type "button" :value "Setup Ex001 w/Public Goods"
           :on-click #(swap! globals setup globals "ex001pg")}])

(defn setup-1dot3 []
  [:input {:type "button" :value "Setup"
           :on-click #(swap! globals setup globals "ex1dot3")}])

(defn iterate-button []
  [:input {:type "button" :value "Iterate and check"
           :on-click #(swap! globals proceed globals)}])

(defn show-globals []
    (let [keys-to-show [:private-good-prices :threshold-met :iteration :price-deltas :intermediate-good-prices :nature-prices :labor-prices :public-good-prices :price-delta :price-deltas :pdlist :pollutant-prices :surplus-list :supply-list :demand-list]
        ]
     [:div " "
           (setup-1dot3)
           "  "
           (iterate-button)
           "  "
           [:p]
           [:table
            (map (fn [x] [:tr [:td (str (first x))]
                          [:td (str (second x))]])
                 (sort (select-keys @globals keys-to-show))
;                   (sort @globals)
                 )]
            [:p]
     ]))


(defn home-page []
  [:div [:h2 "Welcome to pequod-cljs"]
   [:div [:a {:href "/about"} "go to about"]
    [:p]
    (show-globals)]])

(defn about-page []
  [:div [:h2 "About pequod-cljs"]
   [:div [:a {:href "/"} "go to the other page"]]])

;; -------------------------
;; Routes

(defonce page (atom #'home-page))

(defn current-page []
  [:div [@page]])

(secretary/defroute "/" []
  (reset! page #'home-page))

(secretary/defroute "/about" []
  (reset! page #'about-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
