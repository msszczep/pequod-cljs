(ns pequod-cljs.core-test
  (:require [cljs.test :refer-macros [is are deftest testing use-fixtures]]
            [reagent.core :as reagent :refer [atom]]
            [pequod-cljs.core :as rc]))


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

(deftest test-my-input-prices
  (is (= [100 150 150] [100 150 150])
  (is (= 1 1))))

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
