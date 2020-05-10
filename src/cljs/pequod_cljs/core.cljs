(ns pequod-cljs.core
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]
              [pequod-cljs.ex006 :as ex006]
              [pequod-cljs.ex007 :as ex007]
              [pequod-cljs.ex008 :as ex008]
              [pequod-cljs.ex009 :as ex009]
              [pequod-cljs.ex010 :as ex010]
              [pequod-cljs.ex011 :as ex011]
              [pequod-cljs.ex012 :as ex012]
              [pequod-cljs.ex013 :as ex013]
              [pequod-cljs.ex014 :as ex014]
              [pequod-cljs.ex015 :as ex015]
              [pequod-cljs.ex016 :as ex016]
              [pequod-cljs.ex017 :as ex017]
              [pequod-cljs.ex018 :as ex018]
              [pequod-cljs.ex019 :as ex019]
              [pequod-cljs.ex020 :as ex020]
              [pequod-cljs.ex021 :as ex021]
              [pequod-cljs.ex022 :as ex022]
              [pequod-cljs.ex023 :as ex023]
              [pequod-cljs.ex024 :as ex024]
              [pequod-cljs.ex025 :as ex025]
              [pequod-cljs.ex026 :as ex026]
              [pequod-cljs.ex027 :as ex027]
              [pequod-cljs.ex028 :as ex028]
              [pequod-cljs.ex029 :as ex029]
              [pequod-cljs.ex030 :as ex030]
              [pequod-cljs.ex031 :as ex031]
              [pequod-cljs.ex032 :as ex032]
              [pequod-cljs.ex033 :as ex033]
              [pequod-cljs.ex034 :as ex034]
              [pequod-cljs.ex035 :as ex035]
              [pequod-cljs.ex036 :as ex036]
              [pequod-cljs.ex037 :as ex037]
              [pequod-cljs.ex038 :as ex038]
              [pequod-cljs.ex039 :as ex039]
              [pequod-cljs.ex040 :as ex040]
              [pequod-cljs.ex041 :as ex041]
              [pequod-cljs.ex042 :as ex042]
              [pequod-cljs.ex043 :as ex043]
              [pequod-cljs.ex044 :as ex044]
              [pequod-cljs.ex045 :as ex045]
              [pequod-cljs.ex046 :as ex046]
              [pequod-cljs.ex047 :as ex047]
              [pequod-cljs.ex048 :as ex048]
              [pequod-cljs.ex049 :as ex049]
              [pequod-cljs.ex050 :as ex050]
              [pequod-cljs.ex051 :as ex051]
              [pequod-cljs.ex052 :as ex052]
              [pequod-cljs.ex053 :as ex053]
              [pequod-cljs.ex054 :as ex054]
              [pequod-cljs.ex055 :as ex055]
              [pequod-cljs.ex056 :as ex056]
              [pequod-cljs.ex057 :as ex057]
              [pequod-cljs.ex058 :as ex058]
              [pequod-cljs.ex059 :as ex059]
              [pequod-cljs.ex060 :as ex060]
              [pequod-cljs.ex061 :as ex061]
              [pequod-cljs.ex062 :as ex062]
              [pequod-cljs.ex063 :as ex063]
              [pequod-cljs.ex064 :as ex064]
              [pequod-cljs.ex065 :as ex065]
              [pequod-cljs.ex066 :as ex066]
              [pequod-cljs.ex067 :as ex067]
              [pequod-cljs.ex068 :as ex068]
              [pequod-cljs.ex069 :as ex069]
              [pequod-cljs.ex070 :as ex070]
              [pequod-cljs.ex071 :as ex071]
              [pequod-cljs.ex072 :as ex072]
              [pequod-cljs.ex073 :as ex073]
              [cljs.pprint :as pprint]
              [goog.string :as gstring]
              [goog.string.format]))


(def globals
  (atom {:init-private-good-price 700
         :init-intermediate-price 700
         :init-labor-price        700
         :init-nature-price       700
         :init-public-good-price  700

         :private-goods             10
         :intermediate-inputs       10
         :resources                 10
         :labors                    10
         :public-goods              10
         ; to scale up the number of categories, simply adjust
         ; this ^^ set of numbers

         :private-good-prices      []
         :intermediate-good-prices []
         :labor-prices             []
         :nature-prices            []
         :public-good-prices      []

         :private-good-surpluses   []
         :intermediate-input-surpluses []
         :nature-surpluses         []
         :labor-surpluses          []
         :public-good-surpluses    []

         :threshold-report         []
         :threshold-report-prev    []
         :threshold-met?           false
         :pdlist                   []
         :delta-delay              0
         :price-deltas             []
         ; :price-delta              0
         :wcs                      []
         :ccs                      []
         :iteration                0
         :natural-resources-supply 0
         :labor-supply             0
         :turtle-council           {}
         :top-output-councils      []
         :last-years-supply        []
         :last-years-private-good-prices []
         :last-years-public-good-prices []}))


(defn initialize-prices [t]
  (let [private-goods (t :private-goods)
        im-inputs (t :intermediate-inputs)
        resources (t :resources)
        labor (t :labors)
        public-goods (t :public-goods)]
    (assoc t
      :private-good-prices (vec (repeat private-goods (t :init-private-good-price)))
      :intermediate-good-prices (vec (repeat im-inputs (t :init-intermediate-price)))
      :nature-prices (vec (repeat resources (t :init-nature-price)))
      :labor-prices (vec (repeat labor (t :init-labor-price)))
      :public-good-prices (vec (repeat public-goods (t :init-public-good-price)))
      :price-deltas (vec (repeat 10 0.05))
      :pdlist (vec (repeat (+ private-goods im-inputs resources labor public-goods) 0.25)))))


(defn add-ids [cs]
  (loop [i 1
         cs cs
         updated-cs []]
    (if (empty? cs)
      updated-cs
      (recur (inc i) (rest cs) (conj updated-cs (assoc (first cs) :id i))))))

(defn augment-exponents [council-type exponents]
  (let [augments-to-use (if (= :wc council-type)
                            [0 0.001 0.002 0.003 0.004]
                            [(- .002) (- .001) 0 .001 .002])]
   (->> #(rand-nth augments-to-use)
        repeatedly
        (take (count exponents))
        (interleave exponents)
        (partition 2)
        (map (fn [[a b]] (+ a b))))))

(defn augment-wc [wc]
  (assoc wc :input-exponents (augment-exponents :wc (:input-exponents wc))
            :nature-exponents (augment-exponents :wc (:nature-exponents wc))
            :labor-exponents (augment-exponents :wc (:labor-exponents wc))))

(defn augment-cc [cc]
  (assoc cc :utility-exponents (augment-exponents :cc (:utility-exponents cc))
            :public-good-exponents (augment-exponents :cc (:public-good-exponents cc))))

(defn augmented-reset [t]
  (assoc t :iteration 0
           :ccs (mapv augment-cc (:ccs t))
           :wcs (mapv augment-wc (:wcs t))
           :last-years-supply (:supply-list t)
           :last-years-private-good-prices (:private-good-prices t)
           :last-years-public-good-prices (:public-good-prices t)))

; TODO: don't hard code labor supply or nature supply
(defn setup [t _ experiment]
  (let [intermediate-inputs (vec (range 1 (inc (t :intermediate-inputs))))
        nature-types (vec (range 1 (inc (t :resources))))
        labor-types (vec (range 1 (inc (t :labors))))
        private-goods (vec (range 1 (inc (t :private-goods))))
        public-good-types (vec (range 1 (inc (t :public-goods))))]
    (-> t
        initialize-prices
        (assoc :delta-delay 5
               :natural-resources-supply (repeat (t :resources) 1000)
               :labor-supply (repeat (t :labors) 1000)
               :private-goods private-goods
               :intermediate-inputs intermediate-inputs
               :nature-types nature-types
               :labor-types labor-types
               :public-good-types public-good-types
               :surplus-threshold 0.05
               :ccs (add-ids
                      (case @experiment
                        "ex006" ex006/ccs
                        "ex007" ex007/ccs
                        "ex008" ex008/ccs
                        "ex009" ex009/ccs
                        "ex010" ex010/ccs
                        "ex011" ex011/ccs
                        "ex012" ex012/ccs
                        "ex013" ex013/ccs
                        "ex014" ex014/ccs
                        "ex015" ex015/ccs
                        "ex016" ex016/ccs
                        "ex017" ex017/ccs
                        "ex018" ex018/ccs
                        "ex019" ex019/ccs
                        "ex020" ex020/ccs
                        "ex021" ex021/ccs
                        "ex022" ex022/ccs
                        "ex023" ex023/ccs
                        "ex024" ex024/ccs
                        "ex025" ex025/ccs
                        "ex026" ex026/ccs
                        "ex027" ex027/ccs
                        "ex028" ex028/ccs
                        "ex029" ex029/ccs
                        "ex030" ex030/ccs
                        "ex031" ex031/ccs
                        "ex032" ex032/ccs
                        "ex033" ex033/ccs
                        "ex034" ex034/ccs
                        "ex035" ex035/ccs
                        "ex036" ex036/ccs
                        "ex037" ex037/ccs
                        "ex038" ex038/ccs
                        "ex039" ex039/ccs
                        "ex040" ex040/ccs
                        "ex041" ex041/ccs
                        "ex042" ex042/ccs
                        "ex043" ex043/ccs
                        "ex044" ex044/ccs
                        "ex045" ex045/ccs
                        "ex046" ex046/ccs
                        "ex047" ex047/ccs
                        "ex048" ex048/ccs
                        "ex049" ex049/ccs
                        "ex050" ex050/ccs
                        "ex051" ex051/ccs
                        "ex052" ex052/ccs
                        "ex053" ex053/ccs
                        "ex054" ex054/ccs
                        "ex055" ex055/ccs
                        "ex056" ex056/ccs
                        "ex057" ex057/ccs
                        "ex058" ex058/ccs
                        "ex059" ex059/ccs
                        "ex060" ex060/ccs
                        "ex061" ex061/ccs
                        "ex062" ex062/ccs
                        "ex063" ex063/ccs
                        "ex064" ex064/ccs
                        "ex065" ex065/ccs
                        "ex066" ex066/ccs
                        "ex067" ex067/ccs
                        "ex068" ex068/ccs
                       "ex069" ex069/ccs
                       "ex070" ex070/ccs
                       "ex071" ex071/ccs
                       "ex072" ex072/ccs
                       "ex073" ex073/ccs
                        ex006/ccs))
               :wcs  (add-ids
                       (case @experiment
                          "ex006" ex006/wcs
                          "ex007" ex007/wcs
                          "ex008" ex008/wcs
                          "ex009" ex009/wcs
                          "ex010" ex010/wcs
                          "ex011" ex011/wcs
                          "ex012" ex012/wcs
                          "ex013" ex013/wcs
                          "ex014" ex014/wcs
                          "ex015" ex015/wcs
                          "ex016" ex016/wcs
                          "ex017" ex017/wcs
                          "ex018" ex018/wcs
                          "ex019" ex019/wcs
                          "ex020" ex020/wcs
                          "ex021" ex021/wcs
                          "ex022" ex022/wcs
                          "ex023" ex023/wcs
                          "ex024" ex024/wcs
                          "ex025" ex025/wcs
                          "ex026" ex026/wcs
                          "ex027" ex027/wcs
                          "ex028" ex028/wcs
                          "ex029" ex029/wcs
                          "ex030" ex030/wcs
                          "ex031" ex031/wcs
     "ex032" ex032/wcs
     "ex033" ex033/wcs
     "ex034" ex034/wcs
     "ex035" ex035/wcs
     "ex036" ex036/wcs
     "ex037" ex037/wcs
     "ex038" ex038/wcs
     "ex039" ex039/wcs
     "ex040" ex040/wcs
     "ex041" ex041/wcs
     "ex042" ex042/wcs
     "ex043" ex043/wcs
     "ex044" ex044/wcs
     "ex045" ex045/wcs
     "ex046" ex046/wcs
     "ex047" ex047/wcs
     "ex048" ex048/wcs
     "ex049" ex049/wcs
     "ex050" ex050/wcs
     "ex051" ex051/wcs
     "ex052" ex052/wcs
     "ex053" ex053/wcs
     "ex054" ex054/wcs
     "ex055" ex055/wcs
     "ex056" ex056/wcs
     "ex057" ex057/wcs
     "ex058" ex058/wcs
     "ex059" ex059/wcs
     "ex060" ex060/wcs
     "ex061" ex061/wcs
     "ex062" ex062/wcs
     "ex063" ex063/wcs
     "ex064" ex064/wcs
     "ex065" ex065/wcs
     "ex066" ex066/wcs
     "ex067" ex067/wcs
     "ex068" ex068/wcs
     "ex069" ex069/wcs
     "ex070" ex070/wcs
     "ex071" ex071/wcs
     "ex072" ex072/wcs
     "ex073" ex073/wcs
      ex006/wcs))))))

(defn consume [private-goods private-good-prices public-goods public-good-prices num-of-ccs cc]
  (let [utility-exponents (cc :utility-exponents)
        public-good-exponents (cc :public-good-exponents)
        income (cc :income)
        private-good-demands (mapv (fn [private-good]
                              (/ (* income (nth utility-exponents (dec private-good)))
                                 (* (apply + (concat utility-exponents public-good-exponents))
                                    (nth private-good-prices (dec private-good)))))
                              private-goods)
        public-good-demands (mapv (fn [public-good]
                                    (/ (* income (nth public-good-exponents (dec public-good)))
                                       (* (apply + (concat utility-exponents public-good-exponents))
                                          (/ (nth public-good-prices (dec public-good))
                                             num-of-ccs))))
                                  public-goods)]
    (assoc cc :private-good-demands private-good-demands
              :public-good-demands public-good-demands)))

(defn assign-new-proposal [production-inputs xs]
  (let [num-input-quantities (count (first production-inputs))
        num-nature-quantities (count (second production-inputs))
        input-quantities (->> xs
                              (take num-input-quantities)
                              (into []))
        nature-quantities (->> xs
                               (drop num-input-quantities)
                               (take num-nature-quantities)
                               (into []))
        labor-quantities (->> xs
                              (drop (+ num-input-quantities num-nature-quantities))
                              (into []))]
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
        [input-qs nature-qs labor-qs] (assign-new-proposal p-i [x1 x2 x3 x4 x5 x6 x7])]
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
      :labor-quantities labor-qs}))


(defn solution-8 [a s c k ps b λ p-i]
  (let [[b1 b2 b3 b4 b5 b6 b7 b8] b
        [p1 p2 p3 p4 p5 p6 p7 p8] (flatten ps)
        output (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* c (Math/log k)) (* c (Math/log s)) (- (* c (Math/log λ))) (- (* c (Math/log c))) (- (* b1 k (Math/log b1))) (* b1 k (Math/log p1)) (- (* b1 k (Math/log λ))) (- (* b2 k (Math/log b2))) (* b2 k (Math/log p2)) (- (* b2 k (Math/log λ))) (- (* b3 k (Math/log b3))) (* b3 k (Math/log p3)) (- (* b3 k (Math/log λ))) (- (* b4 k (Math/log b4))) (* b4 k (Math/log p4)) (- (* b4 k (Math/log λ))) (- (* b5 k (Math/log b5))) (* b5 k (Math/log p5)) (- (* b5 k (Math/log λ))) (- (* b6 k (Math/log b6))) (* b6 k (Math/log p6)) (- (* b6 k (Math/log λ))) (- (* b7 k (Math/log b7))) (* b7 k (Math/log p7)) (- (* b7 k (Math/log λ))) (- (* b8 k (Math/log b8))) (* b8 k (Math/log p8)) (- (* b8 k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7) (* k b8))))
        x1 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* b2 k (Math/log b1)) (* b3 k (Math/log b1)) (* b4 k (Math/log b1)) (* b5 k (Math/log b1)) (* b6 k (Math/log b1)) (* b7 k (Math/log b1)) (* b8 k (Math/log b1)) (* c (Math/log b1)) (- (* k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* b8 k (Math/log b8))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* b2 k (Math/log p1))) (- (* b3 k (Math/log p1))) (- (* b4 k (Math/log p1))) (- (* b5 k (Math/log p1))) (- (* b6 k (Math/log p1))) (- (* b7 k (Math/log p1))) (- (* b8 k (Math/log p1))) (* k (Math/log p1)) (- (* c (Math/log p1))) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* b8 k (Math/log p8)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7) (* k b8))))
        x2 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* b1 k (Math/log b2)) (* b3 k (Math/log b2)) (* b4 k (Math/log b2)) (* b5 k (Math/log b2)) (* b6 k (Math/log b2)) (* b7 k (Math/log b2)) (* b8 k (Math/log b2)) (* c (Math/log b2)) (- (* k (Math/log b2))) (- (* b1 k (Math/log b1))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* b8 k (Math/log b8))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* b1 k (Math/log p2))) (- (* b3 k (Math/log p2))) (- (* b4 k (Math/log p2))) (- (* b5 k (Math/log p2))) (- (* b6 k (Math/log p2))) (- (* b7 k (Math/log p2))) (- (* b8 k (Math/log p2))) (* k (Math/log p2)) (- (* c (Math/log p2))) (* b1 k (Math/log p1)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* b8 k (Math/log p8)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7) (* k b8))))
        x3 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* b1 k (Math/log b3)) (* b2 k (Math/log b3)) (* b4 k (Math/log b3)) (* b5 k (Math/log b3)) (* b6 k (Math/log b3)) (* b7 k (Math/log b3)) (* b8 k (Math/log b3)) (* c (Math/log b3)) (- (* k (Math/log b3))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* b8 k (Math/log b8))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* b1 k (Math/log p3))) (- (* b2 k (Math/log p3))) (- (* b4 k (Math/log p3))) (- (* b5 k (Math/log p3))) (- (* b6 k (Math/log p3))) (- (* b7 k (Math/log p3))) (- (* b8 k (Math/log p3))) (* k (Math/log p3)) (- (* c (Math/log p3))) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* b8 k (Math/log p8)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7) (* k b8))))
        x4 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* b1 k (Math/log b4)) (* b2 k (Math/log b4)) (* b3 k (Math/log b4)) (* b5 k (Math/log b4)) (* b6 k (Math/log b4)) (* b7 k (Math/log b4)) (* b8 k (Math/log b4)) (* c (Math/log b4)) (- (* k (Math/log b4))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* b8 k (Math/log b8))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* b1 k (Math/log p4))) (- (* b2 k (Math/log p4))) (- (* b3 k (Math/log p4))) (- (* b5 k (Math/log p4))) (- (* b6 k (Math/log p4))) (- (* b7 k (Math/log p4))) (- (* b8 k (Math/log p4))) (* k (Math/log p4)) (- (* c (Math/log p4))) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* b8 k (Math/log p8)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7) (* k b8))))
        x5 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* b1 k (Math/log b5)) (* b2 k (Math/log b5)) (* b3 k (Math/log b5)) (* b4 k (Math/log b5)) (* b6 k (Math/log b5)) (* b7 k (Math/log b5)) (* b8 k (Math/log b5)) (* c (Math/log b5)) (- (* k (Math/log b5))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* b8 k (Math/log b8))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* b1 k (Math/log p5))) (- (* b2 k (Math/log p5))) (- (* b3 k (Math/log p5))) (- (* b4 k (Math/log p5))) (- (* b6 k (Math/log p5))) (- (* b7 k (Math/log p5))) (- (* b8 k (Math/log p5))) (* k (Math/log p5)) (- (* c (Math/log p5))) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* b8 k (Math/log p8)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7) (* k b8))))
        x6 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* b1 k (Math/log b6)) (* b2 k (Math/log b6)) (* b3 k (Math/log b6)) (* b4 k (Math/log b6)) (* b5 k (Math/log b6)) (* b7 k (Math/log b6)) (* b8 k (Math/log b6)) (* c (Math/log b6)) (- (* k (Math/log b6))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b7 k (Math/log b7))) (- (* b8 k (Math/log b8))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* b1 k (Math/log p6))) (- (* b2 k (Math/log p6))) (- (* b3 k (Math/log p6))) (- (* b4 k (Math/log p6))) (- (* b5 k (Math/log p6))) (- (* b7 k (Math/log p6))) (- (* b8 k (Math/log p6))) (* k (Math/log p6)) (- (* c (Math/log p6))) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b7 k (Math/log p7)) (* b8 k (Math/log p8)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7) (* k b8))))
        x7 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* b1 k (Math/log b7)) (* b2 k (Math/log b7)) (* b3 k (Math/log b7)) (* b4 k (Math/log b7)) (* b5 k (Math/log b7)) (* b6 k (Math/log b7)) (* b8 k (Math/log b7)) (* c (Math/log b7)) (- (* k (Math/log b7))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b8 k (Math/log b8))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* b1 k (Math/log p7))) (- (* b2 k (Math/log p7))) (- (* b3 k (Math/log p7))) (- (* b4 k (Math/log p7))) (- (* b5 k (Math/log p7))) (- (* b6 k (Math/log p7))) (- (* b8 k (Math/log p7))) (* k (Math/log p7)) (- (* c (Math/log p7))) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b8 k (Math/log p8)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7) (* k b8))))
        x8 (Math/pow Math/E (/ (+ (- (* k (Math/log a))) (* b1 k (Math/log b8)) (* b2 k (Math/log b8)) (* b3 k (Math/log b8)) (* b4 k (Math/log b8)) (* b5 k (Math/log b8)) (* b6 k (Math/log b8)) (* b7 k (Math/log b8)) (* c (Math/log b8)) (- (* k (Math/log b8))) (- (* b1 k (Math/log b1))) (- (* b2 k (Math/log b2))) (- (* b3 k (Math/log b3))) (- (* b4 k (Math/log b4))) (- (* b5 k (Math/log b5))) (- (* b6 k (Math/log b6))) (- (* b7 k (Math/log b7))) (- (* c (Math/log c))) (* c (Math/log k)) (- (* b1 k (Math/log p8))) (- (* b2 k (Math/log p8))) (- (* b3 k (Math/log p8))) (- (* b4 k (Math/log p8))) (- (* b5 k (Math/log p8))) (- (* b6 k (Math/log p8))) (- (* b7 k (Math/log p8))) (* k (Math/log p8)) (- (* c (Math/log p8))) (* b1 k (Math/log p1)) (* b2 k (Math/log p2)) (* b3 k (Math/log p3)) (* b4 k (Math/log p4)) (* b5 k (Math/log p5)) (* b6 k (Math/log p6)) (* b7 k (Math/log p7)) (* c (Math/log s)) (- (* k (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7) (* k b8))))
        effort (Math/pow Math/E (/ (+ (- (* (Math/log a))) (- (* b1 (Math/log b1))) (- (* b2 (Math/log b2))) (- (* b3 (Math/log b3))) (- (* b4 (Math/log b4))) (- (* b5 (Math/log b5))) (- (* b6 (Math/log b6))) (- (* b7 (Math/log b7))) (- (* b8 (Math/log b8))) (- (* (Math/log c))) (* b1 (Math/log c)) (* b2 (Math/log c)) (* b3 (Math/log c)) (* b4 (Math/log c)) (* b5 (Math/log c)) (* b6 (Math/log c)) (* b7 (Math/log c)) (* b8 (Math/log c)) (* (Math/log k)) (* b1 (Math/log k)) (* b2 (Math/log k)) (* b3 (Math/log k)) (* b4 (Math/log k)) (* b5 (Math/log k)) (* b6 (Math/log k)) (* b7 (Math/log k)) (* b8 (Math/log k)) (* b1 (Math/log p1)) (* b2 (Math/log p2)) (* b3 (Math/log p3)) (* b4 (Math/log p4)) (* b5 (Math/log p5)) (* b6 (Math/log p6)) (* b7 (Math/log p7)) (* b8 (Math/log p8)) (* (Math/log s)) (* b1 (Math/log s)) (* b2 (Math/log s)) (* b3 (Math/log s)) (* b4 (Math/log s)) (* b5 (Math/log s)) (* b6 (Math/log s)) (* b7 (Math/log s)) (* b8 (Math/log s)) (- (* (Math/log λ)))) (+ c (- k) (* k b1) (* k b2) (* k b3) (* k b4) (* k b5) (* k b6) (* k b7) (* k b8))))
        [input-qs nature-qs labor-qs] (assign-new-proposal p-i [x1 x2 x3 x4 x5 x6 x7 x8])]
     {:output output
      :x1 x1
      :x2 x2
      :x3 x3
      :x4 x4
      :x5 x5
      :x6 x6
      :x7 x7
      :x8 x8
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
  (max 0.001 (min price-delta (Math/abs (* price-delta (nth pdlist J))))))

#_(defn get-deltas [J price-delta pdlist]
  (max 0.001 (min price-delta (Math/abs (nth pdlist J)))))

(defn update-surpluses-prices
  [type inputs prices wcs ccs natural-resources-supply labor-supply pdlist offset-1 offset-2 offset-3 offset-4]
  (loop [inputs inputs
         prices prices
         surpluses []
         new-deltas []
         J 0]
    (if (empty? inputs)
      {:prices prices :surpluses surpluses :new-deltas new-deltas}
      (let [supply (condp = type
                     "private-goods" (->> wcs
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
                     "nature" (nth natural-resources-supply J)
                     "labor"  (nth labor-supply J)
                     "public-goods" (->> wcs
                                         (filter #(and (= 2 (% :industry))
                                                       (= (first inputs)
                                                          (% :product))))
                                         (map :output)
                                         (reduce +)) )
            demand (condp = type
                     "private-goods"  (->> ccs
                                   (mapv :private-good-demands)
                                   (mapv #(nth % (dec (first inputs))))
                                   (reduce +))
                     "intermediate" (->> wcs
                                         (filter #(contains? (set (first (:production-inputs %)))
                                                             (first inputs)
                                                             ))
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
                                  (reduce +))
                     "public-goods" (/ (->> ccs
                                           (mapv :public-good-demands)
                                           (mapv #(nth % (dec (first inputs))))
                                           (reduce +))
                                       (count ccs)))
            j-offset (condp = type
                       "private-goods" 0
                       "intermediate" offset-1
                       "nature" (+ offset-1 offset-2)
                       "labor" (+ offset-1 offset-2 offset-3)
                       "public-goods" (+ offset-1 offset-2 offset-3 offset-4))
            surplus (- supply demand)
            price-delta-to-use (- 1.05 (Math/pow 0.5 (/ (Math/abs (* 2 surplus)) (+ demand supply))))
            delta (get-deltas (+ J j-offset) price-delta-to-use pdlist)
            new-delta delta
                      #_(cond (<= delta 1) delta
                            :else        (last (take-while (partial < 1)
                                                           (iterate #(/ % 2.0) delta))))
            new-price (cond (pos? surplus) (* (- 1 new-delta) (nth prices (dec (first inputs))))
                            (neg? surplus) (* (+ 1 new-delta) (nth prices (dec (first inputs))))
                            :else (nth prices (dec (first inputs))))]
        (recur (rest inputs)
               (assoc prices J new-price)
               (conj surpluses surplus)
               (conj new-deltas new-delta)
               (inc J))))))


; Q: does lambda change?
(defn proposal [private-good-prices input-prices nature-prices labor-prices public-good-prices wc]
  (letfn [(count-inputs [w]
            ((comp count flatten :production-inputs) w))
          (get-input-prices [[indexes prices]]
            (map #(nth prices (dec %)) indexes))
          (get-lambda-o [w private-good-prices input-prices public-good-prices]
            (let [industry (:industry w)
                  product (:product w)]
              (cond (= 0 industry) (nth private-good-prices (dec product))
                    (= 1 industry) (nth input-prices (dec product))
                    (= 2 industry) (nth public-good-prices (dec product)))))]
    (let [prices-and-indexes (->> (vector input-prices nature-prices labor-prices)
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
          b (concat b-input b-nature b-labor)
          λ (get-lambda-o wc private-good-prices input-prices public-good-prices)
          p-i (wc :production-inputs)]
      (condp = input-count-r
        1 (merge wc (solution-1 a s c k ps b λ p-i))
        2 (merge wc (solution-2 a s c k ps b λ p-i))
        3 (merge wc (solution-3 a s c k ps b λ p-i))
        4 (merge wc (solution-4 a s c k ps b λ p-i))
        5 (merge wc (solution-5 a s c k ps b λ p-i))
        6 (merge wc (solution-6 a s c k ps b λ p-i))
        7 (merge wc (solution-7 a s c k ps b λ p-i))
        8 (merge wc (solution-8 a s c k ps b λ p-i))
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

; TODO: this is actually update-percent-surplus
(defn update-pdlist [supply-list demand-list surplus-list]
  (letfn [(force-to-one [n]
            (let [cap 0.3]
              (if (or (> n cap) (< n (- cap))) cap (Math/abs n))))]
    (let [averaged-s-and-d (->> (interleave (flatten supply-list)
                                           (flatten demand-list))
                                (partition 2)
                                (mapv mean))]
      (->> (interleave (flatten surplus-list) averaged-s-and-d)
           (partition 2)
           (mapv #(/ (first %) (last %)))
           (mapv force-to-one)))))

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
                                          (:labor-quantities m))])
          (sum-input-quantities [qs pos type]
            (->> qs
                 (filter #(and (= pos (:key %)) (= type (:type %))))
                 (map :value)
                 (reduce +)))]
    (let [im-goods-to-use (:intermediate-inputs t) ; already a vector from range
          resources-to-use (range 1 (inc (:resources t)))
          labors-to-use (range 1 (inc (:labors t)))
          private-good-demands (->> t
                             :private-goods
                             (mapv (fn [i] (mapv #(nth (:private-good-demands %) (dec i)) (:ccs t))))
                             (mapv (partial reduce +)))
          all-quantities (->> t
                              :wcs
                              (map get-inputs-and-quantities)
                              flatten)
          input-quantity (mapv (fn [n] (sum-input-quantities all-quantities n :input-quantity)) im-goods-to-use)
          nature-quantity (mapv (fn [n] (sum-input-quantities all-quantities n :nature-quantity)) resources-to-use)
          labor-quantity (mapv (fn [n] (sum-input-quantities all-quantities n :labor-quantity)) labors-to-use)
          public-good-demands
                     (mapv (fn [public-good]
                             (mean (map #(nth (:public-good-demands %) (dec public-good))
                                         (t :ccs))))
                           (t :public-good-types))]
      [private-good-demands
       input-quantity
       nature-quantity
       labor-quantity
       public-good-demands])))


(defn get-supply-list [t]
  (letfn [(get-producers [t industry product]
            (->> t
                 :wcs
                 (filter #(and (= industry (% :industry))
                               (= product (% :product))))
                 (map :output)
                 flatten
                 (reduce +)))]
   (let [private-goods (:private-goods t)
         private-producers (mapv (partial get-producers t 0) private-goods)
         intermediate-inputs (:intermediate-inputs t)
         input-producers (mapv (partial get-producers t 1) intermediate-inputs)
         natural-resources-supply (t :natural-resources-supply)
         labor-supply (t :labor-supply)
         public-good-supply (mapv (partial get-producers t 2) (:public-good-types t))]
     (vector private-producers input-producers natural-resources-supply labor-supply public-good-supply)))) 

(defn report-threshold [surplus-list supply-list demand-list]
  (->> (interleave (flatten surplus-list) (flatten demand-list) (flatten supply-list))
       (partition 3)
       (mapv #(* 100 (/ (Math/abs (* 2 (first %))) (+ (second %) (last %)))))))

(defn compute-gdp [supply-list private-good-prices public-good-prices]
  (let [[private-good-supply _ _ _ public-good-supply] supply-list]
    (->> public-good-prices
         (concat private-good-prices)
         (interleave (concat private-good-supply public-good-supply))
         (partition 2)
         (map (fn [[a b]] (* a b)))
         (apply +))))

(defn iterate-plan [t]
  (let [threshold-report-prev (if (zero? (:iteration t)) [] (:threshold-report t))
        t2 (assoc t :ccs (map (partial consume (t :private-goods) (t :private-good-prices) (t :public-good-types) (t :public-good-prices) (count (t :ccs)))
                              (t :ccs))
                    :wcs (map (partial proposal (t :private-good-prices) (t :intermediate-good-prices) (t :nature-prices) (t :labor-prices) (t :public-good-prices))
                              (t :wcs)))
        {private-good-prices :prices, private-good-surpluses :surpluses, private-good-new-deltas :new-deltas} (update-surpluses-prices "private-goods" (t2 :private-goods) (t2 :private-good-prices) (t2 :wcs) (t2 :ccs) (t2 :natural-resources-supply) (t2 :labor-supply) (t2 :pdlist) (last (t2 :private-goods)) (last (t2 :intermediate-inputs)) (t2 :resources) (t2 :labors))
        {intermediate-good-prices :prices, intermediate-good-surpluses :surpluses, intermediate-good-new-deltas :new-deltas} (update-surpluses-prices "intermediate" (t2 :intermediate-inputs) (t2 :intermediate-good-prices) (t2 :wcs) (t2 :ccs) (t2 :natural-resources-supply) (t2 :labor-supply) (t2 :pdlist) (last (t2 :private-goods)) (last (t2 :intermediate-inputs)) (t2 :resources) (t2 :labors))
        {nature-prices :prices, nature-surpluses :surpluses, nature-new-deltas :new-deltas} (update-surpluses-prices "nature" (t2 :nature-types) (t2 :nature-prices) (t2 :wcs) (t2 :ccs) (t2 :natural-resources-supply) (t2 :labor-supply) (t2 :pdlist) (last (t2 :private-goods)) (last (t2 :intermediate-inputs)) (t2 :resources) (t2 :labors))
        {labor-prices :prices, labor-surpluses :surpluses, labor-new-deltas :new-deltas} (update-surpluses-prices "labor" (t2 :labor-types) (t2 :labor-prices) (t2 :wcs) (t2 :ccs) (t2 :natural-resources-supply) (t2 :labor-supply) (t2 :pdlist) (last (t2 :private-goods)) (last (t2 :intermediate-inputs)) (t2 :resources) (t2 :labors))
        {public-good-prices :prices, public-good-surpluses :surpluses, public-good-new-deltas :new-deltas} (update-surpluses-prices "public-goods" (t2 :public-good-types) (t2 :public-good-prices) (t2 :wcs) (t2 :ccs) (t2 :natural-resources-supply) (t2 :labor-supply) (t2 :pdlist) (last (t2 :private-goods)) (last (t2 :intermediate-inputs)) (t2 :resources) (t2 :labors))
        surplus-list (vector private-good-surpluses intermediate-good-surpluses nature-surpluses labor-surpluses public-good-surpluses)
        supply-list (get-supply-list t2)
        demand-list (get-demand-list t2)
        new-price-deltas (update-price-deltas supply-list demand-list surplus-list)
        new-pdlist (update-pdlist supply-list demand-list surplus-list)
        threshold-report (report-threshold surplus-list supply-list demand-list)
        iteration (inc (:iteration t2))
        top-output-councils (group-by (juxt :industry :product) (:wcs t2))
        gdp-typ-2 (compute-gdp supply-list private-good-prices public-good-prices)
        gdp-typ-1 (compute-gdp (:last-years-supply t2) private-good-prices public-good-prices)
        gdp-lyp-2 (compute-gdp supply-list (:last-years-private-good-prices t2) (:last-years-public-good-prices t2))
        gdp-lyp-1 (compute-gdp (:last-years-supply t2) (:last-years-private-good-prices t2) (:last-years-public-good-prices t2))
        gdp-typ-pi (* 100 (/ (- gdp-typ-2 gdp-typ-1) gdp-typ-1))
        gdp-lyp-pi (* 100 (/ (- gdp-lyp-2 gdp-lyp-1) gdp-lyp-1))
        gdp-avg-pi (mean [gdp-typ-pi gdp-lyp-pi])]
    (assoc t2 :private-good-prices private-good-prices
              :private-good-surpluses private-good-surpluses
              :private-good-new-deltas private-good-new-deltas
              :intermediate-good-prices intermediate-good-prices
              :intermediate-good-surpluses intermediate-good-surpluses
              :intermediate-good-new-deltas intermediate-good-new-deltas
              :nature-prices nature-prices
              :nature-surpluses nature-surpluses
              :nature-new-deltas nature-new-deltas
              :labor-prices labor-prices
              :labor-surpluses labor-surpluses
              :labor-new-deltas labor-new-deltas
              :public-good-prices public-good-prices
              :public-good-surpluses public-good-surpluses
              :public-good-new-deltas public-good-new-deltas
              :demand-list demand-list
              :surplus-list surplus-list
              :supply-list supply-list
              :threshold-report threshold-report
              :threshold-report-prev threshold-report-prev
              :price-deltas new-price-deltas
              :pdlist new-pdlist
              :iteration iteration
              :top-output-councils top-output-councils
              :gdp2 gdp-typ-pi
              :gdp1 gdp-lyp-pi
              :gdp-pi gdp-avg-pi)))

(defn check-surpluses [t]
  (letfn [(check-producers [surpluses supplies inputs]
            (some #(> (Math/abs (nth surpluses (dec %)))
                      (* (:surplus-threshold t) (nth supplies (dec %))))
                    inputs))
          (check-supplies [surpluses supply inputs surplus-threshold]
            (some #(> (Math/abs (nth surpluses (dec %)))
                      (* surplus-threshold (nth supply (dec %))))
                    inputs))
          (get-producers [wcs industry]
            (filter #(= industry (% :industry))) wcs)]
    (let [surplus-threshold (:surplus-threshold t)
          [private-good-supply im-supply _ _ public-good-supply] (get-supply-list t)
          private-goods-check (check-producers (:private-good-surpluses t) private-good-supply (:private-goods t))
          im-goods-check (check-producers (:intermediate-good-surpluses t) im-supply (:intermediate-inputs t))
          nature-check (check-supplies (:nature-surpluses t) (:natural-resources-supply t) (:nature-types t) surplus-threshold)
          labor-check (check-supplies (:labor-surpluses t) (:labor-supply t) (:labor-types t) surplus-threshold)
          public-good-check (check-producers (:public-good-surpluses t) public-good-supply (:public-good-types t))]
      [(every? nil? [private-goods-check im-goods-check nature-check labor-check public-good-check])
       (mapv nil? [private-goods-check im-goods-check nature-check labor-check public-good-check])]
      )))


(defn total-surplus [surplus-list]
  (->> surplus-list
       flatten
       (map Math/abs)
       (reduce +)))


(defn check-wc-exponents [wc]
  (let [sum-all-exponents (reduce + 
                            (concat (:labor-exponents wc)
                                    (:input-exponents wc)
                                    (:nature-exponents wc)))]
    (< sum-all-exponents 1)))


(defn check-cc-exponents [cc]
  (let [sum-all-exponents (reduce + 
                            (concat (:utility-exponents cc)
                                    (:public-good-exponents cc)))]
    (< sum-all-exponents 1)))


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
  (let [[threshold-met? threshold-granular] (check-surpluses t)
        delta-delay (:delta-delay t)
        #_{price-delta :price-delta
         delta-delay :delta-delay} 
#_(if (and (< surplus-total 100)
                                              (<= delta-delay 0))
                                     (adjust-delta price-delta "lower")
                                     {:price-delta price-delta
                                      :delta-delay delta-delay})
        #_{price-delta :price-delta
         delta-delay :delta-delay} 
#_(if (and (> surplus-total 100000)
                                              (<= delta-delay 0))
                                     (adjust-delta price-delta "raise")
                                     {:price-delta price-delta
                                      :delta-delay delta-delay})
        delta-delay (if (pos? delta-delay)
                      (dec delta-delay) delta-delay)
        t-updated (assoc t :threshold-met? threshold-met?
                           :threshold-granular threshold-granular
                           :delta-delay delta-delay)]
    t-updated))


(defn proceed [t]
  (let [t-plus (iterate-plan t)]
    (rest-of-to-do t-plus)))


(defn proceed-iterate-five [t]
  (loop [t-temp t 
         i 5]
    (if (= 0 i)
      t-temp
      (let [t-plus (iterate-plan t-temp)]
        (recur (rest-of-to-do t-plus)
               (dec i))))))


(defn proceed-iterate-ten [t]
  (loop [t-temp t 
         i 10]
    (if (= 0 i)
      t-temp
      (let [t-plus (iterate-plan t-temp)]
        (recur (rest-of-to-do t-plus)
               (dec i))))))

(defn proceed-iterate-fifty [t]
  (loop [t-temp t
         i 50]
    (if (= 0 i)
      t-temp
      (let [t-plus (iterate-plan t-temp)]
        (recur (rest-of-to-do t-plus)
               (dec i))))))


(defn update-turtle-council [t1 t2 council-type id]
  (letfn [(find-by-id [i data]
            (filter #(= (str (:id %)) i) data))]
    (assoc-in t1
              [:turtle-council]
              (find-by-id @id (get @t2 (keyword @council-type))))))

;; -------------------------
;; Views-

(defn check-quantities [[id input-quantities nature-quantities labor-quantities]]
  (let [i (apply + input-quantities)
        n (apply + nature-quantities)
        l (apply + labor-quantities)]
   (< 5 i)))

(defn sum-quantities [[id input-quantities nature-quantities labor-quantities input-exponents nature-exponents labor-exponents]]
  (let [i (apply + input-quantities)
        n (apply + nature-quantities)
        l (apply + labor-quantities)
        ie (apply + input-exponents)
        ne (apply + nature-exponents)
        le (apply + labor-exponents)]
    [id (+ ie ne le) (+ i n l)]))

(defn truncate-number [n]
  (gstring/format "%.3f" n))

(defn all-buttons []
  (let [experiment-to-use (atom "ex006")
        turtle-council-type (atom "wcs")
        turtle-id (atom 0)]
    [:div
     [:table
       [:tr
         [:td [:select {:field :list
               :id :experiment
               :on-change #(reset! experiment-to-use (-> % .-target .-value))}
          [:option {:key :ex006} "ex006"]
          [:option {:key :ex007} "ex007"]
          [:option {:key :ex008} "ex008"]
          [:option {:key :ex009} "ex009"]
          [:option {:key :ex010} "ex010"]
          [:option {:key :ex011} "ex011"]
          [:option {:key :ex012} "ex012"]
          [:option {:key :ex013} "ex013"]
          [:option {:key :ex014} "ex014"]
          [:option {:key :ex015} "ex015"]
          [:option {:key :ex016} "ex016"]
          [:option {:key :ex017} "ex017"]
          [:option {:key :ex018} "ex018"]
          [:option {:key :ex019} "ex019"]
          [:option {:key :ex020} "ex020"]
          [:option {:key :ex021} "ex021"]
          [:option {:key :ex022} "ex022"]
          [:option {:key :ex023} "ex023"]
          [:option {:key :ex024} "ex024"]
          [:option {:key :ex025} "ex025"]
          [:option {:key :ex026} "ex026"]
          [:option {:key :ex027} "ex027"]
          [:option {:key :ex028} "ex028"]
          [:option {:key :ex029} "ex029"]
          [:option {:key :ex030} "ex030"]
          [:option {:key :ex031} "ex031"]
          [:option {:key :ex032} "ex032"]
          [:option {:key :ex033} "ex033"]
          [:option {:key :ex034} "ex034"]
          [:option {:key :ex035} "ex035"]
          [:option {:key :ex036} "ex036"]
          [:option {:key :ex037} "ex037"]
          [:option {:key :ex038} "ex038"]
          [:option {:key :ex039} "ex039"]
          [:option {:key :ex040} "ex040"]
          [:option {:key :ex041} "ex041"]
          [:option {:key :ex042} "ex042"]
          [:option {:key :ex043} "ex043"]
          [:option {:key :ex044} "ex044"]
          [:option {:key :ex045} "ex045"]
          [:option {:key :ex046} "ex046"]
          [:option {:key :ex047} "ex047"]
          [:option {:key :ex048} "ex048"]
          [:option {:key :ex049} "ex049"]
          [:option {:key :ex050} "ex050"]
          [:option {:key :ex051} "ex051"]
          [:option {:key :ex052} "ex052"]
          [:option {:key :ex053} "ex053"]
          [:option {:key :ex054} "ex054"]
          [:option {:key :ex055} "ex055"]
          [:option {:key :ex056} "ex056"]
          [:option {:key :ex057} "ex057"]
          [:option {:key :ex058} "ex058"]
          [:option {:key :ex059} "ex059"]
          [:option {:key :ex060} "ex060"]
          [:option {:key :ex061} "ex061"]
          [:option {:key :ex062} "ex062"]
          [:option {:key :ex063} "ex063"]
          [:option {:key :ex064} "ex064"]
          [:option {:key :ex065} "ex065"]
          [:option {:key :ex066} "ex066"]
          [:option {:key :ex067} "ex067"]
          [:option {:key :ex068} "ex068"]
          [:option {:key :ex069} "ex069"]
          [:option {:key :ex070} "ex070"]
          [:option {:key :ex071} "ex071"]
          [:option {:key :ex072} "ex072"]
          [:option {:key :ex073} "ex073"]
          ]]
         [:td [:input {:type "button" :value "Setup"
              :on-click #(swap! globals setup globals experiment-to-use)}]]
         [:td [:input {:type "button" :value "Iterate 1X"
           :on-click #(swap! globals proceed globals)}]]
         [:td [:input {:type "button" :value "Iterate 5X"
           :on-click #(swap! globals proceed-iterate-five globals)}]]
         [:td [:input {:type "button" :value "Iterate 10X"
           :on-click #(swap! globals proceed-iterate-ten globals)}]]
         [:td [:input {:type "button" :value "Iterate 50X"
           :on-click #(swap! globals proceed-iterate-fifty globals)}]]
         [:td "Council explorer:"]
         [:td [:select {:field :list
               :id :turtle-council-type
               :on-change #(reset! turtle-council-type (-> % .-target .-value))}
          [:option {:key :wcs} "wcs"]
          [:option {:key :ccs} "ccs"]]]
         [:id [:input {:type :text
                       :id :turtle-id
                       :size 3
                       :on-change #(reset! turtle-id (-> % .-target .-value))}]]
         [:td [:input {:type "button" :value "Show council"
           :on-click #(swap! globals update-turtle-council globals turtle-council-type turtle-id)}]]
         [:td [:input {:type "button" :value "Augmented reset"
           :on-click #(swap! globals augmented-reset globals)}]]
         ]
        [:tr 
         [:td (str "WCs: " (count (get @globals :wcs)))]
         [:td (str "CCs: " (count (get @globals :ccs)))]
         [:td (str "TH: " (get @globals :surplus-threshold))]
         [:td (str "A-GDP TY: " (truncate-number (str (get @globals :gdp2))))]
         [:td (str "A-GDP LY: " (truncate-number (str (get @globals :gdp1))))]
         [:td (str "A-GDP AVG: " (truncate-number (str (get @globals :gdp-pi))))]
         ]]]))

(defn partition-by-five 
  "Now a misnomer: Partition by 10"
  [seq-to-use]
  (if (empty? seq-to-use)
    seq-to-use
    (->> seq-to-use
         flatten
         (mapv truncate-number)
         (partition-all 10)
         (mapv (partial into [])))))


(defn show-color [threshold-report-excerpt]
  (let [tre (first threshold-report-excerpt)
        red "#ff4d4d"]
    (cond (empty? tre) red
          (every? #(< % 3) tre) "#4dd2ff"
          (every? #(< % 5) tre) "lawngreen"
          (every? #(< % 10) tre) "gold"
          (every? #(< % 20) tre) "darkorange"
          :else red)))

(defn show-top-output-councils [d]
  (let [td-cell-style {:border "1px solid #ddd" :text-align "center" :vertical-align "middle" :padding "8px"}]
    (letfn [(transform-top-five [m]
              {:id (:id m)
               :output (truncate-number (:output m))
               :effort (truncate-number (:effort m))
               :exponents-sum (truncate-number (apply + 
                                       (concat (:c m)
                                               (:labor-exponents m)
                                               (:input-exponents m)
                                               (:nature-exponents m))))})]
     [:table {:style {:width "100%" :padding "8px" :border "1px solid #ddd"}}
      [:tr 
       [:th {:style td-cell-style} "Industry"]
       [:th {:style td-cell-style} "Product"]
       [:th {:style td-cell-style} "Top 5"]
       ]
      (map (fn [[k v]] 
             (let [top-five
                   (map #(select-keys % [:id :output :effort :labor-exponents :input-exponents :nature-exponents]) 
                         (reverse (sort-by :output v)))] 
               [:tr {:style {:border "1px solid #ddd"}}
                [:td {:style td-cell-style} (str (first k))]
                [:td {:style td-cell-style} (str (last k))]
                [:td {:style td-cell-style} (str (map transform-top-five top-five))]
                ]))
           (sort d))])))

(defn show-globals []
    (let [td-cell-style {:border "1px solid #ddd" :text-align "center" :vertical-align "middle" :padding "8px"}] 
     [:div [:h4 "Welcome to pequod-cljs"]
           (all-buttons)
           [:p]
           [:h4 "Council explorer: " (get @globals :turtle-council)]
           [:p]
           [:table {:style {:width "100%" :padding "8px" :border "1px solid #ddd"}}
             [:tr 
               [:th {:style td-cell-style} "Iteration: " (get @globals :iteration)]
               [:th {:style td-cell-style} "Private Goods"]
               [:th {:style td-cell-style} "Intermediate Inputs"]
               [:th {:style td-cell-style} "Nature"]
               [:th {:style td-cell-style} "Labor"]
               [:th {:style td-cell-style} "Public Goods"]
             ]
             [:tr {:style {:border "1px solid #ddd"}}
              [:td {:style (assoc td-cell-style :font-weight "bold")} "Prices"]
              [:td {:style td-cell-style} (or (str (mapv truncate-number (get @globals :private-good-prices))) "")]
              [:td {:style td-cell-style} (or (str (mapv truncate-number (get @globals :intermediate-good-prices))) "")]
              [:td {:style td-cell-style} (or (str (mapv truncate-number (get @globals :nature-prices))) "")]
              [:td {:style td-cell-style} (or (str (mapv truncate-number (get @globals :labor-prices))) "")]
              [:td {:style td-cell-style} (or (str (mapv truncate-number (get @globals :public-good-prices))) "")]
             ]
             [:tr {:style {:border "1px solid #ddd"}}
              [:td {:style (assoc td-cell-style :font-weight "bold")} "New Deltas"]
              [:td {:style td-cell-style} (or (str (mapv truncate-number (get @globals :private-good-new-deltas))) "")]
              [:td {:style td-cell-style} (or (str (mapv truncate-number (get @globals :intermediate-good-new-deltas))) "")]
              [:td {:style td-cell-style} (or (str (mapv truncate-number (get @globals :nature-new-deltas))) "")]
              [:td {:style td-cell-style} (or (str (mapv truncate-number (get @globals :labor-new-deltas))) "")]
              [:td {:style td-cell-style} (or (str (mapv truncate-number (get @globals :public-good-new-deltas))) "")]
             ]
             [:tr {:style {:border "1px solid #ddd"}}
              [:td {:style (assoc td-cell-style :font-weight "bold")} "PD List"]
              [:td {:style td-cell-style} (str (or (take 1 (partition-by-five (get @globals :pdlist))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 1 (take 2 (partition-by-five (get @globals :pdlist)))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 2 (take 3 (partition-by-five (get @globals :pdlist)))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 3 (take 4 (partition-by-five (get @globals :pdlist)))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 4 (take 5 (partition-by-five (get @globals :pdlist)))) "[]"))]
             ]
             [:tr {:style {:border "1px solid #ddd"}}
              [:td {:style (assoc td-cell-style :font-weight "bold")} "Supply"]
              [:td {:style td-cell-style} (str (or (take 1 (partition-by-five (get @globals :supply-list))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 1 (take 2 (partition-by-five (get @globals :supply-list)))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 2 (take 3 (partition-by-five (get @globals :supply-list)))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 3 (take 4 (partition-by-five (get @globals :supply-list)))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 4 (take 5 (partition-by-five (get @globals :supply-list)))) "[]"))]
             ]
             [:tr {:style {:border "1px solid #ddd"}}
              [:td {:style (assoc td-cell-style :font-weight "bold")} "Demand"]
              [:td {:style td-cell-style} (str (or (take 1 (partition-by-five (get @globals :demand-list))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 1 (take 2 (partition-by-five (get @globals :demand-list)))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 2 (take 3 (partition-by-five (get @globals :demand-list)))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 3 (take 4 (partition-by-five (get @globals :demand-list)))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 4 (take 5 (partition-by-five (get @globals :demand-list)))) "[]"))]
             ]
             [:tr {:style {:border "1px solid #ddd"}}
              [:td {:style (assoc td-cell-style :font-weight "bold")} "Surplus"]
              [:td {:style td-cell-style} (str (or (take 1 (partition-by-five (get @globals :surplus-list))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 1 (take 2 (partition-by-five (get @globals :surplus-list)))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 2 (take 3 (partition-by-five (get @globals :surplus-list)))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 3 (take 4 (partition-by-five (get @globals :surplus-list)))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 4 (take 5 (partition-by-five (get @globals :surplus-list)))) "[]"))]
             ]
             [:tr {:style {:border "1px solid #ddd"}}
              [:td {:style (assoc td-cell-style :font-weight "bold")} "Percent Surplus / Threshold Met?"]
              [:td {:style (assoc td-cell-style :background (show-color (take 1 (partition-by-five (get @globals :threshold-report)))))} (str (or (take 1 (partition-by-five (get @globals :threshold-report))) "[]"))]
              [:td {:style (assoc td-cell-style :background (show-color (drop 1 (take 2 (partition-by-five (get @globals :threshold-report))))))} (str (or (drop 1 (take 2 (partition-by-five (get @globals :threshold-report)))) "[]"))]
              [:td {:style (assoc td-cell-style :background (show-color (drop 2 (take 3 (partition-by-five (get @globals :threshold-report))))))} (str (or (drop 2 (take 3 (partition-by-five (get @globals :threshold-report)))) "[]"))]
              [:td {:style (assoc td-cell-style :background (show-color (drop 3 (take 4 (partition-by-five (get @globals :threshold-report))))))} (str (or (drop 3 (take 4 (partition-by-five (get @globals :threshold-report)))) "[]"))]
              [:td {:style (assoc td-cell-style :background (show-color (drop 4 (take 5 (partition-by-five (get @globals :threshold-report))))))} (str (or (drop 4 (take 5 (partition-by-five (get @globals :threshold-report)))) "[]"))]
             ]
             [:tr {:style {:border "1px solid #ddd"}}
              [:td {:style (assoc td-cell-style :font-weight "bold")} "Percent Surplus Prev."]
              [:td {:style td-cell-style} (str (or (take 1 (partition-by-five (get @globals :threshold-report-prev))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 1 (take 2 (partition-by-five (get @globals :threshold-report-prev)))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 2 (take 3 (partition-by-five (get @globals :threshold-report-prev)))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 3 (take 4 (partition-by-five (get @globals :threshold-report-prev)))) "[]"))]
              [:td {:style td-cell-style} (str (or (drop 4 (take 5 (partition-by-five (get @globals :threshold-report-prev)))) "[]"))]
             ]
]
[:p]
;    (show-top-output-councils (get @globals :top-output-councils))
     ]))


(defn home-page []
  (show-globals))

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
