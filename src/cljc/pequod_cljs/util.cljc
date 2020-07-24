(ns pequod-cljs.util)

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
      :price-deltas (vec (repeat private-goods 0.05))
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

(defn augmented-reset [t]
  (assoc t :iteration 0
           :ccs (mapv augment-cc (:ccs t))
           :wcs (mapv augment-wc (:wcs t))
           :last-years-supply (:supply-list t)
           :last-years-private-good-prices (:private-good-prices t)
           :last-years-public-good-prices (:public-good-prices t)))

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
