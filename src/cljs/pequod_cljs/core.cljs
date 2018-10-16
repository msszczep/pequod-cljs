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
         :wcs                      []
         :ccs                      []}))

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
        cz (/ 0.5 finals)]
    (repeat consumer-councils
            {:num-workers workers-per-council
             :effort effort
             :income (* 500 effort workers-per-council)
             :cy (+ 6 (rand 9))
             :utility-exponents (take finals (repeatedly #(+ cz (rand cz))))
             :final-demands (repeat 5 0)})))

(defn setup [t]
  (-> t
      initialize-prices
      (assoc
        :price-delta 0.1
        :delta-delay 5
        :final-goods (range 1 (inc (t :finals)))
        :intermediate-inputs (range 1 (inc (t :inputs)))
        :nature-types (range 1 (inc (t :resources)))
        :labor-types (range 1 (inc (t :labors)))
        :ccs (create-ccs 100 10 4))))

;; -------------------------
;; Views-

(defn setup-button []
  [:input {:type "button" :value "Setup"
           :on-click #(swap! globals setup globals)}])


(defn show-globals []
  [:div " "
    (setup-button)
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
