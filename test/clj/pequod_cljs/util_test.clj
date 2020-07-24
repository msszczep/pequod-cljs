(ns pequod-cljs.util-test
  (:require [clojure.test :refer [deftest is]]
            [pequod-cljs.util :as u]))

(deftest initialize-prices
  (let [t {:private-goods 10
           :intermediate-inputs 10
           :resources 10
           :labors 10
           :public-goods 10
           :init-private-good-price 700
           :init-intermediate-price 700
           :init-labor-price        700
           :init-nature-price       700
           :init-public-good-price  700}]
    (is (= (u/initialize-prices t)
           {:init-nature-price 700,
           :private-good-prices
           [700 700 700 700 700 700 700 700 700 700],
           :intermediate-good-prices
           [700 700 700 700 700 700 700 700 700 700],
           :labors 10,
           :labor-prices [700 700 700 700 700 700 700 700 700 700],
           :private-goods 10,
           :public-good-prices
           [700 700 700 700 700 700 700 700 700 700],
           :init-private-good-price 700,
           :pdlist
           [0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25
            0.25],
           :init-intermediate-price 700,
           :init-public-good-price 700,
           :public-goods 10,
           :price-deltas
           [0.05 0.05 0.05 0.05 0.05 0.05 0.05 0.05 0.05 0.05],
           :nature-prices [700 700 700 700 700 700 700 700 700 700],
           :resources 10,
           :intermediate-inputs 10,
           :init-labor-price 700}))))

(deftest add-ids
  (is (= [] (u/add-ids [])))
  (is (= [{:id 1}] (u/add-ids [{}])))
  (is (= [{:id 1} {:id 2}]
         (u/add-ids [{} {}]))))

(deftest augment-exponents
  (let [wc [0.1 0.2 0.3]
        wc-augmented (u/augment-exponents :wc wc)
        cc [0.1 0.2 0.3]
        cc-augmented (u/augment-exponents :cc cc)]
    (is (and (<= 0.1 (first wc-augmented))
             (>= 0.10400000000000001 (first wc-augmented))))
    (is (and (<= 0.2 (second wc-augmented))
             (>= 0.20400000000000001 (second wc-augmented))))
    (is (and (<= 0.3 (last wc-augmented))
             (>= 0.30400000000000001 (last wc-augmented))))
    (is (and (<= 0.098 (first cc-augmented))
             (>= 0.10200000000000001 (first cc-augmented))))
    (is (and (<= 0.198 (second cc-augmented))
             (>= 0.20200000000000001 (second cc-augmented))))
    (is (and (<= 0.298 (last cc-augmented))
             (>= 0.30200000000000001 (last cc-augmented))))))

(deftest augment-wc
  (let [wc {:input-exponents [0.1 0.2 0.3]
            :nature-exponents [0.4 0.5 0.6]
            :labor-exponents [0.7 0.8 0.9]}
        wc-augmented (u/augment-wc wc)]
    (is (= 3 (count (keys wc-augmented))))
    (is (= 3 (count (:input-exponents wc-augmented))))
    (is (= 3 (count (:nature-exponents wc-augmented))))
    (is (= 3 (count (:labor-exponents wc-augmented))))))

(deftest augment-cc
  (let [cc {:utility-exponents [0.1 0.2 0.3]
            :public-good-exponents [0.4 0.5 0.6]}
        cc-augmented (u/augment-cc cc)]
    (is (= 2 (count (keys cc-augmented))))
    (is (= 3 (count (:utility-exponents cc-augmented))))
    (is (= 3 (count (:public-good-exponents cc-augmented))))))

(deftest augmented-reset
  (let [t {:iteration 12
           :ccs [{:utility-exponents [0.1 0.2 0.3]
                  :public-good-exponents [0.4 0.5 0.6]}]
           :wcs [{:input-exponents [0.1 0.2 0.3]
                  :nature-exponents [0.4 0.5 0.6]
                  :labor-exponents [0.7 0.8 0.9]}]
           :supply-list [1 2 3]
           :private-good-prices [4 5 6]
           :public-good-prices [7 8 9]}
        t-augmented (u/augmented-reset t)]
    (is (= 0 (:iteration t-augmented)))
    (is (= (:supply-list t) 
           (:last-years-supply t-augmented)))
    (is (= (:private-good-prices t) 
           (:last-years-private-good-prices t-augmented)))
    (is (= (:public-good-prices t) 
           (:last-years-public-good-prices t-augmented)))))

(deftest consume
  (let [private-goods [1 2]
        private-good-prices [700 700]
        public-goods [1 2]
        public-good-prices [700 700]
        num-of-ccs 2
        cc {:utility-exponents [0.1 0.2]
            :public-good-exponents [0.3 0.4]
            :income 5000}]
    (is (= (u/consume private-goods private-good-prices public-goods public-good-prices num-of-ccs cc)
           {:utility-exponents [0.1 0.2],
            :public-good-exponents [0.3 0.4],
            :income 5000,
            :private-good-demands
             [0.7142857142857143 1.4285714285714286],
            :public-good-demands [4.285714285714286 5.714285714285714]}))))
