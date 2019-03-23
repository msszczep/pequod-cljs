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
         (rc/initialize-prices {:finals 4
                                :inputs 4
                                :resources 1
                                :labors 1
                                :init-nature-price 150
                                :init-intermediate-price 100
                                :init-final-price 100
                                :init-labor-price 150}))))

(deftest test-run-ex001
  (let [wcs ex001/wcs
        ccs ex001/ccs
        globals-before @rc/globals
        globals (rc/setup globals-before "ex001")
        iteration01 (rc/iterate-plan globals)]
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
    (is (= ['(100
             97.93423177355632
             96.1931693902596
             94.46157452560949
             92.89024447929954
             91.35051315640834
             89.82035229448819
             88.29749395399459
             86.7934067482776
             85.33550719213511
             83.89757506436841
             82.46004350437023
             81.02736387205545
             79.6497087625157
             78.27744900045317
             76.93572271033449
             75.64308952456302
             74.3655014417849
             73.08990942599927
             71.8233630989341
             70.55687359918394
             69.29075438826288
             68.03793184411201
             66.79615855713017
             65.5603320580855
             64.32677571713114
             63.1102584752614
             61.898767537549745
             60.69140910073596
             59.49805683456463
             58.30801034569494
             57.14093008156429
             55.97859313008183
             54.82739607673318
             53.68813996216939
             52.55187868654938
             51.426377889996346
             50.303115676859555
             49.186905410437284
             48.08437390399401
             46.995618758182836
             45.91583329816204
             44.845278418714585
             43.80336546887714
             42.76491596537796
             41.73867106936439
             40.72361064399308
             39.73677750659119
             38.75090993903557
             37.773776027600064
             36.79679102766194
             35.82127612658898
             34.849988976974416
             33.87977860690743
             32.91332965143635
             31.959361074794206
             31.015146794817067
             30.107331443393537
             29.200759830330224
             28.31186792103696
             27.423847991956013
             26.542980765113715
             25.662143813906248
             24.782162870420926
             23.91557206373209
             23.052084972933287
             22.191401112132645
             21.360492674399545
             20.538476958309825
             19.717951241515273
             18.91195750487521
             18.117759486995848
             17.345120024259003
             16.581881112024384
             15.823690887822655
             15.102094004742403
             14.393066950442387
             13.684509281909765
             12.97802130762506
             12.278799560703472
             11.587357388237843
             10.89776299140559
             10.232251977304664
             9.570330585431682
             8.911070892254843
             8.283431647722866
             7.656542445957269
             7.032041362359352
             6.416693355682377
             5.814891298235379
             5.218766155656274
             4.64005061822425
             4.063457385656261
             3.508886703687613
             2.9544427066334937
             2.4124616592930503
             1.8897318877328206
             1.3753122407568574
             0.8952007157548647
             0.4350188980580932)
            9.163866347527893]
           (:lorenz-gini-tuple iteration01)))
))

;calculate-consumer-utility
;consume
;assign-new-proposal
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
