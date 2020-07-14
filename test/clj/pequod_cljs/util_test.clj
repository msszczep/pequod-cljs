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
