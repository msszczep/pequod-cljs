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


(deftest proposal-and-solutions
  (let [wc-3 {:effort 0.5, :cq 0.25, :ce 1, :a 0.25, :labor-exponents '(0.3344715806333696), :industry 1, :output 0, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs ['(3) '(1) '(1)], :input-exponents '(0.35704369449641105), :xe 0.05, :s 1, :nature-exponents '(0.40775789400671814)}
        wc-4 {:effort 0.5, :cq 10, :ce 1, :a 10, :labor-exponents [0.2327136009634174], :industry 0, :output 0, :du 2, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[1 2] [1] [1]], :input-exponents [0.18730943766373953 0.1700289432131638], :xe 0.05, :s 1, :nature-exponents [0.1970497669613798]}
        wc-5 {:effort 0.5, :cq 10, :ce 1, :a 10, :labor-exponents [0.24925680571914088], :industry 0, :output 0, :du 2, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[1 2 3] [1] [1]], :input-exponents [0.07540314861302111 0.10098780164832724 0.10718250832816038], :xe 0.05, :s 1, :nature-exponents [0.17057223193493595]}
        wc-6 {:effort 0.5, :cq 10, :ce 1, :a 10, :labor-exponents [0.15813469104723293], :industry 0, :output 0, :du 2, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1]], :input-exponents [0.07136063178539023 0.09705394605246723 0.09837585447317158 0.0516097801370993], :xe 0.05, :s 1, :nature-exponents [0.1978580219892075]}
        final-prices [100 100 100 100]
        input-prices [100 100 100 100]
        nature-prices [150]
        labor-prices [150]]
    (is (= {:effort 40.31122251385122,
            :cq 0.25,
            :ce 1,
            :labor-exponents '(0.3344715806333696),
            :industry 1,
            :output 242164132807.1684,
            :s 1,
            :nature-quantities '(65829557864.94286),
            :du 7,
            :c 0.05,
            :x3 53998013515.14899,
            :product 2,
            :labor-quantities '(53998013515.14899),
            :production-inputs ['(3) '(1) '(1)],
            :input-exponents '(0.35704369449641105),
            :xe 0.05,
            :x1 86463176651.9904,
            :input-quantities '(86463176651.9904),
            :x2 65829557864.94286,
            :nature-exponents '(0.40775789400671814),
            :a 0.25}
           (rc/proposal final-prices input-prices nature-prices labor-prices wc-3)))
    (is (= {:effort 16.30645654793667,
            :cq 10,
            :ce 1,
            :labor-exponents [0.2327136009634174],
            :industry 0,
            :x4 16.500978321509756,
            :output 106.36021005989883,
            :s 1,
            :nature-quantities '(13.972169737510985),
            :du 2,
            :c 0.05,
            :x3 13.972169737510985,
            :product 2,
            :labor-quantities '(16.500978321509756),
            :production-inputs [[1 2] [1] [1]],
            :input-exponents [0.18730943766373953 0.1700289432131638],
            :xe 0.05,
            :x1 19.922271136116837,
            :input-quantities '(19.922271136116837 18.084314116414706),
            :x2 18.084314116414706,
            :nature-exponents [0.1970497669613798],
            :a 10} 
           (rc/proposal final-prices input-prices nature-prices labor-prices wc-4)))
    (is (= {:effort 7.443216445365022,
            :cq 10,
            :ce 1,
            :labor-exponents [0.24925680571914088],
            :industry 0,
            :x4 2.51998735197663,
            :output 22.160588421020304,
            :s 1,
            :nature-quantities '(2.51998735197663),
            :du 2,
            :c 0.05,
            :x3 2.375227452992942,
            :product 1,
            :labor-quantities '(3.6824516551200803),
            :production-inputs [[1 2 3] [1] [1]],
            :input-exponents [0.07540314861302111
                              0.10098780164832724
                              0.10718250832816038],
            :xe 0.05,
            :x1 1.6709781420621876,
            :input-quantities '(1.6709781420621876
                               2.237949107872213
                               2.375227452992942),
            :x5 3.6824516551200803,
            :x2 2.237949107872213,
            :nature-exponents [0.17057223193493595],
            :a 10}
           (rc/proposal final-prices input-prices nature-prices labor-prices wc-5)))
    (is (= {:effort 5.720014296565836,
            :cq 10,
            :ce 1,
            :labor-exponents [0.15813469104723293],
            :industry 0,
            :x4 0.6754391485471135,
            :x6 1.3797173170533052,
            :output 13.087425421167028,
            :s 1,
            :nature-quantities '(1.726301404508923),
            :du 2,
            :c 0.05,
            :x3 1.2874866586612137,
            :product 2,
            :labor-quantities '(1.3797173170533052),
            :production-inputs [[1 2 3 4] [1] [1]],
            :input-exponents [0.07136063178539023
                              0.09705394605246723
                              0.09837585447317158
                              0.0516097801370993],
            :xe 0.05,
            :x1 0.9339269464986589,
            :input-quantities '(0.9339269464986589
                               1.2701862807916346
                               1.2874866586612137
                               0.6754391485471135),
            :x5 1.726301404508923,
            :x2 1.2701862807916346,
            :nature-exponents [0.1978580219892075],
            :a 10}
           (rc/proposal final-prices input-prices nature-prices labor-prices wc-6)))))


(deftest test-run-ex001
  (let [wcs ex001/wcs
        ccs ex001/ccs
        globals-before @rc/globals
        placeholder {}
        globals (rc/setup globals-before placeholder "ex001")
        iteration01 (rc/iterate-plan globals)
        it01-remainder (rc/rest-of-to-do iteration01)
        iteration02 (rc/iterate-plan it01-remainder)
        it02-remainder (rc/rest-of-to-do iteration02)]
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
    (is (= [[2518.329943276917
             290.7735198503314
             502.544207919467
             15819.109593177775]
            [808.3964206510301
             1562.2503026323645
             393.9958247826109
             293.4387354891415]
            [1000]
            [1000]] 
           (:supply-list iteration01)))
    (is (= [[1216.2969702567548
             1247.5799256408354
             1257.5470414490735
             1278.576062653336]
            [4467.779622969347
             2036.819022871612
             1203.2857552447695
             842.5717255704171]
            [3300.0700581744586]
            [3245.0589864868302]] 
           (:demand-list iteration01)))
    (is (= [99.5 100.49999999999999 100.49999999999999 99.5] 
           (:final-prices iteration01)))
    (is (= [1.1711822475769802
            0.9462647444749942
            1.069782597519318
            1.0577280521347097] 
           (:price-deltas iteration01)))
    (is (= [0.6972760616605689
            -1.243935726987648
            -0.8579132858031937
            1.7008773963001753
            -1.3871346111519591
            -0.2637174654438165
            -1.013334080329513
            -0.966774530525183
            -1.069782597519318
            -1.0577280521347097]
           (:pdlist iteration01)))
    (is (= [[0.4350188980580932
             0.8952007157548647
             1.3753122407568574
             1.8897318877328206
             2.4124616592930503
             2.9544427066334937
             3.508886703687613
             4.063457385656261
             4.64005061822425
             5.218766155656274
             5.814891298235379
             6.416693355682377
             7.032041362359352
             7.656542445957269
             8.283431647722866
             8.911070892254843
             9.570330585431682
             10.232251977304664
             10.89776299140559
             11.587357388237843
             12.278799560703472
             12.97802130762506
             13.684509281909765
             14.393066950442387
             15.102094004742403
             15.823690887822655
             16.581881112024384
             17.345120024259003
             18.117759486995848
             18.91195750487521
             19.717951241515273
             20.538476958309825
             21.360492674399545
             22.191401112132645
             23.052084972933287
             23.91557206373209
             24.782162870420926
             25.662143813906248
             26.542980765113715
             27.423847991956013
             28.31186792103696
             29.200759830330224
             30.107331443393537
             31.015146794817067
             31.959361074794206
             32.91332965143635
             33.87977860690743
             34.849988976974416
             35.82127612658898
             36.79679102766194
             37.773776027600064
             38.75090993903557
             39.73677750659119
             40.72361064399308
             41.73867106936439
             42.76491596537796
             43.80336546887714
             44.845278418714585
             45.91583329816204
             46.995618758182836
             48.08437390399401
             49.186905410437284
             50.303115676859555
             51.426377889996346
             52.55187868654938
             53.68813996216939
             54.82739607673318
             55.97859313008183
             57.14093008156429
             58.30801034569494
             59.49805683456463
             60.69140910073596
             61.898767537549745
             63.1102584752614
             64.32677571713114
             65.5603320580855
             66.79615855713017
             68.03793184411201
             69.29075438826288
             70.55687359918394
             71.8233630989341
             73.08990942599927
             74.3655014417849
             75.64308952456302
             76.93572271033449
             78.27744900045317
             79.6497087625157
             81.02736387205545
             82.46004350437023
             83.89757506436841
             85.33550719213511
             86.7934067482776
             88.29749395399459
             89.82035229448819
             91.35051315640834
             92.89024447929954
             94.46157452560949
             96.1931693902596
             97.93423177355632
             100]
            9.163866347527893]
           (:lorenz-gini-tuple iteration01)))
        (is (= 27591.879630626994
               (rc/total-surplus (:surplus-list it01-remainder))))
        (is (= 0.1
               (:price-delta it01-remainder)))
        (is (= 4
               (:delta-delay it01-remainder)))
        (is (= ;[110.55 103.15036052771042 110.55 110.2160840317781]
               [110.55 103.15036052771033 110.55 110.21608403177807]
               (:input-prices it02-remainder)))
        (is (= ;[-3316.9865085951824 -346.48809252699334 -749.8419302463965 -539.0844309509484]
                [-3316.986508595146 -346.48809252698015 -749.8419302463902 -539.0844309509438]
               (:input-surpluses it02-remainder)))
        (is (= [165.825]
               (:labor-prices it02-remainder)))
        (is (= ;[-2043.4963315576038] 
                [-2043.4963315575937]
               (:labor-surpluses it02-remainder)))
        (is (= [165.825]
               (:nature-prices it02-remainder)))
        (is (= ;[-2089.3078897631785]
               [-2089.307889763159]
               (:nature-surpluses it02-remainder)))
        (is (= ;[0.6435769241525859 -1.2405579108180442 -0.8534157447332376 1.6796277920424052 -1.3443413179471868 -0.19953173534130858 -0.9749653569614951 -0.9573192477684649 -1.0218393655285152 -1.0107571091923926]
               [0.6435769241525822 -1.240557910818045 -0.8534157447332403 1.6796277920424048 -1.344341317947182 -0.19953173534130186 -0.9749653569614913 -0.9573192477684612 -1.0218393655285103 -1.0107571091923901]
               (:pdlist it02-remainder)))
        (is (= ;[1.12795916612731 0.8945665182379948 1.0218393655285152 1.0107571091923924]
               [1.1279591661273085 0.894566518237989 1.0218393655285103 1.0107571091923901]
               (:price-deltas it02-remainder)))))


(deftest consume
  (let [cc {:effort 1, :num-workers 10, :utility-exponents [0.2379038877985112 0.23517856300054907 0.22661946853423187 0.2450284522738656], :final-demands '(0 0 0 0 0), :cy 10.037467640470766, :income 5000}
        final-goods [1 2 3 4] 
        final-prices [100 100 100 100]]
    (is (= {:effort 1,
            :num-workers 10,
            :utility-exponents [0.2379038877985112
                                0.23517856300054907
                                0.22661946853423187
                                0.2450284522738656],
            :final-demands '(12.591099796749074
                            12.446861563287504
                            11.993870174233471
                            12.96816846572995),
            :cy 10.037467640470766,
            :income 5000}
           (rc/consume final-goods final-prices cc)))))


(deftest test-home
  (with-mounted-component (rc/home-page)
    (fn [c div]
      (is (found-in #"Welcome to" div)))))
