(ns pequod-cljs.ex1dot3)

(def ccs 
  [{:effort 1,
     :num-workers 10,
     :utility-exponents
     [0.09498778166135498
      0.16444748727973574
      0.1019007055987225
      0.13714894052661772],
     :final-demands [0 0 0 0 0],
     :cy 8.596785144920721,
     :public-good-exponents [0.14771475742304319],
     :public-good-demands [0],
     :pollution-supply-coefficients [0.013630132633780878],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.15020580240591397
     0.14770589526164632
     0.1155275644378859
     0.10577409801902823],
    :final-demands [0 0 0 0 0],
    :cy 13.210069105541145,
    :public-good-exponents [0.16520783255838625],
    :public-good-demands [0],
    :pollution-supply-coefficients [0.010742689131548218],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.16357516689778268
     0.12876676209345828
     0.10734007960330016
     0.1130748152670023],
    :final-demands [0 0 0 0 0],
    :cy 13.289499005460048,
    :public-good-exponents [0.15009434893560414],
    :public-good-demands [0],
    :pollution-supply-coefficients [0.012716094718422474],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.09629434774662779
     0.10846321431183673
     0.11416213914207335
     0.12658039322754996],
    :final-demands [0 0 0 0 0],
    :cy 7.813405125505837,
    :public-good-exponents [0.13602086388305695],
    :public-good-demands [0],
    :pollution-supply-coefficients [0.01274174117492849],
    :income 5000}
   {:effort 1,
    :num-workers 10,
    :utility-exponents
    [0.12753355701743596
     0.1392341665885359
     0.12674949229608565
     0.16189708187159813],
    :final-demands [0 0 0 0 0],
    :cy 8.223640437836657,
    :public-good-exponents [0.13881594805929337],
    :public-good-demands [0],
    :pollution-supply-coefficients [0.012964976661909236],
    :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.09869870164087885
   0.13072040187662176
   0.0972647823869155
   0.1442169646552604],
  :final-demands [0 0 0 0 0],
  :cy 10.906918330706567,
  :public-good-exponents [0.11721847430891688],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.014460379316641123],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.1472406680684749
   0.13166348992089946
   0.14842292262347057
   0.13679963318090108],
  :final-demands [0 0 0 0 0],
  :cy 11.556307900860833,
  :public-good-exponents [0.10031833592418571],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.016424883810893548],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.11414807259534107
   0.09938132708319916
   0.12731307655700738
   0.12082932165380851],
  :final-demands [0 0 0 0 0],
  :cy 10.738906887378675,
  :public-good-exponents [0.13911741348980944],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.012186419646421268],
  :income 5000}
 {:effort 1,
  :num-workers 10,
  :utility-exponents
  [0.11941688873767872
   0.13139011942560413
   0.13200560619546753
   0.09729662711245107],
  :final-demands [0 0 0 0 0],
  :cy 12.671774927186728,
  :public-good-exponents [0.16638170929702867],
  :public-good-demands [0],
  :pollution-supply-coefficients [0.014072199063819635], 
  :income 5000}])

  (def wcs
[#_{:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.09935352394548516],
  :industry 2,
  :pollutant-exponents [0.010718992535000137],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1] [1] [1]],
  :input-exponents
  [0.04329853795869807
   0.046309813318193276
   0.010661673691089668
   0.0028467943221126],
  :xe 0.05,
  :nature-exponents [0.1293192056482164],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.1028135837115091846],
  :industry 1,
  :pollutant-exponents [0.0506247002196812135],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[2 3 4] [1] [1] [1]],
  :input-exponents
  [0.1028395340831637837 0.1051012103511783424 0.1026041935226524322],
  :xe 0.05,
  :nature-exponents [0.15830300871834108],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.14225223945364215],
  :industry 1,
  :pollutant-exponents [0.0509783213289809423],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 2,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1] [1] [1]],
  :input-exponents
  [0.1024259343354575216
   0.100025546022025140224
   0.1045582750907660396
   0.1010072492709029934],
  :xe 0.05,
  :nature-exponents [0.17114360349958974],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.1059561698032832004],
  :industry 1,
  :pollutant-exponents [0.0518300585669427558],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 3,
  :labor-quantities [0],
  :production-inputs [[1 4] [1] [1] [1]],
  :input-exponents [0.10317881202768294 0.107368782212382892],
  :xe 0.05,
  :nature-exponents [0.107365004303918443],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.103898926413162349],
  :industry 1,
  :pollutant-exponents [0.0504134408951774589],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 4,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1] [1] [1]],
  :input-exponents
  [0.1002613803363984535
   0.104687635904715819
   0.10235250980842982
   0.1036686195145560874],
  :xe 0.05,
  :nature-exponents [0.15772917366242398],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.1018877216754667983],
  :industry 0,
  :pollutant-exponents [0.0512275388492131843],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 1,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1] [1] [1]],
  :input-exponents
  [0.103795106825185634
   0.10013785408615739959
   0.103344902652351769
   0.101804463230505914],
  :xe 0.05,
  :nature-exponents [0.12483588242156488],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.1884777170868126],
  :industry 0,
  :pollutant-exponents [0.0514109227307426592],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 2,
  :labor-quantities [0],
  :production-inputs [[1 2 3 4] [1] [1] [1]],
  :input-exponents
  [0.1019815239562791766
   0.1043130101445939574
   0.1009240755973502357
   0.10007318195010368567],
  :xe 0.05,
  :nature-exponents [0.12477735759171629],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.106505369404588182],
  :industry 0,
  :pollutant-exponents [0.05007042814060128766],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 3,
  :labor-quantities [0],
  :production-inputs [[3] [1] [1] [1]],
  :input-exponents [0.1376063572667742],
  :xe 0.05,
  :nature-exponents [0.106435987209037308],
  :a 0.25}
 {:effort 0.5,
  :cq 0.25,
  :ce 1,
  :labor-exponents [0.108714647882116121],
  :industry 0,
  :pollutant-exponents [0.0503565055052977049],
  :output 0,
  :s 1,
  :du 7,
  :c 0.05,
  :product 4,
  :labor-quantities [0],
  :production-inputs [[1 3 4] [1] [1] [1]],
  :input-exponents
  [0.10227497566461492 0.1023973286200220035 0.1020152030581462317],
  :xe 0.05, 
  :nature-exponents [0.1041930420272733485], 
  :a 0.25}])

  
