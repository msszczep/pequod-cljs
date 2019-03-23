(ns pequod-cljs.core-test
  (:require [cljs.test :refer-macros [is are deftest testing use-fixtures]]
            [reagent.core :as reagent :refer [atom]]
            [pequod-cljs.core :as rc]
            [pequod-cljs.ex001data :as ex001]))


(def isClient (not (nil? (try (.-document js/window)
                              (catch js/Object e nil)))))

(def rflush reagent/flush)

(defn add-test-div [name]
  (let [doc     js/document
        body    (.-body js/document)
        div     (.createElement doc "div")]
    (.appendChild body div)
    div))

(defn with-mounted-component [comp f]
  (when isClient
    (let [div (add-test-div "_testreagent")]
      (let [comp (reagent/render-component comp div #(f comp div))]
        (reagent/unmount-component-at-node div)
        (reagent/flush)
        (.removeChild (.-body js/document) div)))))


(defn found-in [re div]
  (let [res (.-innerHTML div)]
    (if (re-find re res)
      true
      (do (println "Not found: " res)
          false))))


(deftest mean
  (is (= 4 (rc/mean [1 4 7]))))

(deftest initialize-prices
    (is (= {:init-nature-price 150,
	             :labors 1,
	             :labor-prices [150],
	             :finals 4,
	             :pdlist [0.05 0.05 0.05 0.05 0.05 0.05 0.05 0.05 0.05 0.05],
	             :inputs 4,
	             :init-intermediate-price 100,
	             :init-final-price 100,
	             :price-deltas [0.05 0.05 0.05 0.05],
	             :nature-prices [150],
	             :resources 1,
	             :final-prices [100 100 100 100],
	             :init-labor-price 150,
	             :input-prices [100 100 100 100]}
(rc/initialize-prices {:finals 4 :inputs 4 :resources 1 :labors 1 :init-nature-price 150 :init-intermediate-price 100 :init-final-price 100 :init-labor-price 150}))))

(deftest test-run-ex001
  (let [wcs ex001/wcs
        ccs ex001/ccs
        globals-before @rc/globals
        globals (rc/setup globals-before "ex001")
        ]
    (is (= 100 (count ccs)))
    (is (= 80 (count wcs)))
    (is (= {:init-nature-price 150,
            :delta-delay 0,
            :old-labor-prices [],
            :input-surpluses [],
            :labors 1,
            :threshold-met false,
            :labor-supply 0,
            :natural-resources-supply 0,
            :lorenz-gini-tuple [],
            :old-input-prices [],
            :labor-prices [],
            :nature-surpluses [],
            :finals 4,
            :labor-surpluses [],
            :iteration 0,
            :old-final-prices [],
            :pdlist [],
            :inputs 4,
            :init-intermediate-price 100,
            :init-final-price 100,
            :price-deltas [],
            :old-nature-prices [],
            :price-delta 0,
            :nature-prices [],
            :resources 1,
            :final-surpluses [],
            :wcs [],
            :final-prices [],
            :ccs [],
            :init-labor-price 150,
            :input-prices []}
           globals-before))
    (is (= {:input-prices [100 100 100 100],
            :init-labor-price 150,
            :pdlist [0.05 0.05 0.05 0.05 0.05 0.05 0.05 0.05 0.05 0.05],
            :inputs 4,
            :init-intermediate-price 100,
            :init-final-price 100}
           (select-keys globals
                        [:input-prices :init-labor-price :pdlist :inputs
                         :init-intermediate-price :init-final-price])))
))


;calculate-consumer-utility
;update-lorenz-and-gini
;consume
;assign-new-proposal
;solution-1
;solution-2
;solution-3
;solution-4
;solution-5
;solution-6
;get-input-quantity
;get-deltas
;update-surpluses-prices
;proposal
;price-change
;other-price-change
;get-demand-list
;get-supply-list
;iterate-plan
;check-surpluses
;total-surplus
;raise-delta
;lower-delta
;rest-of-to-do
;proceed


;turtle 101
;{:effort 0.5, :cq 0.25, :ce 1, :A 0.25, :labor-exponents (0.3344715806333696), :industry 1, :output 0, :du 7, :c 0.05, :product 2, :lab
;or-quantities [0], :production-inputs [(3) (1) (1)], :input-exponents (0.35704369449641105), :xe 0.05, :S 1, :nature-exponents (0.40775
;789400671814)}

;turtle 110
;{:effort 0.5, :cq 10, :ce 1, :A 10, :labor-exponents [0.2327136009634174], :industry 0, :output 0, :du 2, :c 0.05, :product 2, :labor-q
;uantities [0], :production-inputs [[1 2] [1] [1]], :input-exponents [0.18730943766373953 0.1700289432131638], :xe 0.05, :S 1, :nature-e
;xponents [0.1970497669613798]}

;turtle 101
;{:effort 0.5, :cq 10, :ce 1, :A 10, :labor-exponents [0.24925680571914088], :industry 0, :output 0, :du 2, :c 0.05, :product 1, :labor-
;quantities [0], :production-inputs [[1 2 3] [1] [1]], :input-exponents [0.07540314861302111 0.10098780164832724 0.10718250832816038], :
;xe 0.05, :S 1, :nature-exponents [0.17057223193493595]}

;(sort (proposal [100 100 100 100] [150] [150] wc))

;:input-prices	[100 100 100 100]
;:nature-prices  [150]
;:labor-prices   [150] 


(deftest test-home
  (with-mounted-component (rc/home-page)
    (fn [c div]
      (is (found-in #"Welcome to" div)))))
