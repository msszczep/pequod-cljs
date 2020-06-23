(ns pequod-cljs.csvgen
   (:require [pequod-cljs.dep1ex01 :as ex01]
             [pequod-cljs.dep1ex02 :as ex02]
             [pequod-cljs.dep1ex03 :as ex03]
             [pequod-cljs.dep1ex04 :as ex04]
             [pequod-cljs.dep1ex05 :as ex05]
             [pequod-cljs.dep1ex06 :as ex06]
             [pequod-cljs.dep1ex07 :as ex07]
             [pequod-cljs.dep1ex08 :as ex08]
             [pequod-cljs.dep1ex09 :as ex09]
             [pequod-cljs.dep1ex10 :as ex10]
             [pequod-cljs.dep1ex11 :as ex11]
             [pequod-cljs.dep1ex12 :as ex12]
             [pequod-cljs.dep1ex13 :as ex13]
             [pequod-cljs.dep1ex14 :as ex14]
             [pequod-cljs.dep1ex15 :as ex15]
             [pequod-cljs.dep1ex16 :as ex16]
             [pequod-cljs.dep1ex17 :as ex17]
             [pequod-cljs.dep1ex18 :as ex18]
             [pequod-cljs.dep1ex19 :as ex19]
             [pequod-cljs.dep1ex20 :as ex20]
             [pequod-cljs.dep1ex21 :as ex21]
             [pequod-cljs.dep1ex22 :as ex22]
             [pequod-cljs.dep1ex23 :as ex23]
             [pequod-cljs.dep1ex24 :as ex24]
             [pequod-cljs.dep1ex25 :as ex25]
             [pequod-cljs.dep1ex26 :as ex26]
             [pequod-cljs.dep1ex27 :as ex27]
             [pequod-cljs.dep1ex28 :as ex28]
             [pequod-cljs.dep1ex29 :as ex29]
             [pequod-cljs.dep1ex30 :as ex30]
             [pequod-cljs.dep1ex31 :as ex31]
             [pequod-cljs.dep1ex32 :as ex32]
             [pequod-cljs.dep1ex33 :as ex33]
             [pequod-cljs.dep1ex34 :as ex34]
             [pequod-cljs.dep1ex35 :as ex35]
             [pequod-cljs.dep1ex36 :as ex36]
             [pequod-cljs.dep1ex37 :as ex37]
             [pequod-cljs.dep1ex38 :as ex38]
             [pequod-cljs.dep1ex39 :as ex39]
             [pequod-cljs.dep1ex40 :as ex40]
             [pequod-cljs.dep1ex41 :as ex41]
             [pequod-cljs.dep1ex42 :as ex42]
             [pequod-cljs.dep1ex43 :as ex43]
             [pequod-cljs.dep1ex44 :as ex44]
             [pequod-cljs.dep1ex45 :as ex45]
             [pequod-cljs.dep1ex46 :as ex46]
             [pequod-cljs.dep1ex47 :as ex47]
             [pequod-cljs.dep1ex48 :as ex48]
             [pequod-cljs.dep1ex49 :as ex49]
             [pequod-cljs.dep1ex50 :as ex50]
             [pequod-cljs.dep1ex51 :as ex51]
             [pequod-cljs.dep1ex52 :as ex52]
             [pequod-cljs.dep1ex53 :as ex53]
             [pequod-cljs.dep1ex54 :as ex54]
             [pequod-cljs.dep1ex55 :as ex55]
             [pequod-cljs.dep1ex56 :as ex56]
             [pequod-cljs.dep1ex57 :as ex57]
             [pequod-cljs.dep1ex58 :as ex58]
             [pequod-cljs.dep1ex59 :as ex59]
             [pequod-cljs.dep1ex60 :as ex60]
             [pequod-cljs.dep1ex61 :as ex61]
             [pequod-cljs.dep1ex62 :as ex62]
             [pequod-cljs.dep1ex63 :as ex63]
             [pequod-cljs.dep1ex64 :as ex64]
             [pequod-cljs.dep1ex65 :as ex65]
             [pequod-cljs.dep1ex66 :as ex66]
             [pequod-cljs.dep1ex67 :as ex67]
             [pequod-cljs.dep1ex68 :as ex68]
             [pequod-cljs.dep1ex69 :as ex69]
             [pequod-cljs.dep1ex70 :as ex70]
             [pequod-cljs.dep1ex71 :as ex71]
             [pequod-cljs.dep1ex72 :as ex72]
             [pequod-cljs.dep1ex73 :as ex73]
             [pequod-cljs.dep1ex74 :as ex74]
             [pequod-cljs.dep1ex75 :as ex75]
             [pequod-cljs.dep1ex76 :as ex76]
             [pequod-cljs.dep1ex77 :as ex77]
             [pequod-cljs.dep1ex78 :as ex78]
             [pequod-cljs.dep1ex79 :as ex79]
             [pequod-cljs.dep1ex80 :as ex80]
             [pequod-cljs.dep1ex81 :as ex81]
             [pequod-cljs.dep1ex82 :as ex82]
             [pequod-cljs.dep1ex83 :as ex83]
             [pequod-cljs.dep1ex84 :as ex84]
             [pequod-cljs.dep1ex85 :as ex85]
             [pequod-cljs.dep1ex86 :as ex86]
             [pequod-cljs.dep1ex87 :as ex87]
             [pequod-cljs.dep1ex88 :as ex88]
             [pequod-cljs.dep1ex89 :as ex89]
             [pequod-cljs.dep1ex90 :as ex90]
             [pequod-cljs.dep1ex91 :as ex91]
             [pequod-cljs.dep1ex92 :as ex92]
             [pequod-cljs.dep1ex93 :as ex93]
             [pequod-cljs.dep1ex94 :as ex94]
             [pequod-cljs.dep1ex95 :as ex95]
             [pequod-cljs.dep1ex96 :as ex96]
             [pequod-cljs.dep1ex97 :as ex97]
             [pequod-cljs.dep1ex98 :as ex98]
             [pequod-cljs.dep1ex99 :as ex99]
             [pequod-cljs.dep1ex100 :as ex100]
             [pequod-cljs.ex081 :as ex081]
))

(def globals
  (atom {:init-private-good-price 700
         :init-intermediate-price 700
         :init-labor-price        700
         :init-nature-price       700
         :init-public-good-price  700

         :private-goods             100
         :intermediate-inputs       100
         :resources                 100
         :labors                    100
         :public-goods              100
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

(defn abs [n] (max n (- n)))

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
      :price-deltas (vec (repeat 100 0.05))
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
                            [(- 0.002) (- 0.001) 0 0.001 0.002])]
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

(defn augmented-reset [t _]
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
                      (case experiment
                        "ex01" ex01/ccs
                        "ex02" ex02/ccs
                        "ex03" ex03/ccs
                        "ex04" ex04/ccs
                        "ex05" ex05/ccs
                        "ex06" ex06/ccs
                        "ex07" ex07/ccs
                        "ex08" ex08/ccs
                        "ex09" ex09/ccs
                        "ex10" ex10/ccs
                        "ex11" ex11/ccs
                        "ex12" ex12/ccs
                        "ex13" ex13/ccs
                        "ex14" ex14/ccs
                        "ex15" ex15/ccs
                        "ex16" ex16/ccs
                        "ex17" ex17/ccs
                        "ex18" ex18/ccs
                        "ex19" ex19/ccs
                        "ex20" ex20/ccs
                        "ex21" ex21/ccs
                        "ex22" ex22/ccs
                        "ex23" ex23/ccs
                        "ex24" ex24/ccs
                        "ex25" ex25/ccs
                        "ex26" ex26/ccs
                        "ex27" ex27/ccs
                        "ex28" ex28/ccs
                        "ex29" ex29/ccs
                        "ex30" ex30/ccs
                        "ex31" ex31/ccs
                        "ex32" ex32/ccs
                        "ex33" ex33/ccs
                        "ex34" ex34/ccs
                        "ex35" ex35/ccs
                        "ex36" ex36/ccs
                        "ex37" ex37/ccs
                        "ex38" ex38/ccs
                        "ex39" ex39/ccs
                        "ex40" ex40/ccs
                        "ex41" ex41/ccs
                        "ex42" ex42/ccs
                        "ex43" ex43/ccs
                        "ex44" ex44/ccs
                        "ex45" ex45/ccs
                        "ex46" ex46/ccs
                        "ex47" ex47/ccs
                        "ex48" ex48/ccs
                        "ex49" ex49/ccs
                        "ex50" ex50/ccs
                        "ex51" ex51/ccs
                        "ex52" ex52/ccs
                        "ex53" ex53/ccs
                        "ex54" ex54/ccs
                        "ex55" ex55/ccs
                        "ex56" ex56/ccs
                        "ex57" ex57/ccs
                        "ex58" ex58/ccs
                        "ex59" ex59/ccs
                        "ex60" ex60/ccs
                        "ex61" ex61/ccs
                        "ex62" ex62/ccs
                        "ex63" ex63/ccs
                        "ex64" ex64/ccs
                        "ex65" ex65/ccs
                        "ex66" ex66/ccs
                        "ex67" ex67/ccs
                        "ex68" ex68/ccs
                        "ex69" ex69/ccs
                        "ex70" ex70/ccs
                        "ex71" ex71/ccs
                        "ex72" ex72/ccs
                        "ex73" ex73/ccs
                        "ex74" ex74/ccs
                        "ex75" ex75/ccs
                        "ex76" ex76/ccs
                        "ex77" ex77/ccs
                        "ex78" ex78/ccs
                        "ex79" ex79/ccs
                        "ex80" ex80/ccs
                        "ex81" ex81/ccs
                        "ex82" ex82/ccs
                        "ex83" ex83/ccs
                        "ex84" ex84/ccs
                        "ex85" ex85/ccs
                        "ex86" ex86/ccs
                        "ex87" ex87/ccs
                        "ex88" ex88/ccs
                        "ex89" ex89/ccs
                        "ex90" ex90/ccs
                        "ex91" ex91/ccs
                        "ex92" ex92/ccs
                        "ex93" ex93/ccs
                        "ex94" ex94/ccs
                        "ex95" ex95/ccs
                        "ex96" ex96/ccs
                        "ex97" ex97/ccs
                        "ex98" ex98/ccs
                        "ex99" ex99/ccs
                        "ex100" ex100/ccs
                        "ex081" ex081/ccs
                        ex06/ccs))
               :wcs (add-ids
                      (case experiment
                        "ex01" ex01/wcs
                        "ex02" ex02/wcs
                        "ex03" ex03/wcs
                        "ex04" ex04/wcs
                        "ex05" ex05/wcs
                        "ex06" ex06/wcs
                        "ex07" ex07/wcs
                        "ex08" ex08/wcs
                        "ex09" ex09/wcs
                        "ex10" ex10/wcs
                        "ex11" ex11/wcs
                        "ex12" ex12/wcs
                        "ex13" ex13/wcs
                        "ex14" ex14/wcs
                        "ex15" ex15/wcs
                        "ex16" ex16/wcs
                        "ex17" ex17/wcs
                        "ex18" ex18/wcs
                        "ex19" ex19/wcs
                        "ex20" ex20/wcs
                        "ex21" ex21/wcs
                        "ex22" ex22/wcs
                        "ex23" ex23/wcs
                        "ex24" ex24/wcs
                        "ex25" ex25/wcs
                        "ex26" ex26/wcs
                        "ex27" ex27/wcs
                        "ex28" ex28/wcs
                        "ex29" ex29/wcs
                        "ex30" ex30/wcs
                        "ex31" ex31/wcs
                        "ex32" ex32/wcs
                        "ex33" ex33/wcs
                        "ex34" ex34/wcs
                        "ex35" ex35/wcs
                        "ex36" ex36/wcs
                        "ex37" ex37/wcs
                        "ex38" ex38/wcs
                        "ex39" ex39/wcs
                        "ex40" ex40/wcs
                        "ex41" ex41/wcs
                        "ex42" ex42/wcs
                        "ex43" ex43/wcs
                        "ex44" ex44/wcs
                        "ex45" ex45/wcs
                        "ex46" ex46/wcs
                        "ex47" ex47/wcs
                        "ex48" ex48/wcs
                        "ex49" ex49/wcs
                        "ex50" ex50/wcs
                        "ex51" ex51/wcs
                        "ex52" ex52/wcs
                        "ex53" ex53/wcs
                        "ex54" ex54/wcs
                        "ex55" ex55/wcs
                        "ex56" ex56/wcs
                        "ex57" ex57/wcs
                        "ex58" ex58/wcs
                        "ex59" ex59/wcs
                        "ex60" ex60/wcs
                        "ex61" ex61/wcs
                        "ex62" ex62/wcs
                        "ex63" ex63/wcs
                        "ex64" ex64/wcs
                        "ex65" ex65/wcs
                        "ex66" ex66/wcs
                        "ex67" ex67/wcs
                        "ex68" ex68/wcs
                        "ex69" ex69/wcs
                        "ex70" ex70/wcs
                        "ex71" ex71/wcs
                        "ex72" ex72/wcs
                        "ex73" ex73/wcs
                        "ex74" ex74/wcs
                        "ex75" ex75/wcs
                        "ex76" ex76/wcs
                        "ex77" ex77/wcs
                        "ex78" ex78/wcs
                        "ex79" ex79/wcs
                        "ex80" ex80/wcs
                        "ex81" ex81/wcs
                        "ex82" ex82/wcs
                        "ex83" ex83/wcs
                        "ex84" ex84/wcs
                        "ex85" ex85/wcs
                        "ex86" ex86/wcs
                        "ex87" ex87/wcs
                        "ex88" ex88/wcs
                        "ex89" ex89/wcs
                        "ex90" ex90/wcs
                        "ex91" ex91/wcs
                        "ex92" ex92/wcs
                        "ex93" ex93/wcs
                        "ex94" ex94/wcs
                        "ex95" ex95/wcs
                        "ex96" ex96/wcs
                        "ex97" ex97/wcs
                        "ex98" ex98/wcs
                        "ex99" ex99/wcs
                        "ex100" ex100/wcs
                        "ex081" ex081/wcs
                        ex06/wcs))
))))

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
  (max 0.001 (min price-delta (abs (* price-delta (nth pdlist J))))))

#_(defn get-deltas [J price-delta pdlist]
  (max 0.001 (min price-delta (abs (nth pdlist J)))))

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
            price-delta-to-use (- 1.05 (Math/pow 0.5 (/ (abs (* 2 surplus)) (+ demand supply))))
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
         (mapv #(abs (/ (first %) (last %)))))))

(defn update-pdlist [supply-list demand-list surplus-list]
  (letfn [(force-to-one [n]
            (let [cap 0.25]
              (if (or (> n cap) (< n (- cap))) cap (abs n))))]
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
       (mapv #(* 100 (/ (abs (* 2 (first %))) (+ (second %) (last %)))))))

(defn compute-gdp [supply-list private-good-prices public-good-prices]
  (let [[private-good-supply _ _ _ public-good-supply] supply-list]
    (->> public-good-prices
         (concat private-good-prices)
         (interleave (concat private-good-supply public-good-supply))
         (partition 2)
         (map (fn [[a b]] (* a b)))
         (apply +))))

(defn show-color [tre]
  (cond (empty? tre) :red
        (every? #(< % 3) tre) :blue
        (every? #(< % 5) tre) :green
        (every? #(< % 10) tre) :yellow
        (every? #(< % 20) tre) :orange
        :else :red))

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
        color (show-color threshold-report)]
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
              :color color)))

(defn create-toothache [wc]
  (if (:toothache wc)
    (assoc wc :input-exponents (mapv (partial + 0.1) (:input-exponents wc))
              :nature-exponents (mapv (partial + 0.1) (:nature-exponents wc))
              :labor-exponents (mapv (partial + 0.1) (:labor-exponents wc)))
    wc))

(defn change-toothache-polarity [ids wc]
  (if (contains? ids (:id wc))
    (assoc wc :toothache true)
    wc))

(defn create-toothaches [t _]
  (let [toothache-percentage 0.2
        ids-to-use (->> t
                        :wcs
                        (map :id)
                        shuffle
                        (take (* toothache-percentage (count (:wcs t))))
                        set)
        wcs-to-use (mapv (partial change-toothache-polarity ids-to-use) 
                         (:wcs t))]
    (assoc t :wcs (mapv create-toothache wcs-to-use))))

(defn rest-of-to-do [t]
  (let [delta-delay (:delta-delay t)
        delta-delay (if (pos? delta-delay)
                      (dec delta-delay) delta-delay)
        t-updated (assoc t :delta-delay delta-delay)]
    t-updated))

(defn proceed [t _]
  (let [t-plus (iterate-plan t)]
    (rest-of-to-do t-plus)))

; time lein run -m pequod-cljs.csvgen

(defn sum-wc-exponents [wc]
  (reduce +
          (concat (vector (:c wc))
            (:labor-exponents wc)
            (:input-exponents wc)
            (:nature-exponents wc))))

(defn print-csv [args-to-print data]
  (let [all-args (flatten (mapv (partial get data) args-to-print))
        outputs (map :output (get data :wcs))
        efforts (map :effort (get data :wcs))
        wcs     (interleave outputs efforts)]
    (clojure.string/join "," (concat all-args wcs))))

(defn get-csv-header [data]
  (let [spaces-map [:private-good-prices
                    :intermediate-good-prices
                    :nature-prices
                    :labor-prices
                    :public-good-prices
                    :new-deltas-private-goods
                    :new-deltas-intermediate-goods
                    :new-deltas-nature
                    :new-deltas-labor
                    :new-deltas-public-goods
                    :pdlist-private-goods
                    :pdlist-intermediate-goods
                    :pdlist-nature
                    :pdlist-labor
                    :pdlist-public-goods
                    :supply-private-goods
                    :supply-intermediate-goods
                    :supply-nature
                    :supply-labor
                    :supply-public-goods
                    :demand-private-goods
                    :demand-intermediate-goods
                    :demand-nature
                    :demand-labor
                    :demand-public-goods
                    :surplus-private-goods
                    :surplus-intermediate-goods
                    :surplus-nature
                    :surplus-labor
                    :surplus-public-goods
                    :threshold-report-private-goods
                    :threshold-report-intermediate-goods
                    :threshold-report-nature
                    :threshold-report-labor
                    :threshold-report-public-goods]
        headers1 (for [k spaces-map
                       n (range 1 101)] 
                   (str k "-" n))
        headers2 (->> :wcs
                      (get data)
                      (mapv :id)
                      sort
                      (mapv #(vector (str "wc_" % "_output") (str "wc_" % "_effort")))
                      flatten)]
    (concat [:iteration :color] headers1 headers2)))

(defn -main [& [ns-to-use]]
  (let [keys-to-print [:iteration :color :private-good-prices :intermediate-good-prices :nature-prices :labor-prices :public-good-prices :private-good-new-deltas :intermediate-good-new-deltas :nature-new-deltas :labor-new-deltas :public-good-new-deltas :pdlist :supply-list :demand-list :surplus-list :threshold-report]
        spacing-count 3501 ; (50 * 5) + (100 * 6) + 2 - 1
        toothaches? false]
    (do
      (swap! globals setup globals ns-to-use)
      (if toothaches?
        (swap! globals create-toothaches globals))
      (swap! globals proceed globals)
      (println (clojure.string/join "," (get-csv-header @globals)))
      (println (print-csv keys-to-print @globals))
      (while (and (some #(> % 3) (get @globals :threshold-report))
                  (> 200 (get @globals :iteration)))
        (do
          (swap! globals proceed globals) 
          (println (print-csv keys-to-print @globals))))
      (println (clojure.string/join "," (concat ["exponent-sum"]
                                                (repeat spacing-count "") 
                                                (interleave (map sum-wc-exponents (sort-by :id (get @globals :wcs)))
                                                            (repeat "")))))
      (swap! globals augmented-reset globals)
      (do
        (swap! globals proceed globals) 
        (println (print-csv keys-to-print @globals)))
      (while (and (some #(> % 3) (get @globals :threshold-report))
                  (> 200 (get @globals :iteration)))
        (do
          (swap! globals proceed globals) 
          (println (print-csv keys-to-print @globals))))
      (println (clojure.string/join "," (concat ["exponent-sum"]
                                                (repeat spacing-count "") 
                                                (interleave (map sum-wc-exponents (sort-by :id (get @globals :wcs)))
                                                            (repeat ""))))))))

