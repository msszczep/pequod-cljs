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
        wc-augmented (u/augment-exponents :wc wc)]
    (is (and (<= 0.1 (first wc-augmented))
             (>= 0.10400000000000001 (first wc-augmented))))
    (is (and (<= 0.2 (second wc-augmented))
             (>= 0.20400000000000001 (second wc-augmented))))
    (is (and (<= 0.3 (last wc-augmented))
             (>= 0.30400000000000001 (last wc-augmented))))))
