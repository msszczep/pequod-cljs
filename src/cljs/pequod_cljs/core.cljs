(ns pequod-cljs.core
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]))

;; Pequod code

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
         :iteration                0}))

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
      :final-prices (repeat finals (t :init-final-price))
      :input-prices (repeat inputs (t :init-intermediate-price))
      :nature-prices (repeat resources (t :init-nature-price))
      :labor-prices (repeat labor (t :init-labor-price))
      :price-deltas (repeat 4 0.05)
      :pdlist (repeat (+ finals inputs resources labor) 0.05))))

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
                    :utility-exponents %
                    :final-demands (repeat 5 0))
         utility-exponents)))

(defn create-wcs [worker-councils goods industry]
  (->> goods
       (map #(repeat (/ worker-councils 2 (count goods))
                     {:industry industry :product %}))
       flatten))

(defn continue-setup-wcs [intermediate-inputs nature-types labor-types wc]
  "Assumes wc is a map"
  (letfn [(get-random-subset [input-seq]
            (->> input-seq
                 shuffle
                 (take (rand-nth input-seq))
                 sort))]
    (let [production-inputs (vector (get-random-subset intermediate-inputs)
                                    (get-random-subset nature-types)
                                    (get-random-subset labor-types))
          input-exponents (when (pos? (count (first production-inputs)))
                            (let [xz (/ 0.2 (count (first production-inputs)))]
                              (take (count (first production-inputs))
                                    (repeatedly #(+ xz (rand xz))))))
          nature-exponents (let [rz (/ 0.2 (count (second production-inputs)))]
                             (take (count (second production-inputs))
                                   (repeatedly #(+ 0.05 rz (rand rz)))))
          labor-exponents (let [lz (/ 0.2 (count (last production-inputs)))]
                            (take (count (last production-inputs))
                                  (repeatedly #(+ 0.05 lz (rand lz)))))]
      (merge wc {:production-inputs production-inputs
                 :xe 0.05
                 :c 0.05
                 :input-exponents input-exponents
                 :nature-exponents nature-exponents
                 :labor-exponents labor-exponents
                 :cq 0.25
                 :ce 1
                 :du 7
                 :S 1
                 :A 0.25
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

(defn setup [t]
  (let [intermediate-inputs (range 1 (inc (t :inputs)))
        nature-types (range 1 (inc (t :resources)))
        labor-types (range 1 (inc (t :labors)))
        final-goods (range 1 (inc (t :finals)))
        ccs (create-ccs 100 10 4)]
    (-> t
        initialize-prices
        (assoc :price-delta 0.1
               :delta-delay 5
               :final-goods final-goods
               :intermediate-inputs intermediate-inputs
               :nature-types nature-types
               :labor-types labor-types
               :ccs ccs
               :wcs (->> (merge (create-wcs 80 final-goods 0)
                                (create-wcs 80 intermediate-inputs 1))
                         flatten
                         (map (partial continue-setup-wcs
                                       intermediate-inputs
                                       nature-types
                                       labor-types)))
               :lorenz-gini-tuple (update-lorenz-and-gini ccs)))))

(defn consume [final-goods final-prices cc]
  (let [utility-exponents (cc :utility-exponents)
        income (cc :income)
        final-demands (map (fn [final-good]
                             (/ (nth utility-exponents (dec final-good))
                                (* (apply + utility-exponents)
                                   (nth final-prices (dec final-good)))))
                      final-goods)]
    (assoc cc :final-demands final-demands)))

(defn process-plan [t]
  (let [updated-ccs (map (partial consume (t :final-goods) (t :final-prices))
                         (t :ccs))]
    (assoc t :ccs updated-ccs)))

(defn iterate-plan [t]
  (process-plan t))

;; -------------------------
;; Views-

(defn setup-button []
  [:input {:type "button" :value "Setup"
           :on-click #(swap! globals setup globals)}])

(defn iterate-button []
  [:input {:type "button" :value "Iterate"
           :on-click #(swap! globals iterate-plan globals)}])

(defn show-globals []
  [:div " "
    (setup-button)
    "  "
    (iterate-button)
    [:p]
    [:table
     (map (fn [x] [:tr [:td (str (first x))] 
                       [:td (str (second x))]]) 
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
