(ns pequod-cljs.core
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]
              [pequod-cljs.ex001data :as ex001]))

(def globals
  (atom {:init-final-price        100
         :init-intermediate-price 100
         :init-labor-price        150
         :init-nature-price       150
         :finals                    4
         :inputs                    4
         :resources                 1
         :labors                    1
         :final-prices             []
         :input-prices             []
         :labor-prices             []
         :nature-prices            []
         :old-final-prices         []
         :old-input-prices         []
         :old-nature-prices        []
         :old-labor-prices         []
         :final-surpluses          []
         :input-surpluses          []
         :nature-surpluses         []
         :labor-surpluses          []
         :threshold-met            false
         :pdlist                   []
         :delta-delay              0
         :price-deltas             []
         :price-delta              0
         :lorenz-gini-tuple        [] 
           ; both lorenz-points and gini-index-reserve
         :wcs                      []
         :ccs                      []
         :iteration                0
         :natural-resources-supply 0
         :labor-supply             0}))

(defn standardize-prices [t]
  (assoc t
    :init-final-price 80
    :init-intermediate-price 80
    :init-nature-price 80
    :init-labor-price 80))

(defn randomize-prices [t]
  (assoc t
    :init-final-price (+ 40 (rand-nth (range 0 40)))
    :init-intermediate-price (+ 40 (rand-nth (range 0 40)))
    :init-nature-price (+ 30 (rand-nth (range 0 30)))
    :init-labor-price (+ 30 (rand-nth (range 0 30)))))

(defn initialize-prices [t]
  (let [finals (t :finals)
        inputs (t :inputs)
        resources (t :resources)
        labor (t :labors)]
    (assoc t
      :final-prices (vec (repeat finals (t :init-final-price)))
      :input-prices (vec (repeat inputs (t :init-intermediate-price)))
      :nature-prices (vec (repeat resources (t :init-nature-price)))
      :labor-prices (vec (repeat labor (t :init-labor-price)))
      :price-deltas (vec (repeat 4 0.05))
      :pdlist (vec (repeat (+ finals inputs resources labor) 0.05)))))

(defn create-ccs [consumer-councils workers-per-council finals]
  (let [effort 1
        cz (/ 0.5 finals)
        utility-exponents (->> #(+ cz (rand cz))
                               repeatedly
                               (take (* finals consumer-councils))
                               (partition finals))]
    (map #(hash-map :num-workers workers-per-council
                    :effort effort
                    :income (* 500 effort workers-per-council)
                    :cy (+ 6 (rand 9))
                    :utility-exponents (vec %)
                    :final-demands (vec (repeat 5 0)))
         utility-exponents)))

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
                            (let [xz (/ 0.2 (count (first production-inputs)))]
                              (vec (take (count (first production-inputs))
                                     (repeatedly #(+ xz (rand xz)))))))
          nature-exponents (let [rz (/ 0.2 (count (second production-inputs)))]
                             (vec (take (count (second production-inputs))
                                    (repeatedly #(+ 0.05 rz (rand rz))))))
          labor-exponents (let [lz (/ 0.2 (count (last production-inputs)))]
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

(defn calculate-consumer-utility [cc]
  (let [final-demands (:final-demands cc)
        utility-exponents (:utility-exponents cc)
        cy (:cy cc)]
    (->> (interleave final-demands utility-exponents)
         (partition 2)
         (map #(Math/pow (first %) (last %)))
         (reduce *)
         (* cy))))

(defn update-lorenz-and-gini [ccs]
  (let [num-people (count ccs)
        sorted-wealths (mapv calculate-consumer-utility ccs)
        total-wealth (reduce + sorted-wealths)]
    (if (pos? total-wealth)
      (loop [wealth-sum-so-far 0
             index 0
             gini-index-reserve 0
             lorenz-points []
             num-people-counter 0]
        (if (= num-people num-people-counter)
          [lorenz-points gini-index-reserve]
          (recur (+ wealth-sum-so-far (get sorted-wealths index))
                 (inc index)
                 (+ gini-index-reserve
                    (/ index num-people)
                    (- (/ wealth-sum-so-far total-wealth)))
                 (cons (* (/ wealth-sum-so-far total-wealth) 100) lorenz-points)
                 (inc num-people-counter))))
      [[] 0])))

(defn setup [t button-type]
  (let [intermediate-inputs (vec (range 1 (inc (t :inputs))))
        nature-types (vec (range 1 (inc (t :resources))))
        labor-types (vec (range 1 (inc (t :labors))))
        final-goods (vec (range 1 (inc (t :finals))))
        ccs (create-ccs 100 10 4)]
    (-> t
        initialize-prices
        (assoc :price-delta 0.1
               :delta-delay 5
               :natural-resources-supply 1000
               :labor-supply 1000
               :final-goods final-goods
               :intermediate-inputs intermediate-inputs
               :nature-types nature-types
               :labor-types labor-types
               :ccs ex001/ccs
                    #_(if (= button-type "random") ccs ex001/ccs)
               :wcs ex001/wcs
               #_(if (= button-type "ex001") ex001/wcs
                   (->> (merge (create-wcs 80 final-goods 0)
                               (create-wcs 80 intermediate-inputs 1))
                        flatten
                        (map (partial continue-setup-wcs
                                      intermediate-inputs
                                      nature-types
                                      labor-types))))
               :lorenz-gini-tuple (update-lorenz-and-gini ccs)))))
; TODO Get buttons to work as intended

(defn consume [final-goods final-prices cc]
  (let [utility-exponents (cc :utility-exponents)
        income (cc :income) 
        final-demands (map (fn [final-good]
                             (/ (* income (nth utility-exponents (dec final-good)))
                                (* (apply + utility-exponents)
                                   (nth final-prices (dec final-good)))))
                      final-goods)]
    (assoc cc :final-demands final-demands)))


(defn assign-new-proposal [production-inputs xs]
  (let [num-input-quantities (count (first production-inputs))
        num-nature-quantities (count (second production-inputs))
        input-quantities (take num-input-quantities xs)
        nature-quantities (->> xs
                               (drop num-input-quantities)
                               (take num-nature-quantities))
        labor-quantities (drop (+ num-input-quantities num-nature-quantities)
                               xs)]
    [input-quantities nature-quantities labor-quantities]))


(defn solution-1 [a s c k ps b λ p-i]
  (let [b1 (first b)
        p1 (first (flatten ps))
        output (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ)))) (+ c (- k) (* k b1))))
        x1 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* c (Math/log b1)) (- (* k (Math/log b1))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* c (Math/log p1))) (* k (Math/log p1)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1))))
        effort (Math/pow Math/E (/ (+ (- (* (Math/log a))) (- (* b1 (Math/log b1))) (- (* (Math/log c))) (* b1 (Math/log c)) (* (Math/log k)) (- (* b1 (Math/log k))) (* b1 (Math/log p1)) (* (Math/log s)) (- (* b1 (Math/log s))) (- (* (Math/log λ)))) (+ c (- k) (* k b1))))
        [input-qs nature-qs labor-qs] (assign-new-proposal p-i [x1])]
    {:output output
     :x1 x1
     :effort effort
     :input-quantities input-qs
     :nature-quantities nature-qs
     :labor-quantities labor-qs}))


(defn solution-2 [a s c k ps b λ p-i]
  (let [[b1 b2] b
        [p1 p2] (flatten ps)
        output (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2))))
        x1 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* c (Math/log b1)) (- (* k (Math/log b1))) (* b2 k (Math/log b1)) (- (* b2 k (Math/log b2))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* c (Math/log p1))) (* k (Math/log p1)) (- (* b2 k (Math/log p1))) (* b2 k (Math/log p2)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2))))
        x2 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (* c (Math/log b2)) (- (* k (Math/log b2))) (* b1 k (Math/log b2)) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (- (* c (Math/log p2))) (* k (Math/log p2)) (- (* b1 k (Math/log p2))) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2))))
        effort (Math/pow Math/E (/ (+ (- (* (Math/log a))) (- (* b1 (Math/log b1))) (- (* b2 (Math/log b2))) (- (* (Math/log c))) (* b1 (Math/log c)) (* b2 (Math/log c)) (* (Math/log k)) (- (* b1 (Math/log k))) (- (* b2 (Math/log k))) (* b1 (Math/log p1)) (* b2 (Math/log p2)) (* (Math/log s)) (- (* b1 (Math/log s))) (- (* b2 (Math/log s))) (- (* (Math/log λ)))) (+ c (- k) (* k b1) (* k b2))))
        [input-qs nature-qs labor-qs] (assign-new-proposal p-i [x1 x2])]
    {:output output
     :x1 x1
     :x2 x2
     :effort effort
     :input-quantities input-qs
     :nature-quantities nature-qs
     :labor-quantities labor-qs}))


(defn solution-3 [a s c k ps b λ p-i]
  (let [[b1 b2 b3] b
        [p1 p2 p3] (flatten ps)
        output (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3))))
        x1 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* c (Math/log b1)) (- (* k (Math/log b1))) (* b2 k (Math/log b1)) (* b3 k (Math/log b1)) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* c (Math/log p1))) (* k (Math/log p1)) (- (* b2 k (Math/log p1))) (- (* b3 k (Math/log p1))) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3))))
        x2 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (* c (Math/log b2)) (- (* k (Math/log b2))) (* b1 k (Math/log b2)) (* b3 k (Math/log b2)) (- (* b3 k (Math/log b3))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (- (* c (Math/log p2))) (* k (Math/log p2)) (- (* b1 k (Math/log p2))) (- (* b3 k (Math/log p2))) (* b3 k (Math/log p3)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3))))
        x3 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (* c (Math/log b3)) (- (* k (Math/log b3))) (* b1 k (Math/log b3)) (* b2 k (Math/log b3)) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (- (* c (Math/log p3))) (* k (Math/log p3)) (- (* b1 k (Math/log p3))) (- (* b2 k (Math/log p3))) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3))))
        effort (Math/pow Math/E (/ (+ (- (* (Math/log a))) (- (* b1 (Math/log b1))) (- (* b2 (Math/log b2))) (- (* b3 (Math/log b3))) (* b1 (Math/log p1)) (* b2 (Math/log p2)) (* b3 (Math/log p3)) (- (* b1 (Math/log λ))) (- (* b2 (Math/log λ))) (- (* b3 (Math/log λ))) (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3))) (- (/ (* b1 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ)))))  (+ c (- k) (* k b1) (* k b2) (* k b3)))) (- (/ (* b2 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))))) (+ c (- k) (* k b1) (* k b2) (* k b3)))) (- (/ (* b3 (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))))) (+ c (- k) (* k b1) (* k b2) (* k b3))))) c))
        [input-qs nature-qs labor-qs] (assign-new-proposal p-i [x1 x2 x3])]
    {:output output
     :x1 x1
     :x2 x2
     :x3 x3
     :effort effort
     :input-quantities input-qs
     :nature-quantities nature-qs
     :labor-quantities labor-qs}))

(defn solution-4 [a s c k ps b λ p-i]
  (let [[b1 b2 b3 b4] b
        [p1 p2 p3 p4] (flatten ps)
        output (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4))))
        x1 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* c (Math/log b1)) (- (* k (Math/log b1))) (* b2 k (Math/log b1)) (* b3 k (Math/log b1)) (* b4 k (Math/log b1)) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* c (Math/log p1))) (* k (Math/log p1)) (- (* b2 k (Math/log p1))) (- (* b3 k (Math/log p1))) (- (* b4 k (Math/log p1))) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4))))
        x2 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (* c (Math/log b2)) (- (* k (Math/log b2))) (* b1 k (Math/log b2)) (* b3 k (Math/log b2)) (* b4 k (Math/log b2)) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (- (* c (Math/log p2))) (* k (Math/log p2)) (- (* b1 k (Math/log p2))) (- (* b3 k (Math/log p2))) (- (* b4 k (Math/log p2))) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4))))
        x3 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (* c (Math/log b3)) (- (* k (Math/log b3))) (* b1 k (Math/log b3)) (* b2 k (Math/log b3)) (* b4 k (Math/log b3)) (- (* b4 k (Math/log b4))) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (- (* c (Math/log p3))) (* k (Math/log p3)) (- (* b1 k (Math/log p3))) (- (* b2 k (Math/log p3))) (- (* b4 k (Math/log p3))) (* b4 k (Math/log p4)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4))))
        x4 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (* c (Math/log b4)) (- (* k (Math/log b4))) (* b1 k (Math/log b4)) (* b2 k (Math/log b4)) (* b3 k (Math/log b4)) (- (* c (Math/log c))) (* c (Math/log k)) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (- (* c (Math/log p4))) (* k (Math/log p4)) (- (* b1 k (Math/log p4))) (- (* b2 k (Math/log p4))) (- (* b3 k (Math/log p4))) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4))))
     effort (Math/pow Math/E (/ (+ (- (* (Math/log a))) (- (* b1 (Math/log b1))) (- (* b2 (Math/log b2))) (- (* b3 (Math/log b3))) (- (* b4 (Math/log b4))) (- (* (Math/log c))) (* b1 (Math/log c)) (* b2 (Math/log c)) (* b3 (Math/log c)) (* b4 (Math/log c)) (* (Math/log k)) (- (* b1 (Math/log k))) (- (* b2 (Math/log k))) (- (* b3 (Math/log k))) (- (* b4 (Math/log k))) (* b1 (Math/log p1)) (* b2 (Math/log p2)) (* b3 (Math/log p3)) (* b4 (Math/log p4)) (* (Math/log s)) (- (* b1 (Math/log s))) (- (* b2 (Math/log s))) (- (* b3 (Math/log s))) (- (* b4 (Math/log s))) (- (* (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4))))
        [input-qs nature-qs labor-qs] (assign-new-proposal p-i [x1 x2 x3 x4])]
    {:output output
     :x1 x1
     :x2 x2
     :x3 x3
     :x4 x4
     :effort effort
     :input-quantities input-qs
     :nature-quantities nature-qs
     :labor-quantities labor-qs}))

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
        [input-qs nature-qs labor-qs] (assign-new-proposal p-i [x1 x2 x3 x4 x5])]
     {:output output
      :x1 x1
      :x2 x2
      :x3 x3
      :x4 x4
      :x5 x5
      :effort effort
      :input-quantities input-qs
      :nature-quantities nature-qs
      :labor-quantities labor-qs}))


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
        [input-qs nature-qs labor-qs] (assign-new-proposal p-i [x1 x2 x3 x4 x5 x6])]
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
      :labor-quantities labor-qs}))


(defn get-input-quantity [f ii [production-inputs input-quantities]]
  (->> ii
       first
       (.indexOf (f production-inputs))
       (nth input-quantities)))


(defn get-deltas [J price-delta pdlist]
  (letfn [(abs [n] (max n (- n)))]
    (max 0.001 (min price-delta (abs (* price-delta (nth pdlist J)))))))


(defn update-surpluses-prices
  [type inputs prices wcs ccs natural-resources-supply labor-supply price-delta pdlist]
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
                     "labor"  labor-supply)
            demand (condp = type
                     "final" (->> ccs
                                  (map :final-demands)
                                  (map #(nth % (dec (first inputs))))
                                  (reduce +))
                     "intermediate" (->> wcs
                                         (filter #(contains? (set (first (:production-inputs %)))
                                                             (first inputs)))
                                         (map (juxt :production-inputs :input-quantities))
                                         (map (partial get-input-quantity first inputs))
                                         (reduce +))
                     "nature" (->> wcs
                                   (filter #(contains? (set (second (:production-inputs %)))
                                                       (first inputs)))
                                   (map (juxt :production-inputs :nature-quantities))
                                   (map (partial get-input-quantity second inputs))
                                   (reduce +))
                     "labor" (->> wcs
                                  (filter #(contains? (set (last (:production-inputs %)))
                                                      (first inputs)))
                                  (map (juxt :production-inputs :labor-quantities))
                                  (map (partial get-input-quantity last inputs))
                                  (reduce +)))
            surplus (- supply demand)
            delta (get-deltas J price-delta pdlist)
            new-delta (if (or (<= delta 1) (= type "final")) delta
                          (last (take-while (partial < 1)
                                            (iterate #(/ % 2.0) delta))))
            new-price (cond (pos? surplus) (* (- 1 new-delta) (nth prices (dec (first inputs))))
                            (neg? surplus) (* (+ 1 new-delta) (nth prices (dec (first inputs))))
                            :else (nth prices (dec (first inputs))))]
        (recur (rest inputs)
               (assoc prices J new-price)
               (conj surpluses surplus)
               (inc J))))))


(defn proposal [input-prices nature-prices labor-prices wc]
  (letfn [(count-inputs [w]
            ((comp count flatten :production-inputs) w))
          (get-input-prices [[indexes prices]]
            (map #(nth prices (dec %)) indexes))]
    (let [prices-and-indexes (->> (vector input-prices nature-prices labor-prices)
                                  (interleave (wc :production-inputs))
                                  (partition 2))
          input-count-r (count-inputs wc)
          a (wc :a)
          s (wc :s)
          c (wc :c)
          k (wc :du)
          ps (flatten (map get-input-prices prices-and-indexes))
          b-input (wc :input-exponents)
          b-labor (wc :labor-exponents)
          b-nature (wc :nature-exponents)
          b (concat b-input b-nature b-labor)
          λ (first ps)
          p-i (wc :production-inputs)]
      (condp = input-count-r
        1 (merge wc (solution-1 a s c k ps b λ p-i))
        2 (merge wc (solution-2 a s c k ps b λ p-i))
        3 (merge wc (solution-3 a s c k ps b λ p-i))
        4 (merge wc (solution-4 a s c k ps b λ p-i))
        5 (merge wc (solution-5 a s c k ps b λ p-i))
        6 (merge wc (solution-6 a s c k ps b λ p-i))
        (str "unexpected input-count value: " input-count-r)))))


(defn price-change [supply-list demand-list surplus-list i]
  (letfn [(mean [L] (/ (reduce + L) (count L)))]
    (let [supply-list-means (map mean supply-list)
          demand-list-means (map mean demand-list)
          averaged-s-and-d (->> (interleave supply-list-means
                                            demand-list-means)
                                (partition 2)
                                (map #(/ (+ %1 %2) 2)))]
      (->> (interleave (map mean surplus-list) averaged-s-and-d)
           (partition 2)
           (map #(/ %1 %2))
           #(get % i)
           Math/abs))))


(defn get-demand-list [t]
  (letfn [(get-demand [func quant] (->> t
                                        quant
                                        (filter #(contains? (set (func (:production-inputs %))) (:wcs t)))
                                        (mapv (juxt :production-inputs :input-quantities))
                                        (mapv (partial get-input-quantity func (quant t)))
                                        (mapv (partial reduce +))))]
    (let [final-demands (->> t
                             :final-goods
                             (mapv (fn [i] (mapv #(nth (:final-demands %) (dec i)) (:ccs t))))
                             (mapv (partial reduce +)))
          input-quantity (get-demand first :intermediate-inputs)
          nature-quantity (get-demand second :nature-types)
          labor-quantity (get-demand last :labor-types)]
     [final-demands input-quantity nature-quantity labor-quantity])))


(defn get-supply-list [t]
  (letfn [(get-producers [t industry product]
            (->> t
                 :wcs
                 (filter #(and (= industry (% :industry))
                               (= product (% :product))))
                 (map :output)
                 (reduce +)))]
   (let [final-goods (:final-goods t)
         final-producers (mapv (partial get-producers t 0) final-goods)
         intermediate-inputs (:intermediate-inputs t)
         input-producers (mapv (partial get-producers t 1) intermediate-inputs)
         natural-resources-supply (vector (:natural-resources-supply t))
         labor-supply (vector (:labor-supply t))]
     (vector final-producers input-producers natural-resources-supply labor-supply))))


(defn iterate-plan [t]
  (let [t2 (assoc t :ccs (map (partial consume (t :final-goods) (t :final-prices))
                              (t :ccs))
                    :wcs (map (partial proposal (t :input-prices) (t :nature-prices) (t :labor-prices))
                              (t :wcs)))
        {final-prices :prices, final-surpluses :surpluses} (update-surpluses-prices "final" (t2 :final-goods) (t2 :final-prices) (t2 :wcs) (t2 :ccs) (t2 :natural-resources-supply) (t2 :labor-supply) (t2 :price-delta) (t2 :pdlist))
        {input-prices :prices, input-surpluses :surpluses} (update-surpluses-prices "intermediate" (t2 :intermediate-inputs) (t2 :input-prices) (t2 :wcs) (t2 :ccs) (t2 :natural-resources-supply) (t2 :labor-supply) (t2 :price-delta) (t2 :pdlist))
        {nature-prices :prices, nature-surpluses :surpluses} (update-surpluses-prices "nature" (t2 :nature-types) (t2 :nature-prices) (t2 :wcs) (t2 :ccs) (t2 :natural-resources-supply) (t2 :labor-supply) (t2 :price-delta) (t2 :pdlist))
        {labor-prices :prices, labor-surpluses :surpluses} (update-surpluses-prices "labor" (t2 :labor-types) (t2 :labor-prices) (t2 :wcs) (t2 :ccs) (t2 :natural-resources-supply) (t2 :labor-supply) (t2 :price-delta) (t2 :pdlist))
        surplus-list (vector final-surpluses input-surpluses nature-surpluses labor-surpluses)
        supply-list (get-supply-list t2)
        demand-list (get-demand-list t2)]
    (assoc t2 :final-prices final-prices
              :final-surpluses final-surpluses
              :input-prices input-prices
              :input-surpluses input-surpluses
              :nature-prices nature-prices
              :nature-surpluses nature-surpluses
              :labor-prices labor-prices
              :labor-surpluses labor-surpluses
              :demand-list demand-list
      ;        :pdlist (mapv (partial price-change supply-list demand-list surplus-list) (range 3))
              )))

;; -------------------------
;; Views-

(defn setup-random-button []
  [:input {:type "button" :value "Setup Random"
           :on-click #(swap! globals setup globals "random")}])

(defn setup-ex001-button []
  [:input {:type "button" :value "Setup Ex001"
           :on-click #(swap! globals setup globals "ex001")}])

(defn iterate-button []
  [:input {:type "button" :value "Iterate"
           :on-click #(swap! globals iterate-plan globals)}])

(defn show-globals []
  [:div " "
    (setup-random-button)
    "  "
    (setup-ex001-button)
    "  "
    (iterate-button)
    [:p]
    [:table
     (map (fn [x] [:tr [:td (str (first x))] 
                   [:td (str (second x))
                        #_(if (= ":wcs" (str (first x)))
                          (str (examine (second x)))
                          (str (second x)))]]) 
          (sort @globals))]
    [:p]])

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
