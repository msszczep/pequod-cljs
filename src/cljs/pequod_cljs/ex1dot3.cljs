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
     :pollution-supply-coefficients [0.13630132633780878],
    :base-income 5000}
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
    :pollution-supply-coefficients [0.10742689131548218],
    :base-income 5000}
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
    :pollution-supply-coefficients [0.12716094718422474],
    :base-income 5000}
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
    :pollution-supply-coefficients [0.1274174117492849],
    :base-income 5000}
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
    :pollution-supply-coefficients [0.12964976661909236],
    :base-income 5000}
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
  :pollution-supply-coefficients [0.14460379316641123],
  :base-income 5000}
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
  :pollution-supply-coefficients [0.16424883810893548],
  :base-income 5000}
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
  :pollution-supply-coefficients [0.12186419646421268],
  :base-income 5000}
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
  :pollution-supply-coefficients [0.14072199063819635], 
  :base-income 5000}])

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
  :a 0.25}
{:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.12053781079086186], :industry 1, :pollutant-exponents [0.042936009654518104], :output 0, :s 1, :du 7, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[1] [1] [1] [1]], :input-exponents [0.19610490933128724], :xe 0.05, :nature-exponents [0.044239481961317106], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.15057549540898396], :industry 1, :pollutant-exponents [0.05177142453811804], :output 0, :s 1, :du 7, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[1 2 4] [1] [1] [1]], :input-exponents [0.004909623097844847 0.02130905272179328 0.03765927333775697], :xe 0.05, :nature-exponents [0.09806647884332886], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.2557054541843074], :industry 1, :pollutant-exponents [0.07797391103080051], :output 0, :s 1, :du 7, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1] [1]], :input-exponents [0.01012626335746985 0.03344672918513931 0.0038767390725469256 0.03589146796516562], :xe 0.05, :nature-exponents [0.01327143605006882], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.06662681317758377], :industry 1, :pollutant-exponents [0.0765870175143406], :output 0, :s 1, :du 7, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1] [1]], :input-exponents [0.018421774055268608 0.0062944814872779534 0.017816374768479217 0.014882757794295765], :xe 0.05, :nature-exponents [0.15128373774778353], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.11612251527354989], :industry 1, :pollutant-exponents [0.006880845713398531], :output 0, :s 1, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[2 3 4] [1] [1] [1]], :input-exponents [0.06187479659373499 0.025572081025105886 0.016747000736306143], :xe 0.05, :nature-exponents [0.15176044813502693], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.14435187231104069], :industry 1, :pollutant-exponents [0.06180448375838027], :output 0, :s 1, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1] [1]], :input-exponents [0.011809041004061034 0.011344209392443495 0.03406433766313861 0.007864193399602676], :xe 0.05, :nature-exponents [0.19332286131594847], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.09323509231781144], :industry 1, :pollutant-exponents [0.06767734569498562], :output 0, :s 1, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1] [1]], :input-exponents [0.010343696362549405 2.3195085886152378E-4 0.02599845610366763 0.03342014992959499], :xe 0.05, :nature-exponents [0.039412650059119564], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.1603915639621022], :industry 1, :pollutant-exponents [0.04452114107211307], :output 0, :s 1, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[3] [1] [1] [1]], :input-exponents [0.16289671887351098], :xe 0.05, :nature-exponents [0.0727446986497023], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [9.622337072814347E-5], :industry 1, :pollutant-exponents [0.09298557964473105], :output 0, :s 1, :du 7, :c 0.05, :product 3, :labor-quantities [0], :production-inputs [[1] [1] [1] [1]], :input-exponents [0.08131763177320402], :xe 0.05, :nature-exponents [0.19045687904448028], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.24629029745889733], :industry 1, :pollutant-exponents [0.029262052956717455], :output 0, :s 1, :du 7, :c 0.05, :product 3, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1] [1]], :input-exponents [0.009125951639007302 0.030392984123787988 0.04404321540869675 0.01115395628484196], :xe 0.05, :nature-exponents [0.0840621122860527], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.24614778026599804], :industry 1, :pollutant-exponents [0.07537491812212521], :output 0, :s 1, :du 7, :c 0.05, :product 3, :labor-quantities [0], :production-inputs [[1 2 3] [1] [1] [1]], :input-exponents [0.06581287108153024 0.05052005021887024 0.04710052544133553], :xe 0.05, :nature-exponents [0.04302089219015229], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.2504118017442466], :industry 1, :pollutant-exponents [0.04595952567430165], :output 0, :s 1, :du 7, :c 0.05, :product 3, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1] [1]], :input-exponents [0.01651696666491284 0.02246235181654845 0.03539884306090861 0.04958286929820746], :xe 0.05, :nature-exponents [0.08607313010729943], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.23529369783879683], :industry 1, :pollutant-exponents [0.027310655952544907], :output 0, :s 1, :du 7, :c 0.05, :product 4, :labor-quantities [0], :production-inputs [[2 3 4] [1] [1] [1]], :input-exponents [0.061648873195093754 0.06334493634974885 3.6898975141995324E-4], :xe 0.05, :nature-exponents [0.023099908481826747], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.007136949752553058], :industry 1, :pollutant-exponents [0.06182325446707644], :output 0, :s 1, :du 7, :c 0.05, :product 4, :labor-quantities [0], :production-inputs [[1 2 3] [1] [1] [1]], :input-exponents [0.055825824465521416 0.06109372050146915 0.010589712967936848], :xe 0.05, :nature-exponents [0.08198629095478585], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.15987005549804373], :industry 1, :pollutant-exponents [0.006113702320472348], :output 0, :s 1, :du 7, :c 0.05, :product 4, :labor-quantities [0], :production-inputs [[2 4] [1] [1] [1]], :input-exponents [0.06136331479179104 0.08816203103075193], :xe 0.05, :nature-exponents [0.13042606483813343], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.2965810895704391], :industry 1, :pollutant-exponents [0.08816809232808523], :output 0, :s 1, :du 7, :c 0.05, :product 4, :labor-quantities [0], :production-inputs [[1 2 3] [1] [1] [1]], :input-exponents [0.006343176916683572 0.0023543737425441467 0.06598949717109193], :xe 0.05, :nature-exponents [0.15837660855912108], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.025824748245467072], :industry 0, :pollutant-exponents [0.04264747770562394], :output 0, :s 1, :du 7, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[1 3] [1] [1] [1]], :input-exponents [0.0840117245693811 0.05953723543606526], :xe 0.05, :nature-exponents [0.00878474170293957], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.24581199545368027], :industry 0, :pollutant-exponents [0.005594511415970793], :output 0, :s 1, :du 7, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[3] [1] [1] [1]], :input-exponents [0.1480803057925717], :xe 0.05, :nature-exponents [0.05453753303111402], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.1238806727389561], :industry 0, :pollutant-exponents [0.018975932905149175], :output 0, :s 1, :du 7, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1] [1]], :input-exponents [0.04584410523779061 0.04236422864077258 0.042476795383984765 0.025554695215485874], :xe 0.05, :nature-exponents [0.14370832181466264], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.2550661880200832], :industry 0, :pollutant-exponents [0.08617382156885935], :output 0, :s 1, :du 7, :c 0.05, :product 1, :labor-quantities [0], :production-inputs [[1 2 3 4] [1] [1] [1]], :input-exponents [0.006030299837926728 0.007774716108749136 0.026247286944093978 0.04970852429746684], :xe 0.05, :nature-exponents [0.04640768688828101], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.29752817239972984], :industry 0, :pollutant-exponents [0.0770408296614537], :output 0, :s 1, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[1 3] [1] [1] [1]], :input-exponents [0.08021195968926373 0.013597904524780102], :xe 0.05, :nature-exponents [0.14664874611439666], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.09622286622267481], :industry 0, :pollutant-exponents [0.03519760381830119], :output 0, :s 1, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[1 4] [1] [1] [1]], :input-exponents [0.08893662752814407 0.06051515283058883], :xe 0.05, :nature-exponents [0.18944607940638866], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.18342119361081247], :industry 0, :pollutant-exponents [0.06260654312520456], :output 0, :s 1, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[2 3 4] [1] [1] [1]], :input-exponents [0.049604593048326706 0.05552242590943757 0.02484228883759719], :xe 0.05, :nature-exponents [0.025008216420369503], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.0076329042168782795], :industry 0, :pollutant-exponents [0.07172578269426587], :output 0, :s 1, :du 7, :c 0.05, :product 2, :labor-quantities [0], :production-inputs [[2] [1] [1] [1]], :input-exponents [0.08648204628100715], :xe 0.05, :nature-exponents [0.11863259292874027], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.10718493964646436], :industry 0, :pollutant-exponents [0.015340311984586241], :output 0, :s 1, :du 7, :c 0.05, :product 3, :labor-quantities [0], :production-inputs [[1] [1] [1] [1]], :input-exponents [0.003179990946765754], :xe 0.05, :nature-exponents [0.16712848128406899], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.04020796689414853], :industry 0, :pollutant-exponents [0.020166730484512885], :output 0, :s 1, :du 7, :c 0.05, :product 3, :labor-quantities [0], :production-inputs [[1 3 4] [1] [1] [1]], :input-exponents [0.01669069940510663 0.02367583513907421 0.002275469944621496], :xe 0.05, :nature-exponents [0.14709660585077353], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.20640510954203767], :industry 0, :pollutant-exponents [0.07265748773367642], :output 0, :s 1, :du 7, :c 0.05, :product 3, :labor-quantities [0], :production-inputs [[3 4] [1] [1] [1]], :input-exponents [0.04987371751016245 0.014658472769738862], :xe 0.05, :nature-exponents [0.08579345099240326], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.10603581163455272], :industry 0, :pollutant-exponents [0.012715206013063286], :output 0, :s 1, :du 7, :c 0.05, :product 3, :labor-quantities [0], :production-inputs [[3] [1] [1] [1]], :input-exponents [0.12847014297462994], :xe 0.05, :nature-exponents [0.15491059930404996], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.21829387984187468], :industry 0, :pollutant-exponents [9.311979086361833E-4], :output 0, :s 1, :du 7, :c 0.05, :product 4, :labor-quantities [0], :production-inputs [[1 3 4] [1] [1] [1]], :input-exponents [0.046030938518044934 0.044195040446976974 0.05063008152328206], :xe 0.05, :nature-exponents [0.017497987562582498], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.01526021805501047], :industry 0, :pollutant-exponents [0.007836593127604208], :output 0, :s 1, :du 7, :c 0.05, :product 4, :labor-quantities [0], :production-inputs [[3] [1] [1] [1]], :input-exponents [0.025260777378502408], :xe 0.05, :nature-exponents [0.10231140638932464], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.01126135805902746], :industry 0, :pollutant-exponents [0.08123530316509373], :output 0, :s 1, :du 7, :c 0.05, :product 4, :labor-quantities [0], :production-inputs [[1 2 3] [1] [1] [1]], :input-exponents [0.048023914686200396 0.05087897874300286 0.025531989225209488], :xe 0.05, :nature-exponents [0.07573622345962872], :a 0.25} {:effort 0.5, :cq 0.25, :ce 1, :labor-exponents [0.18490108003493327], :industry 0, :pollutant-exponents [0.030539894217578814], :output 0, :s 1, :du 7, :c 0.05, :product 4, :labor-quantities [0], :production-inputs [[1 3] [1] [1] [1]], :input-exponents [0.09480390348210584 0.06501914229778168], :xe 0.05, :nature-exponents [0.18130929447028665], :a 0.25}
])

  
